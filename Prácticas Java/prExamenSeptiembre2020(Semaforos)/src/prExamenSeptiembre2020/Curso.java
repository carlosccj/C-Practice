package prExamenSeptiembre2020;

import java.util.concurrent.Semaphore;

public class Curso {
	private final int MAX_ALUMNOS_INI = 10;
	private final int ALUMNOS_AV = 3;
	
	private int alumnosIniciacion;
	private Semaphore mutexIniciacion;
	private Semaphore llenoIniciacion;
	
	private int alumnosAvanzado;
	private Semaphore mutexAvanzado;
	private Semaphore llenoAvanzado;
	private Semaphore esperarAvanzado;
	
	public Curso() {
		this.alumnosIniciacion = 0;
		this.alumnosAvanzado = 0;
		this.mutexIniciacion = new Semaphore(1, true);
		this.llenoIniciacion = new Semaphore(1, true);
		this.mutexAvanzado = new Semaphore(1, true);
		this.llenoAvanzado = new Semaphore(1, true);
		this.esperarAvanzado = new Semaphore(0, true);
	}
	
	
	//El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de iniciacion
	public void esperaPlazaIniciacion(int id) throws InterruptedException{
		llenoIniciacion.acquire();
		mutexIniciacion.acquire();
		alumnosIniciacion++;
		System.out.println("PARTE INICIACION: Alumno " + id + " cursa parte iniciacion");
		
		if (alumnosIniciacion < MAX_ALUMNOS_INI) {
			mutexIniciacion.release();
			llenoIniciacion.release();
		} else {
			mutexIniciacion.release();
		}
	}

	//El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public void finIniciacion(int id) throws InterruptedException{
		mutexIniciacion.acquire();
		alumnosIniciacion--;
		System.out.println("PARTE INICIACION: Alumno " + id + " termina parte iniciacion");

		if (alumnosIniciacion == MAX_ALUMNOS_INI - 1) {
			mutexIniciacion.release();
			llenoIniciacion.release();
		} else {
			mutexIniciacion.release();
		}
	}
	
	/* El alumno tendra que esperar:
	 *   - si ya hay un grupo realizando la parte avanzada
	 *   - si todavia no estan los tres miembros del grupo conectados
	 */
	public void esperaPlazaAvanzado(int id) throws InterruptedException{
		llenoAvanzado.acquire();
		mutexAvanzado.acquire();
		alumnosAvanzado++;
		if (alumnosAvanzado < ALUMNOS_AV) {
			System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya " + ALUMNOS_AV + " alumnos");
			mutexAvanzado.release();
			llenoAvanzado.release();
			esperarAvanzado.acquire();
		} else {
			System.out.println("PARTE AVANZADA: Hay " + ALUMNOS_AV + " alumnos. Alumno " + id + " empieza el proyecto");
			mutexAvanzado.release();
		}
	}
	
	/* El alumno:
	 *   - informa que ya ha terminado de cursar la parte avanzada 
	 *   - espera hasta que los tres miembros del grupo hayan terminado su parte 
	 */ 
	public void finAvanzado(int id) throws InterruptedException{
		mutexAvanzado.acquire();
		alumnosAvanzado--;
		if (alumnosAvanzado > 0) {
			esperarAvanzado.release();
			System.out.println("PARTE AVANZADA: Alumno " +  id + " termina su parte del proyecto. Espera al resto");
			mutexAvanzado.release();
		} else {
			System.out.println("\nPARTE AVANZADA: LOS " + ALUMNOS_AV + " ALUMNOS HAN TERMINADO EL CURSO\n");
			mutexAvanzado.release();
			llenoAvanzado.release();
		}
	}
}
