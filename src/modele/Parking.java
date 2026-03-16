package modele;

import java.util.HashSet;
import java.util.Set;

public class Parking {
	
	private int idParking;
	private String adresse;
	private String nom;
	private float hauteur;
	private int placeMax;
	private int placeMaxMoto;
	private int placeMaxElectrique;
	private Set<Transport> transports;
	private Set<Vehicule> vehicules;
	private Set<Tarif> tarifs;
	
	public Parking(int idParking, String adresse, String nom, float hauteur, int placeMax, int placeMaxMoto, int placeMaxElectrique) {
		this.idParking = idParking;
		this.adresse = adresse;
		this.nom = nom;
		this.hauteur = hauteur;
		this.placeMax = placeMax;
		this.placeMaxMoto = placeMaxMoto;
		this.placeMaxElectrique = placeMaxElectrique;
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

	public int getplaceMax() {
		return placeMax;
	}

	public void setplaceMax(int placeMax) {
		this.placeMax = placeMax;
	}

	public int getplaceMaxMoto() {
		return placeMaxMoto;
	}

	public void setplaceMaxMoto(int placeMaxMoto) throws IllegalArgumentException {
		if (this.getplaceMax() > placeMaxMoto + this.getplaceMaxElectrique() + this.getPlace_max_thermique()) {
			throw new IllegalArgumentException("Le nombre de place incorrect disponible ne peut pas dépassé le nombre de place maximum");
		}
		else {
			this.placeMaxMoto = placeMaxMoto;
		}
	}

	public int getplaceMaxElectrique() {
		return placeMaxElectrique;
	}
	
	public int getPlace_max_thermique() {
		return this.getplaceMax() - this.getplaceMaxElectrique() - this.getplaceMaxMoto();
	}

	public void setplaceMaxElectrique(int placeMaxElectrique) throws IllegalArgumentException{
		if (this.getplaceMax() < placeMaxElectrique + this.getplaceMaxMoto() + this.getPlace_max_thermique()) {
			throw new IllegalArgumentException("Le nombre de place incorrect disponible ne peut pas dépassé le nombre de place maximum");
		} else {
			this.placeMaxElectrique = placeMaxElectrique;
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
		return this.placeMax - (this.getVoituresElectriques().size() + this.getMotos().size() + this.getVoituresThermiques().size());
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
