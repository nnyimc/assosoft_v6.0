package fr.afpa.assosoft.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import fr.afpa.assosoft.beans.InscriptionAsso;

public class InscriptionAssoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return InscriptionAsso.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InscriptionAsso inscriptionAsso = (InscriptionAsso) target;

		if (inscriptionAsso.getAssoMail().isEmpty()) {
			errors.rejectValue("assoMail", "Le mail doit être renseigné !");
		}

	}
}
