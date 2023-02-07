package prPractica_8_2;
import java.util.Random;

public class Proceso extends Thread {
	private int id;
	private int maxRecursos;
	private Control control;
	private Random rand;
	
	public Proceso (int id, Control control, int maxRecursos) {
		this.id = id;
		this.control = control;
		this.maxRecursos = maxRecursos;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			int nRecursos = rand.nextInt(maxRecursos + 1); // Recursos que pide el proceso
			try {
				control.quieroRecursos(id, nRecursos);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			control.liberarRecursos(id, nRecursos);
		}
	}
}
