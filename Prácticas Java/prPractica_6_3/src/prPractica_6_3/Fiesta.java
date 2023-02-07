package prPractica_6_3;

import java.util.concurrent.Semaphore;

public class Fiesta {
	private Caldero caldero;
	private Semaphore pasaCanibal;
	private Semaphore pasaCocinero;
	private Semaphore esperandoCocinero;
	
	public Fiesta (Caldero caldero) {
		this.caldero = caldero;
		pasaCanibal = new Semaphore(1, true);
		pasaCocinero = new Semaphore(0, true);
		esperandoCocinero = new Semaphore(0, true);
	}
	
	public void cocinar() throws InterruptedException {
		pasaCocinero.acquire();
		System.out.println("Cocinero está cocinando\n");
		caldero.llenarCaldero();
		System.out.println("Número de raciones " + caldero.gerRacionesPreparadas() + "\n");
		Thread.sleep(2000);
		esperandoCocinero.release();
	}
	
	public void comer(int id) throws InterruptedException {
		pasaCanibal.acquire();
		if(caldero.isCalderoVacio()) {
			System.out.println("El caldero está vacío\n");
			System.out.println("Caníbal " + id + " despertando cocinero\n");
			pasaCocinero.release();
			esperandoCocinero.acquire();
		}
		System.out.println("Canibal " + id + " está comiendo\n");
		Thread.sleep(2000);
		System.out.println("Canibal " + id + " ha acabado de comer\n");
		caldero.consumirRacion();
		System.out.println("numero de raciones: " + caldero.gerRacionesPreparadas() + "\n");
		pasaCanibal.release();
	}

}
