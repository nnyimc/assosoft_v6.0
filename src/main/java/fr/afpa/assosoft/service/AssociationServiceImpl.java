package fr.afpa.assosoft.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import fr.afpa.assosoft.beans.InscriptionAsso;
import fr.afpa.assosoft.dao.AdhesionRepository;
import fr.afpa.assosoft.dao.AdresseRepository;
import fr.afpa.assosoft.dao.AssociationRepository;
import fr.afpa.assosoft.dao.CategorieRepository;
import fr.afpa.assosoft.dao.MediaRepository;
import fr.afpa.assosoft.dao.PersonneRepository;
import fr.afpa.assosoft.dao.RoleRepository;
import fr.afpa.assosoft.dao.StatutRepository;
import fr.afpa.assosoft.entities.Adhesion;
import fr.afpa.assosoft.entities.Adresse;
import fr.afpa.assosoft.entities.Association;
import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Media;
import fr.afpa.assosoft.entities.Personne;
import fr.afpa.assosoft.entities.Role;
import fr.afpa.assosoft.entities.Statut;

@Service
@Transactional
public class AssociationServiceImpl implements IAssociationService {

	private final AssociationRepository assoRepository;
	private final AdresseRepository adresseRepository;
	private final CategorieRepository categorieRepository;
	private final RoleRepository roleRepository;
	private final StatutRepository statutRepository;
	private final PersonneRepository personneRepository;
	private final AdhesionRepository adhesionRepository;
	private final MediaRepository mediaRepository;

	public AssociationServiceImpl(AssociationRepository assoRepository, AdresseRepository adresseRepository,
			CategorieRepository categorieRepository, RoleRepository roleRepository, StatutRepository statutRepository,
			PersonneRepository personneRepository, AdhesionRepository adhesionRepository,
			MediaRepository mediaRepository) {
		this.assoRepository = assoRepository;
		this.adresseRepository = adresseRepository;
		this.categorieRepository = categorieRepository;
		this.roleRepository = roleRepository;
		this.statutRepository = statutRepository;
		this.personneRepository = personneRepository;
		this.adhesionRepository = adhesionRepository;
		this.mediaRepository = mediaRepository;
	}

	private Media uploadImageAsso(MultipartFile retrievedFile) throws IOException {
		Media assoImage = new Media();

		// Modification de l'url de la Photo
		String[] fileNameArray = retrievedFile.getOriginalFilename().split("\\.");
		assoImage.setUrl(fileNameArray[0]);
		String newUrl = assoImage.getUrl();

		// Copie du fichier dans le répertoire uploads
		Files.write(
				Paths.get("J://assosoft/assosoft_v6.0/src/main/resources/static/images/association/" + newUrl + ".jpg"),
				retrievedFile.getBytes());
		assoImage.setPath("/images/association/" + newUrl + ".jpg");
		assoImage.setUrl(
				"J://assosoft/assosoft_v6.0/src/main/resources/static/images/association/" + newUrl + ".jpg");
		assoImage.setTitre(newUrl);
		return assoImage;
	}

	@Override
	public Page<Association> listerAsso(int page, int size) {
		return assoRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Association> rechercherNomCateg(String rc, int pageAffichee, int size) {
		return assoRepository.rechercherNomCateg(rc, PageRequest.of(pageAffichee, size));
	}

	@Override
	public Association recupererAsso(Long idAsso) {
		return assoRepository.findById(idAsso).orElse(null);
	}

	@Override
	public List<Adresse> recupererLocalites() {
		return adresseRepository.rechercherAdresseVilles();
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
	public Page<Association> rechercherLocalite(String selectLocalite, int page, int size) {
		return assoRepository.rechercheSelectLocalite(selectLocalite, PageRequest.of(page, size));
	}

	@Override
	public Page<Association> rechercherCategorie(String selectCateg, int page, int size) {
		return assoRepository.rechercheSelectCategorie(selectCateg, PageRequest.of(page, size));
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
	public void saveAssociation(InscriptionAsso inscriptionAsso, MultipartFile file) throws IOException {
		// Récupération de la adresseAdmin
		Adresse adresseAdmin = inscriptionAsso.getAdresseAdmin();
		if (null == adresseRepository.recupererAdresseVille(adresseAdmin.getVille())) {
			adresseRepository.save(adresseAdmin);
		} else {
			adresseAdmin = adresseRepository.findByVille(adresseAdmin.getVille());
			adresseRepository.save(adresseAdmin);
		}

		// Création de l'admin
		Role role = new Role();
		role.setId((long) 2);
		role.setNom("Administrateur association");

		Statut statutActive = new Statut();
		statutActive.setId((long) 2);
		statutActive.setNom("Activé");

		Personne admin = new Personne();
		admin.setLogin(inscriptionAsso.getPersonneLogin());
		admin.setEmail(inscriptionAsso.getPersonneMail());
		admin.setMotDePasse(inscriptionAsso.getPersonneMdp());
		admin.setNom(inscriptionAsso.getPersonneNom());
		admin.setPrenom(inscriptionAsso.getPersonnePrenom());
		admin.setRole(role);
		admin.setStatut(statutActive);
		admin.setAdresse(adresseAdmin);

		personneRepository.save(admin);

		// Récupération de la catégorie
		Categorie categorie = inscriptionAsso.getCategorieAsso();
		if (null == categorieRepository.rechercherCategorieNom(categorie.getNom())) {
			categorieRepository.save(categorie);
		} else {
			categorie = categorieRepository.rechercherCategorieNom(categorie.getNom());
			categorieRepository.save(categorie);
		}

		// Traitement de l'image de l'association
		Media media = this.uploadImageAsso(file);
		mediaRepository.save(media);
		Collection<Media> collectionMedia = new ArrayList<Media>();
		collectionMedia.add(media);

		// Récupération de la adresseAsso
		Adresse adresseAsso = inscriptionAsso.getAdresseAsso();
		if (null == adresseRepository.recupererAdresseVille(adresseAsso.getVille())) {
			adresseRepository.save(adresseAsso);
		} else {
			adresseAsso = adresseRepository.findByVille(adresseAsso.getVille());
			adresseRepository.save(adresseAsso);
		}

		// Création de l'association
		Association association = new Association();
		association.setMail(inscriptionAsso.getAssoMail());
		association.setNom(inscriptionAsso.getAssoNom());
		association.setNumRNA(inscriptionAsso.getAssoNumRNA());
		association.setTelephone(inscriptionAsso.getAssoTel());
		association.setUrl(inscriptionAsso.getAssoUrl());
		association.setMedias(collectionMedia);
		association.setStatut(statutActive);
		association.setAdresse(adresseAsso);
		association.setCategorie(categorie);
		assoRepository.save(association);

		// Récupération de la dernière association
		Page<Association> pageAsso = assoRepository.recupererDerniereAsso(PageRequest.of(0, 1));

		// Récupération du dernier média
		Page<Media> pageMedia = mediaRepository.recupererDernierMedia(PageRequest.of(0, 1));
		media = pageMedia.getContent().get(0);

		// Paramétrage de l'association
		media.setAssociation(pageAsso.getContent().get(0));
	}

	@Override
	public void saveAdherent(Personne personne) {
		Role role = new Role();
		role.setId((long) 3);
		role.setNom("Adhérent");

		Statut statut = new Statut();
		statut.setId((long) 2);
		statut.setNom("Activé");

		personne.setRole(role);
		personne.setStatut(statut);
		personneRepository.save(personne);
	}

	@Override
	public List<Categorie> recupererCategoriesadresse(String localite) {
		return categorieRepository.rechercherCategoriesadresse(localite);
	}

	@Override
	public Page<Association> rechercherCategorieLocalite(String localite, String categorie, int page, int size) {
		return assoRepository.rechercheSelectsCategLocalite(localite, categorie, PageRequest.of(page, size));
	}

	@Override
	public void supprimerAsso(Long id) {
		assoRepository.deleteById(id);
	}

}
