package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Personne;

import java.util.List;

//@RepositoryRestResource
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    @Query(
            "FROM Personne p "
            +"INNER JOIN FETCH p.statut "
            +"INNER JOIN FETCH p.ville INNER JOIN FETCH p.role "
            +"WHERE p.personneLogin LIKE :x"
    )
    public Personne findByPersonneLogin(@Param("x")String login);

    @Query(
            "FROM Personne p "
            +"INNER JOIN FETCH p.statut "
            +"INNER JOIN FETCH p.ville INNER JOIN FETCH p.role "
    )
    public List<Personne> findAll();
}
