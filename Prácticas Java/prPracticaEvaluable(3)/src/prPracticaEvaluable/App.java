package prPracticaEvaluable;



import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {            	
            	JFrame frame = new JFrame();
            	Panel panel = new Panel();
            	
            	Controlador controlador = new Controlador(panel);
            	panel.setControlador(controlador);            	
            	
            	frame.setTitle("Práctica 2");
            	
            	frame.setContentPane(panel);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
