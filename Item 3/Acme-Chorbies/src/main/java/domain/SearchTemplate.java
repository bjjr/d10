
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class SearchTemplate extends DomainEntity {

	// Attributes 

	private Integer		age;
	private String		gender;
	private Coordinates	coordinates;
	private String		keyword;
	private String		relationship;


	public Integer getAge() {
		return this.age;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	@Valid
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(final Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(final String relationship) {
		this.relationship = relationship;
	}

}
