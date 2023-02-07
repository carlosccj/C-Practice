package prPractica_6;

import java.util.concurrent.Semaphore;

public class Mesa {
	private Semaphore puedeFumar[];
	private Semaphore puedePoner;
	
	private int elem0;
	private int elem1;
	
	public Mesa () {
		puedeFumar = new Semaphore[3];
		puedePoner = new Semaphore(1, true);
		for (int i = 0; i < 3; i++) {
			puedeFumar[i] = new Semaphore(0, true);
		}
	}
	
	public int ingredienteQueFalta() {
		int falta = 0;
		if (elem0 != 0 && elem1 != 0) {
			falta = 0;
		} else if (elem0 != 1 && elem1 != 1) {
			falta = 1;
		} else if (elem0 != 2 && elem1 != 2) {
			falta = 2;
		}
		return falta;
	}
	
	public void ponerIngredientes (int ing0, int ing1) throws InterruptedException {
		puedePoner.acquire();
		elem0 = ing0;
		elem1 = ing1;
		System.out.println("El agente está colocando los ingredientes\n");
		Thread.sleep(2000);
		System.out.println("El agente ha colocado en la mesa los ingredientes: " + elem0 + ", " + elem1 + "\n");
		puedeFumar[ingredienteQueFalta()].release();
		
	}
	
	public void fumar (int id) throws InterruptedException {
		puedeFumar[ingredienteQueFalta()].acquire();
		System.out.println("Fumador " + id + " está fumando\n");
		Thread.sleep(2000);
		System.out.println("Fumador " + id + " ha terminado de fumar\n");
		puedePoner.release();
	}
}
