package fr.afpa.assosoft.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import fr.afpa.assosoft.entities.Categorie;
import fr.afpa.assosoft.entities.Media;
import fr.afpa.assosoft.entities.Adresse;
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
	private Adresse adresseAdmin;

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
	private Adresse adresseAsso;

	@NotNull
	private Categorie categorieAsso;
}
