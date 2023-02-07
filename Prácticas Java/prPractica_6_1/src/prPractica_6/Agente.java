package prPractica_6;

import java.util.Random;

public class Agente extends Thread {
	//semaforos siempre en el recurso compartido (mesa);
	private int ing1;
	private int ing2;
	private Mesa mesa;
	Random rand = new Random();
	
	public Agente (Mesa mesa) {
		this.mesa = mesa;
	}
	
	@Override
	public void run() {
		while (!isInterrupted()) {
			do {
				ing1 = rand.nextInt(3);
				ing2 = rand.nextInt(3);
			} while (ing1 == ing2);
			try {
			mesa.ponerIngredientes(ing1, ing2);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
