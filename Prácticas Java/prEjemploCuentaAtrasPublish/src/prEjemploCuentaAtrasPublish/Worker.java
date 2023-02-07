package prEjemploCuentaAtrasPublish;

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
		for(int i = numero; i >=0 ; i--) {
			publish(i);
			
			this.setProgress((numero-i)*100/numero);
			publish(this.getProgress());
			
			Thread.sleep(1000);
		}
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
