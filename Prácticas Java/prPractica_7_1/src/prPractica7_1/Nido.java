package prPractica7_1;

import java.util.concurrent.Semaphore;

public class Nido {
	private int maxBichos;
	private int bichos;
	private Semaphore puedeComer;
	private Semaphore puedePoner;
	private Semaphore mutex; //para controlar que polluelos y pajaros no alteren bichos al mismo tiempo
	
	public Nido(int maxBichos) {
		this.bichos = 0;
		this.maxBichos = maxBichos;
		this.mutex = new Semaphore(1, true);
		this.puedeComer = new Semaphore(0, true);
		this.puedePoner = new Semaphore(1, true);
	}
	
	public void depositarBicho(int id) throws InterruptedException {
		this.puedePoner.acquire();
		this.mutex.acquire();
		this.bichos++;
		System.out.println("El pajaro " + id + " ha depositado un bicho\n");
		System.out.println("Ahora hay " + this.bichos + " en el nido\n");
		if(this.bichos == 1) {
			this.puedeComer.release();
		} else if(this.bichos < this.maxBichos) {
			this.puedePoner.release();
		} else if(this.bichos == this.maxBichos) {
			System.out.println("El nido esta lleno\n");
		}
		this.mutex.release();
	}
	
	public void comerBicho(int id) throws InterruptedException {
		this.puedeComer.acquire();
		this.mutex.acquire();
		this.bichos--;
		System.out.println("El polluelo " + id + " se ha comido un bichito\n");
		System.out.println("Ahora hay " + this.bichos + " en el nido\n");
		if(this.bichos > 0) {
			this.puedeComer.release();
		} else if(this.bichos + 1 == this.maxBichos) { // cuando nido esta lleno los pajaros se quedan dormidos
			this.puedePoner.release();
		} else if(this.bichos == 0) {
			System.out.println("No hay pajaros en el nido\n");
		}
		this.mutex.release();
	}

}
