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

/**
 * Main class for Hello World context-aware application of a living room smart lamp.
 * It demonstrates four steps to create a simple context-aware application:
 * <ol>
 * 	<li>Modeling context with Widgets</li>
 * 	<li>Modeling reasoning with Enactors</li>
 * 	<li>Modeling behavior with Services</li>
 * 	<li>Combining models</li>
 * </ol>
 * Running this program would launch a GUI simulation of a room sensor suite
 * with brightness and presence sensors, and lamp light level.
 * 
 * @author Brian Y. Lim
 *
 */
public class HelloRoom {
	
	protected Widget roomWidget;
	protected Widget lightWidget;

	protected Enactor enactor;
	
	protected LightService lightService;
	
	protected HelloRoomUI ui;

	public HelloRoom() {
		super();
		
		/*
		 * Room sensor Widget
		 */
		roomWidget = WidgetXmlParser.createWidget("widgets/room-widget.xml");
		
		/*
		 * Light actuator Widget and Service
		 */
		lightWidget = WidgetXmlParser.createWidget("widgets/light-widget.xml");
		lightService = new LightService(lightWidget);
		lightWidget.addService(lightService);
		
		/*
		 * Enactor to use rules about RoomWidget to update LightWidget
		 */
		enactor = EnactorXmlParser.createEnactor("widgets/room-enactor.xml");

		// setup UI component
		ui = new HelloRoomUI(lightService.lightLabel); // need to attach lightService before starting
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
			
			//Definindo os valores (escala de temperatura) para o JSlider 
			add(temperaturaJSlider = new JSlider(new DefaultBoundedRangeModel(250, 0, 0, 250)) {{
				addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent evt) {
						// Obtendo o valor do JSlider (temperatura vai de 0 até 250)
						short temperatura = (short)temperaturaJSlider.getValue();
						roomWidget.updateData("temperatura", temperatura);
						
						// set color to represent temperature level
//						setBackground(new Color(temperatura, temperatura, temperatura));
					}
				});
				setOpaque(true); // to allow background color to show
				setMajorTickSpacing(50);
				setPaintTicks(true);
				setPaintLabels(true);
			}});

			
			
			
			
			
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
						roomWidget.updateData("presence", presence);
						
						// color text red for when presence is red
						if (presence == 0) { tf.setForeground(Color.red); }
						else { tf.setForeground(Color.black); }
					}
				});
			}});
			
			
			
			
			// UI for light level
			add(new JLabel("light") {{ setFont(getFont().deriveFont(fontSize)); }});
			add(lightLabel);
			
			/*
			 * Init state of widgets
			 */
			short temperatura = (short)temperaturaJSlider.getValue();
			int presence = (Integer) presenceSpinner.getValue();
			roomWidget.updateData("temperatura", temperatura);
			roomWidget.updateData("presence", presence);
		}
		
	}
	
	public static void main(String[] args) {
		Discoverer.start();
		
		HelloRoom app = new HelloRoom();
		
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
