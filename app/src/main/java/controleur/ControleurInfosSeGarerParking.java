package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.App;
import vue.InfoSeGarerParking;
import vue.App.VUE;

public class ControleurInfosSeGarerParking implements ActionListener{
	
	private App App;
	
	public ControleurInfosSeGarerParking (InfoSeGarerParking vue, App App) {
		this.App = App;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Annuler")) {
			App.ChangementVue(VUE.CHOIXSEGARER);
		}else if(b.getText().equals("Valider")) {
			App.ChangementVue(VUE.FENRECAPITULATIF);
		}
	}

}
