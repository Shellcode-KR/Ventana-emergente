package Practicas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Marco_Dialogos extends JFrame{
	
	public Marco_Dialogos() {
		
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		setBounds(500,100,600,450);
		Image myIcon = mipantalla.getImage("src/L64.png");
		setIconImage(myIcon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Prueba de dialogos");
		setLayout(new BorderLayout());
		JPanel laminacuadricula = new JPanel();
		laminacuadricula.setLayout(new GridLayout(2, 3));
		add(laminacuadricula,BorderLayout.CENTER);
		
		String[] primero = {"Mensaje","Confirmar","Opcion","Entrada"};
		lamina_tipo = new Lamina_Botones("TIpo", primero);
		laminacuadricula.add(lamina_tipo);
		
		lamina_tipo_mensaje = new Lamina_Botones("Tipo de Mensaje", new String[] 
				{"ERROR_MESSAGE","INFORMATION_MESSAGE","WARNING_MESSAGE","QUESTION_MESSAGE","PLAIN_MESSAGE"});
		laminacuadricula.add(lamina_tipo_mensaje);
		
		lamina_mensaje =new Lamina_Botones("Mensaje", new String[] {"Cadena","Icono","Componente","Otros","Object[]"});
		laminacuadricula.add(lamina_mensaje);
		
		lamina_tipo_opcion = new Lamina_Botones("Confirmar", 
				new String[] {"DEFAULT_OPTION","YES_NO_OPTION","YES_NO_CANCEL_OPTION","OK_CANCEL_OPTION"});
		laminacuadricula.add(lamina_tipo_opcion);
		
		lamina_opciones = new Lamina_Botones("Opcion", new String[] {"String[]","Icon[]","Object[]"});
		laminacuadricula.add(lamina_opciones);
		
		lamina_entrada = new Lamina_Botones("Entrada", new String[] {"Campo de texto","Combo"});
		laminacuadricula.add(lamina_entrada);
		
		JPanel lamina_mostrar = new JPanel();
		JButton botonMostrar = new JButton("Mostrar");
		botonMostrar.addActionListener(new AccionMostrar());
		
		lamina_mostrar.add(botonMostrar);
		
		add(lamina_mostrar,BorderLayout.SOUTH);
		
		
		
		
		
	}
	//------------------------------------PROPORCIONA EL MENSAJE ...................................................
	public Object dameMensaje() {
		String s = lamina_mensaje.dameSeleccion();
		if(s.equals("Cadena")) {
			return cadenaMensaje;
		}else if(s.equals("Icono")) {
			return iconoMensaje;
		}else if(s.equals("Componente")) {
			return componenteMensaje;
		}else if(s.equals("Otros")) {
			return ObjetoMnesaje;
		}else if(s.equals("Object[]")) {
			return new Object[] {cadenaMensaje, iconoMensaje,componenteMensaje,ObjetoMnesaje};
		}else {
			return null;	
		}
		
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------DEVULEVE TIPO ICONO-----------------------------------------------------------
	public int dameIcono() {
		String s = lamina_tipo_mensaje.dameSeleccion();
		
		if(s.equals("ERROR_MESSAGE")) {
			return 0;
		}else if(s.equals("INFORMATION_MESSAGE")) {
			return 1; 
		}else if(s.equals("WARNING_MESSAGE")) {
			return 2;
		}else if(s.equals("QUESTION_MESSAGE")) {
			return 3;
		}else if(s.equals("PLAIN_MESSAGE")) {
			return -1;
		}else {
			return 0;
		}
	}
	
	
	//---------------------------------------------------------------------------------------------------------------
	//-----------------------DA EL NUMEO DE BOTONES EN CONFIRMAR--------------------------------------------------
	
	public int dameBoton() {
		String s = lamina_tipo_opcion.dameSeleccion();
		
		if(s.equals("DEFAULT_OPTION")) {
			return -1;
		}else if(s.equals("YES_NO_OPTION")) {
			return 0; 
		}else if(s.equals("YES_NO_CANCEL_OPTION")) {
			return 1;
		}else if(s.equals("OK_CANCEL_OPTION")) {
			return 2;
		}else {
			return 0;
		}
	}
	//---------------------------------------------------------------------------------------------------------------
	//--------------------------------- DA OPCIONES A LAMINA OPCIONES------------------------------------------------
	public Object[] dameOpciones(Lamina_Botones lamina) {
		String s = lamina.dameSeleccion();
			if(s.equals("String[]")) {
				return new String[] {"Amarillo","Azul","Rojo"};
			}else if(s.equals("Icon[]")){
				return new Object[] {new ImageIcon("src/L64.png"),new ImageIcon("src/L32.png"),new ImageIcon("src/L16.png")};
			}else if(s.equals("Object[]")) {
				return new Object[] {cadenaMensaje, iconoMensaje,componenteMensaje,ObjetoMnesaje};
			}
			
			else {
				return null;
			}
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------
	private class AccionMostrar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//System.out.println(lamina_tipo.dameSeleccion());
			if(lamina_tipo.dameSeleccion().equals("Mensaje")) {
				JOptionPane.showMessageDialog(Marco_Dialogos.this, dameMensaje(), "Titulo", dameIcono());
			}else if(lamina_tipo.dameSeleccion().equals("Confirmar")) {
				JOptionPane.showConfirmDialog(Marco_Dialogos.this, dameMensaje(), "Titulo",dameBoton(),dameIcono());
				
			}else if(lamina_tipo.dameSeleccion().equals("Opcion")) {
				JOptionPane.showOptionDialog(Marco_Dialogos.this, dameMensaje(), "Titulo", 1, dameIcono(), null, dameOpciones(lamina_opciones), null);
			}else if(lamina_tipo.dameSeleccion().equals("Entrada")) {
				
				if(lamina_entrada.dameSeleccion().equals("Campo de texto")) {
				JOptionPane.showInputDialog(Marco_Dialogos.this,dameMensaje(),"Titulo",dameIcono());
				}else {
					JOptionPane.showInputDialog(Marco_Dialogos.this,dameMensaje(),"Titulo",dameIcono(),null,new String[] {"Amarillo","Azul","Rojo"},"Azul");
				}
			}
		} 
		
	}
	
	
	private Lamina_Botones lamina_tipo;
	private Lamina_Botones lamina_tipo_mensaje;
	private Lamina_Botones lamina_mensaje;
	private Lamina_Botones lamina_tipo_opcion;
	private Lamina_Botones lamina_opciones;
	private Lamina_Botones lamina_entrada;
	
	private String cadenaMensaje = "Mensaje";
	private Icon  iconoMensaje = new ImageIcon("src/L64.png");
	private Object ObjetoMnesaje = new Date();
	private Component componenteMensaje = new LaminaEjemplo();
	
	
}

class LaminaEjemplo extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rectangulo = new Rectangle2D.Double(0,0,getWidth(),getHeight());
		g2.setPaint(Color.yellow);
		g2.fill(rectangulo);
	}
	
}
