package modele;

import java.util.HashSet;
import java.util.Set;

public class Parking {
	
	private final int idParking;
	private String adresse;
	private String nom;
	private float hauteur;
	private int placesMax;
	private int placesMaxMoto;
	private int placesMaxElectrique;
	private final Set<Transport> transports;
	private final Set<Vehicule> vehicules;
	private final Set<Tarif> tarifs;
	
	public Parking(int idParking, String adresse, String nom, float hauteur, int placesMax, int placesMaxMoto, int placesMaxElectrique) {
		this.idParking = idParking;
		this.adresse = adresse;
		this.nom = nom;
		this.hauteur = hauteur;
		this.placesMax = placesMax;
		this.placesMaxMoto = placesMaxMoto;
		this.placesMaxElectrique = placesMaxElectrique;
		this.transports = new HashSet<>();
		this.vehicules = new HashSet<>();
		this.tarifs = new HashSet<>();
	}

	public int getidParking() {
		return idParking;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addTransport(Transport t) {
		this.transports.add(t);
	}
	
	public void addVehicule(Vehicule v) {
		this.vehicules.add(v);
	}
	
	public Set<Vehicule> getVoituresThermiques() {
		Set<Vehicule> resultat = new HashSet<>();
		for (Vehicule v : this.vehicules) {
			if (v.getType() == Type_vehicule.THERMIQUE) {
				resultat.add(v);
			}
		}
		return resultat;
	}
	
	public Set<Vehicule> getVoituresElectriques() {
		Set<Vehicule> resultat = new HashSet<>();
		for (Vehicule v : this.vehicules) {
			if (v.getType() == Type_vehicule.ELECTRIQUE) {
				resultat.add(v);
			}
		}
		return resultat;
	}
	
	public Set<Vehicule> getMotos() {
		Set<Vehicule> resultat = new HashSet<>();
		for (Vehicule v : this.vehicules) {
			if (v.getType() == Type_vehicule.MOTO) {
				resultat.add(v);
			}
		}
		return resultat;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getplacesMax() {
		return placesMax;
	}

	public void setplacesMax(int placesMax) {
		this.placesMax = placesMax;
	}

	public int getplacesMaxMoto() {
		return placesMaxMoto;
	}

	public void setplacesMaxMoto(int placesMaxMoto) throws IllegalArgumentException {
		if (this.getplacesMax() > placesMaxMoto + this.getplacesMaxElectrique() + this.getPlaceMaxThermique()) {
			throw new IllegalArgumentException("Le nombre de place incorrect disponible ne peut pas dépassé le nombre de place maximum");
		}
		else {
			this.placesMaxMoto = placesMaxMoto;
		}
	}

	public int getplacesMaxElectrique() {
		return placesMaxElectrique;
	}
	
	public int getPlaceMaxThermique() {
		return this.getplacesMax() - this.getplacesMaxElectrique() - this.getplacesMaxMoto();
	}

	public void setplacesMaxElectrique(int placesMaxElectrique) throws IllegalArgumentException{
		if (this.getplacesMax() < placesMaxElectrique + this.getplacesMaxMoto() + this.getPlaceMaxThermique()) {
			throw new IllegalArgumentException("Le nombre de place incorrect disponible ne peut pas dépassé le nombre de place maximum");
		} else {
			this.placesMaxElectrique = placesMaxElectrique;
		}
	}

	public float getHauteur() {
		return hauteur;
	}

	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}
	
	public boolean estRelais() {
		return !this.transports.isEmpty();
	}
	
	public int nbPlaceLibres() {
		return this.placesMax - (this.getVoituresElectriques().size() + this.getMotos().size() + this.getVoituresThermiques().size());
	}
	
	public Set<Vehicule> getVehicules() {
		return this.vehicules;
	}
	
	public void removeVehicule(Vehicule v) throws IllegalArgumentException {
		if (this.vehicules.contains(v)) {
			this.vehicules.remove(v);
		} else {
			throw new IllegalArgumentException("Impossible de retirer un véhicule absent du parking");
		}	
	}
	
	public void vider() {
		this.vehicules.clear();
	}
	
	public Set<Tarif> getTarifs() {
		return this.tarifs;
	}
	
	public void addTarif(Tarif t) {
		this.tarifs.add(t);
	}

	@Override
	public String toString() {
		return this.nom + " - " + this.adresse;
	}

}
