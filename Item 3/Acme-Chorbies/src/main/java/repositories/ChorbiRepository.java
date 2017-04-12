
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

	@Query("select avg(SUBSTRING(CURRENT_DATE, 1, 4) - SUBSTRING(c.birthdate, 1, 4)) from Chorbi c")
	Double findAvgAgeChorbies();

	@Query("select max(SUBSTRING(CURRENT_DATE, 1, 4) - SUBSTRING(c.birthdate, 1, 4)) from Chorbi c")
	Double findMaxAgeChorbies();

	@Query("select min(SUBSTRING(CURRENT_DATE, 1, 4) - SUBSTRING(c.birthdate, 1, 4)) from Chorbi c")
	Double findMinAgeChorbies();

	@Query("select count(c) from Chorbi c where c.creditCard = null or c.creditCard.year < SUBSTRING(CURRENT_DATE, 1, 4) or (c.creditCard.year >= SUBSTRING(CURRENT_DATE, 1, 4) and c.creditCard.month <=  SUBSTRING(CURRENT_DATE, 6, 2))*1./(select c from Chorbi c) ")
	Double findRatioChorbiesNoCCInvCC();

	@Query("select count(c)*1./(select count(c) from Chorbi c) from Chorbi c where c.searchTemplate.relationship like 'activities'")
	Double findRatioChorbiesSearchAct();

	@Query("select count(c)*1./(select count(c) from Chorbi c) from Chorbi c where c.searchTemplate.relationship like 'friendship'")
	Double findRatioChorbiesSearchFriend();

	@Query("select count(c)*1./(select count(c) from Chorbi c) from Chorbi c where c.searchTemplate.relationship like 'love'")
	Double findRatioChorbiesSearchLove();

	@Query("select cl.liked from ChorbiLike cl group by cl.liked order by count(cl) desc")
	Collection<Chorbi> findChorbiesSortNumLikes();

	@Query("select ch.chirped from Chirp ch group by ch.chirped having count(ch) >= (select count(ch) from Chirp ch group by ch.chirped)")
	Collection<Chorbi> findChorbiesMoreChirpsRec();

	@Query("select ch.chirper from Chirp ch group by ch.chirper having count(ch) >= (select count(ch) from Chirp ch group by ch.chirper)")
	Collection<Chorbi> findChorbiesMoreChirpsSend();

	//TODO: Add keyword
	//	@Query("select c from Chorbi c where (?1 is null or c.age = ?1) and (?2 is null or c.gender = ?2) and (?3 is null or c.relationship = ?3) and (?4 is null or c.country = ?4) and (?5 is null or c.state = ?5) and (?6 is null or c.province = ?6) and (?7 is null or c.city = ?7)")
	//	Collection<Chorbi> findChorbiesBySearchTemplate(int age, String gender, String relationship, String country, String state, String province, String city);

}
