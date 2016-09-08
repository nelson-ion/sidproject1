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
						add(new FunctionDescription("fireControl", "Sets the service for fire control", bombeirosWidget.getNonConstantAttributes()));
					}
				}
		);
		
	}

	@Override
	public DataObject execute(ServiceInput serviceInput) {
		// Obtendo atributo do widget.
		boolean incendioDetectado = serviceInput.getInput().getAttributeValue("incendioDetectado");
		
		
		if(incendioDetectado){
			// Obtendo atributos do widget
			String local = serviceInput.getInput().getAttributeValue("localizacao");
			float latitude = serviceInput.getInput().getAttributeValue("latitude");
			float longitute = serviceInput.getInput().getAttributeValue("longitute");
			
			fireLabel.setBackground(Color.RED);
			fireLabel.setText("Sim!!");
			consoleTextArea.setText("");
			consoleTextArea.append("Incêndio Detectado!\n");
			consoleTextArea.append("Local: \t" + local + "\n");
			consoleTextArea.append("Latitude: \t" + latitude + "\n");
			consoleTextArea.append("Longitute: \t" + longitute + "\n");
		}else{
			if(fireLabel != null && consoleTextArea != null){
				fireLabel.setBackground(Color.LIGHT_GRAY);
				fireLabel.setText("Não");
				consoleTextArea.setText("");
			}
		}
		
		return new DataObject(); // no particular info to return
	}

}
