package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Proposition;

//@RepositoryRestResource
@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long> {

}
