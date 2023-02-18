package fr.afpa.assosoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Adresse;

//@RepositoryRestResource
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
	@Query("FROM Adresse adr JOIN adr.associations a GROUP BY a.adresse")
	public List<Adresse> rechercherAdresseVilles();

	@Query("SELECT ville FROM Adresse adr WHERE adr.ville = ?1")
	public String recupererAdresseVille(String ville);

	@Query("SELECT ville FROM Adresse adr WHERE adr.ville = ?1")
	public Adresse findByVille(String ville);
}
