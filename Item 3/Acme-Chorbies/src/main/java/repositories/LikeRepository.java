
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ChorbieLike;

@Repository
public interface LikeRepository extends JpaRepository<ChorbieLike, Integer> {

}
