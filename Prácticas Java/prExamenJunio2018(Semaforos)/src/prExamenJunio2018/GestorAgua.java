package prExamenJunio2018;


import java.util.concurrent.*;

public class GestorAgua {
	private int nOxigeno;
	private int nHidrogeno;
	private Semaphore puedeOxigeno;
	private Semaphore puedeHidrogeno;
	private Semaphore puedeMolecula;
	private Semaphore moleculaFormada;
	
	public GestorAgua() {
		this.nOxigeno = 0;
		this.nHidrogeno = 0;
		this.puedeOxigeno = new Semaphore(1, true);
		this.puedeHidrogeno = new Semaphore(1, true);
		this.puedeMolecula = new Semaphore(1, true);
		this.moleculaFormada = new Semaphore(0, true);
	}
	
	
	public void hListo(int id) throws InterruptedException {
		puedeHidrogeno.acquire();
		puedeMolecula.acquire();
		nHidrogeno++;
		System.out.println("El hidrogeno " + id + " ha entrado y ahora hay " + nOxigeno + " oxigenos y " + nHidrogeno + " hidrogenos");
		puedeMolecula.release();
		if (nHidrogeno < 2) {
			puedeHidrogeno.release();
		}
		
		if (nHidrogeno + nOxigeno < 3) {
			System.out.println("El hidrogeno " + id + " esta esperando a otras moleculas...\n");
			moleculaFormada.acquire();
		} else {
			System.out.println("UNA MOLECULA HA SIDO FORMADA\n");
			nHidrogeno = 0;
			nOxigeno = 0;
			moleculaFormada.release();
			puedeHidrogeno.release();
			puedeOxigeno.release();
		}
	}
	
	public void oListo(int id) throws InterruptedException { 
		puedeOxigeno.acquire();
		puedeMolecula.acquire();
		nOxigeno++;
		System.out.println("El oxigeno " + id + " ha entrado y ahora hay " + nOxigeno + " oxigenos y " + nHidrogeno + " hidrogenos");
		puedeMolecula.release();
		
		if (nHidrogeno + nOxigeno < 3) {
			System.out.println("El oxigeno " + id + " esta esperando a otras moleculas...\n");
			moleculaFormada.acquire();
		} else {
			System.out.println("UNA MOLECULA HA SIDO FORMADA\n");
			nHidrogeno = 0;
			nOxigeno = 0;
			moleculaFormada.release();
			puedeHidrogeno.release();
			puedeOxigeno.release();
		}
	}
}