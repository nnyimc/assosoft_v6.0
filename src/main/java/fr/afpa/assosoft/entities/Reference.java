package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "association_asso_id", "contact_contact_id" }))
public class Reference implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long referenceId;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "assoId")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "contactId")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Contact contact;

}
