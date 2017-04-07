
package forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import security.Authority;
import security.UserAccount;
import domain.Chorbi;
import domain.Coordinates;
import domain.SearchTemplate;

public class ChorbiForm {

	// Chorbi attributes

	private String	name;
	private String	surname;
	private String	email;
	private String	phone;
	private String	picture;
	private String	description;
	private Date	birthdate;
	private String	gender;
	private String	relationship;

	// Coordinates attributes

	private String	country;
	private String	state;
	private String	province;
	private String	city;

	// UserAccount attributes

	private String	username;
	private String	password;

	// Form attributes

	private String	passwdConfirmation;


	// Constructors

	public ChorbiForm() {
		super();
	}

	/**
	 * ChorbiForm constructor using a Chorbi object.
	 * 
	 * @param chorbi
	 *            The chorbi object with data.
	 */

	public ChorbiForm(final Chorbi chorbi) {
		this.name = chorbi.getName();
		this.surname = chorbi.getSurname();
		this.email = chorbi.getEmail();
		this.phone = chorbi.getPhone();
		this.picture = chorbi.getPicture();
		this.description = chorbi.getDescription();
		this.birthdate = chorbi.getBirthdate();
		this.gender = chorbi.getGender();
		this.country = chorbi.getCoordinates().getCountry();
		this.state = chorbi.getCoordinates().getState();
		this.province = chorbi.getCoordinates().getProvince();
		this.city = chorbi.getCoordinates().getCity();
		this.relationship = chorbi.getRelationship();
		this.username = chorbi.getUserAccount().getUsername();
		this.password = "";
		this.passwdConfirmation = "";
	}

	/**
	 * Retrieves the chorbi object from the form.
	 * 
	 * @return A Chorbi object with data pending to be checked.
	 */

	public Chorbi getChorbi() {
		Chorbi res;
		UserAccount ua;
		Authority auth;
		SearchTemplate st;
		final List<Authority> auths;
		Coordinates coordinates;

		res = new Chorbi();

		coordinates = new Coordinates();

		auth = new Authority();
		auths = new ArrayList<>();

		auth.setAuthority(Authority.CHORBI);
		auths.add(auth);

		ua = new UserAccount();
		ua.setAuthorities(auths);

		st = new SearchTemplate();

		res.setName(this.name);
		res.setSurname(this.surname);
		res.setEmail(this.email);
		res.setPhone(this.phone);
		res.setPicture(this.picture);
		res.setDescription(this.description);
		res.setBirthdate(this.birthdate);
		res.setGender(this.gender);
		res.setBanned(false);
		res.setRelationship(this.relationship);

		coordinates.setCountry(this.country);
		coordinates.setState(this.state);
		coordinates.setProvince(this.province);
		coordinates.setCity(this.city);

		res.setCoordinates(coordinates);

		ua.setUsername(this.username);
		ua.setPassword(this.password);

		res.setUserAccount(ua);
		res.setSearchTemplate(st);

		return res;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(final Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(final String relationship) {
		this.relationship = relationship;
	}

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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getPasswdConfirmation() {
		return this.passwdConfirmation;
	}

	public void setPasswdConfirmation(final String passwdConfirmation) {
		this.passwdConfirmation = passwdConfirmation;
	}

}
