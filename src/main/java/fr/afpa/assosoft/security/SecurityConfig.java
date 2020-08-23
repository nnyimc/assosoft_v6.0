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
import org.springframework.security.web.session.HttpSessionEventPublisher;

// Classe permettant de personnaliser Spring Security
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean // Annotation Spring permettant l'accès à la méthode par l'ensemble des classes
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
    
	@Bean 
	//  Permet de signaler la fermeture de la session en cours au registre de Spring 
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	private final DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		try {
			
			/*
			 * auth.inMemoryAuthentication().withUser("jamesbond").password(getBCPE().
			 * encode("6bdv6a3y6s")) .roles("Administrateur plateforme");
			 * auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
			 */
			 
			
			  auth.jdbcAuthentication() .dataSource(dataSource) .usersByUsernameQuery(
			  "select personne_login as principal, personne_mdp as credentials," +
			  "statut_id from personne where personne_login=? and statut_id =2"
			  ) .authoritiesByUsernameQuery(
			  "select personne_login as principal, r.role_intitule as role from personne as p, role as r"
			  + " where personne_login = ? and p.role_id = r.role_id")
			  .rolePrefix("ROLE_") .passwordEncoder(getBCPE());
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		try {
			httpSec.formLogin().loginPage("/login")
				.defaultSuccessUrl("/dashboard")
				.failureUrl("/login?error")
				.usernameParameter("username").passwordParameter("password")
					.and()
				.authorizeRequests()
				.antMatchers("/dashboard", "/dashboard?*").hasRole("Administrateur plateforme")
					.and()
				.authorizeRequests()
				.antMatchers("/dashboard_2", "dashboard_2?*").hasRole("Administrateur association")
					.and()
			    .exceptionHandling().accessDeniedPage("/403")
			    	.and()
			    .logout().logoutSuccessUrl("/login?logout"); 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
