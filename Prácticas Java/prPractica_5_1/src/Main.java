import prPractica5_1.*;

public class Main {
	public static void main(String[] args) {
		Buffer buffer = new Buffer(10);
		Peterson p = new Peterson();
		Productor prod = new Productor(buffer, 30, p);
		Consumidor cons = new Consumidor(buffer, 30, p);
		prod.start();
		cons.start();
		try {
			prod.join();
			cons.join();
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
