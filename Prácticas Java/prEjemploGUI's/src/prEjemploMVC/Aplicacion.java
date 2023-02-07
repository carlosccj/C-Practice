package prEjemploMVC;

import javax.swing.JFrame;

public class Aplicacion {
	
	/*
	 * Cuando creamos la ventana que va a contener al JFrame podemos indicarle si queremos la configuraci�n
	 * de Panel1 o Panel 2
	 */
	
	public static void main (String[] args) {
		JFrame ventana = new JFrame("Un ejemplo"); // Creamos el contenedor superior
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Funci�n autom�tica para cerrar la GUI de manera correcta
		ventana.setContentPane(new Panel4()); // Cambiamos el panel de contenidos
		ventana.pack(); // Ajustamos tama�o (empaquetamos)
		ventana.setVisible(true); // La hacemos visible
	}
}
