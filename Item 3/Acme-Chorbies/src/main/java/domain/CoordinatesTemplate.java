
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.SafeHtml;

@Embeddable
@Access(AccessType.PROPERTY)
public class CoordinatesTemplate {

	//Attributes

	private String	country;
	private String	state;
	private String	province;
	private String	city;


	@SafeHtml
	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	@SafeHtml
	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	@SafeHtml
	public String getProvince() {
		return this.province;
	}

	public void setProvince(final String province) {
		this.province = province;
	}

	@SafeHtml
	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "CoordinatesTemplate [country=" + this.country + ", state=" + this.state + ", province=" + this.province + ", city=" + this.city + "]";
	}

}
