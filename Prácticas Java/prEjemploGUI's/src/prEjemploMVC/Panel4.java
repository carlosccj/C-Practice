package prEjemploMVC;

import javax.swing.*;
import java.awt.*;

public class Panel4 extends JPanel {
	private JLabel descripcion = new JLabel("Descripción");
	private JTextField campo = new JTextField("datos.txt", 10);
	private JButton guardar = new JButton("Guardar");
	private JTextArea area = new JTextArea("Introducir descripción: ");
	private JScrollPane scroll = new JScrollPane(area);
	
	public Panel4() {
		this.setLayout(new BorderLayout());
		this.add(descripcion, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
		JPanel sur = new JPanel(new FlowLayout());
		sur.add(campo);
		sur.add(guardar);
		this.add(sur, BorderLayout.SOUTH);
	}
}
