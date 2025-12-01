package main.java.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.UIManager;

public class PopUpInscriptionValide extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpInscriptionValide frame = new PopUpInscriptionValide();
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
	public PopUpInscriptionValide() {
		setFont(new Font("Arial", Font.PLAIN, 26));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 136);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrLinscrptionABien = new JTextArea();
		txtrLinscrptionABien.setBounds(88, 31, 426, 89);
		txtrLinscrptionABien.setBackground(UIManager.getColor("Button.background"));
		txtrLinscrptionABien.setFont(new Font("Arial", Font.PLAIN, 20));
		txtrLinscrptionABien.setText("L'inscrption a bien été efféctué !");
		contentPane.add(txtrLinscrptionABien);

	}

}
