package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ville implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long villeId;
	@Column(length = 100, nullable = false, unique = true)
	private String villeNom;
	@OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Association> associations;
	@OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Personne> personnes;
	@OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Contact> contacts;

	public Ville(String nom) {
		villeNom = nom;
	}
}
