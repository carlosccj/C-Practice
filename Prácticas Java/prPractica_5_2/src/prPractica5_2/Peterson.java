package prPractica5_2;

public class Peterson {
	private volatile boolean fRio0 = false;
	private volatile boolean fRio1 = false;
	private volatile boolean fPresa0 = false;
	private volatile boolean fPresa1 = false;
	private volatile boolean fRio = false;
	private volatile boolean fPresa = false;
	private volatile int turnoRio = 0;
	private volatile int turnoPresa = 0;
	private volatile int turnoRioPresa = 0;
	
	//pasa a definir directamente las pre y post-condiciones
	
	public void preprR0() {
		fRio0 = true;
		turnoRio = 1;
		while(turnoRio == 1 && fRio1) {
			Thread.yield();
		}
		
		fRio = true;
		turnoRioPresa = 1;
		while(turnoRioPresa == 1 && fPresa) {
			Thread.yield();
		}
	}
	
	public void postprR0() {
		fRio0 = false;
		fRio = false;
	}
	
	public void preprR1() {
		fRio0 = true;
		turnoRio = 0;
		while(turnoRio == 0 && fRio0) {
			Thread.yield();
		}
		
		fRio = true;
		turnoRioPresa = 1;
		while(turnoRioPresa == 1 && fPresa) {
			Thread.yield();
		}
	}
	
	public void postprR1() {
		fRio1 = false;
		fRio = false;
	}
	
	public void preprP0() {
		fPresa0 = true;
		turnoPresa = 1;
		while(turnoPresa == 1 && fPresa1) {
			Thread.yield();
		}
		
		fPresa = true;
		turnoRioPresa = 0;
		while(turnoRioPresa == 0 && fRio) {
			Thread.yield();
		}
	}
	
	public void postprP0() {
		fPresa0 = false;
		fPresa = false;
	}
	
	public void preprP1() {
		fPresa1 = true;
		turnoPresa = 0;
		while(turnoPresa == 0 && fPresa0) {
			Thread.yield();
		}
		
		fPresa = true;
		turnoRioPresa = 0;
		while(turnoRioPresa == 0 && fRio) {
			Thread.yield();
		}
	}
	
	public void postprP1() {
		fPresa1 = false;
		fPresa = false;
	}
}
