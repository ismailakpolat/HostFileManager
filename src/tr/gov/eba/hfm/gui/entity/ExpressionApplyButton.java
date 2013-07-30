///*
// * Is not used currently.
// */
//package tr.gov.eba.hfm.gui.entity;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import tr.gov.eba.hfm.manager.GUIManager;
//
///**
// *
// * @author ismailakpolat
// */
//public class ExpressionApplyButton extends JButton implements ActionListener {
//
//    private ExpressionContainer container;
//    public ExpressionApplyButton(String text, ExpressionContainer exp) {
//        super(text);
//        addActionListener(this);
//        setSize(70, 30);
//        container = exp;
////        setForeground(Color.WHITE);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
////        System.out.println(container.getSelectedIp());
//        if(container.getSelectedIp().equals("canlı")) {
//            GUIManager.instance().removeDns(container.getDns());
//        } else {
//            GUIManager.instance().changeDns(container.getDns(), container.getSelectedIp());
//        }
//    }
//    
//}
