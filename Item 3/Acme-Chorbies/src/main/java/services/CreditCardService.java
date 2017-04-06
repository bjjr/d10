
package services;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CreditCardRepository;
import domain.Chorbi;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository	creditCardRepository;

	@Autowired
	private ChorbiService			chorbiService;

	@Autowired
	private Validator				validator;


	public CreditCard create() {
		final CreditCard res = new CreditCard();

		final Date today = new DateTime().withTimeAtStartOfDay().toDate();

		res.setHolder("");
		res.setBrand("");
		res.setNumber("");
		res.setCvv(0);
		res.setExpirationDate(DateUtils.addDays(today, 1));

		return res;
	}

	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		final Chorbi chorbi;
		final CreditCard res;

		chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi, "You are not logged as a Chorbi");

		Assert.isTrue(this.isCreditCardBrandValid(creditCard), "Brand name is not valid");
		Assert.isTrue(this.isCreditCardDateValid(creditCard), "Date is not valid. The expiration date must be at least one day after today.");

		res = this.creditCardRepository.save(creditCard);
		if (creditCard.getId() == 0) {
			chorbi.setCreditCard(res);
			this.chorbiService.save(chorbi);
		}

		return res;
	}

	public void flush() {
		this.creditCardRepository.flush();
	}

	public CreditCard findOne(final int creditCardId) {
		final CreditCard res = this.creditCardRepository.findOne(creditCardId);
		Assert.notNull(res);
		return res;
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> res;
		res = this.creditCardRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public CreditCard reconstruct(final CreditCard creditCard, final BindingResult bindingResult) {
		final Chorbi chorbi;
		final CreditCard res;

		chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi, "You are not logged as a Chorbi");

		res = creditCard;

		if (!this.isCreditCardDateValid(res))
			bindingResult.rejectValue("creditCard", "Date is not valid. The expiration date must be at least one day after today.");
		else if (!this.isCreditCardBrandValid(creditCard))
			bindingResult.rejectValue("creditCard", "Brand name is not valid");

		this.validator.validate(res, bindingResult);
		return res;

	}
	// Other business methods -------------------------------

	/**
	 * Given a credit card this method checks if its brand
	 * is VISA, MASTERCARD, DISCOVER, DINNERS, or AMEX
	 * 
	 * @param creditCard
	 *            The credit card to be checked.
	 * @return The result of the check.
	 */
	public boolean isCreditCardBrandValid(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		final String[] brands = new String[] {
			"VISA", "MASTERCARD", "DISCOVER", "DINNERS", "AMEX"
		};

		for (final String s : brands)
			if (creditCard.getBrand().toUpperCase().equals(s))
				return true;

		return false;
	}

	/**
	 * Given a credit card this method checks if its expiration date
	 * is at least one day in the future.
	 * 
	 * @param creditCard
	 *            The credit card to be checked.
	 * @return The result of the check.
	 */
	public boolean isCreditCardDateValid(final CreditCard creditCard) {
		long diff;
		Date today;
		Date expiryDate;

		Assert.notNull(creditCard);

		today = new DateTime().withTimeAtStartOfDay().toDate();
		expiryDate = new DateTime(creditCard.getExpirationDate()).withTimeAtStartOfDay().toDate();

		diff = expiryDate.getTime() - today.getTime();

		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= 1;
	}

	public CreditCard findChorbiCreditCard() {
		Chorbi chorbi;
		CreditCard creditCard;

		chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi, "You are not logged as a Chorbi");

		creditCard = this.creditCardRepository.findCreditCardByChorbi(chorbi.getId());
		Assert.notNull(creditCard);

		return creditCard;
	}

	public CreditCard findCreditCardToEdit(final int creditCardId) {
		Chorbi chorbi;
		CreditCard creditCard;

		chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi, "You are not logged as a Chorbi");

		creditCard = this.findOne(creditCardId);
		Assert.notNull(creditCard, "The credit card does not exist");
		Assert.isTrue(this.findChorbiCreditCard().equals(creditCard), "This is not your credit card");

		return creditCard;
	}
}
