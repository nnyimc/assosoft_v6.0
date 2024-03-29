//Controller Spring MVC dédié au front de l'application.
package fr.afpa.assosoft.web;

import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import fr.afpa.assosoft.beans.InscriptionAsso;
import fr.afpa.assosoft.entities.Adhesion;
import fr.afpa.assosoft.entities.Association;
import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Personne;
import fr.afpa.assosoft.entities.Role;
import fr.afpa.assosoft.entities.Statut;
import fr.afpa.assosoft.entities.Adresse;
import fr.afpa.assosoft.service.IAssociationService;

@Controller
public class AssosoftController {

	@Autowired
	// Injection des dépendances qui permet de faire le lien avec la couche métier
	private IAssociationService assoService;

	/*
	 * Cette fonction permet d'effectuer des recherches selon les valeurs qui lui
	 * sont transmises et stocke les résultats dans le modèle via ses attributs
	 */

	private void traitementRecherches(Model model) {
		String contenuRecherche = "", localite = "", categorie = "";
		List<Adresse> listeLocalites = assoService.recupererLocalites();
		List<Categorie> listeCategories = assoService
				.recupererCategories();
		model.addAttribute("contenuRecherche", contenuRecherche);
		model.addAttribute("localiteChoisie", localite);
		model.addAttribute("categorieChoisie", categorie);
		model.addAttribute("listeLocalites", listeLocalites);
		model.addAttribute("listeCategories", listeCategories);
	}

	private void traitementRecherches(Model model,
			List<Categorie> listeCategories, String localite,
			String categorie) {
		String contenuRecherche = "";
		List<Adresse> listeLocalites = assoService.recupererLocalites();
		model.addAttribute("contenuRecherche", contenuRecherche);
		model.addAttribute("localiteChoisie", localite);
		model.addAttribute("categorieChoisie", categorie);
		model.addAttribute("listeLocalites", listeLocalites);
		model.addAttribute("listeCategories", listeCategories);
	}

	private void paginer(Model model, Page<Association> listeAsso,
			int page) {
		model.addAttribute("listeAsso", listeAsso);
		int[] arrPages = new int[listeAsso.getTotalPages()];
		for (int i = 1; i <= arrPages.length; i++) {
			arrPages[i - 1] = i;
		}
		model.addAttribute("arrPages", arrPages);
		model.addAttribute("currentPage", page);
	}

	/*
	 * Si aucun résultat de recherche n'est disponible la valeur 0 est renvoyée en
	 * plus de l'affichage de la page d'accueil
	 */

	private String checkListStatus(Page<Association> pageAsso,
			Model model) {
		if (pageAsso.isEmpty()) {
			int resultat = 0;
			model.addAttribute("noResults", resultat);
		}
		return "index";
	}
	
	private void gestionLiensNavBar(Model model, String referer) {
		// Affichage du lien "Connexion-Deconnexion" dans la barre de navigation
		int mode = 1;
		model.addAttribute("authValue", mode);
		
		// La valeur du referer influe sur l'affichage du lien "Connexion-Deconnexion"
		if(null == referer ) {
			referer = "none";
		}
		model.addAttribute("referer", referer);
	}

	// Méthode qui redirige vers le dashboard
	private void accederDashboard(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		Page<Association> listeAsso = assoService.listerAsso(page - 1, 12);
		int mode = 0;
		model.addAttribute("authValue", mode);
		model.addAttribute("listeAsso", listeAsso);
		traitementRecherches(model);
		paginer(model, listeAsso, page);
	}

	// Methode retournant une vue de la page d'accueil
	@GetMapping({ "/", "/index" })
	public String afficherHomepage(Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "contenuRecherche", defaultValue = "") String contenuRecherche,
			@RequestParam(name = "localite", defaultValue = "") String localite,
			@RequestParam(name = "categorie", defaultValue = "") String categorie,
			@RequestHeader(value = "referer", required = false) String referer) {

		// Initialisation de la page d'accueil
		Page<Association> listeAsso = assoService.listerAsso(page - 1, 6);
		if (!localite.contentEquals("")) {
			List<Categorie> listeCategories = assoService
					.recupererCategoriesadresse(localite);
			if (!categorie.contentEquals("")) {
				listeAsso = assoService.rechercherCategorieLocalite(localite,
						categorie, page - 1, 6);
			} else {
				listeAsso = assoService
						.rechercherLocalite("%" + localite + "%", page - 1, 3);
			}
			traitementRecherches(model, listeCategories, localite,
					categorie);
			paginer(model, listeAsso, page);
			gestionLiensNavBar(model, referer);
			checkListStatus(listeAsso, model);
			return "index";
		} else if (!contenuRecherche.contentEquals("")) {
			listeAsso = assoService.rechercherNomCateg(
					"%" + contenuRecherche + "%", page - 1, 6);
			checkListStatus(listeAsso, model);
		} else if (!categorie.contentEquals("")) {
			listeAsso = assoService
					.rechercherCategorie("%" + categorie + "%", page - 1, 3);
			checkListStatus(listeAsso, model);
		}
		paginer(model, listeAsso, page);
		traitementRecherches(model);
		gestionLiensNavBar(model, referer);
		return "index";
	}

	@GetMapping({ "/inscription" })
	public String afficherInscription(Model model, 
			@RequestHeader(value = "referer", required = false) String referer) {
		traitementRecherches(model);
		model.addAttribute("inscriptionAsso", new InscriptionAsso());
		gestionLiensNavBar(model, referer);
		return "inscription";
	}
	
	
	@GetMapping({ "/login" })
	public String demarrerAuth(Model model, 
			@RequestParam(required = false, name = "logout") String logout, 
			HttpSession httpSession, SessionStatus status){
		traitementRecherches(model);
		if (logout != null){
			// Indiquer que la session en cours est complète
			status.setComplete();

			// Rendre la session invalide et détruire ses propriétés
			httpSession.invalidate();
			return "redirect:/";
	    }
		return "login";	
	}
	
	@PostMapping({"/login"})
	public String traitementAuth(
			Model model,
			@RequestHeader(value = "referer", required = false) String referer
	) {
		traitementRecherches(model);
		gestionLiensNavBar(model, referer);
		return "login";
	}
	
	
	@GetMapping({"/dltAsso"})
	public String supprimerAsso(Long id) {
		assoService.supprimerAsso(id);
		return "redirect:dashboard";
	}
	
	@GetMapping({ "/403" })
	public String gererRestriction(Model model) {
		traitementRecherches(model);
		int mode = 1;
		model.addAttribute("authValue", mode);
		return "403";
	}
	
	@GetMapping({ "/dashboard"})
	public String afficherDashboard(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		accederDashboard(model, page);
		return "dashboard";
	}

	@GetMapping({ "/dashboard_2"})
	public String afficherDashboard2(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		accederDashboard(model, page);
		return "dashboard_2";
	}

	@PostMapping({ "/saveAssociation" })
	public String inscrireAdminAssoEtAsso(
			@Valid @ModelAttribute("inscriptionAsso") InscriptionAsso inscriptionAsso,
			@RequestParam("assoImage") MultipartFile assoImage,
			BindingResult bindingResult) throws IOException {
		if (bindingResult.hasErrors()) {
			return "inscription";
		}
		assoService.saveAssociation(inscriptionAsso, assoImage);
		return "redirect:index";
	}

	/* Méthode qui retourne un objet Association à la page assoDetail.html */
	@GetMapping("/assoDetail")
	public String assoDetail(Model model, Long id,
			@RequestHeader(value = "referer", required = false) String referer) {
		Association association = assoService.recupererAsso(id);
		model.addAttribute("asso", association);
		traitementRecherches(model);
		gestionLiensNavBar(model, referer);
		return "assoDetail";
	}

	@PostMapping({ "/saveAdherent" })
	public String inscrireAdherent(@Valid Personne personne,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "inscription";
		}
		assoService.saveAdherent(personne);
		return "redirect:index";
	}

	@GetMapping("/formAdherent")
	public String form(Model model, Long id,
			@RequestHeader(value = "referer", required = false) String referer) {
		Association association = assoService.recupererAsso(id);
		model.addAttribute("assos", association);
		model.addAttribute("pers", new Personne());
		gestionLiensNavBar(model, referer);
		traitementRecherches(model);
		return "formAdherent";
	}

	@PostMapping("/save")
	public String save(Model model, @Valid Personne pers,
			BindingResult bindingResult, Long id) {
		Association association = assoService.recupererAsso(id);
		Role roleadh = assoService.recupererRole(3L);
		pers.setRole(roleadh);
		Statut statutadh = assoService.recupererStatut(1L);
		pers.setStatut(statutadh);
		Adresse adresse = association.getAdresse();
		pers.setAdresse(adresse);
		model.addAttribute("assos", association);
		model.addAttribute("pers", pers);
		// if(bindingResult.hasErrors()) return "formAdherent";
		Adhesion adhesion = new Adhesion();
		adhesion.setAssociation(association);
		adhesion.setPersonne(pers);
		assoService.savePersonne(pers);
		traitementRecherches(model);
		return "assoDetail";
	}
}
