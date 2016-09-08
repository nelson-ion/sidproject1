package br.ufrn.imd.sid;


import java.awt.Dimension;

import javax.swing.JFrame;

import context.arch.discoverer.Discoverer;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class Applicacao {
	
	protected Widget fireSensorWidget;
	protected Widget bombeirosWidget;
	protected Enactor enactor;
	protected BombeirosService bombeirosService;
	protected InterfaceDoSistema interfaceApp;

	public Applicacao() {
		super();
		
		fireSensorWidget = WidgetXmlParser.createWidget("widgets/fire-sensor.xml");
		bombeirosWidget = WidgetXmlParser.createWidget("widgets/bombeiros-widget.xml");
		enactor = EnactorXmlParser.createEnactor("widgets/room-enactor.xml");
		
		// Definindo os atributos de localização para o sensor
		fireSensorWidget.updateData("latitude", -5.8323);
		fireSensorWidget.updateData("longitute", -35.2053022);
		fireSensorWidget.updateData("localizacao", "Instituto Metropole Digital");
		
		
		bombeirosService = new BombeirosService(bombeirosWidget);
		bombeirosWidget.addService(bombeirosService);
		
		// Instância da interface informando o serviço e o widget
		interfaceApp = new InterfaceDoSistema(bombeirosService,fireSensorWidget); 
		
	}

	public static void main(String[] args) {
		Discoverer.start();
		
		Applicacao app = new Applicacao();
		
		JFrame frame = new JFrame("Hello Room");
		frame.add(app.interfaceApp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(700, 400));
		frame.setLocationRelativeTo(null); // center of screen
		frame.setVisible(true);
	}

}
