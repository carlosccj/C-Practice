package prEjemploAcumulador;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel1 extends JPanel implements Panel {
	
	private JLabel mensajeAcumulador = new JLabel("Valor acumulador"); // Una etiqueta con un texto descriptivo
	private JTextField valorAcum = new JTextField(7);
	private JButton acumular = new JButton("Acumular"); // Un bot�n interactivo con un texto descruptivo
	private JButton resetear = new JButton("Resetear"); // Un bot�n interactivo con un texto descriptivo
	private JButton desacumular = new JButton("Desacumular"); // Un bot�n interactivo con un texto descruptivo
	
	/*
	 * Inicializamos el constructor de Panel1. Damos un valor inicial al campo de texto donde se mostrar�
	 * el valor que se ha ido acumulando y luego vamos a�adiendo cada uno de los componentes por separado.
	 */
	
	public Panel1() {
		valorAcum.setText("0"); 
		add(valorAcum); 
		add(mensajeAcumulador);
		add(acumular); 
		add(resetear);
		add(desacumular);
	}
	
	/*
	 * Registramos el controlador en los botones que hemos creado arriba. Primero a�adimos el ActionListener
	 * y luego le decimos a que bot�n corresponde. Las acciones a llevar a cabo en cada caso quedar�n definidas
	 * dentro de la clase Controlador
	 */
	
	@Override
	public void controlador(ActionListener ctr) {
		acumular.addActionListener(ctr);
		acumular.setActionCommand("Acumular");
		resetear.addActionListener(ctr);
		resetear.setActionCommand("Resetear");
		desacumular.addActionListener(ctr);
		desacumular.setActionCommand("Desacumular");
	}
	
	/*
	 * Aqu� se recibe el valor que se usar� para actualizar el texto de valor acumulado. A �ste m�todo lo llama
	 * la clase Controlador cuando ya ha acumulado o reseteado, para mostrar el valor final de la operaci�n.
	 */

	@Override
	public void nuevoValor(int v) {
		valorAcum.setText(Integer.toString(v));
	}

}
