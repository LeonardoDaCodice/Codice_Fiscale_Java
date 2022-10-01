package eccezioni;

public class ComuneException extends Exception {
	
	public ComuneException(String messaggio) {
		super(messaggio);
	}
	
	
	public ComuneException() {
		super("Comune di nascita Exception!");
	}

}
