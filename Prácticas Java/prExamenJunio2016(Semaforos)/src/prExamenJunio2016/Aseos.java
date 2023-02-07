package prExamenJunio2016;

import java.util.concurrent.Semaphore;

public class Aseos {
	private int clientesDentro;
	private boolean quiereLimpieza;
	private Semaphore puedeCliente;
	private Semaphore mutexCliente;
	private Semaphore puedeLimpieza;
	
	public Aseos() {
		this.clientesDentro = 0;
		this.quiereLimpieza = false;
		this.puedeCliente = new Semaphore(0, true);
		this.mutexCliente = new Semaphore(1, true);
		this.puedeLimpieza = new Semaphore(1, true);
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
		puedeCliente.acquire();
		mutexCliente.acquire();
		clientesDentro++;
		System.out.println("El cliente " + id + " ha ENTRADO en los aseos y ahora hay " + clientesDentro + " clientes dentro\n");
		mutexCliente.release();
		if (quiereLimpieza != true) {
			puedeCliente.release();
		}
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException {
		mutexCliente.acquire();
		clientesDentro--;
		System.out.println("El cliente " + id + " ha SALIDO de los aseos y ahora hay " + clientesDentro + " clientes dentro\n");
		mutexCliente.release();
		if (clientesDentro == 0 && quiereLimpieza == true) {
			puedeLimpieza.release();
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
    	System.out.println("El EQUIPO DE LIMPIEZA quiere entrar en los aseos...\n");
    	quiereLimpieza = true;
    	puedeLimpieza.acquire();
    	System.out.println("El EQUIPO DE LIMPIEZA ha ENTRADO en los aseos...\n");
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
     * @throws InterruptedException 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza() throws InterruptedException{
    	System.out.println("El EQUIPO DE LIMPIEZA ha SALIDO de los aseos...\n");
    	quiereLimpieza = false;
    	puedeCliente.release();
	}
}
