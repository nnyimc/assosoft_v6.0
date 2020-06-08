package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "association_asso_id", "reseau_social_reseau_id" }))
public class LienReseau implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lienReseauId;
	@Column(length = 100, nullable = false, unique = true)
	private String lienReseauUrl;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;
	@ManyToOne(cascade = CascadeType.MERGE)
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private ReseauSocial reseauSocial;
}
