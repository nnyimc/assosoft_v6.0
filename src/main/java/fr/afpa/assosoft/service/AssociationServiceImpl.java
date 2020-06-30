package fr.afpa.assosoft.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.afpa.assosoft.beans.InscriptionAsso;
import fr.afpa.assosoft.dao.AdhesionRepository;
import fr.afpa.assosoft.dao.AssociationRepository;
import fr.afpa.assosoft.dao.CategorieRepository;
import fr.afpa.assosoft.dao.MediaRepository;
import fr.afpa.assosoft.dao.PersonneRepository;
import fr.afpa.assosoft.dao.RoleRepository;
import fr.afpa.assosoft.dao.StatutRepository;
import fr.afpa.assosoft.dao.VilleRepository;
import fr.afpa.assosoft.entities.Adhesion;
import fr.afpa.assosoft.entities.Association;
import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Media;
import fr.afpa.assosoft.entities.Personne;
import fr.afpa.assosoft.entities.Role;
import fr.afpa.assosoft.entities.Statut;
import fr.afpa.assosoft.entities.Ville;

@Service
@Transactional
public class AssociationServiceImpl implements IAssociationService {

	@Autowired
	private AssociationRepository assoRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private StatutRepository statutRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private AdhesionRepository adhesionRepository;
	@Autowired
	private MediaRepository mediaRepository;
	@Autowired
	BCryptPasswordEncoder bPCE;

	private Media uploadImageAsso(MultipartFile retrievedFile)
			throws IOException {
		Media assoImage = new Media();
		// Modification de l'url de la Photo
		String[] fileNameArray = retrievedFile.getOriginalFilename()
				.split("\\.");
		assoImage.setMediaUrl(fileNameArray[0]);
		String newUrl = assoImage.getMediaUrl();
		// Copie du fichier dans le répertoire uploads
		Files.write(
				Paths.get(
						"J://git/assosoft_v6/src/main/resources/static/images/"
								+ newUrl + ".jpg"),
				retrievedFile.getBytes());
		assoImage.setMediaPath("/images/association/" + newUrl + ".jpg");
		assoImage.setMediaUrl(
				"J://git/assosoft_v6/src/main/resources/static/images/"
						+ newUrl + ".jpg");
		assoImage.setMediaTitre(newUrl);
		return assoImage;
	}

	@Override
	public Page<Association> listerAsso(int page, int size) {
		return assoRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Association> rechercherNomCateg(String rc,
			int pageAffichee, int size) {
		return assoRepository.rechercherNomCateg(rc,
				PageRequest.of(pageAffichee, size));
	}

	@Override
	public Association recupererAsso(Long idAsso) {
		return assoRepository.findById(idAsso).orElse(null);
	}

	@Override
	public List<Ville> recupererLocalites() {
		return villeRepository.rechercherVilleNoms();
	}

	@Override
	public List<Categorie> recupererCategories() {
		return categorieRepository.rechercherCategorieNoms();
	}

	@Override
	public Role recupererRole(Long idRole) {
		return roleRepository.findById(idRole).orElse(null);
	}

	@Override
	public Page<Association> rechercherLocalite(String selectLocalite,
			int page, int size) {
		return assoRepository.rechercheSelectLocalite(selectLocalite,
				PageRequest.of(page, size));
	}

	@Override
	public Page<Association> rechercherCategorie(String selectCateg,
			int page, int size) {
		return assoRepository.rechercheSelectCategorie(selectCateg,
				PageRequest.of(page, size));
	}

	@Override
	public Statut recupererStatut(Long idStatut) {
		return statutRepository.findById(idStatut).orElse(null);
	}

	@Override
	public void savePersonne(Personne personne) {
		personneRepository.save(personne);
	}

	@Override
	public void saveAdhesion(Adhesion adhesion) {
		adhesionRepository.save(adhesion);
	}

	@Override
	public void saveAssociation(InscriptionAsso inscriptionAsso,
			MultipartFile file) throws IOException {
		// Récupération de la villeAdmin
		Ville villeAdmin = inscriptionAsso.getVilleAdmin();
		if (null == villeRepository
				.recupererVilleNom(villeAdmin.getVilleNom())) {
			villeRepository.save(villeAdmin);
		} else {
			villeAdmin = villeRepository
					.findByVilleNom(villeAdmin.getVilleNom());
			villeRepository.save(villeAdmin);
		}

		// Création de l'admin
		Role role = new Role();
		role.setRoleId((long) 2);
		role.setRoleIntitule("Administrateur association");
		
		Statut statutActive = new Statut();
		statutActive.setStatutId((long) 2);
		statutActive.setStatutValeur("Activé");
		
		Personne admin = new Personne();
		admin.setPersonneLogin(inscriptionAsso.getPersonneLogin());
		admin.setPersonneMail(inscriptionAsso.getPersonneMail());
		admin.setPersonneMdp(
				bPCE.encode(inscriptionAsso.getPersonneMdp()));
		admin.setPersonneNom(inscriptionAsso.getPersonneNom());
		admin.setPersonnePrenom(inscriptionAsso.getPersonnePrenom());
		admin.setRole(role);
		admin.setStatut(statutActive);
		admin.setVille(villeAdmin);
		
		personneRepository.save(admin);

		// Récupération de la catégorie
		Categorie categorie = inscriptionAsso.getCategorieAsso();
		if (null == categorieRepository
				.rechercherCategorieNom(categorie.getCatIntitule())) {
			categorieRepository.save(categorie);
		} else {
			categorie = categorieRepository
					.rechercherCategorieNom(categorie.getCatIntitule());
			categorieRepository.save(categorie);
		}

		// Traitement de l'image de l'association
		Media media = this.uploadImageAsso(file);
		mediaRepository.save(media);
		Collection<Media> collectionMedia = new ArrayList<Media>();
		collectionMedia.add(media);

		// Récupération de la villeAsso
		Ville villeAsso = inscriptionAsso.getVilleAsso();
		if (null == villeRepository
				.recupererVilleNom(villeAsso.getVilleNom())) {
			villeRepository.save(villeAsso);
		} else {
			villeAsso = villeRepository
					.findByVilleNom(villeAsso.getVilleNom());
			villeRepository.save(villeAsso);
		}

		// Création de l'association
		Association association = new Association();
		association.setAssoMail(inscriptionAsso.getAssoMail());
		association.setAssoNom(inscriptionAsso.getAssoNom());
		association.setAssoNumRNA(inscriptionAsso.getAssoNumRNA());
		association.setAssoTel(inscriptionAsso.getAssoTel());
		association.setAssoUrl(inscriptionAsso.getAssoUrl());
		association.setAdmin(admin);
		association.setMedias(collectionMedia);
		association.setStatut(statutActive);
		association.setVille(villeAsso);
		association.setCategorie(categorie);
		assoRepository.save(association);

		// Récupération de la dernière association
		Page<Association> pageAsso = assoRepository
				.recupererDerniereAsso(PageRequest.of(0, 1));

		// Récupération du dernier média
		Page<Media> pageMedia = mediaRepository
				.recupererDernierMedia(PageRequest.of(0, 1));
		media = pageMedia.getContent().get(0);

		// Paramétrage de l'association
		media.setAssociation(pageAsso.getContent().get(0));
	}

	@Override
	public void saveAdherent(Personne personne) {
		Role role = new Role();
		role.setRoleId((long) 3);
		role.setRoleIntitule("Adhérent");
		
		
		Statut statut = new Statut();
		statut.setStatutId((long) 2);
		statut.setStatutValeur("Activé");
		
		personne.setRole(role);
		personne.setStatut(statut);
		personneRepository.save(personne);
	}

	@Override
	public List<Categorie> recupererCategoriesVille(String localite) {
		return categorieRepository.rechercherCategoriesVille(localite);
	}

	@Override
	public Page<Association> rechercherCategorieLocalite(
			String localite, String categorie, int page, int size) {
		return assoRepository.rechercheSelectsCategLocalite(localite,
				categorie, PageRequest.of(page, size));
	}

	@Override
	public void supprimerAsso(Long id) {
		assoRepository.deleteById(id);
	}

}
