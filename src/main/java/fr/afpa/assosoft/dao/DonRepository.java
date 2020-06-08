package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Don;

//@RepositoryRestResource
@Repository
public interface DonRepository extends JpaRepository<Don, Long> {

}
