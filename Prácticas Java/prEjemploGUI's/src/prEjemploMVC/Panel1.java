package prEjemploMVC;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel1 extends JPanel {
	private JLabel l;
	
	private JButton north;
	private JButton south;
	private JButton east;
	private JPanel west;
	private JButton center;
	
	private JButton panel1;
	private JButton panel2;
	
	/*
	 * En el constructor declaramos 'west' como un panel independiente anidado dentro del principal de la clase.
	 * Este panel dispone a su vez de dos botones propios (panel1 y panel2) y de un esquema en 'GridLayout'.
	 * Dentro del panel principal, 'west' se sigue situando al oeste.
	 */
	
	public Panel1() {
		setLayout(new BorderLayout());
		north = new JButton("NORTE");
		south = new JButton("SOUTH");
		east = new JButton("EAST");
		center = new JButton("CENTER");
		l = new JLabel("Where do you want to travel?");
		add(l);
		add(north, BorderLayout.NORTH);
		add(south, BorderLayout.SOUTH);
		add(east, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
		
		west = new JPanel(new GridLayout(2, 1));
		panel1 = new JButton("Panel 1");
		panel2 = new JButton("Panel 2");
		west.add(panel1);
		west.add(panel2);
		add(west, BorderLayout.WEST);
	}
}
