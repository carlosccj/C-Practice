package prPractica_6_3;

public class Caldero {
	int maxRaciones;
	int racionesPreparadas;
	
	public Caldero (int maxRaciones) {
		this.maxRaciones = maxRaciones;
		this.racionesPreparadas = 0;
	}
	
	public void llenarCaldero() {
		racionesPreparadas = maxRaciones;
	}
	
	public void consumirRacion() {
		racionesPreparadas--;
	}
	
	public int getRacionesPreparadas() {
		return this.racionesPreparadas;
	}
	
	public boolean isCalderoVacio() {
		return racionesPreparadas == 0;
	}

}
