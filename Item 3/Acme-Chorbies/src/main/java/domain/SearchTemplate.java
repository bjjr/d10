
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "relationship")
})
public class SearchTemplate extends DomainEntity {

	// Attributes 

	private Integer				age;
	private String				gender;
	private CoordinatesTemplate	coordinatesTemplate;
	private String				keyword;
	private String				relationship;


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

	public CoordinatesTemplate getCoordinatesTemplate() {
		return this.coordinatesTemplate;
	}

	public void setCoordinatesTemplate(final CoordinatesTemplate coordinatesTemplate) {
		this.coordinatesTemplate = coordinatesTemplate;
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

	@Override
	public String toString() {
		return "SearchTemplate [age=" + this.age + ", gender=" + this.gender + ", coordinatesTemplate=" + this.coordinatesTemplate + ", keyword=" + this.keyword + ", relationship=" + this.relationship + "]";
	}

}
