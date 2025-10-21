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

	public void setPlace_max_moto(int place_max_moto) throws IllegalArgumentException {
		if (this.getPlaces_max() > place_max_moto + this.getPlace_max_electrique() + this.getPlace_max_thermique()) {
			throw new IllegalArgumentException("Le nombre de place incorrect disponible ne peut pas dépassé le nombre de place maximum");
		}
		else {
			this.place_max_moto = place_max_moto;
		}
	}

	public int getPlace_max_electrique() {
		return place_max_electrique;
	}
	
	public int getPlace_max_thermique() {
		return this.getPlaces_max() - this.getPlace_max_electrique() - this.getPlace_max_moto();
	}

	public void setPlace_max_electrique(int place_max_electrique) throws IllegalArgumentException{
		if (this.getPlaces_max() < place_max_electrique + this.getPlace_max_moto() + this.getPlace_max_thermique()) {
			throw new IllegalArgumentException("Le nombre de place incorrect disponible ne peut pas dépassé le nombre de place maximum");
		} else {
			this.place_max_electrique = place_max_electrique;
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
	
	public int nb_place_libres() {
		return this.places_max - (this.getVoituresElectriques().size() + this.getMotos().size() + this.getVoituresThermiques().size());
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

}
