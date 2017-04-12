
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {

	@Query("select c from Chirp c where c.copy = false and c.chirper.id = ?1")
	Collection<Chirp> findChirpsSent(int chorbiId);

	@Query("select c from Chirp c where c.copy = true and c.chirped.id = ?1")
	Collection<Chirp> findChirpsReceived(int chorbiId);

	@Query("select count(cp)*1.0/(select count(c) from Chorbi) from Chirp cp")
	Double findAvgChirpsRecPerChorbi();

	@Query("select count(cp) from Chirp cp group by cp.chirped order by count(cp) desc")
	Collection<Long> findMaxChirpsRecPerChorbi();

	@Query("select count(cp) from Chirp cp group by cp.chirped order by count(cp) asc")
	Collection<Long> findMinChirpsRecPerChorbi();

	@Query("select count(cp)*1.0/(select count(c) from Chorbi) from Chirp cp")
	Double findAvgChirpsSendPerChorbi();

	@Query("select count(cp) from Chirp cp group by cp.chirper order by count(cp) desc")
	Collection<Long> findMaxChirpsSendPerChorbi();

	@Query("select count(cp) from Chirp cp group by cp.chirper order by count(cp) asc")
	Collection<Long> findMinChirpsSendPerChorbi();

}
