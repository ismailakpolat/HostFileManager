/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.start;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tr.gov.eba.hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public class RunApp {

    public static void main(String[] args) {
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        GUIManager.instance();
    }
}
