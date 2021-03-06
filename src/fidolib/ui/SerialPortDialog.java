/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SerialPortDialog.java
 *
 * Created on 27-10-2012, 14:07:43
 */
package fidolib.ui;

import fidolib.com.COMPort;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Steen
 */
public class SerialPortDialog extends javax.swing.JDialog {

    
    /**
     * Reference to the com port
     */
    private COMPort aCOMPort = null;
    /** Creates new form SerialPortDialog */
    public SerialPortDialog(java.awt.Frame parent, boolean modal,COMPort aCOMPort) throws  java.lang.NoClassDefFoundError{
        super(parent, modal);
        this.aCOMPort = aCOMPort;
        initComponents();
        if (this.aCOMPort.portType == COMPort.PortType.AISPORT)
        {
            this.setTitle("AIS Serial port");
            this.baudRateComboBox.setSelectedIndex(5);
        }
        else if (this.aCOMPort.portType == COMPort.PortType.TELEMETRYPORT)
        {
            this.setTitle("Telemetry Serial port");
            this.baudRateComboBox.setSelectedIndex(5);
        }
        
        this.openButton.requestFocus();
        // Get available ports 
        
            List portList = aCOMPort.listPorts();
            if (portList != null) {
                while (!portList.isEmpty()) {
                    portComboBox.insertItemAt((String) portList.remove(0), 0);
                }
                if (portComboBox.getItemCount() > 0) {
                    portComboBox.setSelectedIndex(0);
                }
                /*if (aCOMPort.isCOMPortOpen() == true) {
                    this.openButton.setEnabled(false);
                    this.closeButton.setEnabled(true);
                } else {
                    this.openButton.setEnabled(true);
                    this.closeButton.setEnabled(false);
                }*/
            }
       

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainComPortPanel = new javax.swing.JPanel();
        portLabel = new javax.swing.JLabel();
        portComboBox = new javax.swing.JComboBox();
        baudRateLabel = new javax.swing.JLabel();
        baudRateComboBox = new javax.swing.JComboBox();
        parityLabel = new javax.swing.JLabel();
        parityComboBox = new javax.swing.JComboBox();
        stopBitLabel = new javax.swing.JLabel();
        stopBitComboBox = new javax.swing.JComboBox();
        dataBitsLabel = new javax.swing.JLabel();
        dataBitsComboBox = new javax.swing.JComboBox();
        openButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        closeDialogButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainComPortPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("COM Port"));
        mainComPortPanel.setToolTipText("");
        mainComPortPanel.setName("mainComPortPanel"); // NOI18N

        portLabel.setText("Port");
        portLabel.setName("portLabel"); // NOI18N

        portComboBox.setName("portComboBox"); // NOI18N

        baudRateLabel.setText("Baud rate");
        baudRateLabel.setName("baudRateLabel"); // NOI18N

        baudRateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "300", "600", "1200", "2400", "4800", "9600", "14400", "28800", "36000", "115000" }));
        baudRateComboBox.setSelectedIndex(5);
        baudRateComboBox.setName("baudRateComboBox"); // NOI18N

        parityLabel.setText("Parity");
        parityLabel.setName("parityLabel"); // NOI18N

        parityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4" }));
        parityComboBox.setName("parityComboBox"); // NOI18N

        stopBitLabel.setText("Stop bit");
        stopBitLabel.setName("stopBitLabel"); // NOI18N

        stopBitComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        stopBitComboBox.setName("stopBitComboBox"); // NOI18N

        dataBitsLabel.setText("Data bits");
        dataBitsLabel.setName("dataBitsLabel"); // NOI18N

        dataBitsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "7", "8", "9" }));
        dataBitsComboBox.setSelectedIndex(1);
        dataBitsComboBox.setName("dataBitsComboBox"); // NOI18N

        openButton.setText("Open Port");
        openButton.setName("openButton"); // NOI18N
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close Port");
        closeButton.setName("closeButton"); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        closeDialogButton.setText("Close Dialog");
        closeDialogButton.setName("closeDialogButton"); // NOI18N
        closeDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeDialogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainComPortPanelLayout = new javax.swing.GroupLayout(mainComPortPanel);
        mainComPortPanel.setLayout(mainComPortPanelLayout);
        mainComPortPanelLayout.setHorizontalGroup(
            mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainComPortPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainComPortPanelLayout.createSequentialGroup()
                        .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stopBitLabel)
                            .addComponent(dataBitsLabel)
                            .addComponent(baudRateLabel)
                            .addComponent(portLabel)
                            .addComponent(parityLabel))
                        .addGap(14, 14, 14)
                        .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(portComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(baudRateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(parityComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataBitsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(stopBitComboBox, 0, 1, Short.MAX_VALUE))
                        .addGap(386, 386, 386))
                    .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(closeDialogButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainComPortPanelLayout.createSequentialGroup()
                            .addComponent(openButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(closeButton))))
                .addContainerGap())
        );
        mainComPortPanelLayout.setVerticalGroup(
            mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainComPortPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portLabel)
                    .addComponent(portComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baudRateLabel)
                    .addComponent(baudRateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(parityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stopBitLabel)
                    .addComponent(stopBitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataBitsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataBitsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainComPortPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openButton)
                    .addComponent(closeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeDialogButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainComPortPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainComPortPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed

        try {             
		String portName = (String) portComboBox.getSelectedItem();             
		int baudRate = Integer.parseInt((String) baudRateComboBox.getSelectedItem());             
		int dataBits = Integer.parseInt((String) dataBitsComboBox.getSelectedItem());             
		int stopBits = Integer.parseInt((String) stopBitComboBox.getSelectedItem());             
		int parity = Integer.parseInt((String) parityComboBox.getSelectedItem());
                aCOMPort.connect(portName, baudRate, dataBits, stopBits, parity);             
		this.openButton.setEnabled(false);             
		this.closeButton.setEnabled(true);             
		this.dispose();          
		} 
		catch (Exception e) {             
		JOptionPane.showMessageDialog(this,"COMM Port couldn't be opened" + "\n" + e.getMessage(),
											"Error opening COMM Port",                     
											JOptionPane.ERROR_MESSAGE);         
											}     
	}//GEN-LAST:event_openButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

        try {             
		aCOMPort.closeConnection();              
		this.closeButton.setEnabled(false);         
		} 
		catch (Exception e) {             
		JOptionPane.showMessageDialog(this,"Exception during COMM Port closing" + "\n" + e.getMessage(),
										"Error closing COMM Port",JOptionPane.ERROR_MESSAGE);         
										}         
	    this.openButton.setEnabled(true);     
		}//GEN-LAST:event_closeButtonActionPerformed

    private void closeDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeDialogButtonActionPerformed

        this.dispose();     
		
		}//GEN-LAST:event_closeDialogButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox baudRateComboBox;
    private javax.swing.JLabel baudRateLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton closeDialogButton;
    private javax.swing.JComboBox dataBitsComboBox;
    private javax.swing.JLabel dataBitsLabel;
    private javax.swing.JPanel mainComPortPanel;
    private javax.swing.JButton openButton;
    private javax.swing.JComboBox parityComboBox;
    private javax.swing.JLabel parityLabel;
    private javax.swing.JComboBox portComboBox;
    private javax.swing.JLabel portLabel;
    private javax.swing.JComboBox stopBitComboBox;
    private javax.swing.JLabel stopBitLabel;
    // End of variables declaration//GEN-END:variables
}
