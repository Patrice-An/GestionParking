package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.Accueil;
import main.java.vue.Inscription;
import main.java.vue.SeConnecter;
import modele.dao.requetes.requeteUtilisateur.UtilisateurConnection;

public class ControleurSeConnecter implements ActionListener{

	private SeConnecter vue;
	private String user;
	private String pwd;
			
	public ControleurSeConnecter(SeConnecter vue) {
		this.vue = vue;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Se connecter") {
			if(true) {
				vue.dispose();
				new Accueil(vue.getChampNomUtilisateur().getText());
			}
		} else if (b.getText() == "S'inscrire") {
			vue.dispose();
			new Inscription();
		}
	}
	
	
	
	

}
