package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.ChoixSeGarer;
import vue.App;
import vue.App.VUE;

public class ControleurSeGarer implements ActionListener{
	
	private App App;
	public ControleurSeGarer(ChoixSeGarer vue, App App) {
		this.App = App;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		switch (b.getText()) {
			case "Parking":
				App.ChangementVue(VUE.INFOSEGARERPARKING);
				break;
			case "Voirie":
				App.ChangementVue(VUE.INFOSEGARERVOIRIE);
				break;
		}
	}

}
