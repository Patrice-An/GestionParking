package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.FenetrePrincipal;
import vue.FenetrePrincipal.VUE;
import vue.InfoSeGarerParking;

public class ControleurInfoSeGarer implements ActionListener {

    private InfoSeGarerParking vue;
	private FenetrePrincipal fenetrePrincipal;

    public ControleurInfoSeGarer(InfoSeGarerParking vue, FenetrePrincipal fenetrePrincipal) {
        this.vue = vue;
        this.fenetrePrincipal = fenetrePrincipal;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
        System.out.println(b.getText());
		switch (b.getText()) {
			case "Valider":
				
				break;
			case "Annuler":
				fenetrePrincipal.ChangementVue(VUE.CHOIXSEGARER);
				break;
		}
	}

}
