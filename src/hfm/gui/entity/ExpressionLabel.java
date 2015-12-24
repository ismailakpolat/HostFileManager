/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfm.gui.entity;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public class ExpressionLabel extends JLabel {
    
    public ExpressionLabel(String text) {
        super(text);
        setSize(150, 40);
        setForeground(Color.GREEN);
        setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        setToolTipText(text);
//        addActionListener(this);
//        setEnabled(false);
//        setVisible(true);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println(getText());
//        GUIManager.instance().appendText(getText());
//    }
    
}
