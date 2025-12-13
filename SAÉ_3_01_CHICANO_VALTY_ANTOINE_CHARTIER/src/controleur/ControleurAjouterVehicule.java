package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import main.java.vue.AjouterVehicule;
import main.java.vue.FenetrePrincipal;

public class ControleurAjouterVehicule implements ActionListener{
	
	private AjouterVehicule vue;
	private FenetrePrincipal fenetrePrincipal;
	public ControleurAjouterVehicule(AjouterVehicule vue, FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		this.vue = vue;
	}

	public void actionPerformedButton(JButton b) {
		if (b.getText().equals("Annuler")) {
			System.out.println("Action bouton");
		}
		
	}
	
	public void actionPerformedField(JTextField f) {
		System.out.println("action field");
		f.setText("");
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Valider":
				
				break;
			case "Annuler":
				fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "accueil" );
				break;
		}
	}
}
