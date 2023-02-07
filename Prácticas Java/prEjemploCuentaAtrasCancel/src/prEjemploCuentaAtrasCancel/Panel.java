package prEjemploCuentaAtrasCancel;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel extends JPanel {

	private JLabel etiqueta1;
    private JButton boton, cancel;
    protected JTextArea area;
    protected JScrollPane scroll;
    protected JTextField tiempoField;
   
    public final String START = "start";
    public final String CANCEL = "cancel";
    //-- Barra de progreso
    protected JProgressBar bar;
    
    public Panel() {
    	 tiempoField = new JTextField(5);
         etiqueta1 = new JLabel();
         boton = new JButton();
         cancel = new JButton();
         area = new JTextArea(20,40);
         scroll = new JScrollPane(area);
        

         etiqueta1.setText("Introduzca el valor de inicio");
         boton.setText("Cuenta atrás!");         
         cancel.setText("Cancelar");
         
         this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));         
         JPanel sup= new JPanel();
         sup.add(etiqueta1);
         sup.add(tiempoField);
         this.add(sup);
                  
         this.add(boton);
         this.add(cancel);
         this.add(scroll);  
         //-- Barra de progreso
         bar = new JProgressBar(0,100);
         bar.setStringPainted(true);// para mostrar el % en texto
         this.add(bar);
    }
    
    public void setControlador(ActionListener ctr){
    	boton.addActionListener(ctr);
    	boton.setActionCommand(START);
    	cancel.addActionListener(ctr);
    	cancel.setActionCommand(CANCEL);
    }

	public void escribirLista(List<Integer> list) {
		for(int i=0; i< list.size(); i++)
		{
			area.append(list.get(i)+"\n");
		}
		
	}
}
