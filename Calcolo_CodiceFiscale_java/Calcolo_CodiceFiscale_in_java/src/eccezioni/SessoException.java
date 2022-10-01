package eccezioni;

public class SessoException extends Exception {
	
	
	public SessoException(String messaggio) {
		super(messaggio);
	}
	
	public SessoException() {
		super("Sesso specificato Exception!");
	}

}
