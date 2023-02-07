package prExamenSeptiembre2020;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Curso {
	private static final int CAP_INI = 10;
	private static final int CAP_AVZ = 3;
	private int nAlumnosIni;
	private int nAlumnosAvz;
	private int grupo;
	private int alumnoAcabado;
	private boolean trabajando;
	private ReentrantLock l;
	private Condition puedeIni;
	private Condition puedeAvz;
	
	public Curso() {
		this.nAlumnosIni = 0;
		this.nAlumnosAvz = 0;
		this.grupo = 3;
		alumnoAcabado = 15;
		this.trabajando = false;
		this.l = new ReentrantLock();
		this.puedeIni = l.newCondition();
		this.puedeAvz = l.newCondition();
	}
	
	//El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de iniciacion
	public void esperaPlazaIniciacion(int id) throws InterruptedException{
		l.lock();
		try {
			while (nAlumnosIni == CAP_INI) {
				puedeIni.await();
			}
			nAlumnosIni++;
			System.out.println("El alumno " + id + " ha ENTRADO a FASE INICIAL y ahora hay " + nAlumnosIni + " alumnos en FASE INICIAL\n");
		} finally {
			l.unlock();
		}
	}

	//El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public void finIniciacion(int id) throws InterruptedException{
		l.lock();
		try {
			nAlumnosIni--;
			System.out.println("El alumno " + id + " ha SALIDO de FASE INICIAL y ahora quedan " + nAlumnosIni + " alumnos en FASE INICIAL\n");
		} finally {
			l.unlock();
		}		
	}
	
	/* El alumno tendra que esperar:
	 *   - si ya hay un grupo realizando la parte avanzada
	 *   - si todavia no estan los tres miembros del grupo conectados
	 */
	public void esperaPlazaAvanzado(int id) throws InterruptedException{
		l.lock();
		try {
			if (nAlumnosAvz >= CAP_AVZ && trabajando == false) {
				puedeAvz.signal();
				puedeAvz.signal();
				puedeAvz.signal();
				trabajando = true;
			}
			nAlumnosAvz++;
			System.out.println("El alumno " + id + " ha ENTRADO a FASE AVANZADA y ahora hay " + nAlumnosAvz + " alumnos esperando grupo\n");
			puedeAvz.await();
			nAlumnosAvz--;
			System.out.println("El alumno " + id + " COMIENZA su trabajo en FASE AVANZADA...\n");
		} finally {
			l.unlock();
		}
	}
	
	/* El alumno:
	 *   - informa que ya ha terminado de cursar la parte avanzada 
	 *   - espera hasta que los tres miembros del grupo hayan terminado su parte 
	 */ 
	public void finAvanzado(int id) throws InterruptedException{
		l.lock();
		try {
			grupo--;
			alumnoAcabado--; // ARREGLAR (el ultimo grupo no acaba)
			System.out.println("El alumno " + id + " ha SALIDO de FASE AVANZADA y ahora hay " + nAlumnosAvz + " alumnos esperando grupo\n");
			if (grupo == 0) {
				trabajando = false;
				grupo = CAP_AVZ;
			}
		} finally {
			l.unlock();
		}
	}
}
