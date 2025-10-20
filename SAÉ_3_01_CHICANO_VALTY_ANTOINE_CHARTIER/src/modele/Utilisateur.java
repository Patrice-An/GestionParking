package modele;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Utilisateur {

	private String nom;
	private String prenom;
	private String tel;
	private String mail;
	private LocalDate naissance;
	private Set<Vehicule> vehicules;
	
	public Utilisateur(String nom, String prenom, String tel, String mail, LocalDate naissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.mail = mail;
		this.naissance = naissance;
		this.vehicules = new HashSet<>();
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

	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
	}
	
	public void addVehicule(Vehicule v) {
		this.vehicules.add(v);
	}
	
}
