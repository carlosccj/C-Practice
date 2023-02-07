package prPracticaEvaluable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;

public class Controlador implements ActionListener, PropertyChangeListener {
	
	private Panel panel;
	private WorkerMontecarlo wm;
	private WorkerSeries ws;
	
	public Controlador(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		if (e.getActionCommand().equals("COMENZAR")) {
			System.out.println("actionPerformed - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());			
			wm = new WorkerMontecarlo(panel.getIteraciones(), panel);
			wm.setControlador(this);
			ws = new WorkerSeries(panel.getIteraciones(), panel);
			ws.setControlador(this);
			wm.execute();
			ws.execute();
		}
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("progress")) { 
			panel.setProgresoMonteCarlo((Integer)evt.getNewValue());
			panel.setProgresoLeibniz((Integer)evt.getNewValue());
		}
	}

}