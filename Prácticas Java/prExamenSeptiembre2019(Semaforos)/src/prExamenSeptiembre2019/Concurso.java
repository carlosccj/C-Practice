package prExamenSeptiembre2019;
import java.util.concurrent.Semaphore;

public class Concurso {
	private static final int N_JUGADAS = 10;
	private int jugadasRonda;
	private int rondas0;
	private int caras0;
	private Semaphore puedeJugar0;
	private int rondas1;
	private int caras1;
	private Semaphore puedeJugar1;
	
	public Concurso() {
		this.jugadasRonda = 0;
		this.rondas0 = 0;
		this.caras0 = 0;
		this.puedeJugar0 = new Semaphore(1, true);
		this.rondas1 = 0;
		this.caras1 = 0;
		this.puedeJugar1 = new Semaphore(0, true);
	}
	
	public void tirarMoneda(int id, boolean cara) throws InterruptedException {
		if (id == 0) {
			puedeJugar0.acquire();
			System.out.println("Jugada " + jugadasRonda + " del JUGADOR 0");
			if (cara == true) {
				caras0++;
			}
			if (jugadasRonda < N_JUGADAS) {
				puedeJugar0.release();
			}
		} else {
			puedeJugar1.acquire();
			System.out.println("Jugada " + jugadasRonda + " del JUGADOR 1");
			if (cara == true) {
				caras1++;
			}
			if (jugadasRonda < N_JUGADAS * 2) {
				puedeJugar1.release();
			}
		}
		
		if (id == 0 && jugadasRonda == N_JUGADAS - 1) {
			System.out.println("El jugador " + id + " ha conseguido " + caras0 + " CARAS en esta ronda\n");
			puedeJugar1.release();
			puedeJugar0.acquire();
		} else if (jugadasRonda == (N_JUGADAS * 2) - 1) {
			System.out.println("El jugador " + id + " ha conseguido " + caras1 + " CARAS en esta ronda\n");
			if (caras0 > caras1) {
				rondas0++;
				System.out.println("Esta ronda la ha GANADO el jugador 0 (" + caras0 + " vs " + caras1 + ")\n");
				caras0 = 0;
				caras1 = 0;
				jugadasRonda = 0;
				puedeJugar0.release();
				puedeJugar1.acquire();
			} else if (caras0 < caras1) {
				rondas1++;
				System.out.println("Esta ronda la ha GANADO el jugador 1 (" + caras0 + " vs " + caras1 +")\n");
				caras0 = 0;
				caras1 = 0;
				jugadasRonda = 0;
				puedeJugar0.release();
				puedeJugar1.acquire();
			} else {
				System.out.println("Esta ronda ha quedado empate (" + caras0 + " vs " + caras1 + ")\n");
				caras0 = 0;
				caras1 = 0;
				jugadasRonda = 0;
				puedeJugar0.release();
				puedeJugar1.acquire();
			}
		} else {
			jugadasRonda++;
		}
	}	
	
	public boolean concursoTerminado() {
		boolean terminado = false;
		if (rondas0 == 3) {
			terminado = true;
			System.out.println("Ha ganado el jugador 0!!!\n");
		} else if (rondas1 == 3) {
			terminado = true;
			System.out.println("Ha ganado el jugador 1!!!\n");
		}
		return terminado;
	}
}