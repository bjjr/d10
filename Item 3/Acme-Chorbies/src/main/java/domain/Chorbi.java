
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class Chorbi extends Actor {

	//Attributes

	private String		picture;
	private String		description;
	private Date		birthdate;
	private boolean		gender;
	private Coordinates	coordinates;
	private boolean		banned;


	@NotBlank
	@NotNull
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotBlank
	@NotNull
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Past
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(final Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isGender() {
		return this.gender;
	}

	public void setGender(final boolean gender) {
		this.gender = gender;
	}

	@NotNull
	@Valid
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(final Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(final boolean banned) {
		this.banned = banned;
	}


	//Relationships

	private Relationship	relationship;
	private CreditCard		creditCard;
	private SearchTemplate	searchTemplate;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Relationship getRelationship() {
		return this.relationship;
	}

	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}

	@Valid
	@OneToOne(optional = true)
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public SearchTemplate getSearchTemplate() {
		return this.searchTemplate;
	}

	public void setSearchTemplate(final SearchTemplate searchTemplate) {
		this.searchTemplate = searchTemplate;
	}

}
