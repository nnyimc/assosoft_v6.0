package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Reference;

import java.util.List;

//@RepositoryRestResource
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    @Query("FROM Reference r INNER JOIN FETCH r.association INNER JOIN FETCH r.contact")
    public List<Reference> findAll();
}
