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
public class Adresse implements Serializable {

	private static final long serialVersionUID = -3743927134625383878L;

	@Id
	@Column(name = "adresse_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "adresse_numero", length = 10, nullable = false)
	private String numero;
	
	@Column(name = "adresse_rue", length = 250, nullable = false)
	private String rue;
	
	@Column(name = "adresse_complement", length = 100, nullable = false)
	private String complement;
	
	@Column(name = "adresse_code_postal", length = 20, nullable = false)
	private String codePostal;
	
	@Column(name = "adresse_ville", length = 250, nullable = false)
	private String ville;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Association> associations;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Personne> personnes;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Prestataire> prestataires;
}
