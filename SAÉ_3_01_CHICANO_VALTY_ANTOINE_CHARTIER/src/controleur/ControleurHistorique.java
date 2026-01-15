package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modele.Vehicule;
import vue.FenetrePrincipal;
import vue.GestionVehicule;
import vue.HistoriqueStationnement;

public class ControleurHistorique implements ActionListener {
	
	private FenetrePrincipal fenPrincipal;
	private HistoriqueStationnement vue;

	public ControleurHistorique(HistoriqueStationnement vue, FenetrePrincipal fenPrincipal) {
		this.fenPrincipal = fenPrincipal;
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		Vehicule vehicule = (Vehicule) b.getClientProperty("vehicule");
		JOptionPane.showMessageDialog(null, "Le véhicule : "+vehicule.getImmatriculation()+" doit partir");
	}

}
