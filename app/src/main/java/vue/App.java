package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import controleur.ControleurApp;
import modele.Utilisateur;

public class App extends JFrame {

	private JPanel contentPane;
	private CardLayout layout;
	private JButton btnPrecedent;
	private Utilisateur utilisateur;
	private Accueil accueil;
	private GestionVehicule gestionVehicule;
	private InfoSeGarerParking infoSeGarerParking;
	private InfoSeGarerVoirie infoSeGarerVoirie;
	private AjouterVehicule ajouterVehicule;
	private FenRecapitulatif fenRecapitulatif;
	private HistoriqueStationnement historiqueStationnement;
	private GestionAbonnement gestionAbonnement;

	public enum VUE {

		ACCUEIL("accueil"), SECONNECTER("seConnecter"), INSCRIPTION("incription"), CHOIXSEGARER("choixSeGarer"),
		AJOUTERVEHICULE("AjouterVehicule"), FENRECAPITULATIF("fenRecapitulatif"),
		INFOSEGARERPARKING("infoSeGarerParking"), GESTIONVEHICULE("gestionVehicule"),
		INFOSEGARERVOIRIE("infoSeGarerVoirie"), HISTORIQUESTATIONNEMENT("HistoriqueStationnement"),
		LISTEPARKING("ListeParking"), GESTION_ABONNEMENT("GestionAbonnement");

		VUE(String designation) {
		}
	};

	public static void main(String[] args) {
		FlatDarculaLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					//empty
				}
			}
		});
	}

	public App() {

		ControleurApp controleur = new ControleurApp(this);
		// --- Icone ---
		try (InputStream imgStream = getClass().getResourceAsStream("/images/Parkeo_Logo.png")) {
			if (imgStream != null) {
				BufferedImage img = ImageIO.read(imgStream);
				setIconImage(img);
			}
		} catch (IOException e) {
			//empty
		}

		// --- Paramètres fenêtre ---
		setTitle("Parkéo");
		setSize(500, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Layout principal
		setLayout(new BorderLayout());

		// -------- PANEL NORD --------
		JPanel panelNord = new JPanel();

		btnPrecedent = new JButton("← Précédent");
		panelNord.add(btnPrecedent);
		add(panelNord, BorderLayout.NORTH);

		// -------- PANEL CENTER (CardLayout) --------
		layout = new CardLayout();
		contentPane = new JPanel(layout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPane, BorderLayout.CENTER);

		contentPane.add(new SeConnecter(this), VUE.SECONNECTER.name());
		contentPane.add(new Inscription(this), VUE.INSCRIPTION.name());
		contentPane.add(new ListeParking(), VUE.LISTEPARKING.name());
		contentPane.add(new ChoixSeGarer(this), VUE.CHOIXSEGARER.name());

		changementVue(VUE.SECONNECTER);
		btnPrecedent.addActionListener(controleur);
	}

	public void changementVue(VUE fen) {
		this.setVisiblePrecedent(fen != VUE.INSCRIPTION && fen != VUE.SECONNECTER && fen != VUE.ACCUEIL);
		layout.show(contentPane, fen.name());
	}

	public void setVisiblePrecedent(boolean p) {
		btnPrecedent.setVisible(p);
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
		if(utilisateur != null) {
			chargerVueDependante();
		}
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	private void chargerVueDependante() {
			accueil = new Accueil(this);
			gestionVehicule = new GestionVehicule(this);
			infoSeGarerParking = new InfoSeGarerParking(this);
			infoSeGarerVoirie = new InfoSeGarerVoirie(this);
			ajouterVehicule = new AjouterVehicule(this);
			fenRecapitulatif = new FenRecapitulatif();
			historiqueStationnement = new HistoriqueStationnement(this);
			gestionAbonnement = new GestionAbonnement(this);
			contentPane.add(accueil, VUE.ACCUEIL.name());
			contentPane.add(gestionVehicule, VUE.GESTIONVEHICULE.name());
			contentPane.add(infoSeGarerParking, VUE.INFOSEGARERPARKING.name());
			contentPane.add(infoSeGarerVoirie, VUE.INFOSEGARERVOIRIE.name());
			contentPane.add(ajouterVehicule, VUE.AJOUTERVEHICULE.name());
			contentPane.add(fenRecapitulatif, VUE.FENRECAPITULATIF.name());
			contentPane.add(historiqueStationnement, VUE.HISTORIQUESTATIONNEMENT.name());
			contentPane.add(gestionAbonnement, VUE.GESTION_ABONNEMENT.name());
	}

	public void refresh() {
		gestionVehicule.refresh();
		historiqueStationnement.refresh();
		infoSeGarerParking.refresh();
		infoSeGarerVoirie.refresh();
	}

}
