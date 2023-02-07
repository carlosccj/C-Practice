package prExamenJunio2016;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Aseos {
	private int clientesDentro;
	private boolean quiereLimpieza;
	private ReentrantLock l;
	private Condition puedeCliente;
	private Condition puedeLimpieza;
	
	public Aseos() {
		this.clientesDentro = 0;
		this.quiereLimpieza = false;
		this.l = new ReentrantLock();
		this.puedeCliente = l.newCondition();
		this.puedeLimpieza = l.newCondition();
	}
	
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza está trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza está trabajando o
	 * está esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		l.lock();
		try {
			while (quiereLimpieza == true) {
				puedeCliente.await();
			}
			clientesDentro++;
			System.out.println("El cliente " + id + " ha ENTRADO en los aseos y ahora hay " + clientesDentro + " clientes dentro\n");
		} finally {
			l.unlock();
		}
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException{
		l.lock();
		try {
			clientesDentro--;
			System.out.println("El cliente " + id + " ha SALIDO de los aseos y ahora hay " + clientesDentro + " clientes dentro\n");
			if (quiereLimpieza == true && clientesDentro == 0) {
				puedeLimpieza.signal();
			}
		} finally {
			l.unlock();
		}
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo está solo en los aseos, es decir, espera hasta que no
	 * haya ningún cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
    	l.lock();
    	try {
    		quiereLimpieza = true;
    		System.out.println("El EQUIPO DE LIMPIEZA desea entrar a los aseos...\n");
    		while (clientesDentro != 0) {
        		puedeLimpieza.await();
    		}
    		System.out.println("El EQUIPO DE LIMPIEZA ha ENTRADO en los aseos....\n");
    	} finally {
    		l.unlock();
    	}
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
     * @throws InterruptedException 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza() throws InterruptedException{
    	l.lock();
    	try {
    		System.out.println("El EQUIPO DE LIMPIEZA ha SALIDO de los aseos...\n");
    		puedeCliente.signalAll();
    		quiereLimpieza = false;
    	} finally {
    		l.unlock();
    	}
	}
}
