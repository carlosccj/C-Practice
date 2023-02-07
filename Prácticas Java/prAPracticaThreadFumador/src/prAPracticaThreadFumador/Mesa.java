package prAPracticaThreadFumador;
import java.util.concurrent.Semaphore;

public class Mesa {
	private Semaphore puedeFumar[]; // 0 = tabaco, 1 = papel, 2 = cerillas
	private Semaphore puedePoner;
	private int ingrediente1;
	private int ingrediente2;
	
	public Mesa() {
		this.puedeFumar = new Semaphore[3];
		this.puedePoner = new Semaphore(1, true);
		for (int i = 0; i < puedeFumar.length; i++) {
			puedeFumar[i] = new Semaphore(0, true);
		}
	}
	
	public void ponerIngrediente(int ingrediente1, int ingrediente2) throws InterruptedException {
		puedePoner.acquire();
		this.ingrediente1 = ingrediente1;
		this.ingrediente2 = ingrediente2;
		System.out.println("El agente está colocando los ingredientes...\n");
		System.out.println("El agente ha colocado los ingredientes " + ingrediente1 + " y " + ingrediente2 + " en la mesa\n");
		Thread.sleep(2000);
		for (int i = 0; i < puedeFumar.length; i++) {
			if (i != this.ingrediente1 && i != this.ingrediente2) {
				puedeFumar[i].release();
				System.out.println("En la mesa falta el ingrediente " + i + "\n");
				Thread.sleep(2000);
			}
		}
	}
	
	public void fumar (int id) throws InterruptedException {
		for(int i = 0; i < puedeFumar.length; i++) {
			if (i != this.ingrediente1 && i != this.ingrediente2) {
				puedeFumar[i].acquire();
				System.out.println("El fumador con el ingrediente " + i + " está fumando\n");
				Thread.sleep(2000);
				System.out.println("El fumador con el ingrediente " + i + " ha terminado de fumar\n");
			}
		}
		puedePoner.release();
	}
}
