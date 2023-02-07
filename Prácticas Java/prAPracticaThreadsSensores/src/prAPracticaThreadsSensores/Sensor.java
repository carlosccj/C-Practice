package prAPracticaThreadsSensores;
import java.util.Random;

public class Sensor extends Thread {
	private int id;
	private int temperatura;
	private int humedad;
	private int luz;
	private Entorno entorno;
	private Random rand;
	
	public Sensor(int id, Entorno entorno) {
		this.id = id;
		this.entorno = entorno;
		this.rand = new Random();
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			this.temperatura = rand.nextInt(40);
			this.humedad = rand.nextInt(5);
			this.luz = rand.nextInt(3);
			try {
				this.entorno.tomarMediciones(this.id, temperatura, humedad, luz);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
