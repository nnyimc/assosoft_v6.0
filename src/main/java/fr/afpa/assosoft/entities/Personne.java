package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Personne implements Serializable {

	@Id
	@Column(name = "personne_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personneId;
	@Column(length = 100, nullable = false)
	@NotEmpty
	@Size(min = 2, max = 100)
	private String personneNom;
	@Column(length = 100, nullable = false)
	@NotEmpty
	@Size(min = 2, max = 100)
	private String personnePrenom;
	@Column(length = 100, nullable = false, unique = true)
	@NotEmpty
	@Size(min = 5, max = 100)
	private String personneLogin;
	@Column(length = 20, nullable = false)
	@NotEmpty
	@Size(min = 8, max = 255)
	private String personneMdp;
	@Transient
	private String personneMdpRepeat;
	@Column(length = 100, nullable = false, unique = true)
	@NotEmpty
	@Email
	private String personneMail;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statut_id")
	@NotNull
	@ToString.Exclude
	private Statut statut;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ville_id")
	@NotNull
	@ToString.Exclude
	private Ville ville;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_id")
	@NotNull
	@ToString.Exclude
	private Role role;
	@OneToMany(mappedBy = "admin", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Association> associationsAdmin;
	@OneToMany(mappedBy = "personne", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Adhesion> adhesions;
	@OneToMany(mappedBy = "personne", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Media> medias;
	@OneToMany(mappedBy = "personne", cascade = CascadeType.MERGE)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Don> dons;
	
	public Personne (String nom, String prenom, String login, String mdp,
			String mail, Ville ville, Statut statut, Role role) {
		personneNom = nom;
		personnePrenom = prenom;
		personneLogin = login;
        personneMdp = mdp;
        personneMail = mail;
        this.ville = ville;
        this.statut = statut;
        this.role = role;
	}

}
