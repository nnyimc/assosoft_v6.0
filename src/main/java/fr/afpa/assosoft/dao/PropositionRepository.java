package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Proposition;

import java.util.List;

//@RepositoryRestResource
@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long> {
    @Query("FROM Proposition p INNER JOIN FETCH p.typeProposition")
    public List<Proposition> findAll();
}
