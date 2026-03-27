package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.App;
import vue.App.VUE;

public class ControleurInfosSeGarerParking implements ActionListener{
	
	private final App app;
	
	public ControleurInfosSeGarerParking (App app) {
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Annuler")) {
			app.changementVue(VUE.CHOIXSEGARER);
		}else if(b.getText().equals("Valider")) {
			app.changementVue(VUE.FENRECAPITULATIF);
		}
	}

}
