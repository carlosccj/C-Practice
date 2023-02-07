package prPractica_8_1;
import java.util.ArrayList;

public class Buffer {
	private ArrayList<Integer> elem;
	private int hueco;
	private int tamMax;
	private int[] c; // Por donde va cada consumidor comiendo
	private int p; // Indice compartido por los productores
	private int nCons; // Numero de consumidores
	private int[] falta; // Cuantas unidades les falta a cada consumidor por comer
	private int[] consumida; // Cuantos consumidores han comido esa posicion
	
	public Buffer(int tamMax, int nCons) {
		this.elem = new ArrayList<Integer>(tamMax);
		this.tamMax = tamMax;
		this.nCons = nCons;
		this.c = new int[nCons];
		this.consumida = new int[tamMax];
		this.falta = new int[nCons];
	}
	
	public synchronized void producir (int id, int num) throws InterruptedException {
		System.out.println("Productor " + id + " intentando producir\n");
		while(this.hueco == 0) {
			wait();
		}
		this.elem.add(this.p, num);
		this.consumida[p] = 0; // Nadie ha consumido ese dato
		System.out.println("Productor " + id + " ha producido en posicion " + p + "\n");
		this.p = (this.p + 1) % this.tamMax; // actualizamos indice de los productores
		this.hueco--;
		for (int i = 0; i < nCons; i++) { // Todos los consumidores pueden tomar una unidad
			falta[i]++;
		}
		notifyAll();
		
	}
	
	public synchronized int consumir(int id) throws InterruptedException {
		System.out.println("Consumidor " + id + " consumiendo\n");
		while(this.falta[id] == 0) { // Si no hay nada que consumir debe esperar
			wait();
		}
		int num = elem.get(this.c[id]);
		System.out.println("Consumidor " + id + " consume la posicion " + c[id] + "\n");
		this.falta[id]--;
		this.consumida[this.c[id]]++; // Si una unidad ha sido consumida por todos los consumidores, libero el hueco
		if (this.consumida[this.c[id]] == this.nCons) {
			this.hueco++;
			System.out.println("Posicion " + this.c[id] + " libre\n");
			notifyAll();
		}
		this.c[id] = (this.c[id] + 1) % this.tamMax;
		return num;
	}

}