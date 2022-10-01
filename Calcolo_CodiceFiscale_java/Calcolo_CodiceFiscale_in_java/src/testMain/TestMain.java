package testMain;

import codiceFiscale.CodiceFiscale;
import eccezioni.ComuneException;
import eccezioni.GiornoException;
import eccezioni.MeseException;
import eccezioni.SessoException;
import persona.Persona;

public class TestMain {

	public static void main(String[] args) {

		Persona persona = new Persona("Mario", "Rossi", "Uomo", 01, "Settembre", 2000, "Roma");
		CodiceFiscale codiceFiscale = null;
		
		try {
			//ottengo il codice fiscale passando come orgomento l'oggetto "persona".
			codiceFiscale = new CodiceFiscale(persona);

			//stampo il codice fiscale calcolato.
			System.out.println(codiceFiscale.getCodiceFiscale());
			
			
		} catch (GiornoException | MeseException | ComuneException |SessoException e) {
			System.err.println(e.getMessage());
		}
	}

}
