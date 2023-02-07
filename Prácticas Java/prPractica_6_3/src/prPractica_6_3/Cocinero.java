package prPractica_6_3;

public class Cocinero extends Thread {
	
	private Fiesta fiesta;
	
	public Cocinero(Fiesta fiesta) {
		this.fiesta = fiesta;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			fiesta.cocinar();
		}
	}

}
