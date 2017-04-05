
package services;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Payload;
import javax.validation.constraints.Pattern.Flag;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.internal.constraintvalidators.URLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ChirpRepository;
import domain.Chirp;
import domain.Chorbi;

@Service
@Transactional
public class ChirpService {

	// Managed repository -----------------------------------

	@Autowired
	private ChirpRepository	chirpRepository;

	// Supporting services ----------------------------------

	@Autowired
	private ActorService	actorService;

	@Autowired
	private ChorbiService	chorbiService;

	// Validator --------------------------------------------

	@Autowired
	private Validator		validator;


	// Constructors -----------------------------------------

	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------

	public Chirp create(final int recipientId) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Chirp result;
		final Date moment;
		final Chorbi sender;
		final Chorbi recipient;
		Collection<String> attachments;

		result = new Chirp();
		attachments = new ArrayList<String>();
		sender = this.chorbiService.findByPrincipal();
		recipient = this.chorbiService.findOne(recipientId);
		Assert.isTrue(!sender.equals(recipient), "Cannot send a chirp to you");
		moment = new Date(System.currentTimeMillis() - 1000);
		result.setAttachments(attachments);
		result.setCopy(false);
		result.setMoment(moment);
		result.setRecipient(recipient);
		result.setSender(sender);
		result.setSubject("");
		result.setText("");

		return result;
	}

	public Chirp findOne(final int chirpId) {
		Chirp result;

		result = this.chirpRepository.findOne(chirpId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> result;

		result = this.chirpRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// This method finds, using a query, the chirps sent by a chorbi

	public Collection<Chirp> findChirpsSent() {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Collection<Chirp> chirpsSent;
		final Chorbi chorbi;

		chorbi = this.chorbiService.findByPrincipal();
		chirpsSent = this.chirpRepository.findChirpsSent(chorbi.getId());

		return chirpsSent;
	}

	// This method finds, using a query, the chirps received by a chorbi

	public Collection<Chirp> findChirpsReceived() {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Collection<Chirp> chirpsReceived;
		final Chorbi chorbi;

		chorbi = this.chorbiService.findByPrincipal();
		chirpsReceived = this.chirpRepository.findChirpsReceived(chorbi.getId());

		return chirpsReceived;
	}

	public Chirp send(final Chirp chirp) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Assert.notNull(chirp);

		Chirp result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1000);
		chirp.setMoment(moment);
		chirp.setCopy(false);

		result = this.chirpRepository.save(chirp);

		return result;
	}

	// This method save a copy of a chirp for the recipient of this chirp

	public Chirp saveCopy(final Chirp originalChirp) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Chirp aux;
		Chirp result;

		aux = new Chirp();

		aux.setCopy(true);
		aux.setAttachments(new ArrayList<>(originalChirp.getAttachments()));
		aux.setMoment(originalChirp.getMoment());
		aux.setRecipient(originalChirp.getRecipient());
		aux.setSender(originalChirp.getSender());
		aux.setSubject(originalChirp.getSubject());
		aux.setText(originalChirp.getText());
		result = this.chirpRepository.save(aux);

		return result;
	}

	public void delete(final Chirp chirp) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Assert.notNull(chirp);
		Assert.isTrue(chirp.getId() != 0);

		Assert.isTrue(this.chirpRepository.exists(chirp.getId()));

		Chorbi principal;

		principal = this.chorbiService.findByPrincipal();

		Assert.isTrue((chirp.getSender().equals(principal) && chirp.getCopy() == false) || (chirp.getRecipient().equals(principal) && chirp.getCopy() == true));

		this.chirpRepository.delete(chirp);
	}

	public void flush() {
		this.chirpRepository.flush();
	}

	// Other business methods -------------------------------

	public Chirp reply(final int chirpId) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Chirp originalChirp;
		Chirp newChirp;
		final Chirp result;

		originalChirp = this.findOne(chirpId);
		Assert.isTrue(originalChirp.getCopy(), "Cannot reply a chirp send by you");
		Assert.isTrue(this.chorbiService.findByPrincipal().equals(originalChirp.getRecipient()), "Cannot reply a chirp of other chorbies");

		newChirp = this.create(originalChirp.getSender().getId());
		newChirp.setSubject("RE: " + originalChirp.getSubject());

		result = this.send(newChirp);

		return result;
	}

	public Chirp resend(final int chirpId, final int recipientId) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));

		Chirp originalChirp;
		Chirp newChirp;
		Chirp result;

		originalChirp = this.findOne(chirpId);
		Assert.isTrue(!originalChirp.getCopy(), "Cannot resend a chirp received by you");

		newChirp = this.create(recipientId);
		newChirp.setAttachments(new ArrayList<>(originalChirp.getAttachments()));
		newChirp.setSubject("FW: " + originalChirp.getSubject());
		newChirp.setText(originalChirp.getText());

		result = this.send(newChirp);

		return result;
	}

	// TODO añadir métodos de queries

	public Chirp reconstruct(final Chirp chirp, final BindingResult bindingResult) {
		Assert.isTrue(this.actorService.checkAuthority("CHORBI"));
		Chirp result;
		Chorbi chorbi;

		result = chirp;
		chorbi = this.chorbiService.findByPrincipal();
		result.setSender(chorbi);
		result.setMoment(new Date(System.currentTimeMillis() - 1000));

		this.validateURLs(result.getAttachments(), bindingResult);
		this.validator.validate(result, bindingResult);

		return result;
	}

	private void validateURLs(final Collection<String> attachments, final BindingResult binding) {
		URLValidator validator;

		validator = new URLValidator();

		validator.initialize(new URL() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}

			@Override
			public String regexp() {
				return ".*";
			}

			@Override
			public String protocol() {
				return "";
			}

			@Override
			public int port() {
				return -1;
			}

			@Override
			public Class<? extends Payload>[] payload() {
				return null;
			}

			@Override
			public String message() {
				return "org.hibernate.validator.constraints.URL.message";
			}

			@Override
			public String host() {
				return "";
			}

			@Override
			public Class<?>[] groups() {
				return null;
			}

			@Override
			public Flag[] flags() {
				return null;
			}
		});

		for (final String s : attachments)
			if (!validator.isValid(s, null)) {
				binding.rejectValue("attachments", "org.hibernate.validator.constraints.URL.message");
				break;
			}
	}

}
