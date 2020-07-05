package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Association implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assoId;
	@Column(length = 100, nullable = false, unique = true)
	private String assoNom;
	@Column(length = 40, nullable = false, unique = true)
	private String assoNumRNA;
	@Column(length = 100, nullable = false, unique = true)
	private String assoMail;
	@Column(length = 20, unique = true)
	private String assoTel;
	@Column(length = 100)
	private String assoUrl;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "statutId")
	@NotNull
	@ToString.Exclude
	private Statut statut;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "villeId")
	@NotNull
	@ToString.Exclude
	private Ville ville;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "catId")
	@NotNull
	@ToString.Exclude
	private Categorie categorie;
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "personneId")
	@NotNull
	@ToString.Exclude
	private Personne admin;
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Adhesion> adhesions;
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Offre> offres;
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Media> medias;
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<LienReseau> liensReseau;
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Reference> references;
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Don> dons;
}
