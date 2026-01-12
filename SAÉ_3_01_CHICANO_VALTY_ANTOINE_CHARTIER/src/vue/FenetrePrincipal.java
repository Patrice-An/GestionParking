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

import controleur.ControleurFenetrePrincipal;
import modele.Utilisateur;

public class FenetrePrincipal extends JFrame {

	private JPanel contentPane;
	private CardLayout layout;
	private JButton btnPrecedent ;
	private Utilisateur utilisateur;

	public enum VUE {

		ACCUEIL("accueil"),
		SECONNECTER("seConnecter"),
		INSCRIPTION("incription"),
		CHOIXSEGARER("choixSeGarer"),
		AJOUTERVEHICULE("AjouterVehicule"),
		FENRECAPITULATIF("fenRecapitulatif"),
		INFOSEGARERPARKING("infoSeGarerParking"),
		GESTIONVEHICULE("gestionVehicule"),
		INFOSEGARERVOIRIE("infoSeGarerVoirie"),
		HISTORIQUESTATIONNEMENT("HistoriqueStationnement"),
		MODIFIERVEHICULE("ModifierVehicule"),
		LISTEPARKING("ListeParking");

		VUE(String designation) {
		}
	};

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

	public FenetrePrincipal() {

		ControleurFenetrePrincipal controleur = new ControleurFenetrePrincipal(this);
		// --- Icone ---
		try (InputStream imgStream = getClass().getResourceAsStream("/images/Parkeo_Logo.png")) {
			if (imgStream != null) {
				BufferedImage img = ImageIO.read(imgStream);
				setIconImage(img);
			}
		} catch (IOException e) {
			e.printStackTrace();
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



		ChangementVue(VUE.SECONNECTER);
		btnPrecedent.addActionListener(controleur);
	}

	public void ChangementVue(VUE fen) {
		if(fen != VUE.INSCRIPTION && fen != VUE.SECONNECTER && fen != VUE.ACCUEIL) {
			this.setVisiblePrecedent(true);
			ChargerVueDependante();
		} else {
			this.setVisiblePrecedent(false);
		}
		layout.show(contentPane, fen.name());
	}

	public void setVisiblePrecedent(boolean p) {
		btnPrecedent.setVisible(p);
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void ChargerVueDependante() {
		if (this.utilisateur != null) {
			contentPane.add(new Accueil(this), VUE.ACCUEIL.name());
			contentPane.add(new GestionVehicule(this), VUE.GESTIONVEHICULE.name());
			contentPane.add(new InfoSeGarerParking(this), VUE.INFOSEGARERPARKING.name());
			contentPane.add(new InfoSeGarerVoirie(this), VUE.INFOSEGARERVOIRIE.name());
			contentPane.add(new ChoixSeGarer(this), VUE.CHOIXSEGARER.name());
			contentPane.add(new AjouterVehicule(this), VUE.AJOUTERVEHICULE.name());
			contentPane.add(new FenRecapitulatif(this), VUE.FENRECAPITULATIF.name());
			contentPane.add(new HistoriqueStationnement(this), VUE.HISTORIQUESTATIONNEMENT.name());
		}
	}



}
