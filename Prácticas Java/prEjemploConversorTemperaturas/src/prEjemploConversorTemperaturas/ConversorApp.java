package prEjemploConversorTemperaturas;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;

public class ConversorApp {

	public static void main(String args[]) {
		System.out.println("main() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println("run() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());

				Panel panel = new Panel();
				Controlador c = new Controlador(panel);
				panel.setControlador(c);

				JFrame frame = new JFrame();
				frame.setTitle("Conversor");
				frame.setContentPane(panel);
				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}