package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Don implements Serializable {

	private static final long serialVersionUID = -7452081511447228183L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String description;
	
	@Column(nullable = false)
	@Temporal( value= TemporalType.DATE)
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statut_id")
	@NotNull
	private Statut statut;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_don_id")
	@NotNull
	private TypeDon typeDon;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personne_id")
	@NotNull
	@ToString.Exclude
	private Personne personne;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "association_id")
	@NotNull
	private Association association;


}
