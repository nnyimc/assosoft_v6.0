package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Offre;

import java.util.List;

//@RepositoryRestResource
@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
    @Query(
            "FROM Offre o INNER JOIN FETCH o.association INNER JOIN FETCH o.proposition"
    )
    List<Offre> findAll();
}
