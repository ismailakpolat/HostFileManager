/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfm.gui.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import hfm.gui.panels.IpRemovePanel;

/**
 *
 * @author ismailakpolat
 */
public class ExpressionRemoveIpButton extends JButton implements ActionListener {

    private ExpressionContainer container;
    public ExpressionRemoveIpButton(ExpressionContainer exp) {
        super(new ImageIcon(ExpressionAddIpButton.class.getResource("/tr/gov/eba/hfm/images/minus.png")));
        setSize(16, 16);
        container = exp;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final IpRemovePanel irPane = new IpRemovePanel(container);
//        final JButton but = new JButton("Remove");
//        but.addActionListener(new java.awt.event.ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                irPane.removeSelecteds();
//                Window w = SwingUtilities.getWindowAncestor(but);
//                if(w != null) {
//                    w.dispose();
//                }
//            }
//        });
        
        int retVal = JOptionPane.showOptionDialog(null, irPane, "Remove ips", 
                JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"Back", "Remove"}/*new JButton[]{but}*/, null);
        if(retVal == 1) {
            irPane.removeSelecteds();
        } else if (retVal == 0) {
            System.out.println("do nothing.");
        } 
    }
    
}
