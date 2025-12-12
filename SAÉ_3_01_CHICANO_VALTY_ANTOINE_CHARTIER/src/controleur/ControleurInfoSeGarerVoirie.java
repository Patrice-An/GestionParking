package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.java.vue.ChoixSeGarer;
import main.java.vue.FenetrePrincipal;
import main.java.vue.InfoSeGarerVoirie;

public class ControleurInfoSeGarerVoirie implements ActionListener{
	
	private InfoSeGarerVoirie vue;
	private FenetrePrincipal fenetrePrincipal;
	
	public ControleurInfoSeGarerVoirie(InfoSeGarerVoirie vue, FenetrePrincipal fenetrePrincipal) {
		this.vue = vue;
		this.fenetrePrincipal = fenetrePrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText() == "Annuler") {
			fenetrePrincipal.getLayout().show(fenetrePrincipal.getContentPane(), "choixSeGarer" );
		}
		
		
	}

}
