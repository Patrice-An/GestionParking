package main.java.vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

public class FenetrePrincipal extends JFrame {

	private JPanel contentPane;
	private CardLayout layout;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipal frame = new FenetrePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		layout = new CardLayout(0, 0);
		contentPane = new JPanel(layout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.add(new Accueil(this),"accueil");
		contentPane.add(new SeConnecter(this),"seConnecter");
		contentPane.add(new Inscription(this),"inscription");
		contentPane.add(new ChoixSeGarer(this),"choixSeGarer");
		contentPane.add(new AjouterVehicule(this),"ajouterVehicule");
		contentPane.add(new FenRecapitulatif(this),"fenRecapitulatif");
		contentPane.add(new InfoSeGarerParking(this),"infoSeGarerParking");
		contentPane.add(new InfoSeGarerVoirie(this),"infoSeGarerVoirie");
		
		layout.show(contentPane, "seConnecter");
		
		
		
	}
	
	public CardLayout getCardLayout() {
		return layout;
	}

}
