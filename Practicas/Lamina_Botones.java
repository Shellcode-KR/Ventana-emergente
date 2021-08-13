package Practicas;

import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Lamina_Botones extends JPanel{
	public Lamina_Botones(String titulo,String[] opciones) {
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		grupo = new ButtonGroup();
		
		for(int i=0;i<opciones.length;i++) {
			
			JRadioButton bot = new JRadioButton(opciones[i]);
			bot.setActionCommand(opciones[i]);
			
			add(bot);
			grupo.add(bot);
			bot.setSelected(i==0);
		}
	}
	private ButtonGroup grupo;
	
	public String dameSeleccion() {
		
		return grupo.getSelection().getActionCommand();
	}
}
