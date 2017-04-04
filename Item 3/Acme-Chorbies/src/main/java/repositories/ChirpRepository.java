
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Chirp;

@Repository
public interface ChirpRepository extends JpaRepository<Chirp, Integer> {

	// TODO añadir queries

	@Query("select c from Chirp c where c.copy = false and c.sender.id = ?1")
	Collection<Chirp> findChirpsSent(int chorbiId);

	@Query("select c from Chirp c where c.copy = true and c.recipient.id = ?1")
	Collection<Chirp> findChirpsReceived(int chorbiId);

}
