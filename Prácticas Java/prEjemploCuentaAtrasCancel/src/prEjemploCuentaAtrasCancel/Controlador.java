package prEjemploCuentaAtrasCancel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;

public class Controlador implements ActionListener, PropertyChangeListener  {
	
	Panel panel;
	Worker w;
	public Controlador(Panel panel)
	{
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(panel.START)){			
			System.out.println("actionPerformed - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());						
			w = new Worker(Integer.parseInt(panel.tiempoField.getText()), panel);			
			w.setControlador(this); //registramos el controlador en el evento PropertyChange del worker
			w.execute();
		}else if(e.getActionCommand().equals(panel.CANCEL) && w!=null) {
				w.cancel(true);
		}
	}
	
	//Manejador para el cambio de la propiedad progreso del worker
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("progress")){ //el propertyName esta pre-definido
			panel.bar.setValue((Integer)evt.getNewValue());
		}
		
	}
}
