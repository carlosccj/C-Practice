package prExamenJunio2016;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int NUM_CLIENTES = 50;
		
		Aseos aseo = new Aseos();
		Cliente[] cliente = new Cliente[NUM_CLIENTES];
		for (int i = 0; i<cliente.length; i++){
			cliente[i] = new Cliente(i,aseo);
		}
		EquipoLimpieza equipo = new EquipoLimpieza(aseo);
		for (int i = 0; i<cliente.length; i++){
			cliente[i].start();
		}
		equipo.start();
		
		try {
			for (int i = 0; i<cliente.length; i++){
				cliente[i].join();
			}
			equipo.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
