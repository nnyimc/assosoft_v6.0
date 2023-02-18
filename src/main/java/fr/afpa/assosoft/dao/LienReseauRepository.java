package fr.afpa.assosoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.LienReseauSocial;


//@RepositoryRestResource
@Repository
public interface LienReseauRepository extends JpaRepository<LienReseauSocial, Long> {
    @Query("FROM LienReseauSocial l INNER JOIN FETCH  l.association "
           + "INNER JOIN FETCH l.reseauSocial" )
    public List<LienReseauSocial> findAll();
}
