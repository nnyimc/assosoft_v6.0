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

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		assosoftInitService.initVilles();
		assosoftInitService.initStatuts();
		assosoftInitService.initReseauxSociaux();
		assosoftInitService.initCategories();
		assosoftInitService.initRoles();
		assosoftInitService.initTypesProp();
		assosoftInitService.initPropositions();
		assosoftInitService.initTypesDons();

		assosoftInitService.initContacts();
		assosoftInitService.initPersonnes();

		assosoftInitService.initAssociations();
		assosoftInitService.initMedias();
		assosoftInitService.initOffres();


		assosoftInitService.initLiensReseau();
		assosoftInitService.initDons();
		assosoftInitService.initAdhesions();

		assosoftInitService.initReferences();


	}

}
