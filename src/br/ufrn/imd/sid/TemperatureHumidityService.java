package br.ufrn.imd.sid;


import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class TemperatureHumidityService extends Service {
	
	// elementos de interface para mostrar no painel de controle.
	public JProgressBar concentracaoCO2;
	public JTextArea consoleTextArea;

	@SuppressWarnings("serial")
	public TemperatureHumidityService(final Widget thWidget) {
		super(thWidget, "TemperatureHumidityService",
				new FunctionDescriptions() {
					{ // constructor
						// define function for the service
						add(new FunctionDescription("temperatureHumidityMonitoring", "Sets the service for Temperature Humidity Monitoring", thWidget.getNonConstantAttributes()));
					}
				}
		);
		
	}

	@Override
	public DataObject execute(ServiceInput serviceInput) {
		// Obtendo atributo do widget.
//		short co2 = serviceInput.getInput().getAttributeValue("co2Detectado");
//		concentracaoCO2.setValue(co2);
//		
//		
//		if(co2 >= 50){
//			// Obtendo atributos do widget
//			String local = serviceInput.getInput().getAttributeValue("localizacao");
//			float latitude = serviceInput.getInput().getAttributeValue("latitude");
//			float longitute = serviceInput.getInput().getAttributeValue("longitute");
//			
////			fireLabel.setBackground(Color.RED);
////			fireLabel.setText("Sim!!");
//			consoleTextArea.setText("");
//			consoleTextArea.append("Alerta de Poluição!!\n");
//			consoleTextArea.append("Local: \t" + local + "\n");
//			consoleTextArea.append("Latitude: \t" + latitude + "\n");
//			consoleTextArea.append("Longitute: \t" + longitute + "\n");
//		}else{
////			fireLabel.setBackground(Color.LIGHT_GRAY);
////			fireLabel.setText("Não");
//			consoleTextArea.setText("");
//		}
		
		return new DataObject(); // no particular info to return
	}

}
