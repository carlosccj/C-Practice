package prPractica_6_3;

public class Canibal extends Thread {
	private Fiesta fiesta;
	private int id;
	
	public Canibal(Fiesta fiesta, int id) {
		this.fiesta = fiesta;
		this.id = id;
	}
	
	@Override
	public void run() {
		//fiesta.comer();
	}
	

}
