package prAPracticaThreadsSensores;
import java.util.concurrent.Semaphore;

public class Entorno {
	private Semaphore[] puedeMedir;
	private Semaphore puedeTrabajar;
	private int medidasTomadas;
	
	public Entorno() {
		this.puedeTrabajar = new Semaphore(0, true);
		this.medidasTomadas = 0;
		this.puedeMedir = new Semaphore[3];
		for (int i = 0; i < puedeMedir.length; i++) {
			puedeMedir[i] = new Semaphore(1, true);
		}
	}
	
	public void tomarMediciones(int id, int temperatura, int humedad, int luz) throws InterruptedException {
		puedeMedir[id].acquire();
		System.out.println("El sensor " + id + " está tomando medidas...\n");
		Thread.sleep(2000);
		System.out.println("El sensor " + id + " mide " + temperatura + " grados\n");
		Thread.sleep(1000);
		System.out.println("El sensor " + id + " mide un nivel " + humedad + " de humedad\n");
		Thread.sleep(1000);
		System.out.println("El sensor " + id + " mide un nivel " + luz + " de luz\n");
		System.out.println("El sensor " + id + " ha terminado de hacer mediciones\n");
		this.medidasTomadas++;
		if(this.medidasTomadas == 3) {
			puedeTrabajar.release();
		}
	}
	
	public void trabajar() throws InterruptedException {
		puedeTrabajar.acquire();
		System.out.println("El trabajador está trabajando duramente....\n");
		Thread.sleep(3000);
		System.out.println("El trabajador terminó de trabajar");
		this.medidasTomadas = 0;
		for (int i = 0; i < puedeMedir.length; i++) {
			puedeMedir[i].release();
		}
		System.out.println("Los sensores ya pueden comenzar a medir de nuevo\n");
	}
}
