package prExamenJunio2017;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Guarderia {
	private int nBebes;
	private int nAdultos;
	ReentrantLock l;
	Condition puedeEntrarBebe;
	Condition puedeSalirAdulto;
	// Los adultos siempre pueden entrar, los bebes siempre pueden salir
	
	public Guarderia() {
		this.nBebes = 0;
		this.nAdultos = 0;
		this.l = new ReentrantLock();
		this.puedeEntrarBebe = l.newCondition();
		this.puedeSalirAdulto = l.newCondition();
	}
	
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		l.lock();
		try {
			while (nAdultos * 3 <= nBebes) {
				puedeEntrarBebe.await();
			}
			nBebes++;
			System.out.println("El BEBE " + id + " ha ENTRADO en la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
		} finally {
			l.unlock();
		}
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		l.lock();
		try {
			nBebes--;
			System.out.println("El BEBE " + id + " ha SALIDO de la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
			if ((nAdultos - 1) * 3 >= nBebes) {
				puedeSalirAdulto.signalAll();
			}
		} finally {
			l.unlock();
		}	
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		l.lock();
		try {
			nAdultos++;
			System.out.println("El ADULTO " + id + " ha ENTRADO en la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
			if (nAdultos * 3 > nBebes) {
				puedeEntrarBebe.signalAll();
			}
		} finally {
			l.unlock();
		}
		
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		l.lock();
		try {
			while ((nAdultos - 1) * 3 < nBebes) {
				puedeSalirAdulto.await();
			}
			nAdultos--;
			System.out.println("El ADULTO " + id + " ha SALIDO de la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
		} finally {
			l.unlock();
		}
		
	}

}
