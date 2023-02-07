package prPractica_8_3;
import java.util.Random;

public class Agente extends Thread {
	private Mesa mesa;
	private Random rand;
	private int ing1;
	private int ing2;
	
	public Agente(Mesa mesa) {
		this.mesa = mesa;
	}
	
	@Override
	public void run() {
		this.ing1 = rand.nextInt(3);
		this.ing2 = rand.nextInt(3);
		while(ing1 != ing2) {
			mesa.ponerIngredientes(ing1, ing2);
		}
	}
}
