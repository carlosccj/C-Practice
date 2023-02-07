package prPractica7_1;

public class Polluelo extends Thread {
	private int id;
	private Nido nido;
	
	public Polluelo(int id, Nido nido) {
		this.id = id;
		this.nido = nido;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
			Thread.sleep(2000); //piando
			nido.comerBicho(this.id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}