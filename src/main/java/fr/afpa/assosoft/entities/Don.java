package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
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
	@Column(name = "don_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long donId;
	@Column(length = 100)
	private String donDescription;
	private Date donDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "statut_id")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Statut statut;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_don_id")
	@NotNull
	private TypeDon typeDon;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personne_id")
	@NotNull
	@ToString.Exclude
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Personne personne;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asso_id")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;


}
