//Controller Spring MVC dédié au front de l'application.
package fr.afpa.assosoft.web;

import org.springframework.ui.Model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.afpa.assosoft.beans.InscriptionAsso;
import fr.afpa.assosoft.entities.Adhesion;
import fr.afpa.assosoft.entities.Association;
import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Personne;
import fr.afpa.assosoft.entities.Role;
import fr.afpa.assosoft.entities.Statut;
import fr.afpa.assosoft.entities.Ville;
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
		List<Ville> listeLocalites = assoService.recupererLocalites();
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
		List<Ville> listeLocalites = assoService.recupererLocalites();
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

	// Methode retournant une vue de la page d'accueil
	@GetMapping({ "/", "/index" })
	public String afficherHomepage(Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "contenuRecherche", defaultValue = "") String contenuRecherche,
			@RequestParam(name = "localite", defaultValue = "") String localite,
			@RequestParam(name = "categorie", defaultValue = "") String categorie) {

		// Initialisation de la page d'accueil
		Page<Association> listeAsso = assoService.listerAsso(page - 1, 3);
		if (!localite.contentEquals("")) {
			List<Categorie> listeCategories = assoService
					.recupererCategoriesVille(localite);
			if (!categorie.contentEquals("")) {
				listeAsso = assoService.rechercherCategorieLocalite(localite,
						categorie, page - 1, 3);
			} else {
				listeAsso = assoService
						.rechercherLocalite("%" + localite + "%", page - 1, 3);
			}
			traitementRecherches(model, listeCategories, localite,
					categorie);
			paginer(model, listeAsso, page);
			checkListStatus(listeAsso, model);
			return "index";
		} else if (!contenuRecherche.contentEquals("")) {
			listeAsso = assoService.rechercherNomCateg(
					"%" + contenuRecherche + "%", page - 1, 3);
			checkListStatus(listeAsso, model);
		} else if (!categorie.contentEquals("")) {
			listeAsso = assoService
					.rechercherCategorie("%" + categorie + "%", page - 1, 3);
			checkListStatus(listeAsso, model);
		}
		paginer(model, listeAsso, page);
		traitementRecherches(model);
		
		// Affichage du lien "Connexion" dans la barre de navigation
		int mode = 1;
		model.addAttribute("authValue", mode);
		return "index";
	}

	@GetMapping({ "/inscription" })
	public String afficherInscription(Model model) {
		traitementRecherches(model);
		int mode = 0;
		model.addAttribute("authValue", mode);
		model.addAttribute("inscriptionAsso", new InscriptionAsso());
		return "inscription";
	}
	
	
	@GetMapping({ "/login" })
	public String demarrerAuth(Model model, @RequestParam(required = false, name = "logout") String logout) {
		traitementRecherches(model);
		if (logout != null){
			int mode = 1;
			model.addAttribute("authValue", mode);
			return "redirect:/";
	    } else {
			int mode = 0;
			model.addAttribute("authValue", mode);
			return "login";
		}
	}
	
	@PostMapping({"/login"})
	public String traitementAuth(Model model) {
		traitementRecherches(model);
		return "dashboard";
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
	
	@GetMapping({ "/dashboard" })
	public String afficherDashboard(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
		Page<Association> listeAsso = assoService.listerAsso(page - 1, 4);
		int mode = 0;
		model.addAttribute("authValue", mode);
		model.addAttribute("listeAsso", listeAsso);
		traitementRecherches(model);
		paginer(model, listeAsso, page);
		return "dashboard";
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
	public String assoDetail(Model model, Long id) {
		Association association = assoService.recupererAsso(id);
		model.addAttribute("assos", association);
		traitementRecherches(model);
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
	public String form(Model model, Long id) {
		Association association = assoService.recupererAsso(id);
		model.addAttribute("assos", association);
		model.addAttribute("pers", new Personne());
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
		Ville ville = association.getVille();
		pers.setVille(ville);
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
