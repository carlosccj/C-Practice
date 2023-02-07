import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Coche {
	private static final int CAPACIDAD = 4;
	private int nPasajeros;
	private ReentrantLock l;
	private Condition puedeSubir;
	private Condition puedeBajar;
	private Condition puedeViajar;
	
	public Coche() {
		this.nPasajeros = 0;
		this.l = new ReentrantLock();
		this.puedeBajar = l.newCondition();
		this.puedeSubir = l.newCondition();
		this.puedeViajar = l.newCondition();
	}
	
	public void quieroSubir(int id) throws InterruptedException {
		l.lock();
		try {
			while (nPasajeros == CAPACIDAD) {
				puedeSubir.await();
			}
			nPasajeros++;
			if (nPasajeros < CAPACIDAD) {
				System.out.println("El pasajero " + id + " ha SUBIDO al coche y ahora hay " + nPasajeros + " PASAJEROS");
			} else {
				System.out.println("El pasajero " + id + " ha SUBIDO al coche y el coche esta LLENO\n");
				puedeViajar.signal();
			}
		} finally {
			l.unlock();
		}
	}

	public void quieroBajar(int id) throws InterruptedException {
		l.lock();
		try {
			puedeBajar.await();
			nPasajeros--;
			if (nPasajeros > 0) {
				System.out.println("El pasajero " + id + " ha BAJADO del coche y ahora quedan " + nPasajeros + " dentro");
			} else {
				System.out.println("El pasajero " + id + " ha BAJADO del coche y ahora el coche esta VACIO\n");
				puedeSubir.signalAll();
			}
		} finally {
			l.unlock();
		}
	}
	
	public void inicioViaje() throws InterruptedException {		
		l.lock();
		try {
			puedeViajar.await();
			System.out.println("El viaje ha COMENZADO!!!");
		} finally {
			l.unlock();
		}
	}
	
	public void finViaje() throws InterruptedException {							
		l.lock();
		try {
			System.out.println("El viaje ha TERMINADO!!!\n");
			puedeBajar.signalAll();
		} finally {
			l.unlock();
		}
	}
}
