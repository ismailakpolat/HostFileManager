/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.manager;

import java.awt.Color;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import tr.gov.eba.check.Checker;
import tr.gov.eba.hfm.controller.HostFileManager;
import tr.gov.eba.hfm.entity.Expression;
import tr.gov.eba.hfm.entity.ExtendedDnsIp;
import tr.gov.eba.hfm.gui.main.MainFrame;
import tr.gov.eba.hfm.gui.panels.RightPanel;

/**
 *
 * @author ismailakpolat
 */
public class GUIManager {

    private static GUIManager uniqueInstance = null;
    private MainFrame mainFrame;

    private GUIManager() {
        mainFrame = new MainFrame();
    }

    public static GUIManager instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GUIManager();
        }
        return uniqueInstance;
    }

    /**
     * Adds new dns to right pane.
     *
     * @param dns Name of dns.
     */
    public void addDnsToExpessions(String dns) {
        boolean exists = false;
        for (Expression expression : HostFileManager.expressions) {
            if (expression.getDnsName().equals(dns)) {
                System.out.println("Dns already exists.");
                exists = true;
                break;
            }
        }

        //If DNS does not exists, adds it.
        if (!exists) {
            HostFileManager.expressions.add(new Expression(dns, new ArrayList<ExtendedDnsIp>()));
            updateExpressionsPanel();
        }
    }

    /**
     * Append text to the host terminal screen then removes old dns.
     *
     * @param text
     */
    public void changeDns(String dns, String ip) {
        String area = mainFrame.getArea().getText();
        StringTokenizer tokenizer = new StringTokenizer(area, System.lineSeparator());
        ArrayList<String> list = new ArrayList<String>();

        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }

        boolean isAdded = false;
        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i);

            /**
             * Check if dns and ip is included in list.
             */
            if (temp.matches(getDnsContainsRegex(dns))) {
                System.out.println("Matches: " + temp);
                /**
                 * Check if # is located after dns and ip or not exists.
                 */
                if (temp.indexOf("#") < 0 || (temp.indexOf("#") > temp.indexOf(dns))) {

                    /**
                     * Check if dns contains white spaces at the end and in the
                     * beginning then that is correct dns to replaced.
                     */
                    temp = temp.replaceAll(dns, "\t");

                    if (temp.indexOf("#") > 0) {
                        temp = temp.substring(0, temp.indexOf("#"));
                    }

                    String oldIp = Checker.checkIPAddress(temp.trim());


                    if (oldIp != null) {
                        list.remove(i);
                        list.add(i, ip + "\t" + dns);
                        isAdded = true;
                        break;
                    } else {
                        tokenizer = new StringTokenizer(temp);
                        oldIp = tokenizer.nextToken();
                        oldIp = Checker.checkIPAddress(oldIp);
                        if (oldIp == null) {
                            System.out.println("Ip format or line is wrong.");
                        } else {
                            isAdded = true;
                            list.set(i, list.get(i).replace(dns, ""));
                            list.add(i + 1, ip + "\t" + dns);
                            break;
                        }

                    }
                }
                /**
                 * If dns does not exist, adds it to the end.
                 */
            } else {
//                System.out.println("else");
            }
        }

        /**
         * If data does not exist before.
         */
        if (!isAdded) {
            list.add(ip + "\t" + dns);
        }

        /**
         * Re-set text to area.
         */
        mainFrame.getArea().setText("");

        for (String line : list) {
            SimpleAttributeSet keyWord;
            String wsline = line.replaceAll("\\s", "");
            if (wsline.equals(ip + dns)) {
                keyWord = new SimpleAttributeSet();
                StyleConstants.setForeground(keyWord, Color.GREEN);
            } else {
                keyWord = new SimpleAttributeSet();
                StyleConstants.setForeground(keyWord, Color.WHITE);
            }


            try {
                mainFrame.getArea().getStyledDocument().insertString(0, line + System.lineSeparator(), keyWord);
            } catch (BadLocationException ex) {
                System.out.println("Bad location exception.");
            }
        }
        mainFrame.getArea().setVisible(false);
        mainFrame.getArea().setVisible(true);
    }

    /**
     * Remove dns from hosts screen.
     *
     * @param dns
     */
    public void removeDns(String dns) {
        String area = mainFrame.getArea().getText();
        StringTokenizer tokenizer = new StringTokenizer(area, System.lineSeparator());
        ArrayList<String> list = new ArrayList<String>();

        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }

        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i);

            /**
             * Check if dns and ip is included in list.
             */
            if (temp.matches(getDnsContainsRegex(dns))) {
                /**
                 * Check if # is located after dns and ip or not exists.
                 */
                if (temp.indexOf("#") < 0 || (temp.indexOf("#") > temp.indexOf(dns))) {

                    temp = temp.replaceAll(dns, "\t");

                    if (temp.indexOf("#") > 0) {
                        temp = temp.substring(0, temp.indexOf("#"));
                    }

                    String oldIp = Checker.checkIPAddress(temp.trim());
                    if (oldIp != null) { //If one data(dns-ip) in a row
                        list.remove(i);
//                        break;
                    } else {
                        tokenizer = new StringTokenizer(temp);
                        oldIp = tokenizer.nextToken();
                        oldIp = Checker.checkIPAddress(oldIp);
                        if (oldIp == null) {
                            System.out.println("Ip format or line is wrong.");
                        } else { //If more than one data(dns-ip), in a line.
                            list.set(i, list.get(i).replace(dns, ""));
//                            break;
                        }
                    }
                }
            } else {
//               System.out.println("File is not properly set.");
            }
        }

        /**
         * Re-set text to area.
         */
        mainFrame.getArea().setText("");
        for (String line : list) {
            try {
                mainFrame.getArea().getDocument().insertString(0, line + System.lineSeparator(), null);
            } catch (BadLocationException ex) {
                System.out.println("Bad location exception.");
            }
        }
    }

    /**
     *
     * @param dns
     * @return dns specific contains regular expression.
     */
    private String getDnsContainsRegex(String dns) {
        return "[[. ][^.]]*[" + System.lineSeparator() + "\t ]+" + dns + "[[" + System.lineSeparator() + "\t ][[. ][^.]]]*";
    }

//    /**
//     * @param dns
//     * @return dns search regular expression.
//     */
//    private String getDnsSearchRegex(String dns) {
//        return "["+System.lineSeparator()+"\t ]+"+dns+"["+System.lineSeparator()+"\t ]+";        
//    }
//    
//    /**
//     * Checks whether given dns parameter is word in given line.
//     * 
//     * @param line Line of string where dns will be searched.
//     * @param dns a string parameter.
//     * @return true if dns contains space, tab or eof at the end and in the beginning, otherwise false.
//     */
//    private boolean checkIfASingleWord(String line, String dns) {
//        //If false no space exists in the beginning.
//        boolean isBeforeReq = 0 <= line.indexOf(dns)-1 ? false : true;
//        //If false no space exists at the end.
//        boolean isAfterReq = line.length() < line.indexOf(dns)+dns.length() ? true : false;
//        
//        System.out.println(isAfterReq + " : " + line.length()+ " " + (line.indexOf(dns)+dns.length()));
//        
//        String before = !isBeforeReq ? "" : line.substring(line.indexOf(dns)-1,line.indexOf(dns)); //String before dns.
//        String after = !isAfterReq ? "" : line.substring(line.indexOf(dns)+dns.length(),line.indexOf(dns)+dns.length()+1); //String after dns.
//        
//        //Check if before-after is required and if any of them are required check white spaces.
//        if((isBeforeReq || (before.equals(" ") || before.equals("\t") || before.equals(System.lineSeparator())))
//                && (isAfterReq || (after.equals(" ") || after.equals("\t") || after.equals(System.lineSeparator())))) {
//            return true;
//        } else {
//            return false;
//        }
//    }    
    /**
     * Retrieves texts from main panel text area.
     *
     * @return Main panel text area value.
     */
    public String getAreaText() {
        return mainFrame.getArea().getText();
    }

    /**
     * Sets content of text area.
     *
     * @param text New content of text area.
     */
    public void setAreaText(String text) {
        mainFrame.getArea().setText(text);
    }

    public void safeQuit() {
        mainFrame.dispose();
    }

    public void updateExpressionsPanel() {
        mainFrame.updateRightPane();
    }

    public RightPanel getRightPane() {
        return mainFrame.rightPane;
    }
}
