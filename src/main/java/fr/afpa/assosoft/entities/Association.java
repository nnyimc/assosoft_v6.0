package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Association implements Serializable {

	@Id
	@Column(name ="asso_id")
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
	@JoinColumn(name = "cat_id")
	@NotNull
	@ToString.Exclude
	private Categorie categorie;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personne_id")
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
