/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AISDataPanel.java
 *
 * Created on 05-10-2012, 12:41:34
 */
package fidolib.ui;

import fidolib.data.AISData;
import fidolib.data.Constants;
import fidolib.data.FlightData;
import fidolib.data.VesselInfo;
import fidolib.misc.AuxiliaryFunctions;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Steen
 */
public class AISDataPanel extends ColorPanel {

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
    private transient Timer timer = new Timer();

    /**
     * Creates new form AISDataPanel
     *
     * @param useGradientColors
     * @param aAISData
     * @param textColor
     * @param gradientColorStart
     * @param gradientColorStop
     * @param backgroundColor
     * @param aFlightData
     */
    public AISDataPanel(boolean useGradientColors, Color textColor, Color backgroundColor, Color gradientColorStart, Color gradientColorStop, AISData aAISData, FlightData aFlightData) {
        this.useGradientColors = useGradientColors;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.gradientColorStart = gradientColorStart;
        this.gradientColorStop = gradientColorStop;
        this.aAISData = aAISData;
        this.aFlightData = aFlightData;
        initComponents();
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

    /**
     *
     * @param g
     */
    public void paintData(Graphics g) {

        // Set text color and font size
        g.setColor(Constants.textColor);
        int width = this.getWidth();
        int height = this.getHeight() / 12;
        int smallest = (int) ((double) width / 26);
        if (height < smallest) {
            smallest = height;
        }
        int fontSize = smallest;
        int textXPos = 20;
        int deltaTextPos = this.getWidth() / 4 + 20;
        Font font = new Font("New Courier", Font.BOLD, fontSize);
        g.setFont(font);
        int maxFontSizeFactor = 10;
        int fontSizeFactor = maxFontSizeFactor - 1;

        // Spunik

        g.drawString("Latitude ", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Longitude ", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("COG ", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("SOG ", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("TH", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("Dist. MC", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        g.drawString("BRG f. MC", textXPos, this.getHeight() - (fontSize * fontSizeFactor--));


        textXPos += deltaTextPos;
        Calendar c1 = Calendar.getInstance();
        String deltaTSputnikData;
        VesselInfo sputnik = aAISData.getVessel(aAISData.sputnikMMSI);
        fontSizeFactor = maxFontSizeFactor;
        if (sputnik == null) {
            deltaTSputnikData = Constants.naString;
            g.drawString("Sputnik " + deltaTSputnikData, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));

        } else {
            if (sputnik.timeStamp != 0) {

                long sinceLastData = c1.getTimeInMillis() - sputnik.timeStamp;
                String secondsStr = String.format("%02d", (sinceLastData % 60000 / 1000));
                String minutesStr = String.format("%02d", (sinceLastData / 60000));
                deltaTSputnikData = "" + minutesStr + ":" + secondsStr;
            } else {
                deltaTSputnikData = Constants.naString;
            }
            g.drawString("Sputnik " + deltaTSputnikData, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));

            if (sputnik.timeStamp != 0) {
                g.drawString(sputnik.pos.getLat(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(sputnik.pos.getLon(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString("" + sputnik.cog + Constants.degreeChar, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString("" + sputnik.getSOG(true), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(sputnik.getTH(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));

            } else {
            }
            if ((aAISData.getVessel(aAISData.mcMMSI) != null) && ((aAISData.getVessel(aAISData.sputnikMMSI) != null))) {
                double disanceNauticalMiles = AuxiliaryFunctions.disanceNauticalMiles(aAISData.getVessel(aAISData.mcMMSI).pos, aAISData.getVessel(aAISData.sputnikMMSI).pos);
                double distanceMeters = disanceNauticalMiles * Constants.nauticalMile;

                DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols(new Locale("da", "DK"));
                decimalSymbols.setDecimalSeparator('.');
                decimalSymbols.setGroupingSeparator(',');
                DecimalFormat df = new DecimalFormat("0.0", decimalSymbols);
                String distStr = "" + df.format(disanceNauticalMiles) + " / " + df.format(distanceMeters / 1000);

                g.drawString(distStr, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                String brgStr = "" + AuxiliaryFunctions.initialBearing(aAISData.getVessel(aAISData.mcMMSI).pos, aAISData.getVessel(aAISData.sputnikMMSI).pos) + Constants.degreeChar;
                g.drawString(brgStr, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));

            } else {
                g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            }
        }

        // Mission control
        textXPos += deltaTextPos + 20;
        fontSizeFactor = maxFontSizeFactor;
        //textPos += deltaTextPos / 2;

        String deltaTMCData;
        VesselInfo mc = aAISData.getVessel(aAISData.mcMMSI);

        if (mc == null) {
            deltaTMCData = Constants.naString;
            g.drawString("MC " + deltaTMCData, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            // g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
        } else {
            if (mc.timeStamp != 0) {

                long sinceLastData = c1.getTimeInMillis() - mc.timeStamp;
                String secondsStr = String.format("%02d", (sinceLastData % 60000 / 1000));
                String minutesStr = String.format("%02d", (sinceLastData / 60000));
                deltaTMCData = "" + minutesStr + ":" + secondsStr;
            } else {
                deltaTMCData = Constants.naString;
            }
            g.drawString("MC " + deltaTMCData, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            if (mc.timeStamp != 0) {
                g.drawString(mc.pos.getLat(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(mc.pos.getLon(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString("" + mc.cog + Constants.degreeChar, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString("" + mc.getSOG(true), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(mc.getTH(), textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            } else {
                g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
                // g.drawString(Constants.naString, textXPos, this.getHeight() - (fontSize * fontSizeFactor--));
            }
        }
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
