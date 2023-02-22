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

@Entity
@Table(name = "reseau_social")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReseauSocial implements Serializable {

	private static final long serialVersionUID = 8747277722374657410L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(length = 100, nullable = false, unique = true)
	private String nom;
	
	
	@OneToMany(mappedBy = "reseauSocial", cascade = CascadeType.MERGE)
	private Collection<LienReseauSocial> liens;

}
