package prSeptiembre2016;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa {
	private boolean[] haJugado;
	private boolean[] resultados;
	private int nJugadores;
	private int jugadas;
	private int caras;
	private int cruces;
	private ReentrantLock l;
	private Condition jugar;
	
	public Mesa(int N){
		nJugadores = N;
		jugadas = 0;
		caras = 0;
		cruces = 0;
		haJugado = new boolean[N];
		for (int i = 0; i < N; i++) {
			haJugado[i] = false;
		}
		resultados = new boolean[N];
		this.l = new ReentrantLock();
		jugar = l.newCondition();
	}
	
	/**
	 * 
	 * @param id del jugador que llama al método
	 * @param cara : true si la moneda es cara, false en otro caso
	 * @return un valor entre 0 y N. Si devuelve N es que ningún jugador 
	 * ha ganado y hay que repetir la partida. Si  devuelve un número menor que N, es el id del
	 * jugador ganador.
	 * 
	 * Un jugador llama al método nuevaJugada cuando quiere poner
	 * el resultado de su tirada (cara o cruz) sobre la mesa.
	 * El jugador deja su resultado y, si no es el último, espera a que el resto de 
	 * los jugadores pongan sus jugadas sobre la mesa.
	 * El último jugador comprueba si hay o no un ganador, y despierta
	 * al resto de jugadores
	 *  
	 */
	public int nuevaJugada(int id,boolean cara) throws InterruptedException{
		l.lock();
		int ganador = nJugadores;
		try {
			if (haJugado[id] == true) {
				jugar.await();
			}
			jugadas++;
			System.out.println("El jugador " + id + " ha realizado su jugada");
			haJugado[id] = true;
			resultados[id] = cara;
			System.out.println("Ha sacado " + resultados[id] + "\n");
			
			if (jugadas == nJugadores) {
				System.out.println("Ya han jugado TODOS los jugadores...");
				for (int i = 0; i < nJugadores; i++) {
					if (resultados[i] == true) {
						caras++;
					} else {
						cruces++;
					}
				}
				
				if (caras == nJugadores - 1) {
					for (int i = 0; i < nJugadores; i++) {
						if (resultados[i] == false) {
							ganador = i;
							System.out.println("Hay un ganador, el jugador " + i + " que ha sacado cruz");
						}
					}
				} else if (cruces == nJugadores - 1) {
					for (int i = 0; i < nJugadores; i++) {
						if (resultados[i] == true) {
							ganador = i;
							System.out.println("Hay un ganador, el jugador " + i + " que ha sacado cara");
						}
					}
				} else {
					System.out.println("No ha habido ningun ganador\n");
					caras = 0;
					cruces = 0;
					jugadas = 0;
					resultados = new boolean[nJugadores];
					for (int i = 0; i < nJugadores; i++) {
						haJugado[i] = false;
					}
					jugar.signalAll();
				}
			} else {
				jugar.signalAll();
			}
			
		} finally {
			l.unlock();
		}
		return ganador;
	}
}
