package prPracticaEvaluable;


import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
public class Panel extends JPanel{

	private JLabel label = new JLabel("Numero de iteraciones: ");

	private JTextField iteraciones = new JTextField(16);
	private JButton boton = new JButton();
	private JButton cancelar = new JButton();
	
	private JTextField pi1 = new JTextField(16); //campo de texto donde se muestra el valor aproximado por MonteCarlo
	private JTextField pi2 = new JTextField(16); //campo de texto donde se muestra el valor aproximado por Gregory-Leibniz
	
	private JProgressBar progresoMonteCarlo = new JProgressBar(0,100);
	private JProgressBar progresoLeibniz= new JProgressBar(0,100);

	
	
	public Panel(){
		this.setLayout(new GridLayout(3,1,0,0));
		
		setBotonComenzar();
		setBotonCancelar();
		
		JPanel fila1=new JPanel();
		fila1.add(label);
		fila1.add(iteraciones);
		fila1.add(boton);
		fila1.add(cancelar);
		this.add(fila1);
		
		JPanel fila2=new JPanel();
		fila2.add(new JLabel("Montecarlo"));
		progresoMonteCarlo.setStringPainted(true);
		fila2.add(progresoMonteCarlo);
		fila2.add(pi1);
		this.add(fila2);

		JPanel fila3=new JPanel();
		fila3.add(new JLabel("Series GLe."));
		progresoLeibniz.setStringPainted(true);
		fila3.add(progresoLeibniz);
		fila3.add(pi2);
		this.add(fila3);

	}
	
	public void setBotonComenzar() {
		boton.setText("Comenzar");
		boton.setActionCommand("COMENZAR");
	}
	
	public void setBotonCancelar() {
		cancelar.setText("Cancelar");
		cancelar.setActionCommand("CANCELAR");
	}

	public void setControlador(ActionListener ctr){
		boton.addActionListener(ctr);
		cancelar.addActionListener(ctr);
	}
	
	public int getIteraciones(){
		return Integer.parseInt(iteraciones.getText());
	}
	
	public void escribePI1(Double v) {
		pi1.setText(v.toString());
	}

	public void escribePI2(Double v) {
		pi2.setText(v.toString());
	}	

	public void limpia1(){
		pi1.setText("");
	}
	
	public void limpia2(){
		pi2.setText("");
	
	}

	public void setProgresoMonteCarlo(int p){
		progresoMonteCarlo.setValue(p);	
	}
	
	public void setProgresoLeibniz(int p){
		progresoLeibniz.setValue(p);
	}

	public void escribirMonteCarlo(List<Double> lista) {
		for (int i = 0; i < lista.size(); i++) {
			pi1.setText(lista.get(i).toString());
		}
	}
	
	public void escribirSeries(List<Double> lista) {
		for (int i = 0; i < lista.size(); i++) {
			pi2.setText(lista.get(i).toString());
		}
	}

	
}
