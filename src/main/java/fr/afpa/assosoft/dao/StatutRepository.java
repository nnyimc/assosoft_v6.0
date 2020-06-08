package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Statut;

//@RepositoryRestResource
@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {

}
