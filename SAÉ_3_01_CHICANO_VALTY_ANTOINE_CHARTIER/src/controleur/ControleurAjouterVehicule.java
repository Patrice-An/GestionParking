package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import main.java.vue.AjouterVehicule;

public class ControleurAjouterVehicule implements ActionListener{
	
	private AjouterVehicule vue;
	
	public ControleurAjouterVehicule(AjouterVehicule vue) {
		this.vue = vue;
	}

	public void actionPerformedButton(JButton b) {
		if (b.getText().equals("Annuler")) {
			System.out.println("Action bouton");
		}
		
	}
	
	public void actionPerformedField(JTextField f) {
		System.out.println("action field");
		f.setText("");
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = new JButton();
		JTextField f = new JTextField();
		System.out.println(e.getSource());
		if (e.getSource() instanceof JButton) {
			b = (JButton) e.getSource();
			this.actionPerformedButton(b);
		}
		if (e.getSource() instanceof JTextField) {
			f = (JTextField) e.getSource();
			f.hasFocus();
			this.actionPerformedField(f);
		}
	}
}
