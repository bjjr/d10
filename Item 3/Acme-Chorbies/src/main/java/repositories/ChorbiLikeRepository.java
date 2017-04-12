
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ChorbiLike;

@Repository
public interface ChorbiLikeRepository extends JpaRepository<ChorbiLike, Integer> {

	@Query("select c from ChorbiLike c where c.liker.id = ?1")
	Collection<ChorbiLike> findChorbiLikesByLiker(int chorbiId);

	@Query("select c from ChorbiLike c where c.liked.id = ?1")
	Collection<ChorbiLike> findChorbiLikesByLiked(int chorbiId);

	@Query("select count(cl)*1.0/(select count(c) from Chorbi) from ChorbiLike c")
	Double findAvgLikesPerChorbi();

	@Query("select count(cl) from ChorbiLike cl group by cl.chorbi order by count(cl) desc")
	Collection<Long> findMaxLikesPerChorbi();

	@Query("select count(cl) from ChorbiLike cl group by cl.chorbi order by count(cl) asc")
	Collection<Long> findMinLikesPerChorbi();

}
