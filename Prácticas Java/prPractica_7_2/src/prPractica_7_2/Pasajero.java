package prPractica_7_2;

public class Pasajero extends Thread {

	private int id;
	private Coche coche;
	
	public Pasajero(int id, Coche coche) {
		this.id = id;
		this.coche = coche;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
			this.coche.quieroSubir(this.id);
			this.coche.quieroBajar(this.id);
			Thread.sleep(4000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
