package prPractica_6_2;

public class Sensor {
	private int id; // 0 = temperatura, 1 = humedad, 2 = luz
	private Entorno entorno;
	
	public Sensor (int tipoDeSensor, Entorno entorno) {
		this.id = tipoDeSensor;
		this.entorno = entorno;
	}

}
