package modele;

public class Transport {

	private final int id;
	private String adresse;
	private String ligne;
	private Type_Transport type;
	
	public Transport(int id, String adresse, String ligne, Type_Transport type) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.ligne = ligne;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getLigne() {
		return ligne;
	}

	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

	public Type_Transport getType() {
		return type;
	}

	public void setType(Type_Transport type) {
		this.type = type;
	}
	
	
	
}
