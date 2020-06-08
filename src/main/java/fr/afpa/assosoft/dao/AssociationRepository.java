package fr.afpa.assosoft.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Association;

//@RepositoryRestResource
@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
	@Query("SELECT a FROM Association a where a.categorie.catIntitule like :x or a.assoNom like :x")
	public Page<Association> rechercherNomCateg(@Param("x") String rc, Pageable pageable);

	@Query("SELECT a FROM Association a where a.ville.villeNom like :x")
	public Page<Association> rechercheSelectLocalite(@Param("x") String selectLocalite, Pageable pageable);

	@Query("SELECT a FROM Association a where a.categorie.catIntitule like :x")
	public Page<Association> rechercheSelectCategorie(@Param("x") String selectCateg, Pageable pageable);

	@Query("SELECT a FROM Association a where a.ville.villeNom like :x and a.categorie.catIntitule like :y")
	public Page<Association> rechercheSelectsCategLocalite(@Param("x") String selectLocalite,
			@Param("y") String selectCategorie, Pageable pageable);
}