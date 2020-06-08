package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.TypeDon;

//@RepositoryRestResource
@Repository
public interface TypeDonRepository extends JpaRepository<TypeDon, Long> {

}
