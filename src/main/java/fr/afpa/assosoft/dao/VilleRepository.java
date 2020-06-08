package fr.afpa.assosoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Ville;

//@RepositoryRestResource
@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
	@Query("FROM Ville v JOIN v.associations a GROUP BY v.villeNom")
	public List<Ville> rechercherVilleNoms();

	@Query("SELECT villeNom FROM Ville v WHERE v.villeNom = ?1")
	public String recupererVilleNom(String villenom);

	@Query("SELECT v FROM Ville v WHERE v.villeNom = ?1")
	public Ville findByVilleNom(String villenom);
}
