package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.ChoixSeGarer;
import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;

public class ControleurSeGarer implements ActionListener{
	
	private FenetrePrincipal fenetrePrincipal;
	public ControleurSeGarer(ChoixSeGarer vue, FenetrePrincipal fenetrePrincipal) {
		this.fenetrePrincipal = fenetrePrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Parking":
				fenetrePrincipal.ChangementVue(VUE.INFOSEGARERPARKING);
				break;
			case "Voirie":
				fenetrePrincipal.ChangementVue(VUE.INFOSEGARERVOIRIE);
				break;
		}
	}

}
