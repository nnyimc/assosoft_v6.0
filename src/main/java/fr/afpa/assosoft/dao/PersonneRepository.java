package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Personne;

//@RepositoryRestResource
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
