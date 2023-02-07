package prEjemploConversorTemperaturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class Controlador implements ActionListener {

	private Panel panel;
	
	public Controlador(Panel panel) {
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals(panel.CONVERTIR)) {
		double tempCelsius = Double.parseDouble(panel.getText());
		double tempFahr = tempCelsius * 1.8 + 32;
	        System.out.println("convertButtonActionPerformed - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());
	        panel.setLabel(tempFahr + " Fahrenheit");
		}
    }
}
