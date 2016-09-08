package br.ufrn.imd.sid;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import context.arch.widget.Widget;

//@SuppressWarnings("serial")
public class InterfaceDoSistema extends JPanel {
	
	private static final long serialVersionUID = 4168529448909112298L;
	
//	private float fontSize = 20f;

	// Componentes de Interface
	private javax.swing.JTextArea console;
    private javax.swing.JLabel fireLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelIndencioDetectado;
    private javax.swing.JLabel labelTemperatura;
    private javax.swing.JSeparator linhaAbaixoDoTitulo;
    private javax.swing.JSeparator separadorVerticalEsquerdo;
    private javax.swing.JSlider temperaturaJSlider;
    private javax.swing.JLabel title;
    
	
	public InterfaceDoSistema(BombeirosService bombeirosService, Widget fireSensorWidget) {

		// Construindo interface
		initComponents();
		
		// adicionando o ChangeListener para o JSlider que simula a temperatura no sensor 
		temperaturaJSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				// Obtendo o valor do JSlider (temperatura vai de 0 até 250)
				short temperatura = (short) temperaturaJSlider.getValue();
				System.out.println("TEMP em Interface " + temperatura);
				fireSensorWidget.updateData("temperatura", temperatura);
			}
		});
		
		// Conectando o label de incêndio a variável no Serviço do Bombeiro
		bombeirosService.fireLabel = this.fireLabel;
		bombeirosService.consoleTextArea = this.console;
		
		/*
		 * Init state of widgets
		 */
		short temperatura = (short)temperaturaJSlider.getValue();
		fireSensorWidget.updateData("temperatura", temperatura);
	}



    private void initComponents() {

        title = new javax.swing.JLabel();
        linhaAbaixoDoTitulo = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        separadorVerticalEsquerdo = new javax.swing.JSeparator();
        temperaturaJSlider = new javax.swing.JSlider();
        labelTemperatura = new javax.swing.JLabel();
        fireLabel = new javax.swing.JLabel();
        labelIndencioDetectado = new javax.swing.JLabel();

        title.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Monitoramento e Painel de Controle");

        console.setEditable(false);
        console.setColumns(20);
        console.setRows(5);
        jScrollPane1.setViewportView(console);

        separadorVerticalEsquerdo.setOrientation(javax.swing.SwingConstants.VERTICAL);

        temperaturaJSlider.setMajorTickSpacing(50);
        temperaturaJSlider.setMaximum(250);
        temperaturaJSlider.setPaintLabels(true);
        temperaturaJSlider.setPaintTicks(true);
        temperaturaJSlider.setValue(30);

        labelTemperatura.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        labelTemperatura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTemperatura.setText("Temperatura no Sensor");

        fireLabel.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        fireLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fireLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fireLabel.setOpaque(true);

        labelIndencioDetectado.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        labelIndencioDetectado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIndencioDetectado.setText("Incêndio Detectado?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(linhaAbaixoDoTitulo)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(temperaturaJSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fireLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelIndencioDetectado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorVerticalEsquerdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linhaAbaixoDoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separadorVerticalEsquerdo)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTemperatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(temperaturaJSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelIndencioDetectado)
                        .addGap(2, 2, 2)
                        .addComponent(fireLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }                        
}

