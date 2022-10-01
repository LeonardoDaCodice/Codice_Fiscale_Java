package eccezioni;

public class MeseException extends Exception {

	
	public MeseException(String messaggio) {
		super(messaggio);
	}
	
	
	public MeseException() {
		super("Mese Exception!");
	}
}
