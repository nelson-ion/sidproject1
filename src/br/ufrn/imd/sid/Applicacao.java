package br.ufrn.imd.sid;


import java.awt.Dimension;

import javax.swing.JFrame;

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
	
	protected InterfaceDoSistema ui;

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
		ui = new InterfaceDoSistema(bombeirosService.fireLabel,fireWidget); // need to attach lightService before starting
		
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
//		frame.setSize(new Dimension(300, 200));
		frame.setSize(new Dimension(700, 400));
		frame.setLocationRelativeTo(null); // center of screen
		frame.setVisible(true);
	}

}
