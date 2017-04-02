
package services;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.Chorbi;
import domain.CreditCard;

public class CreditCardService {

	@Autowired
	private CreditCardRepository	creditCardRepository;

	@Autowired
	private ChorbiService			chorbiService;


	public CreditCard create() {
		final CreditCard res = new CreditCard();

		final Date today = new DateTime().withTimeAtStartOfDay().toDate();

		res.setHolder("");
		res.setBrand("");
		res.setNumber("");
		res.setCvv(0);
		res.setExpirationDate(DateUtils.addDays(today, 1));

		return res;
	}

	public CreditCard save(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		final Chorbi chorbi;
		final CreditCard res;

		chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		res = this.creditCardRepository.save(creditCard);

		if (creditCard.getId() == 0) {
			chorbi.setCreditCard(creditCard);
			this.chorbiService.save(chorbi);
		}

		return res;
	}
	public void flush() {
		this.creditCardRepository.flush();
	}

	public CreditCard findOne(final int creditCardId) {
		final CreditCard res = this.creditCardRepository.findOne(creditCardId);
		Assert.notNull(res);
		return res;
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> res;
		res = this.creditCardRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	// Other business methods -------------------------------

	/**
	 * Given a credit card this method checks if its brand
	 * is VISA, MASTERCARD, DISCOVER, DINNERS, or AMEX
	 * 
	 * @param creditCard
	 *            The credit card to be checked.
	 * @return The result of the check.
	 */
	public boolean isCreditCardBrandValid(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		final String[] brands = new String[] {
			"VISA", "MASTERCARD", "DISCOVER", "DINNERS", "AMEX"
		};

		for (final String s : brands)
			if (creditCard.getBrand().toUpperCase().equals(s))
				return true;

		return false;
	}

	/**
	 * Given a credit card this method checks if its cvv
	 * pass pass the Luhn’s algorithm
	 * 
	 * @param creditCard
	 *            The credit card to be checked.
	 * @return The result of the check.
	 */
	public boolean isCreditCardLuhnValid(final String ccNumber) {
		int sum = 0;
		boolean alternate = false;
		for (int i = ccNumber.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(ccNumber.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9)
					n = (n % 10) + 1;
			}
			sum += n;
			alternate = !alternate;
		}
		return (sum % 10 == 0);
	}

	/**
	 * Given a credit card this method checks if its expiration date
	 * is at least one day in the future.
	 * 
	 * @param creditCard
	 *            The credit card to be checked.
	 * @return The result of the check.
	 */
	public boolean isCreditCardDateValid(final CreditCard creditCard) {
		long diff;
		Date today;
		Date expiryDate;

		Assert.notNull(creditCard);

		today = new DateTime().withTimeAtStartOfDay().toDate();
		expiryDate = new DateTime(creditCard.getExpirationDate()).withTimeAtStartOfDay().toDate();

		diff = expiryDate.getTime() - today.getTime();

		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= 1;
	}

}
