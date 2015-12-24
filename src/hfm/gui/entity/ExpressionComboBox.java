/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfm.gui.entity;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import hfm.entity.ExtendedDnsIp;
import hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public final class ExpressionComboBox extends JComboBox implements ItemListener {

    private ExpressionContainer container;
    public ExpressionComboBox(ArrayList<ExtendedDnsIp> items, ExpressionContainer exp) {
        boolean isOnlineAdded = false; //Holds if canlı option is added.
        container = exp;
        for(ExtendedDnsIp item : items) {
            if(!item.isActive() && !isOnlineAdded) { //If an inactive item is found add 'canlı' item to one place higher.
                super.addItem("canlı");
                isOnlineAdded = true;
            }
            super.addItem(((ExtendedDnsIp)item).getName());
        }
        if(!isOnlineAdded)
            super.addItem("canlı");
        setSize(160,40);
        applyColor();
        addItemListener(this);
//        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(container.getSelectedIp().equals("canlı")) {
            GUIManager.instance().removeDns(container.getDns());
            container.paintExprsLabel(new Color(255, 153, 153));
        } else {
            GUIManager.instance().changeDns(container.getDns(), container.getSelectedIp());
            container.paintExprsLabel(Color.green);
        }
    }
    
    /**
     * @return Whether given dns is in production or test environment.
     */
    public boolean isProd() {
        if(((String)super.getItemAt(0)).equals("canlı")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Repaints this label with new color or not according to development env.
     */
    public void applyColor() {
        if(isProd()) {
            container.paintExprsLabel(new Color(255, 153, 153));
        }
    }
    
}
