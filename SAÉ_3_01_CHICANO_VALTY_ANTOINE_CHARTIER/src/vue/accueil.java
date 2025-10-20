package vue;

import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.*;
import java.awt.*;

public class accueil {
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Définir le look-and-feel FlatLaf
		FlatDarculaLaf.setup();

		// Créer et afficher la fenêtre d'accueil
		EventQueue.invokeLater(() -> {
			try {
				accueil window = new accueil();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public accueil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Accueil - Parkéo");
		frame.setBounds(100, 100, 1080, 720); // Positionnement centré à l'écran
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel principal
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		// Texte "Mode Simulation" centré en haut
		JLabel label = new JLabel("Mode Simulation", SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 32));
		panel.add(label, BorderLayout.NORTH);

		// Panel pour les boutons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2, 20, 0)); // Espacement horizontal de 20px
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Marges ajustées

		// Bouton "Se Garer"
		JButton btnSeGarer = new JButton("Se Garer");
		btnSeGarer.setFont(new Font("Arial", Font.PLAIN, 24));
		btnSeGarer.setPreferredSize(new Dimension(200, 60)); // Taille fixe pour les boutons
		buttonPanel.add(btnSeGarer);

		// Bouton "Partir"
		JButton btnPartir = new JButton("Partir");
		btnPartir.setFont(new Font("Arial", Font.PLAIN, 24));
		btnPartir.setPreferredSize(new Dimension(200, 60));
		buttonPanel.add(btnPartir);

		// Ajouter le panel des boutons au centre
		panel.add(buttonPanel, BorderLayout.CENTER);
	}
}
