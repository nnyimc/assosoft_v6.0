/*
 * 1ere partie du mapping objet relationnel
 * Ce Plain Old Java Object (POJO) représente une adhésion. Par POJO on sous-entend qu'il se suffit à lui même 
 * et ne nécessite pas d'implementer une interface ou étendre une classe mère.  
 * 
 */
package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Annotation JPA - permet de créer la table ADHESION comme une entité
@Entity

/*
 * Annotations Lombok - permettent de générer les constructeurs de la classe et
 * de définir les contraintes d'unicité
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "association_asso_id", "personne_personne_id" }))
public class Adhesion implements Serializable {

	/*
	 * Annotations JPA - permettent de désigner la clé primaire Auto-incrémentation
	 * de la valeur de l'id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adhesionId;

	// Mise en place des cardinalités
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	// Annotation JSON - permet d'éviter les boucles infinies en lecture
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;

	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Personne personne;
}
