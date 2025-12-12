package main.java.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JSpinner;
import java.awt.Dimension;
import javax.swing.JButton;

public class AjouterParking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatDarculaLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterParking frame = new AjouterParking();
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
	public AjouterParking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][grow][grow][grow][grow]", "[][][][grow][grow][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel_1 = new JLabel("Adresse :");
		contentPane.add(lblNewLabel_1, "cell 5 0");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 1,alignx left");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 5 1,growx");
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 3,growx,aligny top");
		
		JLabel lblNewLabel_2 = new JLabel("Hauteur :");
		panel.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setPreferredSize(new Dimension(40, 20));
		panel.add(spinner);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 5 3,grow");
		
		JLabel lblNewLabel_2_1 = new JLabel("Nombre de places :");
		panel_1.add(lblNewLabel_2_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setPreferredSize(new Dimension(40, 20));
		panel_1.add(spinner_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 4,grow");
		
		JLabel lblNewLabel_2_2 = new JLabel("Nombre de place moto :");
		panel_2.add(lblNewLabel_2_2);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setPreferredSize(new Dimension(40, 20));
		panel_2.add(spinner_2);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, "cell 5 4,grow");
		
		JLabel lblNewLabel_2_3 = new JLabel("Nombre de place életrique :");
		panel_3.add(lblNewLabel_2_3);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setPreferredSize(new Dimension(40, 20));
		panel_3.add(spinner_3);
		
		JButton btnNewButton = new JButton("Annuler");
		contentPane.add(btnNewButton, "cell 0 6,alignx left,aligny bottom");
		
		JButton btnNewButton_1 = new JButton("Enregistrer");
		contentPane.add(btnNewButton_1, "cell 5 6,alignx right,aligny bottom");

	}

}
