package fr.afpa.assosoft.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Categorie;

//@RepositoryRestResource
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	@Query("FROM Categorie c JOIN c.associations a GROUP BY c.catIntitule")
	public List<Categorie> rechercherCategorieNoms();

	@Query("FROM Categorie c WHERE c.catIntitule = ?1")
	public Categorie rechercherCategorieNom(String catIntitule);

	@Query("FROM Categorie c JOIN c.associations a WHERE a.ville.villeNom LIKE :x GROUP BY c.catIntitule")
	public List<Categorie> rechercherCategoriesVille(@Param("x") String localite);
}
