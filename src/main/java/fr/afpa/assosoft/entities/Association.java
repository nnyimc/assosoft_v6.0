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

	private static final long serialVersionUID = 3937504825502014354L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(length = 100, nullable = false, unique = true)
	private String nom;
	
	@Column(length = 40, nullable = false, unique = true)
	private String numRNA;
	
	@Column(length = 100, nullable = false, unique = true)
	private String mail;
	
	@Column(length = 20, unique = true)
	private String telephone;
	
	@Column(length = 100)
	private String url;
	
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
	@JoinColumn(name = "categorie_id")
	@NotNull
	@ToString.Exclude
	private Categorie categorie;
	
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
	private Collection<LienReseauSocial> liensReseau;
	
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<ContactProfessionnel> contactsProfessinnels;
	
	@OneToMany(mappedBy = "association", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Don> dons;
}
