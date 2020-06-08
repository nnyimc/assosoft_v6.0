package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Contact;

//@RepositoryRestResource
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
