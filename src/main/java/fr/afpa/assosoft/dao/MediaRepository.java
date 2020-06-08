package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Media;

//@RepositoryRestResource
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

}
