package prPractica5_1;

public class Peterson {
	private volatile boolean fProd = false;
	private volatile boolean fCons = false;
	private volatile int turno = 0;
	
	public void preProd() {
		fProd = true;
		turno = 1;
		while (turno == 1 && fCons) {
			Thread.yield();
		}
	}
	
	public void postProd() {
		fProd = false;
	}
	
	public void preCons() {
		fCons = true;
		turno = 0;
		while (turno == 0 && fProd) {
			Thread.yield();
		}
	}
	
	public void postCons() {
		fCons = false;
	}


}
