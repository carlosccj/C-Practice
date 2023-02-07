package prPractica_6_2;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Entorno {
	
	private Semaphore[] trabajoRealizado;
	private Semaphore[] puedeLeer;
	
	public Entorno () {
		trabajoRealizado = new Semaphore[3];
		puedeLeer = new Semaphore[3];
		for (int i = 0; i < 3; i++) {
			puedeLeer[i] = new Semaphore(1, true);
		}
		for (int i = 0; i < 3; i++) {
			trabajoRealizado[i] = new Semaphore(0, true);
		}
	}
	
	public void trabajar() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			trabajoRealizado[i].acquire();
		}
		System.out.println("Trabajador está recogiendo datos de los sensores...\n");
		Thread.sleep(2000);
		System.out.println("Trabajo realizado\n");
		for (int i = 0; i < 3; i++) {
			puedeLeer[i].release();
		}
	}
	
	public void leerValorEntorno (int id) throws InterruptedException {
		puedeLeer[id].acquire();
		if (id == 0) {
			System.out.println("Sensor de temperatura realizando lectura\n");
			Thread.sleep(2000);
			System.out.println("Sensor de temperatura lee el valor..." + new Random().nextInt(40));
			trabajoRealizado[0].release();
		} else if (id == 1) {
			System.out.println("Sensor de humedad realizando lectura\n");
			Thread.sleep(2000);
			System.out.println("Sensor de humedad lee el valor..." + new Random().nextInt(100));
			trabajoRealizado[1].release();
		} else {
			System.out.println("Sensor de luz realizando lectura\n");
			Thread.sleep(2000);
			System.out.println("Sensor de luz lee el valor..." + new Random().nextInt(100));
			trabajoRealizado[2].release();
		}
	}
}
