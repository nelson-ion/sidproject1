package br.ufrn.imd.sid;


import java.awt.Dimension;

import javax.swing.JFrame;

import context.arch.discoverer.Discoverer;
import context.arch.enactor.Enactor;
import context.arch.enactor.EnactorXmlParser;
import context.arch.widget.Widget;
import context.arch.widget.WidgetXmlParser;

public class Applicacao {
	
	protected InterfaceDoSistema interfaceApp;
	
	protected Widget fireSensorWidget;
	protected Widget bombeirosWidget;
	protected Enactor fireEnactor;
	protected BombeirosService bombeirosService;
	
	protected Widget co2Sensor;
	protected Widget ibamaWidget;
	protected Enactor co2Enactor;
	protected IBAMAService ibamaService;

	protected Widget temperaturaUmidadeSensorWidget;
	protected Widget thWidget;
	protected Enactor thEnactor;
	protected TemperatureHumidityService temperatureHumidityService;
	
	
	public Applicacao() {
		super();
		
		// Sensor de Incêndio
		fireSensorWidget = WidgetXmlParser.createWidget("widgets/bombeiros/fire-sensor.xml");
		bombeirosWidget = WidgetXmlParser.createWidget("widgets/bombeiros/bombeiros-widget.xml");
		fireEnactor = EnactorXmlParser.createEnactor("widgets/bombeiros/fire-enactor.xml");
		
		bombeirosService = new BombeirosService(bombeirosWidget);
		bombeirosWidget.addService(bombeirosService);
		
		// Definindo os atributos de localização para o sensor de incêndio
		fireSensorWidget.updateData("latitude", -5.8323);
		fireSensorWidget.updateData("longitute", -35.2053022);
		fireSensorWidget.updateData("localizacao", "Instituto Metropole Digital");

		
		
		
		// Sensor de concentração de Dióxido de Carbono
		co2Sensor = WidgetXmlParser.createWidget("widgets/ibama/concentracao-co2-sensor.xml");
		ibamaWidget = WidgetXmlParser.createWidget("widgets/ibama/ibama-widget.xml");
		co2Enactor = EnactorXmlParser.createEnactor("widgets/ibama/co2-enactor.xml");
		
		ibamaService = new IBAMAService(ibamaWidget);
		ibamaWidget.addService(ibamaService);
		
		// Definindo os atributos de localização para o sensor de incêndio
		co2Sensor.updateData("latitude", -5.8395);
		co2Sensor.updateData("longitute", -35.2016142);
		co2Sensor.updateData("localizacao", "Reitoria UFRN");
		
		
		
		
		temperaturaUmidadeSensorWidget = WidgetXmlParser.createWidget("widgets/temperatura/temperatura-e-umidade-sensor.xml");
		thWidget = WidgetXmlParser.createWidget("widgets/temperatura/th-widget.xml");
		thEnactor = EnactorXmlParser.createEnactor("widgets/temperatura/th-enactor.xml");
		
		temperatureHumidityService = new TemperatureHumidityService(thWidget);
		thWidget.addService(temperatureHumidityService);
		
		
		// Instância da interface informando o serviço e o widget
		interfaceApp = new InterfaceDoSistema(bombeirosService,fireSensorWidget,ibamaService,co2Sensor,
				temperatureHumidityService,temperaturaUmidadeSensorWidget); 
		
	}

	public static void main(String[] args) {
		Discoverer.start();
		
		Applicacao app = new Applicacao();
		
		JFrame frame = new JFrame("2016.2 - TOPICOS AVANCADOS EM SISTEMAS INTEGRADOS E DISTRIBUIDOS III");
		frame.add(app.interfaceApp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(800, 450));
		frame.setLocationRelativeTo(null); // center of screen
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
