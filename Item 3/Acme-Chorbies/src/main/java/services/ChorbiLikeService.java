
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChorbiLikeRepository;
import domain.Chorbi;
import domain.ChorbiLike;

@Service
@Transactional
public class ChorbiLikeService {

	//Managed repository
	@Autowired
	private ChorbiLikeRepository	chorbiLikeRepository;


	//Constructors
	public ChorbiLikeService() {
		super();
	}

	// Simple CRUD methods
	public ChorbiLike create(final Chorbi chorbi) {
		Assert.isTrue(actorService.checkAuthority("CHORBI"));
		Assert.notNull(chorbi);
		Assert.isTrue(chorbi.getId() != 0);

		ChorbiLike result;
		Chorbi principal;
		Date moment;

		principal = chorbiService.findByPrincipal;
		moment = new Date(System.currentTimeMillis() - 1000);

		for (final ChorbiLike c : this.findChorbiLikesByLiker(principal.getId()))
			Assert.isTrue(c.getLiked() != chorbi);

		result = new ChorbiLike();
		result.setLiker(principal);
		result.setLiked(chorbi);
		result.setMoment(moment);

		return result;
	}
	public ChorbiLike save(final ChorbiLike chorbiLike) {
		Assert.isTrue(actorService.checkAuthority("CHORBI"));
		Assert.notNull(chorbiLike);

		ChorbiLike result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1000);
		chorbiLike.setMoment(moment);

		result = this.chorbiLikeRepository.save(chorbiLike);

		return result;
	}

	public void flush() {
		this.chorbiLikeRepository.flush();
	}

	public ChorbiLike findOne(final int id) {
		Assert.notNull(id);
		Assert.isTrue(id != 0);

		ChorbiLike result;

		result = this.chorbiLikeRepository.findOne(id);
		Assert.notNull(result);

		return result;
	}

	public void delete(final ChorbiLike chorbiLike) {
		Assert.isTrue(actorService.checkAuthority("CHORBI"));
		Assert.notNull(chorbiLike);
		Assert.isTrue(chorbiLike.getId() != 0);
		Assert.isTrue(this.chorbiLikeRepository.exists(chorbiLike.getId()));

		this.chorbiLikeRepository.delete(chorbiLike);
	}

	//Other business methods

	public Collection<ChorbiLike> findChorbiLikesByLiker(final int chorbiId) {
		Assert.notNull(chorbiId);
		Assert.isTrue(chorbiId != 0);

		Collection<ChorbiLike> result;

		result = this.chorbiLikeRepository.findChorbiLikesByLiker(chorbiId);

		return result;
	}

	public Collection<ChorbiLike> findChorbiLikesByLiked(final int chorbiId) {
		Assert.notNull(chorbiId);
		Assert.isTrue(chorbiId != 0);

		Collection<ChorbiLike> result;

		result = this.chorbiLikeRepository.findChorbiLikesByLiked(chorbiId);

		return result;
	}

	public void cancelChorbiLike(final Chorbi chorbi) {
		Assert.isTrue(actorService.checkAuthority("CHORBI"));
		Assert.notNull(chorbi);

		Chorbi principal;
		ChorbiLike chorbiLike;

		principal = chorbiService.findByPrincipal;

		for (final ChorbiLike c : this.findChorbiLikesByLiker(principal.getId()))
			if (c.getLiked().equals(chorbi))
				chorbiLike = c;

		this.delete(chorbiLike);

	}

}
