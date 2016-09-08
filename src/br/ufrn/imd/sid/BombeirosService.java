package br.ufrn.imd.sid;


import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

public class BombeirosService extends Service {
	
	// elementos de interface para mostrar no painel de controle.
	public JLabel fireLabel;
	public JTextArea consoleTextArea;

	@SuppressWarnings("serial")
	public BombeirosService(final Widget bombeirosWidget) {
		super(bombeirosWidget, "FireService",
				new FunctionDescriptions() {
					{ // constructor
						// define function for the service
						add(new FunctionDescription("fireControl", "Sets the light level of the lamp", bombeirosWidget.getNonConstantAttributes()));
					}
				}
		);
		
	}

	@Override
	public DataObject execute(ServiceInput serviceInput) {
		// Obtendo atributo do widget.
		boolean incendioDetectado = serviceInput.getInput().getAttributeValue("incendioDetectado");
		
		
		if(incendioDetectado){
			String local = serviceInput.getInput().getAttributeValue("localizacao");
			short latitude = serviceInput.getInput().getAttributeValue("latitude");
			short longitute = serviceInput.getInput().getAttributeValue("longitute");
			
			fireLabel.setBackground(Color.RED);
			fireLabel.setText("Sim!!");
			consoleTextArea.setText("");
			consoleTextArea.append("Incêndio Detectado!\n");
			consoleTextArea.append("Local: " + local + "\n");
			consoleTextArea.append("Latitude: " + latitude + "\n");
			consoleTextArea.append("longitute: " + longitute + "\n");
		}else{
			fireLabel.setBackground(Color.LIGHT_GRAY);
			fireLabel.setText("Não");
			consoleTextArea.setText("");
		}
		
		return new DataObject(); // no particular info to return
	}

}
