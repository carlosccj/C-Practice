package prEjemploCuentaAtrasPublish;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class Controlador implements ActionListener {
	
	Panel panel;
	Worker w;
	public Controlador(Panel panel)
	{
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(panel.START)){
			
			System.out.println("actionPerformed - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());						
			
			w = new Worker(Integer.parseInt(panel.tiempoField.getText()), panel);			
			w.execute();
		}
	}

}
