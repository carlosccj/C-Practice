package prAPracticaThreadsPajaros;

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
				this.nido.cazarBichito(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
