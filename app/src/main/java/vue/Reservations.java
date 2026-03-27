package vue;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class Reservations extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Reservations() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel labelTitre = new JLabel("Réserver");
		labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
		add(labelTitre, BorderLayout.NORTH);
		
		JPanel marginLeft = new JPanel();
		add(marginLeft, BorderLayout.WEST);
		
		JPanel marginRight = new JPanel();
		add(marginRight, BorderLayout.EAST);
		
		JPanel marginBottom = new JPanel();
		add(marginBottom, BorderLayout.SOUTH);
		
		JPanel paneCentre = new JPanel();
		add(paneCentre, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Place avec borne de recharge");
		btnNewButton.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("Place moto");
		btnNewButton1.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton1);
		
		JButton btnNewButton2 = new JButton("Place normale");
		btnNewButton2.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton2);
		
		JButton btnNewButton3 = new JButton("Place pour personne en situation de handicap");
		btnNewButton3.setPreferredSize(new Dimension(250, 50));
		paneCentre.add(btnNewButton3);

	}

}
