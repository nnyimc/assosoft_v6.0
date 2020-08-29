package fr.afpa.assosoft.security;

import javax.sql.DataSource;

import fr.afpa.assosoft.dao.PersonneRepository;
import fr.afpa.assosoft.service.PersonneDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.HashMap;


// Classe permettant de personnaliser Spring Security
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final HashMap<String, String> roleToPath =
			new HashMap<String, String>(){{
				put("Administrateur plateforme", "/dashboard");
				put("Administrateur association", "/dashboard_2");
				put("Adhérent", "/dashboard_3");
			}};


	private final PersonneRepository personneRepository;
	private final DataSource dataSource;
	private final PersonneDetailsService personneDetailsService;

	public SecurityConfig(
						  DataSource dataSource,
						  PersonneRepository personneRepository,
						  PersonneDetailsService personneDetailsService) {
		this.dataSource = dataSource;
		this.personneRepository = personneRepository;
		this.personneDetailsService = personneDetailsService;
	}


	@Bean
	// Annotation Spring permettant l'accès à la méthode par l'ensemble des classes
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(personneDetailsService);
		authenticationProvider.setPasswordEncoder(getBCPE());
		return authenticationProvider;
	}

	@Bean 
	//  Permet de signaler la fermeture de la session en cours au registre de Spring 
	HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}

	@Bean
	AuthenticationSuccessHandler successfulAuthenticationHandler() {
		return new SuccessfulAuthenticationHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		try {
			
			/*
			 * auth.inMemoryAuthentication().withUser("jamesbond").password(getBCPE().
			 * encode("6bdv6a3y6s")) .roles("Administrateur plateforme");
			 * auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
			 */
			 
			/*
			  auth.jdbcAuthentication()
					  .dataSource(dataSource)
					  .usersByUsernameQuery(
			  "select personne_login as principal, personne_mdp as credentials," +
			  "statut_id from personne where personne_login=? and statut_id =2"
					  )
					  .authoritiesByUsernameQuery(
			  "select personne_login as principal, r.role_intitule as role from personne as p, role as r"
			  + " where personne_login = ? and p.role_id = r.role_id"
					  )
					  .rolePrefix("ROLE_")
					  .passwordEncoder(getBCPE());
			 */

			auth.authenticationProvider(authenticationProvider());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		try {
			/*httpSec.formLogin().loginPage("/login")
				.defaultSuccessUrl("/dashboard")
				.failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password")
					.and()
				.authorizeRequests()
				.antMatchers("/dashboard", "/dashboard?*").hasRole("Administrateur plateforme")
					.and()
			    .exceptionHandling().accessDeniedPage("/403")
			    	.and()
			    .logout().logoutSuccessUrl("/login?logout");
			*/

			httpSec.formLogin()
					.loginPage("/login")
					.successHandler(successfulAuthenticationHandler())
					.failureUrl("/login?error")
					/*	.and()
					.authorizeRequests()
					.antMatchers("/dashboard", "/dashboard?*")
					.hasAuthority("Administrateur plateforme")
					.antMatchers("/dashboard_2", "/dashboard_2?*")
					.hasAuthority("Administrateur association")
					.antMatchers("/dashboard_3", "/dashboard_3?*")
					.hasAuthority("Adhérent") */
						.and()
					.logout().logoutSuccessUrl("/login?logout")
						.and()
					.exceptionHandling().accessDeniedPage("/403");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}



}
