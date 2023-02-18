package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personne implements Serializable {

	private static final long serialVersionUID = 5214452032992777838L;

	@Id
	@Column(name = "personne_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "personne_nom", length = 200, nullable = false)
	@NotEmpty
	@Size(min = 1, max = 200)
	private String nom;
	
	@Column(name = "personne_prenom", length = 100, nullable = false)
	@NotEmpty
	@Size(min = 1, max = 100)
	private String prenom;
	
	@Column(name = "personne_identifiant", length = 100, nullable = false, unique = true)
	@NotEmpty
	@Size(min = 5, max = 100)
	private String login;
	
	@Column(name = "personne_mot_de_passe",length = 20, nullable = false)
	@NotEmpty
	@Size(min = 8, max = 255)
	private String motDePasse;
	
	@Transient
	private String motDePasseVerification;
	
	@Column(length = 200, nullable = false, unique = true)
	@NotEmpty
	@Email
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statut_id")
	@NotNull
	@ToString.Exclude
	private Statut statut;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adresse_id")
	@NotNull
	@ToString.Exclude
	private Adresse adresse;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_id")
	@NotNull
	@ToString.Exclude
	private Role role;
	
	@OneToMany(mappedBy = "personne", cascade = CascadeType.MERGE)
	private Collection<Adhesion> adhesions;
	
	@OneToMany(mappedBy = "personne", cascade = CascadeType.MERGE)
	private Collection<Media> medias;
	
	@OneToMany(mappedBy = "personne", cascade = CascadeType.MERGE)
	private Collection<Don> dons;

}
