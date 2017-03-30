
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chirp extends DomainEntity {

	// Attributes 

	private Date				moment;
	private String				subject;
	private String				text;
	private Collection<String>	attachments;


	@Past
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@NotNull
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	@NotNull
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@URL
	@NotNull
	@ElementCollection
	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}


	// Relationships

	private Chorbi	chirper;
	private Chorbi	chirped;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Chorbi getChirper() {
		return this.chirper;
	}

	public void setChirper(final Chorbi chirper) {
		this.chirper = chirper;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Chorbi getChirped() {
		return this.chirped;
	}

	public void setChirped(final Chorbi chirped) {
		this.chirped = chirped;
	}

}
