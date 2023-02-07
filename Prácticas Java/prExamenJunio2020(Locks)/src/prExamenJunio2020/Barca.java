package prExamenJunio2020;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barca {
	private static final int CAPACIDAD = 3;
	private int orilla;
	private int nPasajeros;
	private ReentrantLock l;
	private Condition puedeSubir0;
	private Condition puedeSubir1;
	private Condition puedeBajar;
	private Condition puedeViajar;
	
	public Barca() {
		this.orilla = 0;
		this.nPasajeros = 0;
		this.l = new ReentrantLock();
		this.puedeSubir0 = l.newCondition();
		this.puedeSubir1 = l.newCondition();
		this.puedeBajar = l.newCondition();
		this.puedeViajar = l.newCondition();
	}
	
	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public void subir(int id,int pos) throws InterruptedException{
		l.lock();
		try {
			while (pos != orilla && pos == 0 || nPasajeros == CAPACIDAD) {
				puedeSubir0.await();
			} 
			while (pos != orilla && pos == 1 || nPasajeros == CAPACIDAD) {
				puedeSubir1.await();
			}
			nPasajeros++;
			System.out.println("El pasajero " + id + " ha SUBIDO a la barca y ahora hay " + nPasajeros + " pasajeros\n");
			if (nPasajeros == CAPACIDAD) {
				puedeViajar.signal();
			}
		} finally {
			l.unlock();
		}
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public int bajar(int id) throws InterruptedException{
		l.lock();
		try {
			puedeBajar.await();
			nPasajeros--;
			System.out.println("El pasajero " + id + " HA BAJADO de la barca y ahora hay " + nPasajeros + " pasajeros dentro\n");
			if (nPasajeros == 0) {
				System.out.println("Ya NO QUEDAN pasajero en la barca\n");
				if (orilla == 0) {
					puedeSubir0.signalAll();
				} else if (orilla == 1) {
					puedeSubir1.signalAll();
				}
			}
		} finally {
			l.unlock();
		}
		return orilla;
	}
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public  void esperoSuban() throws InterruptedException{
		l.lock();
		try {
			System.out.println("El barquero espera a que los pasajero suban a la barca...\n");
			puedeViajar.await();
			System.out.println("La barca esta llena, COMIENZA EL VIAJE desde la orilla " + orilla + "\n");
		} finally {
			l.unlock();
		}
	}
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public void finViaje() throws InterruptedException{
		l.lock();
		try {
			orilla = (orilla + 1) % 2;
			System.out.println("La barca HA LLEGADO a la orilla " + orilla + " y el viaje HA TERMINADO\n");
			puedeBajar.signalAll();
		} finally {
			l.unlock();
		}
	}

}
