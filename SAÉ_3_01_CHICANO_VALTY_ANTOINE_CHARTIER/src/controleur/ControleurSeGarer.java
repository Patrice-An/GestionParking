package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.ChoixSeGarer;
import main.java.vue.FenetrePrincipal;
import main.java.vue.InfoSeGarerParking;
import main.java.vue.InfoSeGarerVoirie;

public class ControleurSeGarer implements ActionListener{
	
	private ChoixSeGarer vue;
	private FenetrePrincipal fenetrePrincipal;
	
	public ControleurSeGarer(ChoixSeGarer vue, FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Parking") {
			fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "infoSeGarerParking" );
			
		} else if (b.getText() == "Voirie") {
			fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "infoSeGarerVoirie" );
		}
	}

}
