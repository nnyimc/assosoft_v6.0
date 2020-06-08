package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Offre;

//@RepositoryRestResource
@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {

}
