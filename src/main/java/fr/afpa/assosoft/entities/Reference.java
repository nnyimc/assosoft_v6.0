package fr.afpa.assosoft.entities;

import java.io.Serializable;

import javax.persistence.*;
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
	@Column(name = "reference_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long referenceId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asso_id")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Association association;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	@NotNull
	// @JsonProperty(access = Access.WRITE_ONLY)
	private Contact contact;

}
