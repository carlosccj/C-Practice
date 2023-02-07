package prPracticaEvaluable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class Controlador implements ActionListener {
	
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
			ws = new WorkerSeries(panel.getIteraciones(), panel);
			wm.execute();
			ws.execute();
		}
	}

}
