package prAPracticaThreadsCanibales;
import java.util.Random;

public class Canibal extends Thread {

	private int id;
	private Fiesta fiesta;
	private Random rand;
	
	public Canibal (int id, Fiesta fiesta) {
		this.id = id;
		this.fiesta = fiesta;
		this.rand = new Random();
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
				int racionesDevoradas = 5 + this.rand.nextInt(4); // 10 son todas las raciones que se sacan de un explorador
				this.fiesta.comer(this.id, racionesDevoradas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
