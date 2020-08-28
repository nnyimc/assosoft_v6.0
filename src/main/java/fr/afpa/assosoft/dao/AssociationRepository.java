package fr.afpa.assosoft.dao;

import fr.afpa.assosoft.entities.Adhesion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Association;

import java.util.List;

//@RepositoryRestResource
@Repository
public interface AssociationRepository
		extends JpaRepository<Association, Long> {
	@Query("select a "
			+ "from Association a "
			+ "inner join fetch a.ville "
			+ "inner join fetch a.categorie "
			+ "inner join fetch a.statut "
			+ "inner join fetch a.admin")
	public List<Association> findAll();

	@Query(
			value = "SELECT a "
			+ "FROM Association a "
			+ "inner join fetch a.admin "
			+ "inner join fetch a.statut "
			+ "inner join fetch a.ville "
			+ "inner join fetch a.categorie cat "
			+ "where cat.catIntitule "
			+ "like :x or a.assoNom like :x",

			// Indispensable dans le cadre d'une pagination - fetching inutile
			countQuery = "SELECT COUNT(a) "
					+ "FROM Association a "
			        + "inner join a.admin "
					+ "inner join a.ville "
					+ "inner join a.categorie cat "
					+ "where cat.catIntitule "
			        + "like :x or a.assoNom like :x"
	)
	public Page<Association> rechercherNomCateg(@Param("x") String rc,
			Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.ville "
			+"inner join fetch a.admin "
			+"inner join fetch a.statut "
			+"inner join fetch a.categorie "
			+"where a.ville.villeNom like :x ",

			countQuery = "SELECT a FROM Association a "
			+"inner join a.ville "
			+"inner join a.admin "
			+"inner join a.statut "
			+"inner join a.categorie "
			+"where a.ville.villeNom like :x"
	)
	public Page<Association> rechercheSelectLocalite(
			@Param("x") String selectLocalite, Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.categorie "
			+"inner join fetch a.admin "
			+"inner join fetch a.ville "
			+"inner join fetch a.statut "
			+"where a.categorie.catIntitule like :x ",

			countQuery = "SELECT a FROM Association a "
			+"inner join a.categorie "
			+"inner join a.admin "
			+"inner join a.ville "
			+"inner join a.statut "
			+"where a.categorie.catIntitule like :x"
	)
	public Page<Association> rechercheSelectCategorie(
			@Param("x") String selectCateg, Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.ville "
			+"inner join fetch a.categorie "
			+"inner join fetch a.statut "
			+"inner join fetch a.admin "
			+"where a.ville.villeNom "
			+"like :x and a.categorie.catIntitule like :y ",

			countQuery = "SELECT a FROM Association a "
			+ "inner join a.ville "
			+ "inner join a.categorie "
			+ "inner join a.statut "
			+ "inner join a.admin "
			+ "where a.ville.villeNom "
			+ "like :x and a.categorie.catIntitule like :y"

	)
	public Page<Association> rechercheSelectsCategLocalite(
			@Param("x") String selectLocalite,
			@Param("y") String selectCategorie, Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.admin "
			+"inner join fetch a.ville "
			+"inner join fetch a.statut "
			+"inner join fetch a.categorie "
			+"ORDER BY a.assoId DESC ",

			countQuery = "SELECT a FROM Association a "
			+"inner join a.admin "
			+"inner join a.statut "
			+"inner join a.ville "
			+"inner join a.categorie "
			+"ORDER BY a.assoId"
	)
	public Page<Association> recupererDerniereAsso(Pageable pageable);
}