/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.gui.panels;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import tr.gov.eba.hfm.controller.HostFileManager;
import tr.gov.eba.hfm.gui.main.MainFrame;

/**
 *
 * @author ismailakpolat
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
//        setBounds(0,0,800,600);
        setVisible(true);
    }
    
    public JTextPane getArea() {
        return textArea;
    }
    
    public JScrollPane getMainScrPane() {
        return mainScrPane;
    }
    
    /**
     * Set scroll pane title to given parameter.
     * 
     * @param title Title to be given.
     */
    public void setScrTitle(String title) {
        mainScrPane.setViewportBorder(BorderFactory.createTitledBorder(title));
    }
    
//    public String getScrTitle() {
//        mainScrPane.getViewport().geş
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        mainScrPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();

        setPreferredSize(new java.awt.Dimension(775, 650));
        setSize(new java.awt.Dimension(780, 650));
        setLayout(null);

        jButton1.setText("Update Hosts File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(590, 560, 180, 29);

        mainScrPane.setViewportBorder(javax.swing.BorderFactory.createTitledBorder("Default"));

        textArea.setBackground(new java.awt.Color(70, 70, 70));
        textArea.setBorder(null);
        textArea.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        textArea.setForeground(new java.awt.Color(255, 255, 255));
        textArea.setCaretColor(new java.awt.Color(255, 255, 255));
        mainScrPane.setViewportView(textArea);

        add(mainScrPane);
        mainScrPane.setBounds(6, 0, 768, 560);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        System.out.println(MainFrame.mainPane.getArea().getText());
        HostFileManager.createHostFile(MainFrame.mainPane.getArea().getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane mainScrPane;
    private javax.swing.JTextPane textArea;
    // End of variables declaration//GEN-END:variables
}
