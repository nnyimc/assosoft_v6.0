package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lien_reseau_social")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LienReseauSocial implements Serializable {

	private static final long serialVersionUID = -1283812198989860821L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(length = 250, nullable = false, unique = true)
	private String url;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "association_id")
	@NotNull
	private Association association;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reseau_social_id")
	@NotNull
	private ReseauSocial reseauSocial;
}
