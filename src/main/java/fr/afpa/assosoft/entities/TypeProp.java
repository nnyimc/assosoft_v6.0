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
import lombok.ToString;

@Entity
@Table(name = "type_prop")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeProp implements Serializable {

	@Id
	@Column(name = "type_proposition_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typePropositionId;

	@Column(length = 80, nullable = false, unique = true)
	private String typePropositionValeur;

	@OneToMany(mappedBy = "typeProp", cascade = CascadeType.MERGE)
	private Collection<Proposition> propositions;

}
