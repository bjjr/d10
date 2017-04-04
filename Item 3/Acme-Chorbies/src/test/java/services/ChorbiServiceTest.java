
package services;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

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
			{
				null, "20/01/1985", "passwd1", "passwd1", null
			// User inputs valid data
			}, {
				null, "20/01/2003", "passwd1", "passwd1", IllegalArgumentException.class
			// New user is under legal age
			}, {
				null, "20/01/1985", "passwd1", "passwd2", IllegalArgumentException.class
			// User did not write the same password in confirmation field
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.registerTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);
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
			BindingResult binding;

			chorbiForm = new ChorbiForm();
			binding = new BindingResult() {

				@Override
				public void setNestedPath(final String nestedPath) {
				}

				@Override
				public void rejectValue(final String field, final String errorCode, final Object[] errorArgs, final String defaultMessage) {
				}

				@Override
				public void rejectValue(final String field, final String errorCode, final String defaultMessage) {
				}

				@Override
				public void rejectValue(final String field, final String errorCode) {
				}

				@Override
				public void reject(final String errorCode, final Object[] errorArgs, final String defaultMessage) {
				}

				@Override
				public void reject(final String errorCode, final String defaultMessage) {
				}

				@Override
				public void reject(final String errorCode) {
				}

				@Override
				public void pushNestedPath(final String subPath) {
				}

				@Override
				public void popNestedPath() throws IllegalStateException {
				}

				@Override
				public boolean hasGlobalErrors() {
					return false;
				}

				@Override
				public boolean hasFieldErrors(final String field) {
					return false;
				}

				@Override
				public boolean hasFieldErrors() {
					return false;
				}

				@Override
				public boolean hasErrors() {
					return false;
				}

				@Override
				public String getObjectName() {
					return null;
				}

				@Override
				public String getNestedPath() {
					return null;
				}

				@Override
				public List<ObjectError> getGlobalErrors() {
					return null;
				}

				@Override
				public int getGlobalErrorCount() {
					return 0;
				}

				@Override
				public ObjectError getGlobalError() {
					return null;
				}

				@Override
				public Object getFieldValue(final String field) {
					return null;
				}

				@Override
				public Class<?> getFieldType(final String field) {
					return null;
				}

				@Override
				public List<FieldError> getFieldErrors(final String field) {
					return null;
				}

				@Override
				public List<FieldError> getFieldErrors() {
					return null;
				}

				@Override
				public int getFieldErrorCount(final String field) {
					return 0;
				}

				@Override
				public int getFieldErrorCount() {
					return 0;
				}

				@Override
				public FieldError getFieldError(final String field) {
					return null;
				}

				@Override
				public FieldError getFieldError() {
					return null;
				}

				@Override
				public int getErrorCount() {
					return 0;
				}

				@Override
				public List<ObjectError> getAllErrors() {
					return null;
				}

				@Override
				public void addAllErrors(final Errors errors) {
				}

				@Override
				public String[] resolveMessageCodes(final String errorCode, final String field) {
					return null;
				}

				@Override
				public String[] resolveMessageCodes(final String errorCode) {
					return null;
				}

				@Override
				public void recordSuppressedField(final String field) {
				}

				@Override
				public Object getTarget() {
					return null;
				}

				@Override
				public String[] getSuppressedFields() {
					return null;
				}

				@Override
				public Object getRawFieldValue(final String field) {
					return null;
				}

				@Override
				public PropertyEditorRegistry getPropertyEditorRegistry() {
					return null;
				}

				@Override
				public Map<String, Object> getModel() {
					return null;
				}

				@Override
				public PropertyEditor findEditor(final String field, final Class<?> valueType) {
					return null;
				}

				@Override
				public void addError(final ObjectError error) {
				}
			};

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
}
