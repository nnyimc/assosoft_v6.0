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

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "media_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "media_titre", length = 100, nullable = false, unique = true)
	private String titre;
	
	@Column(name = "media_url", length = 100, nullable = false, unique = true)
	private String url;
	
	@Column(name = "media_path", length = 100, nullable = false, unique = true)
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personne_id")
	private Personne personne;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "association_id")
	private Association association;

}
