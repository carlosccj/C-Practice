package prEjemploCuentaAtrasDone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Worker extends SwingWorker<List<Integer>, Void> { // <Lo que devuelve en el protected del @Override, lo que publica>

	private int numero;
	private Panel panel;
	
	public Worker(int numero, Panel panel)
	{
		this.numero = numero;
		this.panel = panel;
	}
	
	@Override
	protected List<Integer> doInBackground() throws InterruptedException {
		System.out.println("worker doInBackground() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());
		List<Integer> lista = new ArrayList<Integer>();
		for(int i = numero; i >= 0; i--)	{
			lista.add(i);
			Thread.sleep(1000);
		}
		return lista;
	}

	protected void done() {
		try {
			System.out.println("worker done() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());
			panel.escribirLista(get()); //get() - espera a que termine doInBackground  y devuelve los valores generados
			
		} catch (InterruptedException | ExecutionException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
