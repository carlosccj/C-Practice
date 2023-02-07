package prPractica_7_2;

import java.util.concurrent.Semaphore;

public class Coche extends Thread {
	private int pasajeros;
	private int capacidadMaxima;
	private Semaphore darVuelta;
	private Semaphore puedeSubir;
	private Semaphore puedeBajar;
	
	public Coche(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
		this.pasajeros = 0;
		this.darVuelta = new Semaphore(0, true);
		this.puedeSubir = new Semaphore(1, true);
		this.puedeBajar = new Semaphore(0, true);
	}
	
	public void finalizarViaje() {
		this.puedeBajar.release();
	}
	
	public void iniciarViaje() throws InterruptedException {
		this.darVuelta.acquire();
		System.out.println("Iniciando viaje\n");
		Thread.sleep(5000);
		System.out.println("Viaje finalizado\n");
		this.puedeBajar.release();
		//finalizarViaje();
	}
	
	public void quieroBajar(int id) throws InterruptedException {
		this.puedeBajar.acquire();
		System.out.println("El pasajero " + id + " ha bajado del coche");
		pasajeros--;
		if (this.pasajeros > 0) {
			this.puedeBajar.release();
		} else {
			this.puedeSubir.release();			
			System.out.println("El coche se ha vaciado\n");
		}
	}
	
	public void quieroSubir(int id) throws InterruptedException {
		this.puedeSubir.acquire();
		System.out.println("Pasajero " + id + " ha subidoal coche\n");
		pasajeros++;
		if (this.pasajeros < this.capacidadMaxima) {
			this.puedeSubir.release();
		} else {
			System.out.println("El ccohe esta lleno\n");
			this.darVuelta.release();
		}
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
				this.iniciarViaje();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
