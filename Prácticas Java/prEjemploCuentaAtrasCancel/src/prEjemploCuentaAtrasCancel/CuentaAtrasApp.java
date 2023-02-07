package prEjemploCuentaAtrasCancel;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;

public class CuentaAtrasApp {
   
    public static void main(String args[]) {
    	System.out.println("main() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());
    	
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	System.out.println("run() - isEventDispatchThread? "+ SwingUtilities.isEventDispatchThread());
            	JFrame frame = new JFrame();
            	Panel panel = new Panel();
            	
            	Controlador controlador = new Controlador(panel);
            	panel.setControlador(controlador);
            	
            	
            	frame.setTitle("Cuentra atrás");
            	
            	frame.setContentPane(panel);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }

    
}