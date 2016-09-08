package br.ufrn.imd.sid;

import javax.swing.JLabel;
import javax.swing.JPanel;

import context.arch.widget.Widget;

public class AppInterface extends JPanel {

	private static final long serialVersionUID = 1L;
	
//	private float fontSize = 20f;
	
	private javax.swing.JTextArea console;
    private javax.swing.JLabel fireLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTemperatura;
    private javax.swing.JSeparator linhaAbaixoDoTitulo;
    private javax.swing.JSeparator separadorVerticalEsquerdo;
    private javax.swing.JSlider temperaturaJSlider;
    private javax.swing.JLabel title;
	
	public AppInterface(JLabel lightLabel, Widget fireWidget) {
        initComponents();
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
        labelTemperatura.setText("Temperatura no Sensor");

        fireLabel.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        fireLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fireLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        fireLabel.setOpaque(true);

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
                    .addComponent(fireLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fireLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 124, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    
        
        
    }


    
}

