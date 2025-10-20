package vue;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
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
		frame = new JFrame("Accueil - Parkéo ");
		frame.setBounds(1080, 720, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
