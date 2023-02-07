package prPracticaEvaluable;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class WorkerMontecarlo extends SwingWorker<Double, Void> {

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
	protected Double doInBackground() throws InterruptedException {
		System.out.println("worker doInBackground() - isEventDispatchThread? " + SwingUtilities.isEventDispatchThread());
		int px = 0;
		int py = 0;
		int inside = 0;
		for (int i = 0; i < this.iteraciones; i++) {
			px = rand.nextInt(RADIO + 1);
			py = rand.nextInt(RADIO + 1);
			inside = isInside(px, py) ? inside + 1 : inside;
		}
		return (((double)4 * ((double)inside) / (double)iteraciones));
	}
	
	protected void done() {
		try {
			System.out.println("worker done() - isEventDispatchThread? " + SwingUtilities.isEventDispatchThread());
			panel.escribePI1(get());
		} catch (InterruptedException | ExecutionException e ) {
			e.printStackTrace();
		}
	}
	
	public boolean isInside(int px, int py) {
		boolean inside = false;
		if ((px * px + py * py) < (RADIO * RADIO)) {
			inside = true;
		}
		return inside;
	}

}
