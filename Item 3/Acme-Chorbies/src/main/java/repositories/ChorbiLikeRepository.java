
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ChorbiLike;

@Repository
public interface ChorbiLikeRepository extends JpaRepository<ChorbiLike, Integer> {

}
