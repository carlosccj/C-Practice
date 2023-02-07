package prAPracticaThreadsCanibales;

public class Cocinero extends Thread {
	private Fiesta fiesta;
	
	public Cocinero (Fiesta fiesta) {
		this.fiesta = fiesta;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
				this.fiesta.cocinarExplorador();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
