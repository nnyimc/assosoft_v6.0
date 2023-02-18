package fr.afpa.assosoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Prestataire;

//@RepositoryRestResource
@Repository
public interface PrestataireRepository extends JpaRepository<Prestataire, Long> {
    @Query("FROM Prestataire p INNER JOIN FETCH p.adresse INNER JOIN FETCH p.statut")
    public List<Prestataire> findAll();
}
