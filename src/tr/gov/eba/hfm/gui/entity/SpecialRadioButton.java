/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.gui.entity;

import javax.swing.JRadioButton;

/**
 *
 * @author ismailakpolat
 */
public class SpecialRadioButton extends JRadioButton {
    private int order;

    public SpecialRadioButton(int order) {
        this.order = order;
    }
    
    public int getOrder() {
        return order;
    }
}
