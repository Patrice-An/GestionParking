package modele;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Utilisateur {

	private final int id;
	private String nom;
	private String prenom;
	private String tel;
	private LocalDate naissance;
	private String mail;
	private final String password;
	private final Set<Vehicule> vehicules;
	private final Set<Abonnement> abonnements;

	public Utilisateur(int id, String nom, String prenom, String tel, LocalDate naissance, String mail, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.naissance = naissance;
		this.mail = mail;
		this.password = password;
		this.vehicules = new HashSet<>();
		this.abonnements = new HashSet<>();
	}

	public int getid() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public LocalDate getNaissance() {
		return naissance;
	}
	
	public Set<Abonnement> getAbonnement() {
		return abonnements;
	}

	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
	}
	
	public String getPassword() {
		return password;
	}

	public void addVehicule(Vehicule v) {
		this.vehicules.add(v);
	}

	public Set<Vehicule> getVehicules() {
		return vehicules;
	}

	public void ajouterAbonnement(Abonnement abonnement) {
		this.abonnements.add(abonnement);
	}

	public void retirerAbonnement(Abonnement abonnement) {
		this.abonnements.remove(abonnement);
	}

	@Override
	public String toString() {
		return prenom + " " + nom;
	}
	
	public void removeVehicule(Vehicule v) {
		this.vehicules.remove(v);
	}
	
	
}
