package fr.afpa.assosoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = { "rue", "complement", "codePostal","ville"})
})

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse implements Serializable {

	private static final long serialVersionUID = -3743927134625383878L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 250, nullable = false)
	private String rue;
	
	@Column(length = 100)
	private String complement;
	
	@Column(length = 20, nullable = false)
	private String codePostal;
	
	@Column( length = 250, nullable = false)
	private String ville;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Association> associations;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Personne> personnes;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.MERGE)
	@ToString.Exclude
	private Collection<Prestataire> prestataires;
}
