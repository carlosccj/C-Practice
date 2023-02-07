package prPractica_8_3;

public class Fumador extends Thread {
	private int id;
	private Mesa mesa;
	private int material;
	
	public Fumador(int id, Mesa mesa, int material) {
		this.id = id;
		this.mesa = mesa;
		this.material = material;
	}
	
	@Override
	public void run() {
		while (!isInterrupted()) {
			mesa.fumar(id, material);
		}
	}
}
