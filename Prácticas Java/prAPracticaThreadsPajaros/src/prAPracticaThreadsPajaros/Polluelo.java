package prAPracticaThreadsPajaros;

public class Polluelo extends Thread {
	private int id;
	private Nido nido;
	
	public Polluelo (int id, Nido nido) {
		this.id = id;
		this.nido = nido;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
				this.nido.comerBichito(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
