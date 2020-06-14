package fr.afpa.assosoft.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import fr.afpa.assosoft.beans.InscriptionAsso;
import fr.afpa.assosoft.entities.Adhesion;
import fr.afpa.assosoft.entities.Association;
import fr.afpa.assosoft.entities.Ville;
import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Personne;
import fr.afpa.assosoft.entities.Role;
import fr.afpa.assosoft.entities.Statut;

public interface IAssociationService {

	public Page<Association> listerAsso(int page, int size);

	public Page<Association> rechercherNomCateg(String rc, int page, int size);

	public Page<Association> rechercherLocalite(String rc, int page, int size);

	public Page<Association> rechercherCategorie(String rc, int page, int size);

	public Page<Association> rechercherCategorieLocalite(String localite, String categorie, int page, int size);

	public Association recupererAsso(Long idAsso);

	public Role recupererRole(Long idRole);

	public Statut recupererStatut(Long idStatut);

	public void savePersonne(Personne personne);

	public void saveAdhesion(Adhesion adhesion);

	public void saveAdherent(Personne personne);

	public List<Ville> recupererLocalites();

	public List<Categorie> recupererCategories();

	public List<Categorie> recupererCategoriesVille(String localite);

	public void saveAssociation(InscriptionAsso inscriptionAsso, MultipartFile file) throws IOException;

	public void supprimerAsso(Long id);

}
