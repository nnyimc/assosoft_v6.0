package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

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
public class Contact implements Serializable {

	@Id
	@Column(name = "contact_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactId;
	@Column(length = 100, nullable = false, unique = true)
	private String contactLibelle;
	@Column(length = 100, nullable = false, unique = true)
	private String contactMail;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ville_id")
	private Ville ville;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statut_id")
	@NotNull
	private Statut statut;
	@OneToMany(mappedBy = "contact", cascade = CascadeType.MERGE)
	private Collection<Reference> references;

}
