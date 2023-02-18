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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "statut")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statut implements Serializable {

	private static final long serialVersionUID = -6856242653573705490L;

	@Id
	@Column(name="statut_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 40, nullable = false, unique = true)
	private String nom;
	
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Association> associations;
	
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Personne> personnes;
	
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Prestataire> contacts;
	
	@OneToMany(mappedBy = "statut", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Don> dons;

}
