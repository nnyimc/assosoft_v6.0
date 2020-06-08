package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Role;

//@RepositoryRestResource
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
