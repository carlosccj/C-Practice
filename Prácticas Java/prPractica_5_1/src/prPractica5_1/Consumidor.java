package prPractica5_1;

public class Consumidor extends Thread {
	private Buffer buffer;
	private int numIter;
	private Peterson p;
	
	public Consumidor (Buffer buffer, int numIter, Peterson p) {
		this.buffer = buffer;
		this.numIter = numIter;
		this.p = p;
	}
	
	public void run() {
		for (int i = 0; i < this.numIter; i++) {
			while (buffer.getNelem() == 0) {
				System.out.println("Buffer vacio: Esperando a que se produzca\n");
				Thread.yield();
			}
			
			//preprotocolo consumidor
			p.preCons();
			//preprotocolo consumidor
			
			System.out.println("Elemento consumido: " + buffer.leer());
			System.out.println("nelem: " + buffer.getNelem());
			System.out.println("Estado del buffer: " + buffer.getListaDeElementos());
			
			//postprotocolo consumidor
			p.postCons();
			//postprotocolo consumidor
		}
	}
}