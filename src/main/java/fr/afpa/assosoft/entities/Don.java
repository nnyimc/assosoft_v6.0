package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Don implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long donId;
	@Column(length = 100)
	private String donDescription;
	private Date donDate;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Statut statut;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	private TypeDon typeDon;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Personne personne;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;
}
