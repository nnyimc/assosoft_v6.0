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
@Table

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReseauSocial implements Serializable {

	@Id
	@Column(name = "reseau_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reseauId;
	
	
	@Column(length = 100, nullable = false, unique = true)
	private String reseauNom;
	
	
	@OneToMany(mappedBy = "reseauSocial", cascade = CascadeType.MERGE)
	private Collection<LienReseau> liensReseau;

}
