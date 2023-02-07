package prPracticaEvaluable;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Random;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class WorkerMontecarlo extends SwingWorker<Void, Double> {

	private int iteraciones;
	private Panel panel;
	private Random rand;
	private static final int RADIO = 50; // Suponemos que el radio del círculo es 50
	
	public WorkerMontecarlo (int iteraciones, Panel panel) {
		this.iteraciones = iteraciones;
		this.panel = panel;
		this.rand = new Random();
	}
	
	@Override
	protected Void doInBackground() throws InterruptedException {
		System.out.println("worker doInBackground() - isEventDispatchThread? " + SwingUtilities.isEventDispatchThread());
		int px = 0;
		int py = 0;
		int inside = 0;
		double res = 0;
		for (int i = 0; i < this.iteraciones; i++) {
			px = rand.nextInt(RADIO + 1);
			py = rand.nextInt(RADIO + 1);
			inside = isInside(px, py) ? inside + 1 : inside;
			res = ((double)4 * ((double)inside) / (double)iteraciones);
			publish(res);
			this.setProgress((i)*100/iteraciones);
			Thread.sleep(50);
		}
		this.setProgress(100);
		return null;
	}
	
	synchronized protected void process(List<Double> v) {
		panel.limpia1();
		panel.escribirMonteCarlo(v);
		notifyAll();
	}	
	
	synchronized protected void setControlador(PropertyChangeListener controlador) {
		this.addPropertyChangeListener(controlador);
	}	
	
	public boolean isInside(int px, int py) {
		boolean inside = false;
		if ((px * px + py * py) < (RADIO * RADIO)) {
			inside = true;
		}
		return inside;
	}

}
