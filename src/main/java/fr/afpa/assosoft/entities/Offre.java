package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offre implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offreId;
	private Date dateDebut;
	private Date dateFin;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Association association;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Proposition proposition;
}
