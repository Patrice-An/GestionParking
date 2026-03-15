package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.FenetrePrincipal;
import vue.InfoSeGarerParking;
import vue.FenetrePrincipal.VUE;

public class ControleurInfosSeGarerParking implements ActionListener{
	
	private FenetrePrincipal fenetrePrincipal;
	
	public ControleurInfosSeGarerParking (InfoSeGarerParking vue, FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Annuler")) {
			fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
		}else if(b.getText().equals("Valider")) {
			fenetrePrincipal.ChangementVue(VUE.FENRECAPITULATIF);
		}
	}

}
