package prPracticaEvaluable;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class WorkerSeries extends SwingWorker<Double, Void> {
	private int iteraciones;
	private Panel panel;
	private static final double NUM = 4;
	
	public WorkerSeries (int iteraciones, Panel panel) {
		this.iteraciones = iteraciones;
		this.panel = panel;
	}
	
	@Override
	protected Double doInBackground() throws InterruptedException {
		System.out.println("worker doInBackground() - isEventDispatchThread? " + SwingUtilities.isEventDispatchThread());
		double den = 1;
		double res = 0;
		for (int i = 0; i <= this.iteraciones; i++) {
			if (i % 2 == 0) {
				res += (NUM / den);
			} else {
				res -= (NUM / den);
			}
			den += 2;
		}
		return res;
	}
	
	protected void done() {
		try {
			System.out.println("worker done() - isEventDispatchThread? " + SwingUtilities.isEventDispatchThread());
			panel.escribePI2(get());
		} catch (InterruptedException | ExecutionException e ) {
			e.printStackTrace();
		}
	}
}
