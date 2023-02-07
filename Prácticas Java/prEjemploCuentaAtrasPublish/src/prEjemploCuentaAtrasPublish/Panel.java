package prEjemploCuentaAtrasPublish;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel extends JPanel {

	private JLabel etiqueta1;
    private JButton boton;
    protected JTextArea area;
    protected JScrollPane scroll;
    protected JTextField tiempoField;
  
    public final String START = "start";
    
    public Panel()
    {
    	 tiempoField = new JTextField(5);
         etiqueta1 = new JLabel();
         boton = new JButton();
         area = new JTextArea(20,40);
         scroll = new JScrollPane(area);
        

         etiqueta1.setText("Introduzca el valor de inicio");
         boton.setText("Cuenta atrás!");         
         
         this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));         
         JPanel sup= new JPanel();
         sup.add(etiqueta1);
         sup.add(tiempoField);
         this.add(sup);
                  
         this.add(boton);
     
         this.add(scroll);         
    }
    
    public void setControlador(ActionListener ctr)
    {
    	boton.addActionListener(ctr);
    	boton.setActionCommand(START);
    }

	public void escribirLista(List<Integer> list) {
		for(int i=0; i< list.size(); i++) {
			area.append(list.get(i)+"\n");
		}
		
	}
}
