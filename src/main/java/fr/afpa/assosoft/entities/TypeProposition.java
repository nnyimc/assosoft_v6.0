package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type_proposition")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeProposition implements Serializable {

	private static final long serialVersionUID = -119252794891219439L;

	@Id
	@Column(name = "type_proposition_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typePropositionId;

	@Column(length = 80, nullable = false, unique = true)
	private String typePropositionNom;

	@OneToMany(mappedBy = "typeProposition", cascade = CascadeType.MERGE)
	private Collection<Proposition> propositions;

}
