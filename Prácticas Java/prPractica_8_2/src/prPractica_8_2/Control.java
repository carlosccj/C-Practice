package prPractica_8_2;

import java.util.ArrayList;

public class Control {
	private ArrayList<Integer> peticiones;
	private int totalRecursos;
	
	public Control (int totalRecursos) {
		this.totalRecursos = totalRecursos;
		this.peticiones = new ArrayList<Integer>();
	}
	
	public synchronized void quieroRecursos (int id, int numRecursos) throws InterruptedException {
		System.out.println("Proceso " + id + " quiere " + numRecursos + "\n");
		this.peticiones.add(id); // Metemos en la lista de prioridad al proceso id (siempre al final de la lista)
		while (peticiones.get(0) != id || numRecursos > totalRecursos) {
			wait();
		}
		System.out.println("Proceso " + id + "solicita " + numRecursos + " recursos\n");
		totalRecursos -= numRecursos;
		System.out.println("Recursos disponibles: " + this.totalRecursos + "\n");
	}
	
	public synchronized void liberarRecursos (int id, int numRecursos) {
		this.totalRecursos += numRecursos;
		System.out.println("Proceso " + id + " libera " + numRecursos + "\n");
		System.out.println("Disponibles: " + this.totalRecursos + "\n");
		peticiones.remove(0);
		notifyAll();
	}

}
