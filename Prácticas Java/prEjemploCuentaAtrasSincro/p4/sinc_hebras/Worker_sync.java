package p4.sinc_hebras;

import java.util.List;
import javax.swing.SwingWorker;

public class Worker_sync extends SwingWorker<Void, Integer> {

	private Worker w;
	private Panel panel;
	private int numero;
	
	public Worker_sync(int numero, Panel panel, Worker w)
	{
		this.w = w;
		this.numero = numero;
		this.panel = panel;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		int i=0;
		int num;
		do {
			num = w.leerLista(i);
			publish(numero-num);
			i++;
		}while(i<=numero);
		return null;
	}

	public void process(List<Integer> lista)
	{
		panel.escribirLista2(lista);
	}
	
}
