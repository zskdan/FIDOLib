/*
 * This JPanel contains all the controls used to set, reset,
 * start, stop, hold and restart the count down clock. It's
 * Inserted in the left panel of the main panel.
 *
 */
/**
 *
 * @author Steen Andersen
 */
package fidolib.ui;

import fidolib.data.Constants;
import fidolib.data.CountDownData;
import fidolib.data.CountDownData.PresetComboBox;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Steen Andersen
 */
public class CountdownControlPanel extends javax.swing.JPanel {

    /**
     * Parent dialog
     */
    private CountDownDialog aCountDownDialog;
    
    /**
     * Timer delay
     */
    private int delay = 0;
    /**
     * Timer period for GUI updates
     */
    private int period = Constants.deltaT_GUI;
    /**
     * The timer itself
     */
    private static Timer timer = new Timer();

    /** Creates new form CountdownControlPanel */
    public CountdownControlPanel(CountDownDialog aCountDownDialog) {
        initComponents();
        this.aCountDownDialog = aCountDownDialog;
        
        if (CountDownData.countDownRunning == false) {
            if (CountDownData.countDownHold == false) {
            this.jButton1Start.setText(Constants.startButtonTxt);
            this.jButton1Hold.setText(Constants.holdButtonTxt);
            this.jButton1Reset.setText(Constants.resetButtonTxt);
            this.jButton1Start.setEnabled(true);
            this.jButton1Hold.setEnabled(false);
            this.jButton1Stop.setEnabled(false);
            this.jButton1Reset.setEnabled(true);
            this.jComboBoxPresets.setEnabled(true);
            this.jTextField1manualTimeEntering.setEnabled(true);
            }
            else {
                this.jButton1Start.setEnabled(false);
                this.jButton1Hold.setEnabled(true);
                this.jButton1Hold.setText(Constants.restartButtonTxt);
                this.jButton1Stop.setEnabled(true);
                this.jButton1Reset.setEnabled(false);
                this.jComboBoxPresets.setEnabled(false);
                this.jTextField1manualTimeEntering.setEnabled(false);
                
            }

        } else {
            if (CountDownData.countDownHold == true) {
                this.jButton1Start.setEnabled(false);
                this.jButton1Hold.setEnabled(true);
                this.jButton1Hold.setText(Constants.restartButtonTxt);
                this.jButton1Stop.setEnabled(true);
                this.jButton1Reset.setEnabled(false);
                this.jComboBoxPresets.setEnabled(false);

                this.jTextField1manualTimeEntering.setEnabled(false);
            } else {
                this.jButton1Start.setEnabled(false);
                this.jButton1Hold.setEnabled(true);
                this.jButton1Hold.setText(Constants.holdButtonTxt);
                this.jButton1Stop.setEnabled(true);
                this.jButton1Reset.setEnabled(false);
                this.jComboBoxPresets.setEnabled(false);
                this.jTextField1manualTimeEntering.setEnabled(false);
            }
        }

        jComboBoxPresets.setModel(new DefaultComboBoxModel(PresetComboBox.enumsToStringArray()));


        startTimer();
    }

    /**
     * Start the timer
     */
    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                todo();
            }
        }, delay, period);
    }

    private void todo() {
        if (CountDownData.countDownRunning == true) {
            Calendar calender = Calendar.getInstance();
            long now = calender.getTime().getTime();
            long remainingTime = CountDownData.startCountDown - (now - CountDownData.countDownTimeStamp);
            
            if ((remainingTime < 0)) { // passed T-0
                jButton1Hold.setEnabled(false);
            }
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

        jButton1Start = new javax.swing.JButton();
        jButton1Hold = new javax.swing.JButton();
        jButton1Stop = new javax.swing.JButton();
        jButton1Reset = new javax.swing.JButton();
        jComboBoxPresets = new javax.swing.JComboBox();
        jTextField1manualTimeEntering = new javax.swing.JTextField();

        setName("Form"); // NOI18N

        jButton1Start.setFont(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getFont("jButton1Start.font")); // NOI18N
        jButton1Start.setText(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getString("jButton1Start.text")); // NOI18N
        jButton1Start.setName("jButton1Start"); // NOI18N
        jButton1Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1StartActionPerformed(evt);
            }
        });

        jButton1Hold.setFont(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getFont("jButton1Hold.font")); // NOI18N
        jButton1Hold.setText(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getString("jButton1Hold.text")); // NOI18N
        jButton1Hold.setName("jButton1Hold"); // NOI18N
        jButton1Hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1HoldActionPerformed(evt);
            }
        });

        jButton1Stop.setFont(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getFont("jButton1Stop.font")); // NOI18N
        jButton1Stop.setText(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getString("jButton1Stop.text")); // NOI18N
        jButton1Stop.setName("jButton1Stop"); // NOI18N
        jButton1Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1StopActionPerformed(evt);
            }
        });

        jButton1Reset.setFont(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getFont("jButton1Reset.font")); // NOI18N
        jButton1Reset.setText(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getString("jButton1Reset.text")); // NOI18N
        jButton1Reset.setName("jButton1Reset"); // NOI18N
        jButton1Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ResetActionPerformed(evt);
            }
        });

        jComboBoxPresets.setFont(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getFont("jComboBoxPresets.font")); // NOI18N
        jComboBoxPresets.setName("jComboBoxPresets"); // NOI18N
        jComboBoxPresets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPresetsActionPerformed(evt);
            }
        });

        jTextField1manualTimeEntering.setText(org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CountdownControlPanel.class).getString("jTextField1manualTimeEntering.text")); // NOI18N
        jTextField1manualTimeEntering.setName("jTextField1manualTimeEntering"); // NOI18N
        jTextField1manualTimeEntering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1manualTimeEnteringActionPerformed(evt);
            }
        });
        jTextField1manualTimeEntering.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1manualTimeEnteringFocusGained(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1Start, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton1Hold, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton1Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPresets, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1manualTimeEntering, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1Hold, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addComponent(jButton1Stop)
                        .addComponent(jButton1Reset)
                        .addComponent(jComboBoxPresets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1manualTimeEntering, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1Start, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1StartActionPerformed

        CountDownData.startCountDown();

        if (aCountDownDialog != null)
        {
            aCountDownDialog.dispose();
        }



    }//GEN-LAST:event_jButton1StartActionPerformed

    private void jButton1HoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1HoldActionPerformed
        if (this.jButton1Hold.getText().equals(Constants.holdButtonTxt)) {
            // The hold button is pressed
            CountDownData.holdResetCountDown();

            this.jButton1Start.setEnabled(false);
            this.jButton1Hold.setEnabled(true);
            this.jButton1Hold.setText(Constants.restartButtonTxt);
            this.jButton1Stop.setEnabled(true);
            this.jButton1Reset.setEnabled(false);
            this.jComboBoxPresets.setEnabled(false);
            

            this.jTextField1manualTimeEntering.setEnabled(false);
        } else // The restart button is pressed
        {
           CountDownData.holdResetCountDown();

            this.jButton1Start.setEnabled(false);
            this.jButton1Hold.setEnabled(true);
            this.jButton1Hold.setText(Constants.holdButtonTxt);
            this.jButton1Stop.setEnabled(true);
            this.jButton1Reset.setEnabled(false);
            this.jComboBoxPresets.setEnabled(false);
            this.jTextField1manualTimeEntering.setEnabled(false);


        }

        

    }//GEN-LAST:event_jButton1HoldActionPerformed

    private void jButton1StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1StopActionPerformed


        CountDownData.stopCountDown();
        this.jButton1Start.setEnabled(true);
        this.jButton1Hold.setEnabled(false);
        this.jButton1Stop.setEnabled(false);
        this.jButton1Reset.setEnabled(true);
        this.jComboBoxPresets.setEnabled(true);

        this.jTextField1manualTimeEntering.setEnabled(true);
        this.jButton1Hold.setText(Constants.holdButtonTxt);
        jButton1ResetActionPerformed(null);


    }//GEN-LAST:event_jButton1StopActionPerformed

    private void jButton1ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ResetActionPerformed
        CountDownData.holdResetCountDown();

        this.jButton1Start.setEnabled(true);
        this.jButton1Hold.setEnabled(false);
        this.jButton1Stop.setEnabled(false);
        this.jButton1Reset.setEnabled(true);
        this.jComboBoxPresets.setEnabled(true);

        this.jTextField1manualTimeEntering.setEnabled(true);

        this.jButton1Hold.setText(Constants.holdButtonTxt);

    }//GEN-LAST:event_jButton1ResetActionPerformed

    private void jComboBoxPresetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPresetsActionPerformed
        String selectItem = (String) this.jComboBoxPresets.getSelectedItem();
        initializeClock(CountDownData.PresetComboBox.findByLabel(selectItem).getTime());

    }//GEN-LAST:event_jComboBoxPresetsActionPerformed

    /**
     * Show a message box with an error for time format
     */
    private void showTimeFormatExceptionMessage() {
        JOptionPane.showMessageDialog(this,
                "There was a number format exception.\nPlease use the time format: mm.ss",
                "Number format exception",
                JOptionPane.ERROR_MESSAGE);
    }
    private void jTextField1manualTimeEnteringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1manualTimeEnteringActionPerformed

        String timeStr = this.jTextField1manualTimeEntering.getText();
        if (!timeStr.equals("")) {
            String[] parmStrings = Pattern.compile("\\.").split(timeStr);
            boolean aNumberFormatException = false;
            if (parmStrings.length == 2) {

                // Get the minutes
                int min = -1;
                int sec = -1;
                try {
                    min = Integer.parseInt(parmStrings[0]);

                } catch (NumberFormatException e) {
                    aNumberFormatException = true;
                }
                // Get the seconds
                try {
                    sec = Integer.parseInt(parmStrings[1]);

                } catch (NumberFormatException e) {
                    aNumberFormatException = true;
                }
                if ((min < 0) || (sec < 0 || (sec > 60))) // No negative values or sec > 60
                {

                    showTimeFormatExceptionMessage();
                    return;
                }
                if (aNumberFormatException == false) { // Correct format

                    initializeClock((min * CountDownData.minute) + (sec * CountDownData.sec));

                } else { // Number format exception
                    showTimeFormatExceptionMessage();

                }
            } else {
                showTimeFormatExceptionMessage();
            }

        }

    }//GEN-LAST:event_jTextField1manualTimeEnteringActionPerformed

    private void jTextField1manualTimeEnteringFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1manualTimeEnteringFocusGained
        int textLength = this.jTextField1manualTimeEntering.getText().length();
        if (textLength > 0) {
            this.jTextField1manualTimeEntering.setSelectionStart(0);
            this.jTextField1manualTimeEntering.setSelectionEnd(textLength);

        }

    }//GEN-LAST:event_jTextField1manualTimeEnteringFocusGained

    private void initializeClock(long sec) {
        CountDownData.startCountDown = sec;
        CountDownData.resetTo = CountDownData.startCountDown;
        CountDownData.remainingTime = CountDownData.startCountDown;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1Hold;
    private javax.swing.JButton jButton1Reset;
    private javax.swing.JButton jButton1Start;
    private javax.swing.JButton jButton1Stop;
    private javax.swing.JComboBox jComboBoxPresets;
    private javax.swing.JTextField jTextField1manualTimeEntering;
    // End of variables declaration//GEN-END:variables
}
