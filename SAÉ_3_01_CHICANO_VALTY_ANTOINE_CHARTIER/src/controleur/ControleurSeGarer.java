package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.ChoixSeGarer;
import vue.FenetrePrincipal;
import vue.InfoSeGarerParking;
import vue.InfoSeGarerVoirie;
import vue.FenetrePrincipal.VUE;

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
		System.out.println(b.getText());
		if (b.getText() == "Parking") {
			fenetrePrincipal.ChangementVue(VUE.INFOSEGARERPARKING);
			
		} else if (b.getText() == "Voirie") {
			fenetrePrincipal.ChangementVue(VUE.INFOSEGARERVOIRIE);
		}
	}

}
