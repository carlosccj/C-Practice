package prEjemploAcumulador;


public class Modelo {
	
	private int acum = 0;
	
	public void acumular (int ac) {
		acum += ac;
	}
	
	public void desacumular(int ac) {
		acum -= ac;
	}
	
	public void resetear() {
		acum = 0;
	}
	
	public int obtenerAcumulador() {
		return acum;
	}
}
