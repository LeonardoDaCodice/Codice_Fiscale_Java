package eccezioni;

public class GiornoException extends Exception {
	
	public GiornoException(String messaggio) {
		super(messaggio);
	}
	
	
	public GiornoException() {
		super("Giorno nascita Exception!");
	}

}
