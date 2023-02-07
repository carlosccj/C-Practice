package prPractica5_1;

public class Productor extends Thread {
	private Buffer buffer;
	private int numIter;
	private Peterson p;
	
	public Productor (Buffer buffer, int numIter, Peterson p) {
		this.buffer = buffer;
		this.numIter = numIter;
		this.p = p;
	}
	
	public void run() {
		for (int i = 0; i < this.numIter; i++) {
			while (buffer.getNelem() == buffer.getTotalElem()) {
				System.out.println("Buffer lleno\n");
				Thread.yield();
			}
			//preprotocolo productor
			p.preProd();
			//preprotocolo productor
	
			buffer.escribir(i);
			System.out.println("Elemento producido: " + i);
			System.out.println("nelem: " + buffer.getNelem());
			System.out.println("Estado del buffer: " + buffer.getListaDeElementos());
			
			//postprotocolo
			p.postProd();
			//postprotocolo;
		}
	}
}
