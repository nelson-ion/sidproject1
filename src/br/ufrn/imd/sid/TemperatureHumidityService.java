package br.ufrn.imd.sid;


import javax.swing.JLabel;
import javax.swing.JTextArea;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class TemperatureHumidityService extends Service {
	
	// elementos de interface para mostrar no painel de controle.
	public JLabel temperaturaLabel;
	public JLabel umidadeLabel;
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
		short temperatura = serviceInput.getInput().getAttributeValue("temperatura");
		short umidade = serviceInput.getInput().getAttributeValue("umidade");
		
		if(temperaturaLabel != null && umidadeLabel != null){
			temperaturaLabel.setText(String.valueOf(temperatura) + "ºC");
			umidadeLabel.setText(String.valueOf(umidade) + "%");
			
		}
		
		return new DataObject(); // no particular info to return
	}

}
