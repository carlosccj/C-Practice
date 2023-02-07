package prAPracticaThreadsSensores;

public class Trabajador extends Thread {
	private Entorno entorno;
	
	public Trabajador(Entorno entorno) {
		this.entorno = entorno;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			try {
				this.entorno.trabajar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
