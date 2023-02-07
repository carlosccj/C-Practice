
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Gestor {
	
	ReentrantLock l;
	private int impA;
	private int impB;
	private Condition puedeUsarA;
	private Condition puedeUsarB;
	private Condition puedeUsarAB;

	
	public Gestor(int impA, int impB) {
		l = new ReentrantLock();
		this.impA = impA;
		this.impB = impB;
		puedeUsarA = l.newCondition();
		puedeUsarB = l.newCondition();
		puedeUsarAB = l.newCondition();
	}
	
	public void imprimirA(int id) throws InterruptedException{
		l.lock();
		try {
			while (impA == 0) {
				System.out.println("Documento " + id + " esperando impresora A\n");
				puedeUsarA.await();
			}
			impA--; // Mensaje de impresion lo pone la hebra
		} finally {
			l.unlock();
		}
	}
	
	public void imprimirB(int id) throws InterruptedException {
		l.lock();
		try {
			while (impB == 0) {
				System.out.println("Documento " + id + " esperando impresora B\n");
				puedeUsarB.await();
			}
			impB--; // Mensaje de impresion lo pone la hebra
		} finally {
			l.unlock();
		}
	}
	
	public int imprimirAB(int id) throws InterruptedException {
		l.lock();
		int impresoraAsignada;
		try {
			while (impA == 0 && impB == 0) {
				System.out.println("Documento " + id + " esperando impresora (tipo AB)\n");
				puedeUsarAB.await();
			}
			if (impA > 0) {
				impA--; 
				impresoraAsignada = 0;
			} else {
				impB--;
				impresoraAsignada = 1;
			}
		} finally {
			l.unlock();
		}
		return impresoraAsignada;
	}
	
	public void liberar(int id, int tipo) { //tipo 0 => imp A || tipo 1=> impB
		l.lock();
		try {
			System.out.println("Doc. " + id + " libera una impresora tipo " + tipo);
			if (tipo == 0) {
				impA++;
				// Despertamos a ambas pero en realidad solo va a entrar una
				puedeUsarA.signal();
				puedeUsarAB.signal();
			} else {
				impB++;
				// Despertamos a ambas pero en realidad solo va a entrar una
				puedeUsarB.signal();
				puedeUsarAB.signal();
			}
		} finally {
			l.unlock();
		}
	}

}
