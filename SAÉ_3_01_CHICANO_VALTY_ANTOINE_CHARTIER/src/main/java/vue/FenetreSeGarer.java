package main.java.vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.FlatDarculaLaf;

public class FenetreSeGarer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FlatDarculaLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreSeGarer window = new FenetreSeGarer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetreSeGarer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnVoirie = new JButton("Se garer en voirie");
		frame.getContentPane().add(btnVoirie, BorderLayout.WEST);
		
		JButton btnParking = new JButton("Se garer sur un parking");
		btnParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnParking, BorderLayout.EAST);
		
		JLabel labelSeGarer = new JLabel("Où souhaitez vous vous garez ?");
		labelSeGarer.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(labelSeGarer, BorderLayout.NORTH);
	}

}
