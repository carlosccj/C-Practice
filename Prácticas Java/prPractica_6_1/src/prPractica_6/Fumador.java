package prPractica_6;

import java.util.concurrent.Semaphore;

public class Fumador extends Thread {
	private int id; //0 = tabaco, 1 = papel, 2 = cerillas;
	private Mesa mesa;
	
	public Fumador (int id, Mesa mesa) {
		this.id = id;
		this.mesa = mesa;
	}
	
	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				mesa.fumar(this.id);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
