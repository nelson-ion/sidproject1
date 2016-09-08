package br.ufrn.imd.sid;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import context.arch.widget.Widget;

@SuppressWarnings("serial")
public class InterfaceDoSistema extends JPanel {
	
	private static final long serialVersionUID = 4168529448909112298L;
	
	// Componentes de Interface
	private JSlider temperaturaJSlider;
	private JSpinner presenceSpinner;
	
	
	private float fontSize = 20f;
	
	public InterfaceDoSistema(JLabel lightLabel, Widget fireWidget) {
		// Associando variáveis locais
		
		//Definindo a divisão da janela
		setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns
		
		/*
		 * Label para o atuador de temperatura.
		 */
		add(new JLabel("Temperatura") {{ setFont(getFont().deriveFont(fontSize)); }});
		
		//Definindo os valores (escala de temperatura) para o JSlider e a temperatura inicial de 28ºC 
		add(
			temperaturaJSlider = new JSlider(new DefaultBoundedRangeModel(28, 0, 0, 250)) {{
			addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					// Obtendo o valor do JSlider (temperatura vai de 0 até 250)
					short temperatura = (short) temperaturaJSlider.getValue();
					fireWidget.updateData("temperatura", temperatura);
					
					// set color to represent temperature level
//					setBackground(new Color(temperatura, temperatura, temperatura));
				}
			});
			setOpaque(true); // to allow background color to show
			setMajorTickSpacing(50);
			setPaintTicks(true);
			setPaintLabels(true);
		}}
				);

		
		
		
		// UI for light level
		add(new JLabel("Incêndio?") {{ setFont(getFont().deriveFont(fontSize)); }});
		add(lightLabel);
		

		
		
		
		/*
		 * UI for presence
		 */
		add(new JLabel("presence") {{ setFont(getFont().deriveFont(fontSize)); }});
		
		add(presenceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1)) {{ // # people from 0 to 5
			// get text field to color text
			final JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) getEditor();
			final JFormattedTextField tf = editor.getTextField();
			tf.setForeground(Color.red);
			setFont(getFont().deriveFont(fontSize));
			
			addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {
					int presence = (Integer) presenceSpinner.getValue();
					fireWidget.updateData("presence", presence);
					
					// color text red for when presence is red
					if (presence == 0) { tf.setForeground(Color.red); }
					else { tf.setForeground(Color.black); }
				}
			});
		}});
		
		
		
		/*
		 * Init state of widgets
		 */
		short temperatura = (short)temperaturaJSlider.getValue();
		int presence = (Integer) presenceSpinner.getValue();
		fireWidget.updateData("temperatura", temperatura);
		fireWidget.updateData("presence", presence);
	}
	
}

