package br.ufrn.imd.sid;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import context.arch.widget.Widget;

//@SuppressWarnings("serial")
public class InterfaceDoSistema extends JPanel {
	
	private static final long serialVersionUID = 4168529448909112298L;
	
	
	public InterfaceDoSistema(BombeirosService bombeirosService, Widget fireSensorWidget, IBAMAService ibamaService, Widget co2Sensor) {

		// Construindo interface
		initComponents();
		
		// adicionando o ChangeListener para o JSlider que simula a temperatura no sensor 
		temperaturaJSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				// Obtendo o valor do JSlider (temperatura vai de 0 até 250)
				short temperatura = (short) temperaturaJSlider.getValue();
				fireSensorWidget.updateData("temperatura", temperatura);
			}
		});
		// Conectando o label de incêndio a variável no Serviço do Bombeiro
		bombeirosService.fireLabel = this.fireLabel;
		bombeirosService.consoleTextArea = this.console;
		
		
		
		// adicionando o ChangeListener para o JSlider que simula a concentração de CO2 no sensor 
		concentracaoCO2JSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				// Obtendo o valor do JSlider (temperatura vai de 0 até 250)
				short co2 = (short) concentracaoCO2JSlider.getValue();
				co2Sensor.updateData("co2", co2);
			}
		});
		// Conectando o ProgressBar de CO2 a variável no Serviço do IBAMA
		ibamaService.concentracaoCO2 = this.visorDePoluição;
		ibamaService.consoleTextArea = this.console;
		
		/*
		 * Init state of widgets
		 */
		short temperatura = (short)temperaturaJSlider.getValue();
		fireSensorWidget.updateData("temperatura", temperatura);
		
		short co2 = (short)concentracaoCO2JSlider.getValue();
		co2Sensor.updateData("temperatura", co2);
	}




    private void initComponents() {

        title = new javax.swing.JLabel();
        linhaAbaixoDoTitulo = new javax.swing.JSeparator();
        labelTemperatura = new javax.swing.JLabel();
        temperaturaJSlider = new javax.swing.JSlider();
        labelIndencioDetectado = new javax.swing.JLabel();
        fireLabel = new javax.swing.JLabel();
        separadorVerticalEsquerdo = new javax.swing.JSeparator();
        concentracaoCO2JSlider = new javax.swing.JSlider();
        labelConcentracaoCO2 = new javax.swing.JLabel();
        labelRepresentacaoCO2 = new javax.swing.JLabel();
        visorDePoluição = new javax.swing.JProgressBar();
        separadorVerticalDireito = new javax.swing.JSeparator();
        console = new javax.swing.JTextArea();

        title.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Monitoramento e Painel de Controle");

        labelTemperatura.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        labelTemperatura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTemperatura.setText("Temperatura no Sensor");

        temperaturaJSlider.setMajorTickSpacing(50);
        temperaturaJSlider.setMaximum(250);
        temperaturaJSlider.setPaintLabels(true);
        temperaturaJSlider.setPaintTicks(true);
        temperaturaJSlider.setValue(30);
        temperaturaJSlider.setOpaque(true);

        labelIndencioDetectado.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        labelIndencioDetectado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIndencioDetectado.setText("Incêndio Detectado?");

        fireLabel.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        fireLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fireLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fireLabel.setOpaque(true);

        separadorVerticalEsquerdo.setOrientation(javax.swing.SwingConstants.VERTICAL);

        concentracaoCO2JSlider.setMajorTickSpacing(10);
        concentracaoCO2JSlider.setPaintLabels(true);
        concentracaoCO2JSlider.setPaintTicks(true);
        concentracaoCO2JSlider.setValue(30);
        concentracaoCO2JSlider.setOpaque(true);

        labelConcentracaoCO2.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        labelConcentracaoCO2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConcentracaoCO2.setText("Concentração de CO2");

        labelRepresentacaoCO2.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        labelRepresentacaoCO2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRepresentacaoCO2.setText("Representação do CO2");

        visorDePoluição.setString("0 CO2");
        visorDePoluição.setStringPainted(true);

        separadorVerticalDireito.setOrientation(javax.swing.SwingConstants.VERTICAL);

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linhaAbaixoDoTitulo)
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(console)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fireLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelIndencioDetectado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(temperaturaJSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separadorVerticalEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelConcentracaoCO2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelRepresentacaoCO2, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(concentracaoCO2JSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(visorDePoluição, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(separadorVerticalDireito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linhaAbaixoDoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelTemperatura)
                            .addGap(18, 18, 18)
                            .addComponent(temperaturaJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelIndencioDetectado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fireLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(separadorVerticalEsquerdo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelConcentracaoCO2)
                        .addGap(18, 18, 18)
                        .addComponent(concentracaoCO2JSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelRepresentacaoCO2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(visorDePoluição, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(separadorVerticalDireito, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(console, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JSlider concentracaoCO2JSlider;
    private javax.swing.JTextArea console;
    private javax.swing.JLabel fireLabel;
    private javax.swing.JLabel labelConcentracaoCO2;
    private javax.swing.JLabel labelIndencioDetectado;
    private javax.swing.JLabel labelRepresentacaoCO2;
    private javax.swing.JLabel labelTemperatura;
    private javax.swing.JSeparator linhaAbaixoDoTitulo;
    private javax.swing.JSeparator separadorVerticalDireito;
    private javax.swing.JSeparator separadorVerticalEsquerdo;
    private javax.swing.JSlider temperaturaJSlider;
    private javax.swing.JLabel title;
    private javax.swing.JProgressBar visorDePoluição;
    // End of variables declaration                   
}
