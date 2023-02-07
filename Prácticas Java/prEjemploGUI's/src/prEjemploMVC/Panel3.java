package prEjemploMVC;

import javax.swing.*;
import java.awt.*;

public class Panel3 extends JPanel {
	private JButton norte = new JButton("Norte");
	private JButton centro = new JButton("Centro");
	private JPanel groupWest = new JPanel(new GridLayout(2, 1));
	private ButtonGroup grupo = new ButtonGroup();
	private JRadioButton rb1 = new JRadioButton("RB1");
	private JRadioButton rb2 = new JRadioButton("RB2");
	private JCheckBox este = new JCheckBox("Este", true);
	private JLabel sur = new JLabel("Este es el sur");
	
	public Panel3() {
		this.setLayout(new BorderLayout());
		this.add(norte, BorderLayout.NORTH);
		this.add(centro, BorderLayout.CENTER);
		grupo.add(rb1);
		grupo.add(rb2);
		groupWest.add(rb1);
		groupWest.add(rb2);
		this.add(groupWest, BorderLayout.WEST);
		this.add(este, BorderLayout.EAST);
		JPanel panelSur = new JPanel(new FlowLayout());
		panelSur.add(sur);
		this.add(panelSur, BorderLayout.SOUTH);
	}
	
}
