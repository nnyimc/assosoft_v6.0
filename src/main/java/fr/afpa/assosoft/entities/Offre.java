package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offre implements Serializable {

	@Id
	@Column(name = "offre_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offreId;
	private Date dateDebut;
	private Date dateFin;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asso_id")
	private Association association;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proposition_id")
	private Proposition proposition;
}
