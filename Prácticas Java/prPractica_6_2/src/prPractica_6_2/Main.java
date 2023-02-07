package prPractica_6_2;

public class Main {
	public static void main(String[] args) {
		Entorno entorno = new Entorno();
		Trabajador trabajador = new Trabajador(entorno);
		Sensor f0 = new Sensor(0, entorno);
		Sensor f1 = new Sensor(1, entorno);
		Sensor f2 = new Sensor(2, entorno);
		
		try {
			trabajador.start();
			f0.start();
			f1.start();
			f2.start();
			agente.join();
			f0.join();
			f1.join();
			f2.join();
		} catch (InterruptedException e) {
			
		}
	}
}
