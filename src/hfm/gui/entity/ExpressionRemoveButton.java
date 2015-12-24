/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfm.gui.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import hfm.controller.HostFileManager;
import hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public class ExpressionRemoveButton extends JButton implements ActionListener {

    private ExpressionContainer container;
    public ExpressionRemoveButton(ExpressionContainer container) {
        super(new ImageIcon(ExpressionAddIpButton.class.getResource("/tr/gov/eba/hfm/images/remove.png")));
        this.container = container;
        setSize(16, 16);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HostFileManager.removeExpression(container.getDns());
        GUIManager.instance().updateExpressionsPanel();
    }
    
    
}
