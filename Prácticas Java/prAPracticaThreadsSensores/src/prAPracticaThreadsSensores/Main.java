package prAPracticaThreadsSensores;

public class Main {
	public static void main(String args[]) {
		Entorno entorno = new Entorno();
		Sensor sensor0 = new Sensor(0, entorno);
		Sensor sensor1 = new Sensor(1, entorno);
		Sensor sensor2 = new Sensor(2, entorno);
		Trabajador trabajador = new Trabajador(entorno);
		
		try {
			sensor0.start();
			sensor1.start();
			sensor2.start();
			trabajador.start();
			
			sensor0.join();
			sensor1.join();
			sensor2.join();
			trabajador.join();
		} catch (InterruptedException e) {
			
		}
	}

}
