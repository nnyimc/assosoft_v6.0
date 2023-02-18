package fr.afpa.assosoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Categorie;

//@RepositoryRestResource
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	@Query("FROM Categorie c GROUP BY c.nom")
	public List<Categorie> rechercherCategorieNoms();

	@Query("FROM Categorie c WHERE c.nom = ?1")
	public Categorie rechercherCategorieNom(String catIntitule);

	@Query("FROM Categorie c INNER JOIN FETCH c.associations a "
			+ "WHERE a.adresse.ville LIKE :x GROUP BY c.nom")
	public List<Categorie> rechercherCategoriesadresse(@Param("x") String localite);

}
