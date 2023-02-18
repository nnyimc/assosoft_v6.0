package fr.afpa.assosoft.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Association;

//@RepositoryRestResource
@Repository
public interface AssociationRepository
		extends JpaRepository<Association, Long> {
	@Query("select a "
			+ "from Association a "
			+ "inner join fetch a.adresse "
			+ "inner join fetch a.categorie "
			+ "inner join fetch a.statut ")
	public List<Association> findAll();

	@Query(
			value = "SELECT a "
			+ "FROM Association a "
			+ "inner join fetch a.statut "
			+ "inner join fetch a.adresse "
			+ "inner join fetch a.categorie cat "
			+ "where cat.nom "
			+ "like :x or a.nom like :x",

			// Indispensable dans le cadre d'une pagination - fetching inutile
			countQuery = "SELECT COUNT(a) "
					+ "FROM Association a "
					+ "inner join a.adresse "
					+ "inner join a.categorie cat "
					+ "where cat.nom "
			        + "like :x or a.nom like :x"
	)
	public Page<Association> rechercherNomCateg(@Param("x") String rc,
			Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.adresse "
			+"inner join fetch a.statut "
			+"inner join fetch a.categorie "
			+"where a.adresse.ville like :x ",

			countQuery = "SELECT a FROM Association a "
			+"inner join a.adresse "
			+"inner join a.statut "
			+"inner join a.categorie "
			+"where a.adresse.ville like :x"
	)
	public Page<Association> rechercheSelectLocalite(
			@Param("x") String selectLocalite, Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.categorie "
			+"inner join fetch a.adresse "
			+"inner join fetch a.statut "
			+"where a.categorie.nom like :x ",

			countQuery = "SELECT a FROM Association a "
			+"inner join a.categorie "
			+"inner join a.adresse "
			+"inner join a.statut "
			+"where a.categorie.nom like :x"
	)
	public Page<Association> rechercheSelectCategorie(
			@Param("x") String selectCateg, Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.adresse "
			+"inner join fetch a.categorie "
			+"inner join fetch a.statut "
			+"where a.adresse.ville "
			+"like :x and a.categorie.nom like :y ",

			countQuery = "SELECT a FROM Association a "
			+ "inner join a.adresse "
			+ "inner join a.categorie "
			+ "inner join a.statut "
			+ "where a.adresse.ville "
			+ "like :x and a.categorie.nom like :y"

	)
	public Page<Association> rechercheSelectsCategLocalite(
			@Param("x") String selectLocalite,
			@Param("y") String selectCategorie, Pageable pageable);

	@Query(
			value = "SELECT a FROM Association a "
			+"inner join fetch a.adresse "
			+"inner join fetch a.statut "
			+"inner join fetch a.categorie "
			+"ORDER BY a.id DESC ",

			countQuery = "SELECT a FROM Association a "
			+"inner join a.statut "
			+"inner join a.adresse "
			+"inner join a.categorie "
			+"ORDER BY a.id"
	)
	public Page<Association> recupererDerniereAsso(Pageable pageable);
}