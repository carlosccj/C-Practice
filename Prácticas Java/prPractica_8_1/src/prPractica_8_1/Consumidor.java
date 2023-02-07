package prPractica_8_1;
import java.util.Random;

public class Consumidor extends Thread {
	private int id;
	private Buffer buffer;
	private int nIter;
	
	public Consumidor(int id, int nIter, Buffer buffer) {
		this.id = id;
		this.nIter = nIter;
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < nIter; i++) {
			try {
				buffer.consumir(this.id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}