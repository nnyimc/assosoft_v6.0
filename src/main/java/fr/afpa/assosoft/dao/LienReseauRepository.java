package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.LienReseau;

import java.util.List;


//@RepositoryRestResource
@Repository
public interface LienReseauRepository extends JpaRepository<LienReseau, Long> {
    @Query("FROM LienReseau l INNER JOIN FETCH  l.association "
           + "INNER JOIN FETCH l.reseauSocial" )
    public List<LienReseau> findAll();
}
