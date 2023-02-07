package prAPracticaThreadsPajaros;
import java.util.concurrent.Semaphore;

public class Nido {
	private int platoBichitos;
	private int maxBichitos;
	private Semaphore[] puedeComer;
	private Semaphore puedeCazar;
	private Semaphore mutex;
	
	public Nido (int nPolluelos, int maxBichitos) {
		this.maxBichitos = maxBichitos;
		this.platoBichitos = maxBichitos;
		this.puedeCazar = new Semaphore(0, true);
		this.mutex = new Semaphore(1, true);
		for (int i = 0; i < nPolluelos; i++) {
			this.puedeComer[i] = new Semaphore(1, true);
		}
	}
	
	public void comerBichito(int id) throws InterruptedException {
		this.mutex.acquire();
		this.puedeComer[id].acquire();
		if (platoBichitos < maxBichitos && platoBichitos > 0) {
			System.out.println("Hay " + platoBichitos + " bichitos en el plato\n");
			System.out.println("El pollo " + id + " se come un bichito\n");
			Thread.sleep(2000);
			platoBichitos--;
			Thread.sleep(3000);
			mutex.release();
			puedeCazar.release();
			System.out.println("Ahora quedan " + platoBichitos + " en el plato\n");
		} else if (platoBichitos == maxBichitos) {
			System.out.println("El plato está lleno de bichitos\n");
			puedeCazar.acquire();
			System.out.println("El pollo " + id + " se come un bichito\n");
			Thread.sleep(2000);
			platoBichitos--;
			mutex.release();
			System.out.println("Ahora quedan " + platoBichitos + " en el plato\n");
		} else {
			System.out.println("No quedan bichitos en el plato y el pollo " + id + " pia a los papás pájaro\n");
			mutex.release();
			puedeCazar.release();
			Thread.sleep(3000);
			// No hace nada (pia)
		}
	}
	
	public void cazarBichito(int id) throws InterruptedException {
		this.mutex.acquire();
		this.puedeCazar.acquire();
		if(platoBichitos < maxBichitos) {
			Thread.sleep(2000);
			platoBichitos++;
			System.out.println("El pájaro " + id + " ha cazado un bichito\n");
			System.out.println("Ahora hay " + platoBichitos + " bichitos en el plato\n");
			mutex.release();
		} else {
			// No hace nada
		}
	}
}
