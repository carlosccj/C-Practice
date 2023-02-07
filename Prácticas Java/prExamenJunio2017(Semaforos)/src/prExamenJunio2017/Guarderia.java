package prExamenJunio2017;
import java.util.concurrent.Semaphore;

public class Guarderia {
	private int nBebes;
	private int nAdultos;
	private Semaphore mutexBebe;
	private Semaphore mutexEntrar;
	private Semaphore mutexSalir;
	private Semaphore puedeEntrarBebe;
	private Semaphore mutexAdulto;
	private Semaphore puedeSalirAdulto;
	
	public Guarderia() {
		this.nBebes = 0;
		this.mutexEntrar = new Semaphore(1, true);
		this.mutexSalir = new Semaphore(1, true);
		this.nAdultos = 0;
		this.mutexBebe = new Semaphore(1, true);
		this.puedeEntrarBebe = new Semaphore(0, true);
		this.mutexAdulto = new Semaphore(1, true);
		this.puedeSalirAdulto = new Semaphore(0, true);
	}
	
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuado entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		puedeEntrarBebe.acquire();
		mutexEntrar.acquire();
		mutexBebe.acquire();
		nBebes++;
		System.out.println("El BEBE " + id + " ha ENTRADO en la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
		mutexBebe.release();
		mutexEntrar.release();
		if (nAdultos * 3 > nBebes) {
			puedeEntrarBebe.release();
		}
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		mutexBebe.acquire();
		mutexSalir.acquire();
		nBebes--;
		System.out.println("El BEBE " + id + " ha SALIDO de la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
		if ((nAdultos - 1) * 3 >= nBebes) {
			mutexBebe.release();
			puedeSalirAdulto.release();
		} else {
			mutexBebe.release();
		}
		mutexSalir.release();
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		mutexAdulto.acquire();
		mutexEntrar.acquire();
		nAdultos++;
		System.out.println("El ADULTO " + id + " ha ENTRADO en la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
		mutexEntrar.release();
		if (nAdultos * 3 > nBebes) {
			mutexAdulto.release();
			puedeEntrarBebe.release();
		} else {
			mutexAdulto.release();
		}
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		puedeSalirAdulto.acquire();
		mutexSalir.acquire();
		mutexAdulto.acquire();
		nAdultos--;
		System.out.println("El ADULTO " + id + " ha SALIDO de la guarderia y ahora hay " + nBebes + " bebes y " + nAdultos + " adultos\n");
		if ((nAdultos - 1) * 3 >= nBebes) {
			puedeSalirAdulto.release();
		}
		mutexAdulto.release();
		mutexSalir.release();
	}

}
