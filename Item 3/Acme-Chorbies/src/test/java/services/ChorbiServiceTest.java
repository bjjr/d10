
package services;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import utilities.AbstractTest;
import domain.Chorbi;
import forms.ChorbiForm;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChorbiServiceTest extends AbstractTest {

	// System under test ----------------------------

	@Autowired
	private ChorbiService	chorbiService;


	// Services -------------------------------------

	// Tests ----------------------------------------

	/*
	 * Functional Requirement: Register to the system as a chorbi.
	 */

	@Test
	public void registerDriver() {
		final Object testingData[][] = {
			{// User inputs valid data
				null, "20/01/1985", "passwd1", "passwd1", null
			}, {// New user is under legal age
				null, "20/01/2003", "passwd1", "passwd1", IllegalArgumentException.class
			}, {// User did not write the same password in confirmation field
				null, "20/01/1985", "passwd1", "passwd2", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.registerTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
	}

	/*
	 * Functional Requirement: An actor who is authenticated as a chorbi must be able to change his or her profile
	 */

	// TODO: Fix this tests

	//	@Test
	//	public void profileEditionDriver() {
	//		final Object testingData[][] = {
	//			{// A chorbi changes his/her profile correctly
	//				"chorbi1", "This is my new description", "newemail@test.com", null
	//			}, {// A chorbi makes a mistake in his/her description
	//				"chorbi2", "", "newemail@test.com", IllegalArgumentException.class
	//			}, {// A chorbi makes a mistake in his/her email
	//				"chorbi3", "This is my new description", "email.", IllegalArgumentException.class
	//			}
	//		};
	//
	//		for (int i = 0; i < testingData.length; i++)
	//			this.profileEditionTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	//	}

	@Test
	public void maskSensibleDataTest() {
		String text, maskedText, expected;

		text = "You can contact me at +34 111 222 333 or someone@somewhere.com";
		maskedText = this.chorbiService.maskSensibleData(text);
		expected = "You can contact me at *** or ***";

		Assert.isTrue(maskedText.equals(expected));
	}

	// Templates ------------------------------------

	protected void registerTemplate(final String username, final String birthdate, final String passwd1, final String passwd2, final Class<?> expected) {
		Class<?> caught;
		DateTimeFormatter dtf;
		DateTime dt;

		dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
		dt = dtf.parseDateTime(birthdate);

		caught = null;

		try {
			this.authenticate(username);

			ChorbiForm chorbiForm;
			Chorbi reconstructed, saved;
			DataBinder dataBinder;
			BindingResult binding;

			chorbiForm = new ChorbiForm();
			dataBinder = new DataBinder(chorbiForm, "chorbiForm");
			binding = dataBinder.getBindingResult();

			chorbiForm.setName("test");
			chorbiForm.setSurname("testSurname");
			chorbiForm.setEmail("test@test.com");
			chorbiForm.setPhone("+65 215000333");
			chorbiForm.setPicture("https://testpic.com");
			chorbiForm.setDescription("testDescription");
			chorbiForm.setBirthdate(dt.toDate());
			chorbiForm.setGender("FEMALE");
			chorbiForm.setCountry("test");
			chorbiForm.setCity("test");
			chorbiForm.setRelationship("LOVE");
			chorbiForm.setUsername("testChorbi");
			chorbiForm.setPassword(passwd1);
			chorbiForm.setPasswdConfirmation(passwd2);

			reconstructed = this.chorbiService.reconstruct(chorbiForm, binding);
			Assert.isTrue(!binding.hasErrors());

			saved = this.chorbiService.save(reconstructed);
			Assert.isTrue(saved.getId() != 0);

			this.unauthenticate();
		} catch (final Throwable th) {
			caught = th.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void profileEditionTemplate(final String username, final String description, final String email, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			this.authenticate(username);

			Chorbi principal, copy, reconstructed;
			DataBinder dataBinder;
			BindingResult binding;

			principal = this.chorbiService.findByPrincipal();
			copy = principal;
			dataBinder = new DataBinder(copy, "chorbi");
			binding = dataBinder.getBindingResult();

			copy.setDescription(description);
			copy.setEmail(email);

			// Simulating a pruned object

			copy.setCreditCard(null);
			copy.setSearchTemplate(null);

			reconstructed = this.chorbiService.reconstruct(copy, binding);
			Assert.isTrue(!binding.hasErrors());

			this.chorbiService.save(reconstructed);

			this.unauthenticate();
		} catch (final Throwable th) {
			caught = th.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
