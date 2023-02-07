package prEjemploMVC;

import java.awt.*;
import javax.swing.*;

public class Panel2 extends JPanel {
	private JPanel botonesNorte;
	private JButton norte1;
	private JButton norte2;
	private TextArea area;
	private JScrollPane scroll;
	private JLabel label;
	
	public Panel2() {
		botonesNorte = new JPanel(new FlowLayout());
		norte1 = new JButton("Norte1");
		norte2 = new JButton("Norte2");
		botonesNorte.add(norte1);
		botonesNorte.add(norte2);
		label = new JLabel("GUI creada");
		
		this.setLayout(new BorderLayout());
		area = new TextArea(20, 20);
		scroll = new JScrollPane(area);
		this.add(botonesNorte, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		this.add(label, BorderLayout.SOUTH);
	}
}
