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
			vue.dispose();
			new ChoixSeGarer();

		} 
		if (b.getText() == "Partir"){
			vue.dispose();
		}
		
	}}
