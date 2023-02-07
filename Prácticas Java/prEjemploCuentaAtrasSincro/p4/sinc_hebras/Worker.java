package p4.sinc_hebras;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, Integer> {

	private int numero;
	private Panel panel;
	private List<Integer> miLista;

	public Worker(int numero, Panel panel){
		this.numero = numero;
		this.panel = panel;
		miLista = new ArrayList<Integer>();
	}

	@Override	
	protected Void doInBackground() throws Exception {		
		int i =numero;
		while(!isCancelled() && i>0){
			publish(i); 							
			this.setProgress((numero-i)*100/numero);
			Thread.sleep(1000);
			i--;
		}
		this.setProgress(100);
		return null;
	}	

	protected void setControlador(PropertyChangeListener controlador){
		this.addPropertyChangeListener(controlador);
	}
	
	
	/*----------Ahora estos metodos son sincronizados----------------*/
	synchronized protected void process(List<Integer> lista){		
		panel.escribirLista(lista);
		miLista.addAll(lista);
		notifyAll();
	}
	
	synchronized public int leerLista(int indice) throws InterruptedException {		
		while(indice>=miLista.size())
			wait();
		return miLista.get(indice);
	}
		
}
