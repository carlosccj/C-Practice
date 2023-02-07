package prPractica5_2;

public class Presa extends Thread {
	private Peterson p;
	private Lago lago;
	private int id; //necesitamos saber si es el rio 1 o el rio 2
	
	public Presa (Lago lago, int id, Peterson p) {
		this.lago = lago;
		this.id = id;
		this.p = p;
	}
	
	@Override
	public void run() {
		if (id == 0) {
			for (int i = 0; i < 1000; i++) {
				while(lago.getNivel() == 0) {
					System.out.println("El lago no tiene agua, en espera presa 0\n");
					Thread.yield();
				}
				p.preprR0();
				lago.decNivel();
				System.out.println("Presa 0 ha decrementado un nivel: " + lago.getNivel());
				p.postprR0();
			}
		} else {
			for (int i = 0; i < 1000; i++) {
				while(lago.getNivel() == 0) {
					System.out.println("El lago no tiene agua, en espera presa 1\n");
					Thread.yield();
				}
				p.preprR1();
				lago.decNivel();
				System.out.println("Presa 1 ha decrementado un nivel: " + lago.getNivel());
				p.postprR1();
			}
		}
	}
}
