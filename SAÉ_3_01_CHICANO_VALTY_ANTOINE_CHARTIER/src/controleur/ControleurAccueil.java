package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.Accueil;
import main.java.vue.ChoixSeGarer;

public class ControleurAccueil implements ActionListener{
	
	private Accueil vue;
	
	public ControleurAccueil(Accueil vue) {
		this.vue = vue;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Se Garer") {
			vue.setVisible(false);
			new ChoixSeGarer();

		} 
		if (b.getText() == "Partir"){
			System.out.println("ntm putain de perceuse");
			vue.fermer();
		}
		
	}}
