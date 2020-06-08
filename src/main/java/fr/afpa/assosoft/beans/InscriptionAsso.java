package fr.afpa.assosoft.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Media;
import fr.afpa.assosoft.entities.Ville;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionAsso {

	@NotNull
	private MultipartFile assoImage;

	@NotEmpty
	private String personneNom;

	@NotEmpty
	private String personnePrenom;

	@NotEmpty
	private String personneLogin;

	@NotEmpty
	private String personneMdp;

	@NotEmpty
	private String personneMdpRepeat;

	@NotEmpty
	private String personneMail;

	@NotNull
	private Ville villeAdmin;

	@NotEmpty
	private String assoNom;

	@NotEmpty
	private String assoNumRNA;

	@NotEmpty
	private String assoMail;

	@NotEmpty
	private String assoTel;

	@NotEmpty
	private String assoUrl;

	@NotNull
	private Ville villeAsso;

	@NotNull
	private Categorie categorieAsso;
}
