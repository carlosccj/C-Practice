package prEjemploCuentaAtrasCancel;

import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void, Integer> { // <Lo que devuelve en el protected del @Override, lo que publica>

	private int numero;
	private Panel panel;
	
	public Worker(int numero, Panel panel)
	{
		this.numero = numero;
		this.panel = panel;
	}
	
	@Override
	protected Void doInBackground() throws Exception {		
		int i=numero;
		while(!isCancelled() && i>0)
		{
			publish(i);
			this.setProgress((numero-i)*100/numero);
			Thread.sleep(1000);
			i--;
		}
		this.setProgress(100);
		return null;
	}

	protected void process(List<Integer> lista)
	{	
		panel.escribirLista(lista);
	}
	
	protected void setControlador(PropertyChangeListener controlador)
	{
		this.addPropertyChangeListener(controlador);
	}	
}
