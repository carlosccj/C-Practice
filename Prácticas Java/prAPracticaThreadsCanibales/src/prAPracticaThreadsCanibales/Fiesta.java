package prAPracticaThreadsCanibales;
import java.util.concurrent.Semaphore;

public class Fiesta {
	
	private Semaphore puedeComer;
	private Semaphore puedeCocinar;
	private int caldero;
	private int maxCaldero;
	
	public Fiesta (int maxCaldero, int nCanibales) {
		this.caldero = 10;
		this.maxCaldero = maxCaldero; // Suponemos que esto es 10
		this.puedeCocinar = new Semaphore(0, true);
		this.puedeComer = new Semaphore(1, true);
	}
	
	public void comer (int id, int racionesDevoradas) throws InterruptedException {
		puedeComer.acquire();
		if (caldero == 0) {
			System.out.println("El canibal " + id + " se ha encontrado el caldero vacío y está furioso\n");
			System.out.println("El canibal " + id + " llama a al cocinero a gritos...\n");
			Thread.sleep(2000);
			this.puedeCocinar.release();
		} else {
			System.out.println("El canibal " + id + " ha encontrado el caldero con " + caldero + " raciones\n");
			System.out.println("El canibal " + id + " se dispone a darse un festín\n");
			Thread.sleep(2000);
			caldero -= racionesDevoradas;
			racionesDevoradas = (caldero < 0) ? racionesDevoradas + caldero : racionesDevoradas;
			System.out.println("El canibal " + id + " ha devorado " + racionesDevoradas + " raciones\n");
			caldero = (caldero < 0) ? 0 : caldero;
			puedeComer.release();
			Thread.sleep(2000);
		}
	}
	
	public void cocinarExplorador() throws InterruptedException {
		this.puedeCocinar.acquire();
		System.out.println("El cocinero se dispone a despellejar y asar vivo a otro pobre explorador...\n");
		Thread.sleep(2000);
		this.caldero = this.maxCaldero;
		System.out.println("El caldero está ahora rebosante con la carne y los huesos del desgraciado y el cocinero se retira...\n");
		puedeComer.release();
	}
	

}
