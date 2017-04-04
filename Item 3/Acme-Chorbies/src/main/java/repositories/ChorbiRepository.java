
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chorbi;

@Repository
public interface ChorbiRepository extends JpaRepository<Chorbi, Integer> {

	@Query("select c from Chorbi c where c.userAccount.id = ?1")
	Chorbi findByUserAccountId(int userAccountId);

	//TODO: Add keyword
	@Query("select c from Chorbi c where (?1 is null or c.age = ?1) and (?2 is null or c.gender = ?2) and (?3 is null or c.relationship = ?3) and (?4 is null or c.country = ?4) and (?5 is null or c.state = ?5) and (?6 is null or c.province = ?6) and (?7 is null or c.city = ?7)")
	Collection<Chorbi> findChorbiesBySearchTemplate(int age, String gender, String relationship, String country, String state, String province, String city);

}
