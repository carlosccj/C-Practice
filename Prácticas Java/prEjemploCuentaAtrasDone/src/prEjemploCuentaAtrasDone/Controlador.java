package prEjemploCuentaAtrasDone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class Controlador implements ActionListener {
	Worker w;
	Panel panel;
	public Controlador(Panel panel)
	{
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(panel.START)){
			
			System.out.println("actionPerformed - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());			
			panel.area.append("Empezamos cuenta atrás!");
			//Creamos un trabajador que haga la tarea de procesado
			w = new Worker(Integer.parseInt(panel.tiempoField.getText()), panel);			
			w.execute();//Lanzamos la ejecucion de la hebra trabajadora
		}
	}

}
