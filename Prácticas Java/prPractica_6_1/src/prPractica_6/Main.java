package prPractica_6;

public class Main {
	public static void main(String[] args) {
		Mesa mesa = new Mesa();
		Agente agente = new Agente(mesa);
		Fumador f0 = new Fumador(0, mesa);
		Fumador f1 = new Fumador(1, mesa);
		Fumador f2 = new Fumador(2, mesa);
		
		try {
			agente.start();
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
