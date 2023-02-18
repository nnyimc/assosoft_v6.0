package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proposition implements Serializable {

	private static final long serialVersionUID = -7764228985417842643L;
	
	@Id
	@Column(name = "proposition_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "proposition_nom",length = 100, nullable = false, unique = true)
	private String nom;
	
	@DecimalMin(value = "0.01", inclusive = true)
	@DecimalMax(value = "9999.99", inclusive = true)
	private double prix;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_proposition_id")
	@NotNull
	private TypeProposition typeProposition;
	
	@OneToMany(mappedBy = "proposition", cascade = CascadeType.MERGE)
	private Collection<Offre> offres;
}
