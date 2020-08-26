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
public class TypeDon implements Serializable {

	@Id
	@Column(name = "type_don_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeDonId;
	
	
	@Column(length = 20, nullable = false, unique = true)
	private String typeDonDesc;
	
	
	@OneToMany(mappedBy = "typeDon", cascade = CascadeType.MERGE)
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Don> dons;
	
}
