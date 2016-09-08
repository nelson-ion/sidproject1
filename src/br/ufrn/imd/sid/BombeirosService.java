package br.ufrn.imd.sid;


import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import context.arch.comm.DataObject;
import context.arch.service.Service;
import context.arch.service.helper.FunctionDescription;
import context.arch.service.helper.FunctionDescriptions;
import context.arch.service.helper.ServiceInput;
import context.arch.widget.Widget;

/**
 * Custom service for the room application to set the light level.
 * @author Brian Y. Lim
 *
 */
public class BombeirosService extends Service {
	
	// package protected to be accessible to UI of HelloRoom app
	public JLabel fireLabel;

	@SuppressWarnings("serial")
	public BombeirosService(final Widget widget) {
		super(widget, "LightService", 
				new FunctionDescriptions() {
					{ // constructor
						// define function for the service
						add(new FunctionDescription(
								"lightControl", 
								"Sets the light level of the lamp", 
								widget.getNonConstantAttributes()));
					}
				}
		);
		
		/*
		 * set up light label (for use in a UI)
		 */
		fireLabel = new JLabel("0") {{
			setHorizontalAlignment(JLabel.CENTER);
			setBorder(BorderFactory.createEtchedBorder());
			
			setOpaque(true); // to allow background color to show
			// set color to represent light level
//			setBackground(Color.black); // initially dark
		}};
	}

	@Override
	public DataObject execute(ServiceInput serviceInput) {
		// Obtendo atributo do widget.
		boolean incendioDetectado = serviceInput.getInput().getAttributeValue("incendioDetectado");

		if(incendioDetectado){
			fireLabel.setBackground(Color.RED);
			fireLabel.setText("Sim!!");
		}else{
			fireLabel.setBackground(Color.LIGHT_GRAY);
			fireLabel.setText("Não");
		}
		
		// Olhando o que há no background.
		System.out.println(fireLabel.getBackground());
		return new DataObject(); // no particular info to return
	}

}
