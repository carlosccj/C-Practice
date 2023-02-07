package prAPracticaThreadFumador;
import java.util.Random;

public class Agente extends Thread {
	private int ingrediente1;
	private int ingrediente2; // 0 = tabaco, 1 = papel, 2 = cerillas
	private Mesa mesa;
	private Random rand;
	
	public Agente (Mesa mesa) {
		this.mesa = mesa;
		this.rand = new Random();
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			this.ingrediente1 = rand.nextInt(3);
			this.ingrediente2 = rand.nextInt(3);
			if (ingrediente1 != ingrediente2) {
				try {
					this.mesa.ponerIngrediente(ingrediente1, ingrediente2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
