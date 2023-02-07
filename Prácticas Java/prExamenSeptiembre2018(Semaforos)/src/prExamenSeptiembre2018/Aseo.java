package prExamenSeptiembre2018;

import java.util.concurrent.Semaphore;

public class Aseo {
	
	private int nHombres;
	private int nMujeres;
	private boolean noHombres;
	private boolean noMujeres;
	private Semaphore puedeHombre;
	private Semaphore puedeMujer;
	private Semaphore puedeGeneral;
	private Semaphore espera;
	
	public Aseo() {
		this.nHombres = 0;
		this.nMujeres = 0;
		this.noHombres = false;
		this.noMujeres = false;
		this.puedeHombre = new Semaphore(1, true);
		this.puedeMujer = new Semaphore(1, true);
		this.puedeGeneral = new Semaphore(1, true);
		this.espera = new Semaphore(0, true);
	}

	
	/**
	 * El hombre id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay alguna mujer en ese
	 * momento en el aseo
	 */
	public void llegaHombre(int id) throws InterruptedException {
		if (nMujeres > 0) {
			puedeHombre.acquire();
		} else {
			puedeGeneral.acquire();
			if (noHombres == true) {
				puedeGeneral.release();
				espera.acquire();
			}
			if (nHombres == 0) {
				noMujeres = true;
			}
			nHombres++;
			System.out.println("El HOMBRE " + id + " ha entrado en el aseo y ahora hay " + nHombres + " hombres y " + nMujeres + " mujeres dentro\n");
			puedeGeneral.release();
			puedeHombre.release();
		}
	}
	/**
	 * La mujer id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay algun hombre en ese
	 * momento en el aseo
	 */
	public void llegaMujer(int id) throws InterruptedException {
		if (nHombres > 0) {
			puedeMujer.acquire();
		} else {
			puedeGeneral.acquire();
			if (noMujeres == true) {
				puedeGeneral.release();
				espera.acquire();
			}
			if (nMujeres == 0) {
				noHombres = true;
			}
			nMujeres++;
			System.out.println("La MUJER " + id + " ha entrado en el aseo y ahora hay " + nHombres + " hombres y " + nMujeres + " mujeres dentro\n");
			puedeGeneral.release();
			puedeMujer.release();
		}
	}
	/**
	 * El hombre id, que estaba en el aseo, sale
	 */
	public void saleHombre(int id)throws InterruptedException {
		puedeGeneral.acquire();
		nHombres--;
		System.out.println("El HOMBRE " + id + " ha salido del aseo y ahora quedan " + nHombres + " hombres y " + nMujeres + " mujeres dentro\n");
		puedeGeneral.release();
		if (nHombres == 0) {
			espera.release();
		}
	}
	
	public void saleMujer(int id)throws InterruptedException {
		puedeGeneral.acquire();
		nMujeres--;
		System.out.println("La MUJER " + id + " ha salido del aseo y ahora quedan " + nHombres + " hombres y " + nMujeres + " mujeres dentro\n");
		puedeGeneral.release();
		if (nMujeres == 0) {
			espera.release();
		}
	}
}
