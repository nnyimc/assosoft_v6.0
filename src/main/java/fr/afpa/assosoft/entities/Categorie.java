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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categorie implements Serializable {

	private static final long serialVersionUID = 3625878441143846114L;
	
	@Id
	@Column(name = "categorie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80, nullable = false, unique = true)
	private String nom;
	
	@OneToMany(mappedBy = "categorie", cascade = CascadeType.MERGE)
	private Collection<Association> associations;

}
