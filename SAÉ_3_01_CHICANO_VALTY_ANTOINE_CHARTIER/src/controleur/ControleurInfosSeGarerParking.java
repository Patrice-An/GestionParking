package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.ChoixSeGarer;
import main.java.vue.FenRecapitulatif;
import main.java.vue.FenetrePrincipal;
import main.java.vue.InfoSeGarerParking;

public class ControleurInfosSeGarerParking implements ActionListener{
	
	private InfoSeGarerParking vue;
	private FenetrePrincipal fenetrePrincipal;
	
	public ControleurInfosSeGarerParking (InfoSeGarerParking vue, FenetrePrincipal fenetrePrincipal) {
		this.vue = vue;
		this.fenetrePrincipal = fenetrePrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "choixSeGarer" );
		}else if(b.getText() == "Valider") {
			fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "fenRecapitulatif" );
		}
	}

}
