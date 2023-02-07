package prAPracticaThreadsCanibales;

public class Main {
	public static void main(String args[]) {
		Fiesta fiesta = new Fiesta(10, 3);
		Canibal c1 = new Canibal(1, fiesta);
		Canibal c2 = new Canibal(2, fiesta);
		Canibal c3 = new Canibal(3, fiesta);
		Cocinero cocinero = new Cocinero(fiesta);
		
		try {
			c1.start();
			c2.start();
			c3.start();
			cocinero.start();
			
			c1.join();
			c2.join();
			c3.join();
			cocinero.join();
		} catch (InterruptedException e) {
			
		}
	}
}
