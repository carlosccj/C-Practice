package prPractica5_1;

import java.util.ArrayList;

public class Buffer {
	private int totalElem; //maximo de elementos
	private int nelem; // numero de elementos valido
	private ArrayList<Integer> elem;
	private int c; //indice de consumo
	private int p; //indice de produccion
	
	public Buffer (int totalElem) {
		this.totalElem = totalElem;
		this.c = 0;
		this.p = 0;
		this.nelem = 0;
		this.elem = new ArrayList<Integer>();
		for (int i = 0; i < totalElem; i++) {
			elem.add(-1);
		}
	}
	
	public int getNelem() {
		return this.nelem;
	}
	
	public ArrayList<Integer> getListaDeElementos() {
		return this.elem;
	}
	
	public int getTotalElem() {
		return this.totalElem;
	}
	
	public void escribir (int elemento) {
		this.elem.set(this.p, elemento);
		this.nelem++;
		this.p += (p + 1) % nelem;
	}
	
	public int leer () {
		int elemento = this.elem.get(c);
		this.nelem++;
		this.p += (p + 1) % nelem;
		return elemento;
	}

}
