
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Relationship;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {

}
