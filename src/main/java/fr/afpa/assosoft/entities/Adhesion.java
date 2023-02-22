/*
 * 1ere partie du mapping objet relationnel
 * Ce Plain Old Java Object (POJO) représente une adhésion. Par POJO on sous-entend qu'il se suffit à lui même 
 * et ne nécessite pas d'implementer une interface ou étendre une classe mère.  
 * 
 */
package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Annotation JPA - permet de créer la table ADHESION comme une entité
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "association_id", "personne_id" }))

/*
 * Annotations Lombok - permettent de générer les constructeurs de la classe et
 * de définir les contraintes d'unicité
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adhesion implements Serializable {

	private static final long serialVersionUID = 8861212455020453690L;

	/*
	 * Annotations JPA - permettent de désigner la clé primaire Auto-incrémentation
	 * de la valeur de l'id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Mise en place des cardinalités
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "association_id")
	@NotNull
	private Association association;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personne_id")
	@NotNull
	private Personne personne;
}
