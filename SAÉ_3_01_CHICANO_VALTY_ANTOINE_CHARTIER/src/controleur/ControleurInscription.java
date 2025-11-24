package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.Accueil;
import main.java.vue.Inscription;
import main.java.vue.SeConnecter;

public class ControleurInscription implements ActionListener{
	
	public ControleurInscription(Inscription vue) {
		this.vue = vue;
	}

	Inscription vue;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Valider") {
			vue.dispose();
			new SeConnecter();
		} else if (b.getText() == "Connectez-vous") {
			vue.dispose();
			new SeConnecter();
		}
	}

}
