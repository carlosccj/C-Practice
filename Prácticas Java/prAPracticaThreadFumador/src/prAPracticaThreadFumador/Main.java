package prAPracticaThreadFumador;

public class Main {
	public static void main (String args[]) {
		Mesa mesa = new Mesa();
		Fumador fumadorPapel = new Fumador(0, mesa);
		Fumador fumadorTabaco = new Fumador(1, mesa);
		Fumador fumadorCerillas = new Fumador(2, mesa);
		Agente agente = new Agente(mesa);
		
		try {
			agente.start();
			fumadorPapel.start();
			fumadorTabaco.start();
			fumadorCerillas.start();
			
			agente.join();
			fumadorPapel.join();
			fumadorTabaco.join();
			fumadorCerillas.join();
		} catch(InterruptedException e) {
			
		}
	}

}
