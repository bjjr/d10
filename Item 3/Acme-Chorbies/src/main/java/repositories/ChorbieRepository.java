
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbie;

@Repository
public interface ChorbieRepository extends JpaRepository<Chorbie, Integer> {

	@Query("select c from Chorbie c where c.userAccount.id = ?1")
	Chorbie findByUserAccountId(int userAccountId);

}
