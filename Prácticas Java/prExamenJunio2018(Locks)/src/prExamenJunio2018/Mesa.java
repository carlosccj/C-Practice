package prExamenJunio2018;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
	private static final int MAX_RACIONES = 10;
	private int nRaciones;
	private int llamador;
	private boolean preparado;
	private boolean haLlegado;
	private ReentrantLock l;
	private Condition puedeComer;
	private Condition puedeRepartir;
	private Condition puedePagar;
	private Condition puedeIrse;
	
	public Mesa() {
		this.nRaciones = 0;
		this.llamador = 0;
		this.preparado = true;
		this.haLlegado = false;
		this.l = new ReentrantLock();
		this.puedeComer = l.newCondition();
		this.puedeRepartir = l.newCondition();
		this.puedePagar = l.newCondition();
		this.puedeIrse = l.newCondition();
	}
	
	
	/**
	 * 
	 * @param id
	 * El estudiante id quiere una raci�n de pizza. 
	 * Si hay una raci�n la coge y sigue estudiante.
	 * Si no hay y es el primero que se da cuenta de que la mesa est� vac�a
	 * llama al pizzero y
	 * espera hasta que traiga una nueva pizza. Cuando el pizzero trae la pizza
	 * espera hasta que el estudiante que le ha llamado le pague.
	 * Si no hay pizza y no es el primer que se da cuenta de que la mesa est� vac�a
	 * espera hasta que haya un trozo para �l.
	 * @throws InterruptedException 
	 * 
	 */
	public void nuevaRacion(int id) throws InterruptedException{
		l.lock();
		try {
			while (nRaciones == 0 && llamador > 0) {
				puedeComer.await();
			}
			if (nRaciones == 0) {
				llamador++;
				while (preparado == false) {
					puedePagar.await();
				}
				puedeRepartir.signal();
				while (haLlegado == false) {
					System.out.println("El estudiante " + id + " ha llamado al pizzero y espera para pagar\n");
					puedePagar.await();
				}
				puedeIrse.signal();
				nRaciones--;
				System.out.println("El estudiante que ha llamado (" + id + ") se come la primera racion\n");
				llamador--;
				puedeComer.signalAll();
			} else if (nRaciones > 0) {
				nRaciones--;
				System.out.println("El estudiante " + id + " ha comida una raci�n y ahora quedan " + nRaciones + " raciones\n");
				puedeComer.signalAll();
			}
		} finally {
			l.unlock();
		}
	}


	/**
	 * El pizzero entrega la pizza y espera hasta que le paguen para irse
	 * @throws InterruptedException 
	 */
	public void nuevoCliente() throws InterruptedException {
		l.lock();
		try {
			System.out.println("El pizzero ha llegado con una nueva pizza\n");
			haLlegado = true;
			puedePagar.signal();
			puedeIrse.await();
			nRaciones = MAX_RACIONES;
			System.out.println("El pizzero se ha marchado\n");
			preparado = false;
			haLlegado = false;
		} finally {
			l.unlock();
		}
	}
	

/**
	 * El pizzero espera hasta que alg�n cliente le llama para hacer una pizza y
	 * llev�rsela a domicilio
	 * @throws InterruptedException 
	 */
	public void nuevaPizza() throws InterruptedException {
		l.lock();
		try {
			preparado = true;
			puedePagar.signal();
			System.out.println("El pizzero espera un pedido...\n");
			puedeRepartir.await();
			System.out.println("El pizzero esta en camino...\n");
		} finally {
			l.unlock();
		}
	}
}
