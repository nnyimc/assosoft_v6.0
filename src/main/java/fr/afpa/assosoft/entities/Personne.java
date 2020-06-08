package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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
	// org.hibernate.PersistentObjectException:
	// detached entity passed to persist ---> Eviter CascadeType.ALL
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	@ToString.Exclude
	private Statut statut;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	@ToString.Exclude
	private Ville ville;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	@ToString.Exclude
	private Role role;
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Association> associationsAdmin;
	@OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Adhesion> adhesions;
	@OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Media> medias;
	@OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Don> dons;

}
