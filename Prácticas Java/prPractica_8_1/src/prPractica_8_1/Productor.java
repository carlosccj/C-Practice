package prPractica_8_1;
import java.util.Random;

public class Productor extends Thread {
	private int id;
	private Buffer buffer;
	private int nIter;
	Random rand;
	
	public Productor(int id, int nIter, Buffer buffer) {
		this.id = id;
		this.nIter = nIter;
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < nIter; i++) {
			try {
				buffer.producir(this.id, rand.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
