package main.java.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import modele.Utilisateur;

import java.awt.CardLayout;

public class FenetrePrincipal extends JFrame {

	private JPanel contentPane;
	private CardLayout layout;
	private Utilisateur utilisateur;
	public enum VUE{
		
		ACCUEIL("accueil"), 
		SECONNECTER("seConnecter"),
		INSCRIPTION("incription"),
		CHOIXSEGARER("choixSeGarer"),
		AJOUTERVEHICULE("AjouterVehicule"),
		FENRECAPITULATIF("fenRecapitulatif"),
		INFOSEGARERPARKING("infoSeGarerParking"),
		INFOSEGARERVOIRIE("infoSeGarerVoirie");

		VUE(String desgination) {
		}
	};




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


		contentPane.add(new Accueil(this),VUE.ACCUEIL.name());
		contentPane.add(new SeConnecter(this),VUE.SECONNECTER.name());
		contentPane.add(new Inscription(this),VUE.INSCRIPTION.name());
		contentPane.add(new ChoixSeGarer(this),VUE.CHOIXSEGARER.name());
		contentPane.add(new AjouterVehicule(this),VUE.AJOUTERVEHICULE.name());
		contentPane.add(new FenRecapitulatif(this),VUE.FENRECAPITULATIF.name());
		contentPane.add(new InfoSeGarerParking(this),VUE.INFOSEGARERPARKING.name());
		contentPane.add(new InfoSeGarerVoirie(this),VUE.INFOSEGARERVOIRIE.name());

		ChangementVue(VUE.SECONNECTER);

	}

	public void ChangementVue(VUE fen) {
		layout.show(contentPane, fen.name());
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatDarculaLaf.setup();
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

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}