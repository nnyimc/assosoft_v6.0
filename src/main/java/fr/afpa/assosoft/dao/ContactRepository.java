package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Contact;

import java.util.List;

import static org.springframework.http.HttpHeaders.FROM;

//@RepositoryRestResource
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("FROM Contact c INNER JOIN FETCH c.ville INNER JOIN FETCH c.statut")
    public List<Contact> findAll();
}
