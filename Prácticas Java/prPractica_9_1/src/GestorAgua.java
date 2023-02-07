import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GestorAgua {
	
	ReentrantLock l;
	private Condition moleculaFormada;
	private Condition puedePonerO;
	private Condition puedePonerH;
	private int atomosH;
	private boolean atomosO;

	public GestorAgua () {
		l = new ReentrantLock();
		puedePonerH = l.newCondition();
		puedePonerO = l.newCondition();
		moleculaFormada = l.newCondition();
		atomosH = 0;
		atomosO = false;
	}
	
	public void hListo(int id) throws InterruptedException {
		//ENTRA UN ATOMO DE HIDROGENO
		l.lock();
		try {
			while (atomosH == 2) {
				puedePonerH.await();
			}
			atomosH++;
			System.out.println("Tenemos " + atomosH + " atomos de hidrogeno\n");
			if (!atomosO || atomosH == 1) {
				System.out.println("Esperando atomos para formar molecula\n");
				moleculaFormada.await();
			} else {
				System.out.println("Molecula formada en H\n");
				atomosH = 0;
				atomosO = false;
				puedePonerH.signalAll(); // Despierto a TODAS las moleculas de hidrogeno
				puedePonerO.signal(); // Solo necesito despertar a UNA molecula de oxigeno
				moleculaFormada.signalAll(); // Valdria tambien con DOS signals (uno para cada molecula que queda esperando)
			}
		} finally {
			l.unlock();
		}
	}
	
	public void oListo(int id) throws InterruptedException { 
		//ENTRA UN ATOMO DE OXIGENO
		l.lock();
		try {
			while (atomosO == true) {
				puedePonerO.await();
			}
			atomosO = true;
			System.out.println("Ya tenemos un atomo de Oxigeno\n");
			if (atomosH < 2) {
				System.out.println("Esperando atomos para formar molecula\n");
				moleculaFormada.await();
			} else {
				System.out.println("Molecula formada en O\n");
				atomosH = 0;
				atomosO = false;
				puedePonerH.signalAll();
				puedePonerO.signal();
				moleculaFormada.signal();
			}
		} finally {
			l.unlock();
		}

	}
}