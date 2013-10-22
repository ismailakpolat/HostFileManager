/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.gov.eba.hfm.gui.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import tr.gov.eba.hfm.config.Config;
import tr.gov.eba.hfm.controller.HostFileManager;
import tr.gov.eba.hfm.gui.panels.MainPanel;
import tr.gov.eba.hfm.gui.panels.ProfileListPanel;
import tr.gov.eba.hfm.gui.panels.RightPanel;
import tr.gov.eba.hfm.manager.GUIManager;

/**
 *
 * @author ismailakpolat
 */
public class MainFrame extends javax.swing.JFrame {

    public static MainPanel mainPane;
    public static RightPanel rightPane;
    public static ProfileListPanel profilePane;
    public static File curFile;
    
    /** Creates new form MainFrame */
    public MainFrame() {
        /**
         * These 4 inits order must not be changed.
         */
        initComponents();
        mainPane = new MainPanel();
        rightPane = new RightPanel();
        profilePane = new ProfileListPanel();
        initFrame();
        
        setBounds(0,0,1200,650);
        add(mainPane);
        rightPaneContainer.add(rightPane);
        add(profilePane);
        setVisible(true);
        setResizable(false);
        curFile = null;
    }
    
    private void initFrame() {
        HostFileManager.initHfm();
        try {
            mainPane.getArea().getDocument().insertString(0,HostFileManager.hostFileContent,null);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
//        mainPane.getArea().setText(HostFileManager.hostFileContent);
        rightPane.recreateContent();
    }
    
    public void updateRightPane() {
        rightPane.recreateContent();
    }

    public JTextPane getArea() {
        return mainPane.getArea();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightPaneContainer = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        quitItem = new javax.swing.JMenuItem();
        fileItem = new javax.swing.JMenu();
        openItem = new javax.swing.JMenuItem();
        saveItem = new javax.swing.JMenuItem();
        saveAsItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        saveExpressionItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 650));
        getContentPane().setLayout(null);

        rightPaneContainer.setBackground(new java.awt.Color(204, 255, 255));
        rightPaneContainer.setLayout(null);
        getContentPane().add(rightPaneContainer);
        rightPaneContainer.setBounds(780, 0, 420, 650);

        jMenu2.setText("File");

        quitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitItem.setText("Quit");
        quitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitItemActionPerformed(evt);
            }
        });
        jMenu2.add(quitItem);

        menuBar.add(jMenu2);

        fileItem.setText("Profile");

        openItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openItem.setText("Open");
        openItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemActionPerformed(evt);
            }
        });
        fileItem.add(openItem);

        saveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveItem.setText("Save");
        saveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });
        fileItem.add(saveItem);

        saveAsItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsItem.setText("Save As");
        saveAsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsItemActionPerformed(evt);
            }
        });
        fileItem.add(saveAsItem);

        menuBar.add(fileItem);

        jMenu1.setText("Expressions");

        saveExpressionItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveExpressionItem.setText("Save");
        saveExpressionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveExpressionItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveExpressionItem);

        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    private String getSavePath() {
//        try {
//            return MainFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().substring();
//        } catch (URISyntaxException ex) {
//            return "";
//        }
//    }
    
    /**
     * Save text in host editor screen to a profile file.
     * 
     * @param evt Event object.
     */
    private void saveAsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsItemActionPerformed
        JFileChooser chooser = new JFileChooser();
        
        //Set directory of chooser and creates if required folders do not exist.
        setProfileDirectory(chooser);
        
//        chooser.setControlButtonsAreShown(false);
        chooser.setMultiSelectionEnabled(false);
        chooser.setAcceptAllFileFilterUsed(false);
//        chooser.setFileFilter(new FileNameExtensionFilter("hfm files", ".sasdfms"));
        chooser.setFileFilter(new FileNameExtensionFilter("hfm files",  "hfm"));
        
        
        int retVal = chooser.showSaveDialog(null);
        if(retVal == JFileChooser.APPROVE_OPTION) {
            setCurFile(chooser.getSelectedFile());
            if(curFile.getName().length() > 4) {
                if(!curFile.getName().substring(curFile.getName().length()-4).equals(Config.hfmFilesExtension)) {
                    setCurFile(new File(curFile.getAbsolutePath() + Config.hfmFilesExtension));
                }
            } else {
                setCurFile(new File(curFile.getAbsolutePath() + Config.hfmFilesExtension));
            }
            String filePath = curFile.getAbsolutePath(); //getName
            System.out.println("fpath:"+filePath);
            BufferedWriter writer = null;
            try {
                saveUpdate(writer, filePath);
                ProfileListPanel.updateList();  //To update profile list.
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not write to file.");
            } finally {
                try {
                    if(writer != null)
                        writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not close writer.");
                }
            }
            
        }
//        HostFileManager.readHfmFiles(false, true);
    }//GEN-LAST:event_saveAsItemActionPerformed

    /**
     * Saves editor pane contents and expressions to file and do some update operations.
     * 
     * @param writer BufferedWriter object.
     * @param path Path to write content into.
     * @throws IOException 
     */
    private void saveUpdate(BufferedWriter writer, String path) throws IOException {
        writer = new BufferedWriter(new FileWriter(new File(path)));
        writer.write(GUIManager.instance().getAreaText());
        writer.flush();

        HostFileManager.readFile(curFile.getAbsolutePath());//Re-set expressions value.
        HostFileManager.saveExpressions(); //Write new expressions to file.
        GUIManager.instance().updateExpressionsPanel();  
    }
    
    /**
     * Read profile from folder.
     * 
     * @param evt Event object.
     */
    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        JFileChooser chooser = new JFileChooser();
        
        //Set directory of chooser and creates if required folders do not exist.
        setProfileDirectory(chooser);
        
        int retVal = chooser.showOpenDialog(null);
        
        if(retVal == JFileChooser.OPEN_DIALOG) {
            BufferedReader reader = null;
            File chosenFile = chooser.getSelectedFile();
            try {
                reader = new BufferedReader(new FileReader(new File(chosenFile.getAbsolutePath())));
                String content = ""; 
                while(reader.ready()) {
                    content += reader.readLine() + "\r\n";
                }
                GUIManager.instance().setAreaText(content);
                HostFileManager.readFile(chosenFile.getAbsolutePath()); //Reads file and creates expressions from beginning.
                setCurFile(chosenFile);
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
        GUIManager.instance().updateExpressionsPanel();
    }//GEN-LAST:event_openItemActionPerformed

    private void setProfileDirectory(JFileChooser chooser) {
//        try {
//            File mfile = new File(MainFrame.class.getProtectionDomain().getCodeSource().
//                    getLocation().toURI().getPath());
            String pPath = HostFileManager.getContextPath();
            File file = new File(/*mfile.getParentFile().getPath()*/pPath + "/"+Config.hfmFilesName + "/" +Config.hfmProfilesName);
            if(!file.exists()) {
                file.mkdirs();
            }
            chooser.setCurrentDirectory(file);
//        } catch (URISyntaxException ex) {
//            JOptionPane.showMessageDialog(null, "URI Syntax of templates path is wrong.");
//        }
    }
    
    /**
     * Saves current text in text area to opened profile file.
     * 
     * @param evt Event object.
     */
    private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
        if(curFile == null) {
            saveAsItemActionPerformed(evt);
        } else {
            BufferedWriter writer = null;
            try {
                saveUpdate(writer, curFile.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not save(write) file.");
            } finally {
                try {
                    if(writer != null)
                        writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Could not close writer.");
                }
            }
            JOptionPane.showMessageDialog(null, "File update success.");
        }
    }//GEN-LAST:event_saveItemActionPerformed

    private void saveExpressionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveExpressionItemActionPerformed
        boolean isSaved = HostFileManager.saveExpressions();
        if(isSaved) {
            JOptionPane.showMessageDialog(null, "Expressions are updated.");
        }
    }//GEN-LAST:event_saveExpressionItemActionPerformed

    private void quitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitItemActionPerformed
        GUIManager.instance().safeQuit();
    }//GEN-LAST:event_quitItemActionPerformed

    /**
     * Set curFile parameter to current file edited and updates title.
     * 
     * @param file File that is currently edited.
     */
    public static void setCurFile(File file) {
        curFile = file;
        mainPane.getMainScrPane().setViewportBorder(BorderFactory.createTitledBorder(file.getName()));
        mainPane.setVisible(false);
        mainPane.setVisible(true);
    }

    public static File getCurFile() {
        return curFile;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu fileItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JMenuItem quitItem;
    private javax.swing.JPanel rightPaneContainer;
    private javax.swing.JMenuItem saveAsItem;
    private javax.swing.JMenuItem saveExpressionItem;
    private javax.swing.JMenuItem saveItem;
    // End of variables declaration//GEN-END:variables

}
