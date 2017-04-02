
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

}
