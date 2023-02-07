package prExamenJunio2019;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tiovivo {
	private int capacidad;
	private int nPasajeros;
	private boolean viajando;
	private ReentrantLock l;
	private Condition puedeSubir;
	private Condition puedeBajar;
	private Condition puedeViajar;
	
	public Tiovivo(int capacidad) {
		this.capacidad = capacidad;
		this.nPasajeros = 0;
		this.viajando = false;
		this.l = new ReentrantLock();
		this.puedeSubir = l.newCondition();
		this.puedeBajar = l.newCondition();
		this.puedeViajar = l.newCondition();
	}
		
	
	public void subir(int id) throws InterruptedException 
	{	
		l.lock();
		try {
			if (nPasajeros == capacidad) {
				puedeSubir.await();
			}
			nPasajeros++;
			System.out.println("El pasajero " + id + " ha SUBIDO al Tiovivo y ahora hay " + nPasajeros + " pasajeros");
			if (nPasajeros == capacidad) {
				viajando = true;
				puedeViajar.signal();
			} 
		} finally {
			l.unlock();
		}
	}
	
	public void bajar(int id) throws InterruptedException 
	{
		l.lock();
		try {
			puedeBajar.await();
			nPasajeros--;
			System.out.println("El pasajero " + id + " ha BAJADO del Tiovivo y ahora quedan " + nPasajeros + " pasajeros");
			if (nPasajeros > 0) {
				puedeBajar.signalAll();
			} else {
				puedeSubir.signalAll();
			}
		} finally {
			l.unlock();
		}
	}
	
	public void esperaLleno () throws InterruptedException 
	{
		l.lock();
		try {
			System.out.println("El Tiovivo esta esperando...\n");
			puedeViajar.await();
			System.out.println("\nEl viaje ha COMENZADO!!!\n");
		} finally {
			l.unlock();
		}
	}
	
	public void finViaje () throws InterruptedException 
	{
		l.lock();
		try {
			System.out.println("El viaje ha TERMINADO!!!\n");
			puedeBajar.signalAll();
		} finally {
			l.unlock();
		}
	}
}
