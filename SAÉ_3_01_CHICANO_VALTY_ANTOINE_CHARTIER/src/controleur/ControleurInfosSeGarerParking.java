package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.ChoixSeGarer;
import vue.FenRecapitulatif;
import vue.FenetrePrincipal;
import vue.InfoSeGarerParking;
import vue.FenetrePrincipal.VUE;

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
			fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
		}else if(b.getText() == "Valider") {
			fenetrePrincipal.ChangementVue(VUE.FENRECAPITULATIF);
		}
	}

}
