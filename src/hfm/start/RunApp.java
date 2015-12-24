/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfm.start;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.AbstractRegionPainter;
import hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public class RunApp {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        //        UIManager.put("nimbusBase", Color.BLACK);
        //To disable nimbus to override jtextpane background color
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter",null);
        
//        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

//        try {
//            Runtime.getRuntime().exec("sudo sh /Users/ismailakpolat/Desktop/scr.sh");
//        } catch (IOException ex) {
//            System.out.println(ex.getLocalizedMessage());
//            JOptionPane.showMessageDialog(null, "sudo");
//        }
        GUIManager.instance();
    }
}
