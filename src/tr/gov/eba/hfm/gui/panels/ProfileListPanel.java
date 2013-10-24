/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.gui.panels;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JList;
import javax.swing.JOptionPane;
import tr.gov.eba.hfm.config.Config;
import tr.gov.eba.hfm.controller.HostFileManager;
import tr.gov.eba.hfm.gui.main.MainFrame;
import tr.gov.eba.hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public class ProfileListPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProfileListPanel
     */
    public ProfileListPanel() {
        initComponents();
        updateList();
//        setBounds(6, 0, 200, 560);
        setVisible(true);
    }

    /**
     * Refreshes profile list.
     */
    public static void updateList() {
        profileList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = HostFileManager.getProfileNames();
            @Override
            public int getSize() { 
                if(strings !=null)
                    return strings.length;
                else
                    return 0; 
            }
            
            @Override
            public Object getElementAt(int i) { 
                if(strings !=null) return
                        strings[i];
                else
                    return ""; 
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        profileList = new javax.swing.JList();

        setBackground(new java.awt.Color(255, 255, 0));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setSize(new java.awt.Dimension(200, 560));
        setLayout(null);

        profileList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "f.hfm" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        profileList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        profileList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(profileList);

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 200, 560);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Action taken when an element is selected from profile list panel.
     * @param evt 
     */
    private void clickAction(java.awt.event.MouseEvent evt) {
        String fileName = ((JList)evt.getSource()).getSelectedValue()+"";
        BufferedReader reader = null;
        try {
            File curFile = new File(HostFileManager.getContextPath()+"/"+Config.hfmFilesName+"/"+Config.hfmProfilesName+"/"+fileName);
            reader = new BufferedReader(new FileReader(new File(curFile.getAbsolutePath())));
            String content = ""; 
            while(reader.ready()) {
                content += reader.readLine() + "\r\n";
            }
            GUIManager.instance().setAreaText(content);
            HostFileManager.readFile(curFile.getAbsolutePath()); //Reads file and creates expressions from beginning.
            MainFrame.setCurFile(curFile);
            GUIManager.instance().updateExpressionsPanel(); 
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File could not be found.");
            reader = null; //If file could not be found, deselect it.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "File could not be read.");
            reader = null; //If file could not be read, deselect it.
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Reader could not be opened.");
            }
        }
    }
    
    private void profileListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileListMouseClicked
        clickAction(evt);
    }//GEN-LAST:event_profileListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JList profileList;
    // End of variables declaration//GEN-END:variables

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        GaussianFilter filter = new GaussianFilter(20);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//            RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setBackground(Color.gray);
//        g2d.setStroke(new BasicStroke(5f));
//        g2d.drawRect(0, 0, getWidth()-5, getHeight()-5);
//
//        int x = 34;
//        int y = 34;
//        int w = getWidth() - 68;
//        int h = getHeight() - 68;
//        int arc = 30;
//
//        g2d.setColor(new Color(0, 0, 0, 220));
//        g2d.fillRoundRect(x, y, w, h, arc, arc);
//
//        g2d.setStroke(new BasicStroke(3f));
//        g2d.setColor(Color.BLACK);
//        g2d.drawRoundRect(x, y, w, h, arc, arc); 
//        g2d.dispose();

//        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
//        filter.filter(null, null);
    }
}
