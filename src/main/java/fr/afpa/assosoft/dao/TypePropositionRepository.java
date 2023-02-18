package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.TypeProposition;

//@RepositoryRestResource
@Repository
public interface TypePropositionRepository extends JpaRepository<TypeProposition, Long> {

}
