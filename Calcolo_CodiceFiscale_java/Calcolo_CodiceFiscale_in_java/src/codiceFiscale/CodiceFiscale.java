package codiceFiscale;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import eccezioni.ComuneException;
import eccezioni.GiornoException;
import eccezioni.MeseException;
import eccezioni.SessoException;
import persona.Persona;


public class CodiceFiscale {

	
	
	private String codCognome;//prime 3 consonanti... 
	private String codNome;//prime 3 consonanti...
	private String codAnnoNascita;//2 cifre
	private String codMeseNascita;
	private String codGiornoNascita;//2 cifre nel caso di una donna vine aggiunto +40
	private String codComuneNascita;//3 cifre
	private String codControllo;//1 lettera
	
	
	private String codiceFiscale;
	
	public CodiceFiscale(String nome,String cognome,String sesso,int eta,int giornoNascita,
			String meseNascita,int annoNascita,String comuneNascita)throws GiornoException, MeseException, ComuneException, SessoException {
		this.codCognome = calcolaCodCognome(cognome);
		this.codNome = calcolaCodNome(nome);
		this.codAnnoNascita = calcolaCodAnnoNascita(annoNascita);
		this.codMeseNascita = calcolaMeseNascita(meseNascita);
		this.codGiornoNascita = calcolaCodGiornoNascita(giornoNascita, sesso);
		this.codComuneNascita = calcolaCodComuneNascita(comuneNascita);
		this.codControllo =""+calcolaCodControllo();
		
		this.codiceFiscale = codCognome+""+codNome+""+codAnnoNascita+""+codMeseNascita+
							""+codGiornoNascita+""+codComuneNascita+""+codControllo;	
	}
	
	
	
	public CodiceFiscale(Persona persona)throws GiornoException, MeseException, ComuneException, SessoException {
		this.codCognome = calcolaCodCognome(persona.getCognome());
		this.codNome = calcolaCodNome(persona.getNome());
		this.codAnnoNascita = calcolaCodAnnoNascita(persona.getAnnoNascita());
		this.codMeseNascita = calcolaMeseNascita(persona.getMeseNascita());
		this.codGiornoNascita = calcolaCodGiornoNascita(persona.getGiornoNascita(), persona.getSesso());
		this.codComuneNascita = calcolaCodComuneNascita(persona.getComuneNascita());
		this.codControllo =""+calcolaCodControllo();
		
		this.codiceFiscale = codCognome+""+codNome+""+codAnnoNascita+""+codMeseNascita+
							""+codGiornoNascita+""+codComuneNascita+""+codControllo;	
	}
	
	


	public String getCodiceFiscale() {
		return this.codiceFiscale.toUpperCase();//restituisco il codice fiscale calcolato, in maiuscolo...
	}
	
	
	
	
	private String calcolaCodCognome(String cognome) {
		return calcolaCodNomeOCognome(cognome);
	}
	
	
	
	private String calcolaCodNome(String nome) {
		char carattere = 0;
		String codice = "";
		int contaConsonanti = 0;
		int i = 0;
		
		String nomeSenzaSpazi = nome.replaceAll(" ", "");
		
		for(i = 0; i < nomeSenzaSpazi.length(); i++) {
			carattere = nomeSenzaSpazi.charAt(i);
			if(!isVocale(carattere)) 
				contaConsonanti++;
		}
		
		
		if(contaConsonanti > 3) {//si prendeno come riferimento la prima consonante la terza e 
								//la quarta consonante...
			contaConsonanti = 0;
			i = 0;
			do {
				carattere = nomeSenzaSpazi.charAt(i);
				
				if(!isVocale(carattere)) {
					contaConsonanti++;
					if(contaConsonanti == 1 || contaConsonanti == 3 || contaConsonanti == 4) {	
						codice += carattere;	
					}
				}
			i++;	
			}while(i < nomeSenzaSpazi.length() && codice.length() < 3);
		}
		else {
			codice = calcolaCodNomeOCognome(nome);
		}
	
		
		return codice;
	}

	
		
	
	
	private String calcolaCodNomeOCognome(String dato) {
		char carattere = 0;
		String consonanti = "";
		String vocali = "";
		String codice = "";
		
		String cognomeSenzaSpazi = dato.replaceAll(" ", "");//rimuovo eventuali spazi dal cognome...
		int i = 0;
		
		if(cognomeSenzaSpazi.length() > 0) {
			
			do {
				carattere = cognomeSenzaSpazi.charAt(i);
				if(!isVocale(carattere))
					consonanti += ""+carattere;
				i++;	
			}while(i < cognomeSenzaSpazi.length()   && consonanti.length() < 3);
			
			
			
			if(consonanti.length() < 3) {//allora significa che non ci sono consonanti sufficenti "3" per completare il codice,
								//quindi aggiungiamo le vocali scansionando nuovamente il cognome dall'inizio...
				i = 0;
				do {
					carattere = cognomeSenzaSpazi.charAt(i);
					if(isVocale(carattere))
						vocali += ""+carattere;
					i++;	
				}while(i < cognomeSenzaSpazi.length() && (consonanti.length() + vocali.length()) < 3);	
				
				codice = consonanti + "" + vocali;
				if(codice.length() < 3) {//significa che il cognome è formato da meno di tre caratteri, perciò aggiungere
										//carattei "X" per completare il codice...
					do {
						codice += "X";
					}while(codice.length() < 3);
				}
			}else{
				codice = consonanti;
			}
		}
		
		return codice;
	}
	
	
	
	private String calcolaCodAnnoNascita(int anno) {
		int risultato = anno % 100;
		
		if(risultato < 10) //per le persone nate dopo il 1999.
			return "0"+risultato;
		
	
		return ""+risultato; 
	}
	
	
	private String calcolaMeseNascita(String mese) throws MeseException {	
		String meseConv = mese.toUpperCase();//converto il mese in maiuscolo...
		String risultato = "";
		
		switch(meseConv) {
		case "GENNAIO": 
			risultato = "A";
			break;
		
		case "FEBBRAIO":
			risultato = "B";
			break;
			
		case "MARZO":
			risultato = "C";
			break;
			
		case "APRILE":
			risultato = "D";
			break;
			
		case "MAGGIO":
			risultato = "E";
			break;
			
		case "GIUGNO":
			risultato = "H";
			break;
			
		case "LUGLIO":
			risultato = "L";
			break;
			
		case "AGOSTO":
			risultato = "M";
			break;
			
		case "SETTEMBRE":
			risultato = "P";
			break;
			
		case "OTTOBRE":
			risultato = "R";
			break;
			
		case "NOVEMBRE":
			risultato = "S";
			break;
			
		case "DICEMBRE":
			risultato = "T";
			break;

		default:
			throw new MeseException();
		}
	return risultato;
	}
	
	
	
	private String calcolaCodGiornoNascita(int giorno, String sesso) throws GiornoException, SessoException{
		if(giorno <= 0 || giorno > 31) {
			throw new GiornoException();
		}
		
		String risultato = "";
		
		if(sesso.equalsIgnoreCase("Donna"))
				risultato = ""+(giorno+40) ;
		else if(sesso.equalsIgnoreCase("Uomo")) {
			if(giorno < 10) {
				risultato = "0"+giorno;
			}else {
				risultato = ""+giorno;
			}
		}else {
			throw new SessoException();
		}
		
		return risultato;
	}
	
	
	
	private String calcolaCodComuneNascita(String comune) throws ComuneException {
	
		boolean risultato = false;
		String nomeFile = "src/fileTestualeCodiciComuni.txt";
		BufferedReader streamInput = null;
		String codiceComune = "";
		String nomeComune = "";
			
		try {
			streamInput = new BufferedReader(new FileReader(nomeFile));
				
			String riga = streamInput.readLine();
				
			while(riga != null && risultato == false) {
				nomeComune = riga.substring(4, riga.length()).trim();
				if(nomeComune.equalsIgnoreCase(comune)) { 
					risultato = true;
					codiceComune = riga.substring(0, 4);
				}
			riga = streamInput.readLine();
			}
				
			streamInput.close();	
		}catch(FileNotFoundException e) {
			System.err.println("File per la consultazione dei comuni non trovato!");
			System.err.println(e.getMessage());
		}catch(IOException e) {
			System.err.println("Errore nella lettura del file dei codici comunali!");
			System.err.println(e.getMessage());
		}
		
		if(codiceComune.equalsIgnoreCase("")) {
			throw new ComuneException();
		}
			
	return codiceComune;
	}
	
		

	
	private char calcolaCodControllo() {
		
		int risultato = 0;
		String codice = codCognome+""+codNome+""+codAnnoNascita+""+codMeseNascita+""
						+codGiornoNascita+""+codComuneNascita;
		
		
		for(int i = 0; i < codice.length(); i++) {
			if(((i + 1 ) % 2) != 0) //se dispari
				risultato += valoreCarattereInPosizioneDispari(codice.charAt(i));
			else//se pari		
				risultato += valoreCarattereInPosizionePari(codice.charAt(i));		
		}

	return codDiControllo(risultato % 26);
	}
	

	private int valoreCarattereInPosizionePari(char carattere) {
		int risultato = 0;
		char carConv = Character.toUpperCase(carattere);
		
		switch(carConv) {
		case '0':
			risultato = 0;
			break;
		case '1':
			risultato = 1;
			break;
		case '2':
			risultato = 2;
			break;
		case '3':
			risultato = 3;
			break;
		case '4':
			risultato = 4;
			break;
		case '5':
			risultato = 5;
			break;
		case '6':
			risultato = 6;
			break;
		case '7':
			risultato = 7;
			break;
		case '8':
			risultato = 8;
			break;
		case '9':
			risultato = 9;
			break;
		case 'A':
			risultato = 0;
			break;
		case 'B':
			risultato = 1;
			break;
		case 'C':
			risultato = 2;
			break;
		case 'D':
			risultato = 3;
			break;
		case 'E':
			risultato = 4;
			break;
		case 'F':
			risultato = 5;
			break;
		case 'G':
			risultato = 6;
			break;
		case 'H':
			risultato = 7;
			break;
		case 'I':
			risultato = 8;
			break;
		case 'J':
			risultato = 9;
			break;
		case 'K':
			risultato = 10;
			break;
		case 'L':
			risultato = 11;
			break;
		case 'M':
			risultato = 12;
			break;
		case 'N':
			risultato = 13;
			break;
		case 'O':
			risultato = 14;
			break;
		case 'P':
			risultato = 15;
			break;
		case 'Q':
			risultato = 16;
			break;
		case 'R':
			risultato = 17;
			break;
		case 'S':
			risultato = 18;
			break;
		case 'T':
			risultato = 19;
			break;
		case 'U':
			risultato = 20;
			break;
		case 'V':
			risultato = 21;
			break;
		case 'W':
			risultato = 22;
			break;
		case 'X':
			risultato = 23;
			break;
		case 'Y':
			risultato = 24;
			break;
		case 'Z':
			risultato = 25;
			break;
		}
		
	return risultato;
	}
	
	
	private int valoreCarattereInPosizioneDispari(char carattere) {
		int risultato = 0;
		char carConv = Character.toUpperCase(carattere);

		switch (carConv) {
		case '0':
			risultato = 1;
			break;
		case '1':
			risultato = 0;
			break;
		case '2':
			risultato = 5;
			break;
		case '3':
			risultato = 7;
			break;
		case '4':
			risultato = 9;
			break;
		case '5':
			risultato = 13;
			break;
		case '6':
			risultato = 15;
			break;
		case '7':
			risultato = 17;
			break;
		case '8':
			risultato = 19;
			break;
		case '9':
			risultato = 21;
			break;
		case 'A':
			risultato = 1;
			break;
		case 'B':
			risultato = 0;
			break;
		case 'C':
			risultato = 5;
			break;
		case 'D':
			risultato = 7;
			break;
		case 'E':
			risultato = 9;
			break;
		case 'F':
			risultato = 13;
			break;
		case 'G':
			risultato = 15;
			break;
		case 'H':
			risultato = 17;
			break;
		case 'I':
			risultato = 19;
			break;
		case 'J':
			risultato = 21;
			break;
		case 'K':
			risultato = 2;
			break;
		case 'L':
			risultato = 4;
			break;
		case 'M':
			risultato = 18;
			break;
		case 'N':
			risultato = 20;
			break;
		case 'O':
			risultato = 11;
			break;
		case 'P':
			risultato = 3;
			break;
		case 'Q':
			risultato = 6;
			break;
		case 'R':
			risultato = 8;
			break;
		case 'S':
			risultato = 12;
			break;
		case 'T':
			risultato = 14;
			break;
		case 'U':
			risultato = 16;
			break;
		case 'V':
			risultato = 10;
			break;
		case 'W':
			risultato = 22;
			break;
		case 'X':
			risultato = 25;
			break;
		case 'Y':
			risultato = 24;
			break;
		case 'Z':
			risultato = 23;
			break;
		}

		return risultato;
	}
	
	
	private char codDiControllo(int valore) {
		char codDiControllo = '0';
		
		switch(valore) {
		
		case 0:
			codDiControllo = 'A';
			break;
		case 1:
			codDiControllo = 'B';
			break;
		case 2:
			codDiControllo = 'C';
			break;
		case 3:
			codDiControllo = 'D';
			break;
		case 4:
			codDiControllo = 'E';
			break;
		case 5:
			codDiControllo = 'F';
			break;
		case 6:
			codDiControllo = 'G';
			break;
		case 7:
			codDiControllo = 'H';
			break;
		case 8:
			codDiControllo = 'I';
			break;
		case 9:
			codDiControllo = 'J';
			break;
		case 10:
			codDiControllo = 'K';
			break;
		case 11:
			codDiControllo = 'L';
			break;
		case 12:
			codDiControllo = 'M';
			break;
		case 13:
			codDiControllo = 'N';
			break;
		case 14:
			codDiControllo = 'O';
			break;
		case 15:
			codDiControllo = 'P';
			break;
		case 16:
			codDiControllo = 'Q';
			break;
		case 17:
			codDiControllo = 'R';
			break;
		case 18:
			codDiControllo = 'S';
			break;
		case 19:
			codDiControllo = 'T';
			break;
		case 20:
			codDiControllo = 'U';
			break;
		case 21:
			codDiControllo = 'V';
			break;
		case 22:
			codDiControllo = 'W';
			break;
		case 23:
			codDiControllo = 'X';
			break;
		case 24:
			codDiControllo = 'Y';
			break;
		case 25:
			codDiControllo = 'Z';
			break;
		}
		
	return codDiControllo;
	}
	
	
	
	private boolean isVocale(char carattere) {	
		boolean risultato = false;
		
		switch(carattere) {
			case 'A':
			case 'a':
			case 'E':
			case 'e':
			case 'I':
			case 'i':
			case 'O':
			case 'o':
			case 'U':
			case 'u':
				risultato = true;
			break;
		}
		
	return risultato;
	}
}
