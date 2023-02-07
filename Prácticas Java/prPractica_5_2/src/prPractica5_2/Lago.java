package prPractica5_2;

public class Lago {
	private int nivel;
	
	public Lago (int nivel) {
		this.nivel = nivel;
	}
	
	public void incNivel() {
		nivel++;
	}
	
	public void decNivel() {
		nivel--;
	}
	
	public int getNivel() {
		return this.nivel;
	}

}
