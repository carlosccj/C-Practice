package prEjemploAcumulador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
	protected Panel panel;
	protected Modelo modelo;
	
	/*
	 * La clase Controlador siempre dispone del modelo y de al menos una vista como atributos.
	 */
	
	public Controlador(Panel panel, Modelo modelo) {
		this.panel = panel;
		this.modelo = modelo;
	}
	
	/*
	 * Este método es el resultado de implementar ActionListener. En este caso tan solo se pueden dar dos eventos,
	 * que se pulse sobre acumular, en cuyo caso de usa el método correspondiente del modelo y luego actualiza el valor
	 * final en el panel o resetear, en el que se hace algo parecido.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Acumular")) {
			modelo.acumular(1);
			panel.nuevoValor(modelo.obtenerAcumulador());
		} else if (e.getActionCommand().equals("Resetear")) {
			modelo.resetear();
			panel.nuevoValor(modelo.obtenerAcumulador());
		} else if (e.getActionCommand().equals("Desacumular")) {
			modelo.desacumular(1);
			panel.nuevoValor(modelo.obtenerAcumulador());
		}
	}
}
