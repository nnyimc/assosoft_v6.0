package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

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

@Entity
@Table

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactId;
	@Column(length = 100, nullable = false, unique = true)
	private String contactLibelle;
	@Column(length = 100, nullable = false, unique = true)
	private String contactMail;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "villeId")
	private Ville ville;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "statutId")
	@NotNull
	private Statut statut;
	@OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE)
	private Collection<Reference> references;

}
