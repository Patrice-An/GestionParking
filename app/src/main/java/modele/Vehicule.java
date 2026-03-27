package modele;

public class Vehicule{
	
	private final String immatriculation;
	private float hauteur;
	private final int idUtilisateur;
	private String designation;
	private Type_vehicule type;

	public Vehicule(String immatriculation, float hauteur, int idUtilisateur, String designation, Type_vehicule type) {
		this.immatriculation = immatriculation;
		this.hauteur = hauteur;
		this.idUtilisateur = idUtilisateur;
		this.designation = designation;
		this.type = type;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public float getHauteur() {
		return hauteur;
	}

	public int getidUtilisateur() {
		return idUtilisateur;
	}

	public String getDesignation() {
		return designation;
	}

	public Type_vehicule getType() {
		return type;
	}

	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setType(Type_vehicule type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.designation + " - " + this.immatriculation;
	}

}
