package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.ContactProfessionnel;

import java.util.List;

//@RepositoryRestResource
@Repository
public interface ContactProfessionnelRepository extends JpaRepository<ContactProfessionnel, Long> {
    @Query("FROM ContactProfessionnel c INNER JOIN FETCH c.association INNER JOIN FETCH c.prestataire")
    public List<ContactProfessionnel> findAll();
}
