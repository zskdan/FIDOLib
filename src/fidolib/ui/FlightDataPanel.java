/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FlightDataPanel.java
 *
 * Created on 05-10-2012, 12:36:42
 */
package fidolib.ui;

import fidolib.data.AISData;
import fidolib.data.Constants;
import fidolib.data.FlightData;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Steen
 */
public class FlightDataPanel extends ColorPanel {

    /**
     * Reference to the flight data
     *
     */
    private FlightData aFlightData = null;
    /**
     * Reference to the AIS data
     *
     */
    private AISData aAISData = null;
    /**
     * Timer delay
     */
    private int delay = 0;
    /**
     * Timer period for GUI updates
     */
    private int period = Constants.deltaT_GUI;
    /**
     * The timer it self
     */
    private Timer timer = new Timer();

    /**
     * Creates new form FlightDataPanel
     */
    public FlightDataPanel(boolean useGradientColors, Color textColor, Color backgroundColor, Color gradientColorStart, Color gradientColorStop, AISData aAISData, FlightData aFlightData) {
        this.useGradientColors = useGradientColors;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.gradientColorStart = gradientColorStart;
        this.gradientColorStop = gradientColorStop;
        this.aAISData = aAISData;
        this.aFlightData = aFlightData;
        initComponents();
        // Set and start the timer
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, delay, period);
    }

    @Override
    public void paint(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();

        // Clear the background
        if (useGradientColors == true) {
            Graphics2D g2;
            if (g instanceof Graphics2D) {
                g2 = (Graphics2D) g;
            } else {
                return;
            }
            GradientPaint gp = new GradientPaint(0, 0, gradientColorStart, 0, height, gradientColorStop, true);
            g2.setPaint(gp);
        } else {
            g.setColor(backgroundColor);
        }
        g.fillRect(0, 0, width, height);

        paintData(g);



    }

    public void paintData(Graphics g) {

        g.setColor(Constants.textColor);
        int width = this.getWidth();
        int height = this.getHeight() / 12;
        int smallest = (int) ((double) width / 25);
        if (height < smallest) {
            smallest = height;
        }

        int fontSize = smallest;
        int textXPos = 20;
        int deltaTextPos = smallest * 6 + 40;
        Font font = new Font("New Courier", Font.BOLD, fontSize);
        g.setFont(font);
        int maxFontSizeFactor = 10;
        int fontSizeFactor = maxFontSizeFactor;
        g.drawString("Telemetry", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Latitude", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Longitude", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Dist. MC", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("BRG f. MC", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("ETA", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Flying", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("SD Card", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("AAU volt", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        
        textXPos += deltaTextPos;
        // Print time since last valid data reception
        fontSizeFactor = maxFontSizeFactor;
        if (aFlightData.lastValidDataTimeStamp > 0) {
            Calendar calender = Calendar.getInstance();
            long now = calender.getTime().getTime();

            font = new Font("New Courier", Font.BOLD, fontSize);
            g.setFont(font);
            long sinceLastData = now - aFlightData.lastValidDataTimeStamp;
            if (sinceLastData > 1000) {
                String secondsStr = String.format("%02d", (sinceLastData % 60000 / 1000));
                String minutesStr = String.format("%02d", (sinceLastData / 60000));

                String sinceLastDataStr = "" + minutesStr + ":" + secondsStr;
                g.drawString(sinceLastDataStr + " " + aFlightData.noGoodPackets + "/" + aFlightData.noBadPackets, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));

            } else {
                g.drawString("" + aFlightData.noGoodPackets + " / " + aFlightData.noBadPackets, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));

            }

        } else {
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));

        }

        g.drawString(aFlightData.rocketPosition.getLat(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getLon(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.getMCDistance(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.getMCBearing(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.getETA(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.getFlying(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.getSDCardOK(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.getAAUVoltage(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        
        fontSizeFactor = maxFontSizeFactor;
        textXPos += deltaTextPos + 20;
        g.drawString("Alt", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Dwn", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("COG", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Fix", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("V", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("V v", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("V h", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("A x", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("A y", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("A z", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        fontSizeFactor = maxFontSizeFactor;
        textXPos += deltaTextPos - 80;
        g.drawString(aFlightData.rocketPosition.getAltitude(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getDownrange(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getCOG(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getGPSFix() + " " + aFlightData.getGPSDeltaTIndex(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getVelocity(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getVerticalVelocity(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getHorizontalVelocity(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getAccX(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getAccY(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString(aFlightData.rocketPosition.getAccZ(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
