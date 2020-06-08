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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proposition implements Serializable {

	public Proposition(String propositionNom, double propositionPrix, TypeProp typeProp) {
		propositionNom = this.propositionNom;
		propositionPrix = this.propositionPrix;
		typeProp = this.typeProp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long propositionId;
	@Column(length = 100, nullable = false, unique = true)
	private String propositionNom;
	@DecimalMin(value = "0.01", inclusive = true)
	@DecimalMax(value = "9999.99", inclusive = true)
	private double propositionPrix;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	private TypeProp typeProp;
	@OneToMany(mappedBy = "proposition", cascade = CascadeType.ALL)
	private Collection<Offre> offres;
}
