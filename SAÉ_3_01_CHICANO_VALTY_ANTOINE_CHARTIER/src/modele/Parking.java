package modele;

import java.util.HashSet;
import java.util.Set;

public class Parking {
	
	private String adresse;
	private int places_max;
	private int place_max_moto;
	private int place_max_electrique;
	private float hauteur;
	private Set<Transport> transports;
	private Set<Vehicule> vehicules;
	
	public Parking(String adresse, int places_max, int place_max_moto, int place_max_electrique, float hauteur) {
		super();
		this.adresse = adresse;
		this.places_max = places_max;
		this.place_max_moto = place_max_moto;
		this.place_max_electrique = place_max_electrique;
		this.hauteur = hauteur;
		this.transports = new HashSet<>();
		this.vehicules = new HashSet<>();
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
			if (v.getType() == Type_vehicule.VOITURE_THERMIQUE) {
				resultat.add(v);
			}
		}
		return resultat;
	}
	
	public Set<Vehicule> getVoituresElectriques() {
		Set<Vehicule> resultat = new HashSet<>();
		for (Vehicule v : this.vehicules) {
			if (v.getType() == Type_vehicule.VOITURE_ELECTRIQUE) {
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

	public int getPlaces_max() {
		return places_max;
	}

	public void setPlaces_max(int places_max) {
		this.places_max = places_max;
	}

	public int getPlace_max_moto() {
		return place_max_moto;
	}

	public void setPlace_max_moto(int place_max_moto) {
		this.place_max_moto = place_max_moto;
	}

	public int getPlace_max_electrique() {
		return place_max_electrique;
	}

	public void setPlace_max_electrique(int place_max_electrique) {
		this.place_max_electrique = place_max_electrique;
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

}
