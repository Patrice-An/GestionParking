package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.Accueil;
import main.java.vue.Inscription;
import main.java.vue.SeConnecter;

public class ControleurInscription implements ActionListener{
	
	Inscription vue;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Valider") {
			new SeConnecter();
			vue.dispose();
		} else if (b.getText() == "S'inscrire") {
			new Inscription();
		}
	}

}
