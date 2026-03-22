package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.App;
import vue.App.VUE;

public class ControleurApp implements ActionListener{

	private App vue;
	
	public ControleurApp(App vue) {
		this.vue = vue;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("← Précédent")) {
			vue.ChangementVue(VUE.ACCUEIL);
		}
	}

}
