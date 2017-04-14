
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class CoordinatesTemplate {

	//Attributes

	private String	country;
	private String	state;
	private String	province;
	private String	city;


	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(final String province) {
		this.province = province;
	}

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
