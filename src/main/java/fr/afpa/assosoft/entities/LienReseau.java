package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lien_reseau")

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "association_asso_id", "reseau_social_reseau_id" }))
public class LienReseau implements Serializable {

	@Id
	@Column(name = "lien_reseau_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lienReseauId;
	
	
	@Column(length = 100, nullable = false, unique = true)
	private String lienReseauUrl;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asso_id")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reseau_id")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private ReseauSocial reseauSocial;
}
