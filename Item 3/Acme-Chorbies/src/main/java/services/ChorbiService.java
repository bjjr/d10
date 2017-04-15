
package services;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ChorbiRepository;
import security.LoginService;
import security.UserAccount;
import domain.Chorbi;
import domain.SearchTemplate;
import forms.ChorbiForm;

@Service
@Transactional
public class ChorbiService {

	// Managed repository ---------------------------

	@Autowired
	private ChorbiRepository		chorbiRepository;

	// Supporting services --------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private SearchTemplateService	searchTemplateService;

	// Validator ------------------------------------

	@Autowired
	private Validator				validator;


	// Constructor ----------------------------------

	public ChorbiService() {
		super();
	}

	// Simple CRUD methods --------------------------

	public Chorbi create() {
		Assert.isTrue(!(this.actorService.checkAuthority("CHORBI") || this.actorService.checkAuthority("ADMIN")), "Only a non registered user can use the create method");

		Chorbi res;

		res = new Chorbi();

		res.setSearchTemplate(this.searchTemplateService.create());

		return res;
	}

	public Chorbi save(final Chorbi chorbi) {
		Chorbi res, authChorbi;
		SearchTemplate savedSt;
		String initialPasswd, encodedPasswd;

		// If Chorbi already exists we don't need to re-hash the password and add the search template
		if (chorbi.getId() != 0) {
			authChorbi = this.findByPrincipal();
			Assert.notNull(authChorbi);
		} else {
			initialPasswd = chorbi.getUserAccount().getPassword();
			encodedPasswd = this.hashCodePassword(initialPasswd);

			chorbi.getUserAccount().setPassword(encodedPasswd);

			savedSt = this.searchTemplateService.save(chorbi.getSearchTemplate());
			chorbi.setSearchTemplate(savedSt);
		}

		res = this.chorbiRepository.save(chorbi);

		return res;
	}

	public Chorbi findOne(final int chorbiId) {
		Assert.isTrue(chorbiId != 0);

		Chorbi res;

		res = this.chorbiRepository.findOne(chorbiId);

		return res;
	}

	public Chorbi findOneToEdit(final int chorbiId) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));
		Assert.isTrue(chorbiId != 0);
		Assert.isTrue(this.actorService.findByPrincipal().getId() == chorbiId);

		Chorbi res;

		res = this.chorbiRepository.findOne(chorbiId);

		return res;
	}

	public Collection<Chorbi> findAll() {
		Collection<Chorbi> res;

		res = this.chorbiRepository.findAll();

		return res;
	}

	public void flush() {
		this.chorbiRepository.flush();
	}

	// Other business methods -----------------------

	public Chorbi findByPrincipal() {
		Chorbi res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);

		return res;
	}

	private Chorbi findByUserAccount(final UserAccount userAccount) {
		Chorbi res;

		res = this.chorbiRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(res);

		return res;
	}

	private String hashCodePassword(final String password) {
		String res;
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		res = encoder.encodePassword(password, null);

		return res;
	}

	/*
	 * Reconstruct for pruned object. Used in profile edition.
	 * A chorbi can only edit his/her email, phone, picture, description, and/or relationship.
	 */

	public Chorbi reconstruct(final Chorbi chorbi, final BindingResult binding) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));
		final Chorbi res;
		Chorbi principal;

		res = chorbi;
		principal = this.findByPrincipal();

		res.setCreditCard(principal.getCreditCard());
		res.setSearchTemplate(principal.getSearchTemplate());
		res.setUserAccount(principal.getUserAccount());

		this.validator.validate(chorbi, binding);

		return res;
	}

	/*
	 * Reconstruct for object form. Used in chorbi register.
	 * The new chorbi is given a non configured search template.
	 */

	public Chorbi reconstruct(final ChorbiForm chorbiForm, final BindingResult binding) {
		Assert.isTrue(!(this.actorService.checkAuthority("CHORBI") || this.actorService.checkAuthority("ADMIN")));

		Chorbi res;

		res = chorbiForm.getChorbi();

		this.checkAge(res.getBirthdate(), binding);
		this.checkPasswords(chorbiForm.getPassword(), chorbiForm.getPasswdConfirmation(), binding);

		this.validator.validate(res, binding);

		return res;
	}

	private void checkAge(final Date birthdate, final BindingResult binding) {
		DateTime now, birth;
		Integer years;

		birth = new DateTime(birthdate.getTime());
		now = new DateTime();

		years = Years.yearsBetween(birth, now).getYears();

		if (years < 18)
			binding.rejectValue("birthdate", "chorbi.birthdate.invalid");
	}

	private void checkPasswords(final String passwd1, final String passwd2, final BindingResult binding) {
		if (!passwd1.equals(passwd2))
			binding.rejectValue("password", "chorbi.password.invalid");
	}

	/**
	 * Given a text this method masks sensible data in order to not display it to other users.
	 * 
	 * @param text
	 *            The text to analyse
	 * @return The same text with sensible data masked with asterisks
	 */

	public String maskSensibleData(final String text) {
		final String phoneRegex = "(\\+\\d{1,4})?[\\(\\)\\-\\d\\sA-Z]+\\s";
		final String emailRegex = "([\\w\\.]+)@([\\w\\.]+)\\.(\\w+)";

		String res;

		res = text.replaceAll(phoneRegex, "*** ");
		res = res.replaceAll(emailRegex, "***");

		return res;
	}
}
