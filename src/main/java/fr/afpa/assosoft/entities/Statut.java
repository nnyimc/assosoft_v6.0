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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statut implements Serializable {

	@Id
	@Column(name="statut_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long statutId;
	@Column(length = 40, nullable = false, unique = true)
	private String statutValeur;
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Association> associations;
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Personne> personnes;
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Contact> contacts;
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Don> dons;

}
