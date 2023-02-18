/*
 * Initialisation de l'application
 * La commande run permet d'éxecuter une série de commande au démarrage de Spring Boot
 * Lancement d'algorithme permettant de générer l'ensemble des tables 
 * de la base de données.
 * 
 */

package fr.afpa.assosoft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AssosoftApplication extends SpringBootServletInitializer implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(AssosoftApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
