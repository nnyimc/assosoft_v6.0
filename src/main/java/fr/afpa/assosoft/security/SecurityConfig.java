package fr.afpa.assosoft.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// Classe permettant de personnaliser Spring Security
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean // Annotation Spring permettant l'accès à la méthode par l'ensemble des classes
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		try {
			
			/*
			 * auth.inMemoryAuthentication().withUser("jamesbond").password(getBCPE().
			 * encode( "6bdv6a3y6s")) .roles("Administrateur plateforme");
			 * auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
			 */
			 
			
			  auth.jdbcAuthentication() .dataSource(dataSource) .usersByUsernameQuery(
			  "select personne_login as principal, personne_mdp as credentials," +
			  "statut_statut_id from personne where personne_login=? and statut_statut_id =2"
			  ) .authoritiesByUsernameQuery(
			  "select personne_login as principal, r.role_intitule as role from personne, role as r"
			  + " where personne_login = ? and role_role_id = r.role_id")
			  .rolePrefix("ROLE_") .passwordEncoder(getBCPE());
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.formLogin().loginPage("/login");
			http.authorizeRequests().antMatchers("/dashboard")
					.hasRole("Administrateur plateforme");
			http.authorizeRequests().antMatchers("/dashboard?level=2")
					.hasRole("Administrateur association");
			http.exceptionHandling().accessDeniedPage("/403");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
