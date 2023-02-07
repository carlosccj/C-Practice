package prExamenJunio2019;

import java.util.concurrent.Semaphore;

public class Tiovivo {
	private int capacidad;
	private int nPasajeros;
	private Semaphore mutex;
	private Semaphore puedeSubir;
	private Semaphore puedeBajar;
	private Semaphore puedeViajar;
	
	public Tiovivo(int capacidad) {
		this.capacidad = capacidad;
		this.nPasajeros = 0;
		this.mutex = new Semaphore(1, true);
		this.puedeSubir = new Semaphore(1, true);
		this.puedeBajar = new Semaphore(0, true);
		this.puedeViajar = new Semaphore(0, true);
	}
		
	
	public void subir(int id) throws InterruptedException 
	{	
		puedeSubir.acquire();
		mutex.acquire();
		nPasajeros++;
		if (nPasajeros < capacidad) {
			System.out.println("El pasajero " + id + " ha SUBIDO al Tiovivo y ahora hay " + nPasajeros + " pasajeros");
			mutex.release();
			puedeSubir.release();
		} else {
			System.out.println("El pasajero " + id + " ha SUBIDO al Tiovivo y ahora el Tiovivo esta LLENO\n");
			mutex.release();
			puedeViajar.release();
		}
	}
	
	public void bajar(int id) throws InterruptedException 
	{
		puedeBajar.acquire();
		mutex.acquire();
		nPasajeros--;
		if (nPasajeros > 0) {
			System.out.println("El pasajero " + id + " ha BAJADO del Tiovivo y ahora quedan " + nPasajeros + " pasajeros");
			mutex.release();
			puedeBajar.release();
		} else {
			System.out.println("El pasajero " + id + " ha BAJADO del Tiovivo y ahora el Tiovivo esta VACIO\n");
			mutex.release();
			puedeSubir.release();
		}
	}
	
	public void esperaLleno () throws InterruptedException 
	{
		puedeViajar.acquire();
		System.out.println("El viaje ha COMENZADO!!!\n");
		Thread.sleep(3000);
	}
	
	public void finViaje () 
	{
		System.out.println("El viaje ha TERMINADO!!!\n");
		puedeBajar.release();
	}
}
