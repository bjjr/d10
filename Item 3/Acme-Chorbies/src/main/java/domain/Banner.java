
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

	// Attributes 

	private String	path;


	@NotBlank
	@NotNull
	public String getPath() {
		return this.path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

}
