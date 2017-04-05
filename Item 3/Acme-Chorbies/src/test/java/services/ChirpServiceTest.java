
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Chirp;
import domain.Chorbi;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ChirpServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private ChirpService	chirpService;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;


	// Tests ------------------------------------------------------------------

	/*
	 * Use case: An actor who is authenticated as a chorbi must be able to:
	 * Chirp to another chorbi
	 * Expected errors:
	 * - A non registered user tries to send a chirp --> IllegalArgumentException
	 * - An actor logged as administrator tries to send a chirp --> IllegalArgumentException
	 */

	@Test
	public void sendChirpDriver() {
		final Object testingData[][] = {
			{    //An actor unauthenticated cannot send chirps
				null, 1015, IllegalArgumentException.class
			}, { //An administrator cannot send chirps
				"admin", 1015, IllegalArgumentException.class
			}, { // Successful test
				"chorbi1", 1015, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.sendChirpTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	/*
	 * Use case: An actor who is authenticated as a chorbi must be able to:
	 * Browse the list of chirps that he or she's got
	 */

	@Test
	public void findChirpsReceivedTest() {
		Collection<Chirp> chirpsReceived;
		Boolean isSent;

		this.authenticate("chorbi3");

		chirpsReceived = this.chirpService.findChirpsReceived();
		isSent = false;

		this.unauthenticate();

		for (final Chirp c : chirpsReceived)
			if (!c.getCopy())
				isSent = true;
		Assert.isTrue(!isSent);
	}

	/*
	 * Use case: An actor who is authenticated as a chorbi must be able to:
	 * Browse the list of chirps that he or she's sent
	 */

	@Test
	public void findChirpsSentTest() {
		Collection<Chirp> chirpsSent;
		Boolean isReceived;

		this.authenticate("chorbi3");

		chirpsSent = this.chirpService.findChirpsSent();
		isReceived = false;

		this.unauthenticate();

		for (final Chirp c : chirpsSent)
			if (c.getCopy())
				isReceived = true;
		Assert.isTrue(!isReceived);
	}

	/*
	 * Use case: An actor who is authenticated as a chorbi must be able to:
	 * Reply a chirp that he/she has
	 * Expected errors:
	 * - A chorbi tries to reply a chirp of other chorbi --> IllegalArgumentException
	 * - A chorbi tries to reply a chirp send by him/her --> IllegalArgumentException
	 */

	@Test
	public void replyChirpDriver() {
		final Object testingData[][] = {
			{    //A chorbi cannot reply chirps of other chorbies
				"chorbi1", 1014, IllegalArgumentException.class
			}, { //A chorbi cannot reply a chirp send by him/her
				"chorbi2", 1035, IllegalArgumentException.class
			}, { // Successful test
				"chorbi2", 1038, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.replyChirpTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	/*
	 * Use case: An actor who is authenticated as a chorbi must be able to:
	 * Resend a chirp that he/she has sent
	 * Expected errors:
	 * - A chorbi tries to resend a chirp of other chorbi --> IllegalArgumentException
	 * - A chorbi tries to resend a chirp received by him/her --> IllegalArgumentException
	 */

	@Test
	public void resendChirpDriver() {
		final Object testingData[][] = {
			{    //A chorbi cannot resend a chirp of other chorbi
				"chorbi1", 1038, 1018, IllegalArgumentException.class
			}, { //A chorbi cannot resend a chirp received by him/her
				"chorbi3", 1036, 1018, IllegalArgumentException.class
			}, { // Successful test
				"chorbi3", 1039, 1017, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.resendChirpTemplate((String) testingData[i][0], (int) testingData[i][1], (int) testingData[i][2], (Class<?>) testingData[i][3]);

	}

	/*
	 * Use case: An actor who is authenticated as a chorbi must be able to:
	 * Erase any of the chirps that he or she's got or sent
	 * Expected errors:
	 * - A chorbi tries to delete a chirp of another chorbi --> IllegalArgumentException
	 * - An administrator tries to delete a chirp --> IllegalArgumentException
	 */

	@Test
	public void deleteChirpDriver() {
		final Object testingData[][] = {
			{    //A chorbi cannot delete a chirp of other chorbies
				"chorbi1", 1037, IllegalArgumentException.class
			}, { //An administrator cannot delete a chirp
				"admin", 1037, IllegalArgumentException.class
			}, { // Successful test
				"chorbi3", 1037, null
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.deleteChirpTemplate((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

	}

	// TODO añadir test de métodos de queries

	// Templates --------------------------------------------------------------

	protected void sendChirpTemplate(final String username, final int recipientId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			Chirp created, sent, copy;
			final Chorbi recipient;

			this.authenticate(username);
			recipient = this.chorbiService.findOne(recipientId);
			created = this.chirpService.create(recipientId);
			created.setSubject("Subject test");
			created.setText("Text test");
			sent = this.chirpService.send(created);

			copy = this.chirpService.saveCopy(sent);
			this.chirpService.flush();

			this.unauthenticate();

			Assert.isTrue(!sent.getCopy());
			Assert.isTrue(copy.getCopy());
			Assert.isTrue(sent.getRecipient().equals(recipient));
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void replyChirpTemplate(final String username, final int chirpId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			Chirp chirp, copy, result;

			this.authenticate(username);
			chirp = this.chirpService.findOne(chirpId);
			result = this.chirpService.reply(chirpId);
			result.setText("Test reply");
			copy = this.chirpService.saveCopy(result);
			this.chirpService.flush();
			this.unauthenticate();

			Assert.isTrue(result.getSubject().contains("RE:"));
			Assert.isTrue(result.getSender().equals(chirp.getRecipient()));
			Assert.isTrue(result.getRecipient().equals(chirp.getSender()));
			Assert.isTrue(!result.getCopy());
			Assert.isTrue(copy.getCopy());
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void resendChirpTemplate(final String username, final int chirpId, final int recipientId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			Chirp copy, result;

			this.authenticate(username);
			result = this.chirpService.resend(chirpId, recipientId);
			copy = this.chirpService.saveCopy(result);
			this.chirpService.flush();

			Assert.isTrue(result.getSubject().contains("FW:"));
			Assert.isTrue(result.getSender().equals(this.chorbiService.findByPrincipal()));
			Assert.isTrue(!result.getCopy());
			Assert.isTrue(copy.getCopy());

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void deleteChirpTemplate(final String username, final int chirpId, final Class<?> expected) {
		Class<?> caught;

		caught = null;

		try {
			Chirp chirp;

			chirp = this.chirpService.findOne(chirpId);

			this.authenticate(username);
			this.chirpService.delete(chirp);
			this.chirpService.flush();
			Assert.isTrue(!this.chirpService.findChirpsSent().contains(chirp) && !this.chirpService.findChirpsReceived().contains(chirp));

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
