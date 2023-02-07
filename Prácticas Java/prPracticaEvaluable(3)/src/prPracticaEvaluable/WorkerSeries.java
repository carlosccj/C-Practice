package prPracticaEvaluable;


import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class WorkerSeries extends SwingWorker<Void, Double> {
	private int iteraciones;
	private Panel panel;
	private static final double NUM = 4;
	
	public WorkerSeries (int iteraciones, Panel panel) {
		this.iteraciones = iteraciones;
		this.panel = panel;
	}
	
	@Override
	protected Void doInBackground() throws InterruptedException {
		System.out.println("worker doInBackground() - isEventDispatchThread? " + SwingUtilities.isEventDispatchThread());
		double den = 1;
		double res = 0;
		for (int i = 0; i <= this.iteraciones && !isCancelled(); i++) {
			if (i % 2 == 0) {
				res += (NUM / den);
				publish(res);
			} else {
				res -= (NUM / den);
				publish(res);
			}
			this.setProgress((i) * 100 / iteraciones);
			Thread.sleep(50);
			den += 2;
		}
		this.setProgress(100);
		return null;
	}
	
	synchronized protected void process(List<Double> v) {
		panel.limpia2();
		panel.escribirSeries(v);
		notifyAll();
	}	
	
	synchronized protected void setControlador(PropertyChangeListener controlador) {
		this.addPropertyChangeListener(controlador);
	}	
}
