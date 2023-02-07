package prPractica7_1;

public class Pajaro extends Thread {
	private int id;
	private Nido nido;
	
	public Pajaro(int id, Nido nido) {
		this.id = id;
		this.nido = nido;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
			Thread.sleep(2000); //cazando el bicho
			nido.depositarBicho(this.id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
