package prEjemploAcumulador;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Aplicacion {
	public static void main (String[] args) {
		
		JFrame ventana = new JFrame("Acumulador");
		Modelo modelo = new Modelo();
		Panel1 panel = new Panel1();
		Controlador controlador = new Controlador(panel, modelo);
		
		panel.controlador(controlador);
		ventana.setContentPane((JPanel)panel);
		ventana.setVisible(true);
		ventana.pack();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
