package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.LienReseau;

//@RepositoryRestResource
@Repository
public interface LienReseauRepository extends JpaRepository<LienReseau, Long> {

}
