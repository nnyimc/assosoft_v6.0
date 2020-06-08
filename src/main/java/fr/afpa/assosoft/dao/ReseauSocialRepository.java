package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.ReseauSocial;

//@RepositoryRestResource
@Repository
public interface ReseauSocialRepository extends JpaRepository<ReseauSocial, Long> {

}
