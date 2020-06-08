package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Reference;

//@RepositoryRestResource
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {

}
