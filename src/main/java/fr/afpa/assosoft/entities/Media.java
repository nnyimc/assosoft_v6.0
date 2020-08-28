package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media implements Serializable {

	@Id
	@Column(name = "media_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mediaId;
	@Column(length = 100, nullable = false, unique = true)
	private String mediaTitre;
	@Column(length = 100, nullable = false, unique = true)
	private String mediaUrl;
	@Column(length = 100, nullable = false, unique = true)
	private String mediaPath;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personne_id")
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Personne personne;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asso_id")
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;

}
