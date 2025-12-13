package modele;

public class Vehicule{
	
	private String immatriculation;
	private float hauteur;
	private int id_utilisateur;
	private String désignation;
	private Type_vehicule type;

	public Vehicule(String immatriculation, float hauteur, int id_utilisateur, String désignation, Type_vehicule type) {
		this.immatriculation = immatriculation;
		this.hauteur = hauteur;
		this.id_utilisateur = id_utilisateur;
		this.désignation = désignation;
		this.type = type;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public float getHauteur() {
		return hauteur;
	}

	public int getId_utilisateur() {
		return id_utilisateur;
	}

	public String getDésignation() {
		return désignation;
	}

	public Type_vehicule getType() {
		return type;
	}

	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}

	public void setDésignation(String désignation) {
		this.désignation = désignation;
	}

	public void setType(Type_vehicule type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Vehicule [immatriculation=" + immatriculation + ", hauteur=" + hauteur + ", id_utilisateur="
				+ id_utilisateur + ", désignation=" + désignation + ", type=" + type + "]";
	}

}
