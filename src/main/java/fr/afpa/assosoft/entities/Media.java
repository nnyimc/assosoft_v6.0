package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mediaId;
	@Column(length = 100, nullable = false, unique = true)
	private String mediaTitre;
	@Column(length = 100, nullable = false, unique = true)
	private String mediaUrl;
	@Column(length = 100, nullable = false, unique = true)
	private String mediaPath;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "personneId")
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Personne personne;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "assoId")
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;

}
