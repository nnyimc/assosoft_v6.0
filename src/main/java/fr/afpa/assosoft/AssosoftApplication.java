/*
 * Initialisation de l'application
 * La commande run permet d'éxecuter une série de commande au démarrage de Spring Boot
 * Lancement d'algorithme permettant de générer l'ensemble des tables 
 * de la base de données.
 * 
 */

package fr.afpa.assosoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import fr.afpa.assosoft.service.IAssosoftInitService;

@SpringBootApplication
public class AssosoftApplication extends SpringBootServletInitializer implements CommandLineRunner {

	// Annotation Spring - effectue l'injection des dépendances
	final IAssosoftInitService assosoftInitService;

	public AssosoftApplication(IAssosoftInitService assosoftInitService) {
		this.assosoftInitService = assosoftInitService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AssosoftApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		assosoftInitService.initStatuts();
		assosoftInitService.initVilles();
		assosoftInitService.initCategories();
		assosoftInitService.initRoles();
		assosoftInitService.initTypesDons();
		assosoftInitService.initContacts();
		assosoftInitService.initPersonnes();
		assosoftInitService.initAssociations();
		assosoftInitService.initReferences();
		assosoftInitService.initReseauxSociaux();
		assosoftInitService.initLiensReseau();
		assosoftInitService.initDons();
		assosoftInitService.initAdhesions();
		assosoftInitService.initMedias();
		assosoftInitService.initTypesProp();
		assosoftInitService.initPropositions();
		assosoftInitService.initOffres();
	}

}
