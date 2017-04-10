
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SearchTemplate;

@Repository
public interface SearchTemplateRepository extends JpaRepository<SearchTemplate, Integer> {

	//	//TODO: Add keyword
	//	@Query("select c from Chorbi c where (?1 IS NULL OR c.gender = ?1) and (?2 IS NULL OR c.relationship = ?2) and (?3 IS NULL OR c.coordinates.country = ?3) and (?4 IS NULL OR c.coordinates.state = ?4) and (?5 IS NULL OR c.coordinates.province = ?5) and (?6 IS NULL OR c.coordinates.city = ?6)")
	//	Collection<Chorbi> findChorbiesBySearchTemplate(String gender, String relationship, String country, String state, String province, String city);

}
