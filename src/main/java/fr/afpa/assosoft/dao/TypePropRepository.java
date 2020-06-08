package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.TypeProp;

//@RepositoryRestResource
@Repository
public interface TypePropRepository extends JpaRepository<TypeProp, Long> {

}
