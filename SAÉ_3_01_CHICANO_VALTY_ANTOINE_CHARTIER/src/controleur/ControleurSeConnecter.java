package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.Accueil;
import main.java.vue.Inscription;
import main.java.vue.SeConnecter;

public class ControleurSeConnecter implements ActionListener{

	private SeConnecter vue;
	
	
	public ControleurSeConnecter(SeConnecter vue) {
		this.vue = vue;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Se connecter") {
			if(vue.getTextChampNomUtilisateur() == "etu" && TestMdp(vue.getPasswordField())) {
				new Accueil();
			}
		} else if (b.getText() == "S'inscrire") {
			new Inscription();
		}
	}
	
	public boolean TestMdp(char[] Tc) {
		char[] mdp = {'$','i','u','t','i','n','f','o'};
		for (int i=0; i < 8; i++ ) {
			if (mdp[i] != Tc[i]){
				return false;
			}
		}
		return true;
	}
	
	

}
