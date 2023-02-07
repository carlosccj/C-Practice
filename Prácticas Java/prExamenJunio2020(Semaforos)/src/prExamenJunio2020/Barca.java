package prExamenJunio2020;

import java.util.concurrent.Semaphore;

public class Barca {
	private static final int CAPACIDAD = 3;
	private int nPasajeros;
	private int orillaBarca;
	private Semaphore orilla0;
	private Semaphore orilla1;
	private Semaphore mutex;
	private Semaphore puedeSubir;
	private Semaphore puedeBajar;
	private Semaphore puedeViajar;
	
	public Barca() {
		this.nPasajeros = 0;
		this.orillaBarca = 0;
		this.mutex = new Semaphore(1, true);
		this.puedeSubir = new Semaphore(1, true);
		this.puedeBajar = new Semaphore(0, true);
		this.puedeViajar = new Semaphore(0, true);
		this.orilla0 = new Semaphore(1, true);
		this.orilla1 = new Semaphore(0, true);
	}

	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public void subir(int id,int pos) throws InterruptedException{
		if (pos == 0) {
			orilla0.acquire();
		} else {
			orilla1.acquire();
		}
		puedeSubir.acquire();
		mutex.acquire();
		nPasajeros++;
		System.out.println("El pasajero " + id + " ha subido a la barca desde la orilla " + pos);
		System.out.println("Ahora hay " + nPasajeros + " en la barca\n");
		if (nPasajeros < CAPACIDAD) {
			mutex.release();
			puedeSubir.release();
			if (pos == 0) {
				orilla0.release();
			} else {
				orilla1.release();
			}
		} else {
			mutex.release();
			puedeViajar.release();
			if (pos == 0) {
				orilla1.release();
			} else {
				orilla0.release();
			}
		}
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public int bajar(int id) throws InterruptedException{
		puedeBajar.acquire();
		mutex.acquire();
		nPasajeros--;
		if (nPasajeros > 0) {
			System.out.println("El pasajero " + id + " se ha bajado de la barca");
			System.out.println("Ahora quedan " + nPasajeros + " en la barca\n");
			mutex.release();
			puedeBajar.release();
		} else {
			System.out.println("El pasajero " + id + " se ha bajado de la barca");
			System.out.println("Ya no quedan pasajeros en la barca\n");
			mutex.release();
			puedeSubir.release();
		}
		return orillaBarca;
	}
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public  void esperoSuban() throws InterruptedException{
		puedeViajar.acquire();
		System.out.println("Ha comenzado el viaje desde la ORILLA " + orillaBarca);
		Thread.sleep(5000);
	}
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public void finViaje() throws InterruptedException{
		orillaBarca = (orillaBarca + 1) % 2;
		System.out.println("El viaje ha terminado en la ORILLA " + orillaBarca + "\n");
		puedeBajar.release();
	}

}
