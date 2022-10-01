package persona;

public class Persona {
	
	private String nome;
	private String cognome;
	private String sesso;
	private int annoNascita;
	private String meseNascita;
	private int giornoNascita;
	private String comuneNascita;
	
	
	
	public Persona(String nome, String cognome, String sesso, int giornoNascita,
					String meseNascita,int annoNascita, String comuneNascita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.giornoNascita = giornoNascita;
		this.meseNascita = meseNascita;
		this.annoNascita = annoNascita;
		this.comuneNascita = comuneNascita;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public String getSesso() {
		return sesso;
	}



	public void setSesso(String sesso) {
		this.sesso = sesso;
	}





	public int getAnnoNascita() {
		return annoNascita;
	}



	public void setAnnoNascita(int annoNascita) {
		this.annoNascita = annoNascita;
	}



	public String getMeseNascita() {
		return meseNascita;
	}



	public void setMeseNascita(String meseNascita) {
		this.meseNascita = meseNascita;
	}



	public int getGiornoNascita() {
		return giornoNascita;
	}



	public void setGiornoNascita(int giornoNascita) {
		this.giornoNascita = giornoNascita;
	}



	public String getComuneNascita() {
		return comuneNascita;
	}



	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}



	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", annoNascita="
				+ annoNascita + ", meseNascita=" + meseNascita + ", giornoNascita=" + giornoNascita + ", comuneNascita="
				+ comuneNascita + "]";
	}	
	

}
