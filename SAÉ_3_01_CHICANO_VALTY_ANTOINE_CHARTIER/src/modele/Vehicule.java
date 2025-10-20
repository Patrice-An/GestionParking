package modele;

import java.util.Objects;

public class Vehicule{
	
	private String immatriculation;
	private Type_vehicule type;
	private float hauteur;
	
	public Vehicule(String immatriculation, Type_vehicule type, float hauteur) {
		this.immatriculation = immatriculation;
		this.type = type; 
		this.hauteur = hauteur;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public Type_vehicule getType() {
		return type;
	}
	
	public float getHauteur() {
		return hauteur;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(hauteur, immatriculation, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicule other = (Vehicule) obj;
		return Float.floatToIntBits(hauteur) == Float.floatToIntBits(other.hauteur)
				&& Objects.equals(immatriculation, other.immatriculation) && type == other.type;
	}
}
