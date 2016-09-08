package br.ufrn.imd.sid;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import context.arch.discoverer.Discoverer;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class Applicacao {
	
	protected Widget fireWidget;
	protected Widget bombeirosWidget;

	protected Enactor enactor;
	
	protected BombeirosService bombeirosService;
	
	protected HelloRoomUI ui;

	public Applicacao() {
		super();
		
		/*
		 * Room sensor Widget
		 */
		fireWidget = WidgetXmlParser.createWidget("widgets/fire-sensor.xml");
		
		/*
		 * Light actuator Widget and Service
		 */
		bombeirosWidget = WidgetXmlParser.createWidget("widgets/bombeiros-widget.xml");
		bombeirosService = new BombeirosService(bombeirosWidget);
		bombeirosWidget.addService(bombeirosService);
		
		/*
		 * Enactor to use rules about RoomWidget to update LightWidget
		 */
		enactor = EnactorXmlParser.createEnactor("widgets/room-enactor.xml");

		// setup UI component
		ui = new HelloRoomUI(bombeirosService.fireLabel); // need to attach lightService before starting
	}
	

	/**
	 * Class to represent the UI representation for the application.
	 * @author Brian Y. Lim
	 */
	@SuppressWarnings("serial")
	public class HelloRoomUI extends JPanel {
		
		private JSlider temperaturaJSlider;
		private JSpinner presenceSpinner;
		
		private float fontSize = 20f;
		
		public HelloRoomUI(JLabel lightLabel) {	
			//Definindo a divisão da janela
			setLayout(new GridLayout(3, 2)); // 3 rows, 2 columns
			
			
			
			/*
			 * Label para o atuador de temperatura.
			 */
			add(new JLabel("Temperatura") {{ setFont(getFont().deriveFont(fontSize)); }});
			
			//Definindo os valores (escala de temperatura) para o JSlider e a temperatura inicial de 28ºC 
			add(temperaturaJSlider = new JSlider(new DefaultBoundedRangeModel(28, 0, 0, 250)) {{
				addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent evt) {
						// Obtendo o valor do JSlider (temperatura vai de 0 até 250)
						short temperatura = (short) temperaturaJSlider.getValue();
						fireWidget.updateData("temperatura", temperatura);
						
						// set color to represent temperature level
//						setBackground(new Color(temperatura, temperatura, temperatura));
					}
				});
				setOpaque(true); // to allow background color to show
				setMajorTickSpacing(50);
				setPaintTicks(true);
				setPaintLabels(true);
			}});

			
			
			
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
	
	public static void main(String[] args) {
		Discoverer.start();
		
		Applicacao app = new Applicacao();
		
		/*
		 * GUI frame
		 */
		JFrame frame = new JFrame("Hello Room");
		frame.add(app.ui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(300, 200));
		frame.setLocationRelativeTo(null); // center of screen
		frame.setVisible(true);
	}

}
