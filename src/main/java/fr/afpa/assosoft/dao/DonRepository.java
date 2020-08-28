package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Don;

import java.util.List;


//@RepositoryRestResource
@Repository
public interface DonRepository extends JpaRepository<Don, Long> {
    @Query(
             "FROM Don d "
           + "INNER JOIN FETCH d.statut INNER JOIN FETCH d.typeDon "
           + "INNER JOIN FETCH d.personne INNER JOIN FETCH d.association"
    )
    public List<Don> findAll();
}
