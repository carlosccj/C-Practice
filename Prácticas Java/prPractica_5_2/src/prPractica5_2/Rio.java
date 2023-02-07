package prPractica5_2;

public class Rio extends Thread {
	private Peterson p;
	private Lago lago;
	private int id; //necesitamos saber si es el rio 1 o el rio 2
	
	public Rio(Lago lago, int id, Peterson p) {
		this.lago = lago;
		this.id = id;
		this.p = p;
	}
	
	@Override
	public void run() {
		if (id == 0) {
			for (int i = 0; i < 1000; i++) {
				p.preprR0();
				lago.incNivel();
				System.out.println("Rio 0 ha incrementado nivel: " + lago.getNivel());
				p.postprR0();
			}
		} else {
			for (int i = 0; i < 1000; i++) {
				p.preprR1();
				lago.incNivel();
				System.out.println("Rio 1 ha incrementado nivel: " + lago.getNivel());
				p.postprR1();
			}
		}
	}
}
