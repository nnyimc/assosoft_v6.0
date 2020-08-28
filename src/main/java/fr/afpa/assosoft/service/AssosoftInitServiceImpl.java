package fr.afpa.assosoft.service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.afpa.assosoft.dao.AdhesionRepository;
import fr.afpa.assosoft.dao.AssociationRepository;
import fr.afpa.assosoft.dao.CategorieRepository;
import fr.afpa.assosoft.dao.ContactRepository;
import fr.afpa.assosoft.dao.DonRepository;
import fr.afpa.assosoft.dao.LienReseauRepository;
import fr.afpa.assosoft.dao.MediaRepository;
import fr.afpa.assosoft.dao.OffreRepository;
import fr.afpa.assosoft.dao.PersonneRepository;
import fr.afpa.assosoft.dao.PropositionRepository;
import fr.afpa.assosoft.dao.ReferenceRepository;
import fr.afpa.assosoft.dao.ReseauSocialRepository;
import fr.afpa.assosoft.dao.RoleRepository;
import fr.afpa.assosoft.dao.StatutRepository;
import fr.afpa.assosoft.dao.TypeDonRepository;
import fr.afpa.assosoft.dao.TypePropRepository;
import fr.afpa.assosoft.dao.VilleRepository;
import fr.afpa.assosoft.entities.Adhesion;
import fr.afpa.assosoft.entities.Association;
import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Contact;
import fr.afpa.assosoft.entities.Don;
import fr.afpa.assosoft.entities.LienReseau;
import fr.afpa.assosoft.entities.Media;
import fr.afpa.assosoft.entities.Offre;
import fr.afpa.assosoft.entities.Personne;
import fr.afpa.assosoft.entities.Proposition;
import fr.afpa.assosoft.entities.Reference;
import fr.afpa.assosoft.entities.ReseauSocial;
import fr.afpa.assosoft.entities.Role;
import fr.afpa.assosoft.entities.Statut;
import fr.afpa.assosoft.entities.TypeDon;
import fr.afpa.assosoft.entities.TypeProp;
import fr.afpa.assosoft.entities.Ville;

@Service
@Transactional
public class AssosoftInitServiceImpl implements IAssosoftInitService {

	private final StatutRepository statutRepo;
	private final VilleRepository villeRepo;
	private final CategorieRepository categorieRepo;
	private final RoleRepository roleRepo;
	private final TypeDonRepository typeDonRepo;
	private final ContactRepository contactRepo;
	private final PersonneRepository personneRepo;
	private final AssociationRepository associationRepo;
	private final ReferenceRepository referenceRepo;
	private final ReseauSocialRepository reseauSocialRepo;
	private final LienReseauRepository lienReseauRepo;
	private final DonRepository donRepo;
	private final AdhesionRepository adhesionRepo;
	private final MediaRepository mediaRepo;
	private final TypePropRepository typePropRepo;
	private final PropositionRepository propositionRepo;
	private final OffreRepository offreRepo;

	private final BCryptPasswordEncoder bCPE;

	public AssosoftInitServiceImpl(PersonneRepository personneRepo, StatutRepository statutRepo, VilleRepository villeRepo, CategorieRepository categorieRepo, RoleRepository roleRepo, TypeDonRepository typeDonRepo, ContactRepository contactRepo, AssociationRepository associationRepo, PropositionRepository propositionRepo, ReferenceRepository referenceRepo, ReseauSocialRepository reseauSocialRepo, OffreRepository offreRepo, BCryptPasswordEncoder bCPE, LienReseauRepository lienReseauRepo, DonRepository donRepo, TypePropRepository typePropRepo, AdhesionRepository adhesionRepo, MediaRepository mediaRepo) {
		this.personneRepo = personneRepo;
		this.statutRepo = statutRepo;
		this.villeRepo = villeRepo;
		this.categorieRepo = categorieRepo;
		this.roleRepo = roleRepo;
		this.typeDonRepo = typeDonRepo;
		this.contactRepo = contactRepo;
		this.associationRepo = associationRepo;
		this.propositionRepo = propositionRepo;
		this.referenceRepo = referenceRepo;
		this.reseauSocialRepo = reseauSocialRepo;
		this.offreRepo = offreRepo;
		this.bCPE = bCPE;
		this.lienReseauRepo = lienReseauRepo;
		this.donRepo = donRepo;
		this.typePropRepo = typePropRepo;
		this.adhesionRepo = adhesionRepo;
		this.mediaRepo = mediaRepo;
	}

	@Override
	public void initStatuts() {
		Stream
				.of("En attente", "Activé", "Desactivé", "Accepté", "Refusé")
				.forEach(valStat -> {
					Statut statut = new Statut();
					statut.setStatutValeur(valStat);
					statutRepo.save(statut);
				});
	}

	@Override
	public void initVilles() {
		Stream.of("Paris", "Nantes", "Lyon", "Marseille", "Strasbourg",
				"Toulouse", "Reims", "Orléans", "Valenciennes",
				"Lille", "Chartres", "Nice", "Arles", "Poitiers", "Belfort",
				"Bordeaux", "Rennes", "Troyes", "Grenoble",
				"Angers", "Caen", "Metz", "Dijon", "Saint-Étienne", "Brest",
				"Nancy", "Rouen", "Le Havre", "Limoges",
				"Toulon", "Montpellier", "Tours", "Ajaccio", "Amiens",
				"Cannes").forEach(nomVille -> {
					Ville ville = new Ville();
					ville.setVilleNom(nomVille);
					villeRepo.save(ville);
				});
	}

	@Override
	public void initCategories() {
		Stream.of("Humanitaire", "Tourisme", "Sportive", "Culturelle")
				.forEach(intitCat -> {
					Categorie categorie = new Categorie();
					categorie.setCatIntitule(intitCat);
					categorieRepo.save(categorie);
				});
	}

	@Override
	public void initRoles() {
		Stream.of("Administrateur plateforme",
				"Administrateur association", "Adhérent")
				.forEach(intitRole -> {
					Role role = new Role();
					role.setRoleIntitule(intitRole);
					roleRepo.save(role);
				});
	}

	@Override
	public void initTypesDons() {
		Stream.of("Financement", "Matériel").forEach(typeDonDesc -> {
			TypeDon typeDon = new TypeDon();
			typeDon.setTypeDonDesc(typeDonDesc);
			typeDonRepo.save(typeDon);
		});
	}

	@Override
	public void initContacts() {
		Stream.of("Clim2000", "TraiteurXXL", "RavalementExpress",
				"CostumesGratos", "VoituresLocation", "Interim24/7",
				"CinemaPlaces", "Motoblouz").forEach(libelleCont -> {
					Contact contact = new Contact();
					contact.setContactLibelle(libelleCont);
					contact.setContactMail(
							libelleCont.trim().toLowerCase() + "@gmail.com");
					Statut statut = statutRepo.getOne((long) 1);
			        Random rand = new Random();

			        List<Ville> villes = villeRepo.findAll();
					int k = rand.nextInt(villes.size()-1);
					contact.setStatut(statut);
					contact.setVille(villeRepo.getOne((long) k+1));
					contactRepo.save(contact);
				});
	}

	@Override
	public void initPersonnes() {
		String[] prenoms = { "James", "Louis", "Daniel", "France",
				"Sylvie", "Umberto", "Lino", "Nancy", "Bob", "Louis",
				"David", "Jeanne", "Charles", "Blaise", "Victor", "Claude",
				"Louis", "Marie", "Rosalind", "Patrick",
				"Edith", "Brigitte", "Francoise", "Jules", "Albert", "Indira",
				"Andy", "Donald", "Frank", "George",
				"Pablo", "Marie", "Charlie", "Coco", "Claude", "Maurice",
				"Hector", "Charles", "Henri", "Paul" };
		String[] noms = { "Bond", "De Funes", "Prevert", "Gall", "Vartan",
				"Eco", "Ventura", "Malone", "Marley",
				"Armstrong", "Bowie", "Moreau", "De Gaulle", "Pascal", "Hugo",
				"Monet", "Pasteur", "Curie", "Franklin",
				"Bruel", "Piaf", "Bardot", "Hardy", "Verne", "Einstein",
				"Ghandi", "Warhol", "Trump", "Sinatra", "Sand",
				"Picasso", "Antoinette", "Chaplin", "Chanel", "Debussy",
				"Ravel", "Berlioz", "Gounod", "Matisse",
				"Gauguin" };
		List<Ville> villes = villeRepo.findAll();
		Statut statut = statutRepo.getOne((long) 2);

		for (int i = 0; i < noms.length; i++) {
			Personne personne = new Personne();
			String nom = noms[i];
			String prenom = prenoms[i];
			personne.setPersonneNom(nom);
			personne.setPersonnePrenom(prenom);
			personne.setPersonneLogin(
					(prenom + nom).replace(" ", "").toLowerCase());
			// personne.setPersonneMdp(strAleatoire(10));
			
			// Encodage du mot de passe
			personne.setPersonneMdp(
					bCPE.encode(personne.getPersonneLogin())
				);
			personne.setPersonneMail(creaEmail(prenom, nom));
			personne.setVille(villeAleatoire(villes));
			personne.setStatut(statut);
			if (i < 1) {
				Role role = roleRepo.getOne((long) 1);
				personne.setRole(role);
			} else if (i < 7) {
				Role role = roleRepo.getOne((long) 2);
				personne.setRole(role);
			} else {
				Role role = roleRepo.getOne((long) 3);
				personne.setRole(role);
			}
			personneRepo.save(personne);
		}
	}

	@Override
	public void initAssociations() {
		String[] assoNoms = { "PC Sans Frontieres", "Parisis-Sport FC",
				"CampingPong", "Le Benevole Informatique",
				"Le Sport Pour Tous", "Fondation Cult Club" };
		Categorie catHum = categorieRepo.getOne((long) 1);
		Categorie catTour = categorieRepo.getOne((long) 2);
		Categorie catSport = categorieRepo.getOne((long) 3);
		Categorie catCult = categorieRepo.getOne((long) 4);
		Categorie[] categories = { catHum, catSport, catTour, catHum,
				catSport, catCult };
		Ville ville1 = villeRepo.getOne((long) 1);
		Ville ville2 = villeRepo.getOne((long) 2);
		Ville ville3 = villeRepo.getOne((long) 3);
		Ville ville4 = villeRepo.getOne((long) 4);
		Ville[] villes = { ville1, ville2, ville3, ville4, ville1,
				ville2 };
		Personne[] admins = new Personne[assoNoms.length];
		for (int i = 0; i < assoNoms.length; i++) {
			admins[i] = personneRepo.getOne((long) (i + 2));
		}
		for (int i = 0; i < assoNoms.length; i++) {
			Association association = new Association();
			association.setAssoNom(assoNoms[i]);
			association.setCategorie(categories[i]);
			association.setAssoNumRNA("RNA" + strAleatoire(7));
			association.setAssoMail(creaEmail(assoNoms[i]));
			association.setAssoTel(creaTel());
			association.setAssoUrl(creaUrl(assoNoms[i]));
			association.setVille(villes[i]);
			association.setAdmin(admins[i]);
			Statut statut = statutRepo.getOne((long) 2);
			association.setStatut(statut);
			associationRepo.save(association);
		}
	}

	@Override
	public void initReferences() {
		List<Contact> contacts = contactRepo.findAll();
		Random rand = new Random();
		for (Association asso : associationRepo.findAll()) {
			int k = rand.nextInt(contacts.size());
			for (int i = 0; i < k; i++) {
				Reference reference = new Reference();
				reference.setAssociation(asso);
				reference.setContact(contacts.get(i));
				referenceRepo.save(reference);
			}
		}
	}

	@Override
	public void initReseauxSociaux() {
		Stream.of("Facebook", "Instagram", "Twitter", "YouTube")
				.forEach(resNom -> {
					ReseauSocial resSoc = new ReseauSocial();
					resSoc.setReseauNom(resNom);
					reseauSocialRepo.save(resSoc);
				});
	}

	@Override
	public void initLiensReseau() {
		List<ReseauSocial> reseaux = reseauSocialRepo.findAll();
		Random rand = new Random();
		for (Association asso : associationRepo.findAll()) {
			int k = rand.nextInt(reseaux.size());
			for (int i = 0; i < k; i++) {
				LienReseau lienReseau = new LienReseau();
				lienReseau.setAssociation(asso);
				lienReseau.setReseauSocial(reseaux.get(i));
				String url = "http://"
						+ reseaux.get(i).getReseauNom().toLowerCase() + ".com/"
						+ asso.getAssoNom().replace(" ", "").toLowerCase();
				lienReseau.setLienReseauUrl(url);
				lienReseauRepo.save(lienReseau);
			}
		}

	}

	@Override
	public void initDons() {
		List<Personne> personnes = personneRepo.findAll();
		List<Association> associations = associationRepo.findAll();
		Stream.of("Reception partie heritage", "Paiement par carte",
				"Paiement CB", "Virement bancaire", "Chèque",
				"Espèces").forEach(donDesc -> {
					Don don = new Don();
					TypeDon type = typeDonRepo.getOne((long) 1);
					don.setTypeDon(type);
					don.setDonDescription(donDesc);
					don.setDonDate(new Date());
					don.setPersonne(persoAleatoire(personnes));
					don.setAssociation(assoAleatoire(associations));
					Statut statut = statutRepo.getOne((long) 4);
					don.setStatut(statut);
					donRepo.save(don);
				});
	}

	@Override
	public void initAdhesions() {
		List<Association> associations = associationRepo.findAll();
		List<Personne> personnes = personneRepo.findAll();
		Random rand = new Random();
		for (int i = 7; i < personnes.size(); i++) {
			int k = rand.nextInt(associations.size());
			for (int j = 0; j < k; j++) {
				Adhesion adhesion = new Adhesion();
				adhesion.setPersonne(personnes.get(i));
				adhesion.setAssociation(associations.get(j));
				adhesionRepo.save(adhesion);
			}
		}
	}

	@Override
	public void initMedias() {
		List<Association> associations = associationRepo.findAll();
		int nbTitreAsso = 1;
		for (int i = 0; i < associations.size(); i++) {
			Media media = new Media();
			media.setAssociation(associations.get(i));
			String mediaTitre = "asso" + nbTitreAsso;
			media.setMediaTitre(mediaTitre);
			media.setMediaUrl(
					System.getProperty("user.home")
							+ "/assosoft_v3/images/association/" + mediaTitre
							+ ".jpg");
			media.setMediaPath("/images/association/asso"
					+ mediaTitre.substring(4) + ".jpg");
			mediaRepo.save(media);
			nbTitreAsso++;
		}

		int nbTitrePers = 1;
		List<Personne> personnes = personneRepo.findAll();
		for (int i = 0; i < personnes.size(); i++) {
			Media media = new Media();
			media.setPersonne(personnes.get(i));
			String mediaTitre = "pers" + nbTitrePers;
			media.setMediaTitre(mediaTitre);
			media.setMediaUrl(System.getProperty("user.home")
					+ "/assosoft/images/personne/" + mediaTitre + ".jpg");
			media.setMediaPath("/images/association/pers"
					+ mediaTitre.substring(4) + ".jpg");
			mediaRepo.save(media);
			nbTitrePers++;
		}
	}

	@Override
	public void initTypesProp() {
		TypeProp typeProp = new TypeProp();
		typeProp.setTypePropositionValeur("Activité");
		typePropRepo.save(typeProp);
		typeProp = new TypeProp();
		typeProp.setTypePropositionValeur("Service");
		typePropRepo.save(typeProp);
	}

	@Override
	public void initPropositions() {
		List<TypeProp> typesProp = typePropRepo.findAll();
		Proposition p = new Proposition();
		p.setPropositionNom("Location salle");
		p.setPropositionPrix(500.00);
		p.setTypeProp(typesProp.get(1));
		propositionRepo.save(p);
		p = new Proposition();
		p.setPropositionNom("Location stade");
		p.setPropositionPrix(2500.00);
		p.setTypeProp(typesProp.get(1));
		propositionRepo.save(p);
		p = new Proposition();
		p.setPropositionNom("Location materiel sportif");
		p.setPropositionPrix(250.00);
		p.setTypeProp(typesProp.get(1));
		propositionRepo.save(p);
		p = new Proposition();
		p.setPropositionNom("Location salle des fetes");
		p.setPropositionPrix(1500.00);
		p.setTypeProp(typesProp.get(1));
		propositionRepo.save(p);
		p = new Proposition();
		p.setPropositionNom("Cours particulier Anglais");
		p.setPropositionPrix(30.00);
		p.setTypeProp(typesProp.get(1));
		propositionRepo.save(p);
		p = new Proposition();
		p.setPropositionNom("Réparation PC");
		p.setPropositionPrix(120.00);
		p.setTypeProp(typesProp.get(1));
		propositionRepo.save(p);
		p = new Proposition();
		p.setPropositionNom("Privatisation Discothèque");
		p.setPropositionPrix(3500.00);
		p.setTypeProp(typesProp.get(1));
		propositionRepo.save(p);
	}

	@Override
	public void initOffres() {
		List<Proposition> propositions = propositionRepo.findAll();
		List<Association> associations = associationRepo.findAll();
		Offre offre = new Offre();
		offre.setAssociation(associations.get(0));
		offre.setProposition(propositions.get(5));
		offreRepo.save(offre);
		offre = new Offre();
		offre.setAssociation(associations.get(1));
		offre.setProposition(propositions.get(1));
		offreRepo.save(offre);
		offre = new Offre();
		offre.setAssociation(associations.get(2));
		offre.setProposition(propositions.get(4));
		offreRepo.save(offre);
		offre = new Offre();
		offre.setAssociation(associations.get(3));
		offre.setProposition(propositions.get(0));
		offreRepo.save(offre);
		offre = new Offre();
		offre.setAssociation(associations.get(4));
		offre.setProposition(propositions.get(2));
		offreRepo.save(offre);
		offre = new Offre();
		offre.setAssociation(associations.get(5));
		offre.setProposition(propositions.get(4));
		offreRepo.save(offre);
	}

	private String strAleatoire(int n) {
		String mdp = "";
		Random rand = new Random();
		String caracters = "abcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < n; i++) {
			int k = rand.nextInt(caracters.length());
			mdp += caracters.charAt(k);
		}
		return mdp;
	}

	private String creaEmail(String prenom, String nom) {
		Random rand = new Random();
		String[] domaines = { "gmail.com", "orange.fr", "sfr.fr",
				"yahoo.fr", "hotmail.com" };
		return (prenom + nom).replace(" ", "").toLowerCase() + "@"
				+ domaines[rand.nextInt(5)];
	}

	private String creaEmail(String nom) {
		Random rand = new Random();
		String[] domaines = { "gmail.com", "orange.fr", "sfr.fr",
				"yahoo.fr", "hotmail.com" };
		return (nom).replace(" ", "").toLowerCase() + "@"
				+ domaines[rand.nextInt(5)];
	}

	private Ville villeAleatoire(List<Ville> villes) {
		Random rand = new Random();
		int k = rand.nextInt(villes.size());
		return villes.get(k);
	}

	private Association assoAleatoire(List<Association> associations) {
		Random rand = new Random();
		int k = rand.nextInt(associations.size());
		return associations.get(k);
	}

	private Personne persoAleatoire(List<Personne> personnes) {
		Random rand = new Random();
		int k = rand.nextInt(personnes.size());
		return personnes.get(k);
	}

	private String creaTel() {
		String tel = "08";
		Random rand = new Random();
		String nombres = "0123456789";
		for (int i = 0; i < 8; i++) {
			int k = rand.nextInt(10);
			tel += nombres.charAt(k);
		}
		return tel;
	}

	private String creaUrl(String nom) {
		return "http://"+nom.replace(" ", "").toLowerCase() + ".fr";
	}

}
