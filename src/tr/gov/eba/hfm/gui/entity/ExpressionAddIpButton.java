/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.gui.entity;

import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import tr.gov.eba.check.Checker;
import tr.gov.eba.hfm.controller.HostFileManager;
import tr.gov.eba.hfm.entity.ExtendedDnsIp;
import tr.gov.eba.hfm.gui.panels.IpTextFieldPanel;
import tr.gov.eba.hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public class ExpressionAddIpButton extends JButton implements ActionListener {

    private ExpressionContainer container;
    public ExpressionAddIpButton(ExpressionContainer exp) {
        super(new ImageIcon(ExpressionAddIpButton.class.getResource("/tr/gov/eba/hfm/images/plus.png")));
//        setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        setSize(16, 16);
        container = exp;
        addActionListener(this);
    }

    /**
     * Adds ip to iplist next to corresponding dns.
     * 
     * @param e Event object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        final IpTextFieldPanel field = new IpTextFieldPanel();
        field.setPreferredSize(new Dimension(180,45));
        final JButton but = new JButton("Add");
        but.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Checker.checkIPAddress(field.getIp()) != null) {
                    for(int i=0; i < HostFileManager.expressions.size(); i++) {
                        //Find corresponding container.
                        if(HostFileManager.expressions.get(i).getDnsName().equals(container.getDns())) {
                            HostFileManager.expressions.get(i).addIp(new ExtendedDnsIp(field.getIp(),false));
                            GUIManager.instance().updateExpressionsPanel();
                            break;
                        }
                    }
                    
//                    for(Expression s:HostFileManager.expressions) {
//                        if(s.getDnsName().equals("elma")) {
//                            System.out.println(s.getIpList());
//                        }
//                    }
                
                    Window w = SwingUtilities.getWindowAncestor(but);
                    if(w != null) {
                        w.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(GUIManager.instance().getRightPane() ,"Ip field must be filled.");
                }
            }
        });
        /*int retVal = */JOptionPane.showOptionDialog(GUIManager.instance().getRightPane(), field, "Add IP", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE,null,new JButton[]{but},null);
//        if(retVal == JOptionPane.OK_OPTION) {
//            for(int i=0; i < HostFileManager.expressions.size(); i++) {
//                //Find corresponding container.
//                if(HostFileManager.expressions.get(i).getDnsName().equals(container.getDns())) {
//                    HostFileManager.expressions.get(i).addIp(field.getName());
//                    MainFrame.rightPane.recreateContent();
//                    break;
//                }
//            }
//        }
        
    }
    
    
}
