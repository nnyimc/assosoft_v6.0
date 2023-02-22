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
@Table(name = "type_don")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDon implements Serializable {

	private static final long serialVersionUID = 1800307235756454468L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(length = 20, nullable = false, unique = true)
	private String nom;
	
	
	@OneToMany(mappedBy = "typeDon", cascade = CascadeType.MERGE)
	private Collection<Don> dons;
	
}
