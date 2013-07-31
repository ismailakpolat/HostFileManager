/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import tr.gov.eba.check.Checker;
import tr.gov.eba.hfm.config.Config;
import tr.gov.eba.hfm.entity.Expression;
import tr.gov.eba.hfm.entity.ExtendedDnsIp;
import tr.gov.eba.hfm.json.util.Utility;

/**
 *
 * @author ismailakpolat
 */ //6,6,408,420 expressionsPanel
public class HostFileManager {
    
    public static ArrayList<File> profileFiles;
    public static ArrayList<Expression> expressions;
    public static String hostFileContent = "";

    /**
     * Initializes profileFiles, expressions and hostFileContent.
     */
    public static void initHfm() {
        //Order is important do not change.
        readHostFile(); //Reads current hosts file.
        readHfmFiles(true, true); //Reads hfm files (profile files).
        saveExpressions(); //Saves current expressions to file.
//        GUIManager.instance().updateExpressionsPanel(); //Update right panel
    }

    /**
     * Read file.
     * @param path Path of file to be read. 
     * 
     * @return file content.
     */
    public static void readFile(String path) {
        BufferedReader reader = null;
        expressions = new ArrayList<Expression>();
        try {
//            String path = getHostFilePath();
            if(path == null) {
                JOptionPane.showMessageDialog(null, "Operating system is not supported.Consult application developer.");
            } else {
                reader = new BufferedReader(new FileReader(new File(path))); 
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Hosts file not found.");
        }
        
        hostFileContent = "";
        if (reader != null) {
            try {
                while (reader.ready()) {
                    String line = reader.readLine();
                    createExpressionFromLine(line); //Create expressions from dns file.
                    hostFileContent += line + "\r\n"; //Host editor screen content.
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Hosts file could not be read.");
            }
        }
        
        try {
            if(reader != null)
                reader.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Reader could not be closed.");
        }
    }
    
    /**
     * Read hosts file according to its os name.
     *
     * @return hosts file content.
     */
    public static void readHostFile() {
        String path = getHostFilePath();
        if( path == null) {
            JOptionPane.showMessageDialog(null, "Operating system is not supported.Consult application developer.");
        } else {
            readFile(getHostFilePath());
        }
    }
    
    /**
     * Saves expressions in list to expressions file.
     */
    public static boolean saveExpressions() {
        BufferedWriter writer = null;
        String path = getContextPath();
        if(path != null) {
            try {
                writer = new BufferedWriter(new FileWriter(new File(path+"/"+Config.hfmFilesName+"/"+Config.hfmExpressionsNameExt)));
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                writer.write(gson.toJson(expressions));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not write to expression file.(Try run as administrator)");
                return false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Unknown error occured saving expressions.Consult developer.");
                return false;
            } finally {
                try {
                    if(writer != null)
                        writer.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Writer could not be closed.");
                    return false;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unknown error occured closing writer.Consult developer.");
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Create expression from given line add it to lisr.
     * 
     * @param line a line of host file.
     */
    private static void createExpressionFromLine(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        
        ArrayList<ExtendedDnsIp> list = new ArrayList<ExtendedDnsIp>();

        while(tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            boolean isStartUsed = false;
            
            //
            if(word.startsWith("#") && !isStartUsed) {
                isStartUsed = true;
                if(word.length() > 1) { //if word not only consists of #s but some other things.
                    if(list.isEmpty()) {
                        word = word.substring(1);
                        list.add(new ExtendedDnsIp(word, false));
                    } else {
                        break;
                    }
                }

            //If # is at some place of the word, cut the part before # and add it to the list.
            } else if (word.contains("#")) {
                if(word.indexOf("#")>0) {
                    word = word.substring(0, word.indexOf("#"));
                    list.add(new ExtendedDnsIp(word,true));
                }
                break;
            //If word does not contains #, then continue.
            } else {
                list.add(new ExtendedDnsIp(word,true));
            }
        }

        //If at least one proper dns-ip match is given.
        if(list.size() > 1) {
            if(Checker.checkIPAddress(list.get(0).getName()) != null) {
                for(int i=1; i<list.size(); i++) { //For all elements, check if they are exists in expressions list.
                    boolean isAdded = false;
                    for(int j=0; j<expressions.size(); j++) {
                        if(expressions.get(j).getDnsName().equals(list.get(i).getName())) { //If dns has been added to expression list already.
                            //Put ip to correct place
                            int m = 0;
                            for(ExtendedDnsIp s : expressions.get(j).getIpList()) {
                                if(!s.isActive() && list.get(0).isActive()) { //list.get(i).isActive check can be done with one if.
                                    break;
                                }
                                m++;
                            }
                            expressions.get(j).addIp(m,list.get(0));  //Add ip to ip list of respective dns.
                            isAdded = true;
                            break;
                        }
                    }

                    if(!isAdded) { //If ip has not been added yet, adds new expression to list.
                        ArrayList<ExtendedDnsIp> ipList = new ArrayList<ExtendedDnsIp>();
                        ipList.add(list.get(0));
                        expressions.add(new Expression(list.get(i).getName(), ipList));
                    }
                }
            }
        }
    }
    
    /**
     * Recreates hosts file.
     * 
     * @param fileContent Content of newly created hosts file.
     */
    public static void createHostFile(String text) {
        BufferedWriter writer = null;
        boolean isSuccess = true;
        try {
            writer = new BufferedWriter(new FileWriter(new File(getHostFilePath())));
            writer.write(text);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Need admin permission(Run as admin).Could not write to hosts file.");
            isSuccess = false;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Unexpected Exception.");
            isSuccess = false;
        } finally {
            try {
                if(writer != null)
                    writer.close();
            } catch(IOException io) {
                JOptionPane.showMessageDialog(null, "Writer could not be closed.");
            }
        }
        if(isSuccess) {
            JOptionPane.showMessageDialog(null, "Host file update success.");
        }
        
    }

    /**
     * Discovers path of hosts file.
     * 
     * @return Operating system dependant hosts file path.
     */
    private static String getHostFilePath() {
        String path = null;
        if (System.getProperty("os.name").startsWith("Mac") || System.getProperty("os.name").startsWith("Linux")) {
            path = "/etc/hosts";
        } else if (System.getProperty("os.name").startsWith("Windows")) {
            path = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        }
        return path;
    }

    /**
     * Get context path of the jar file of this project.
     * 
     * @param jarPath path of jar file.
     * @return context path of jar file without including jar name.
     */
    private static String getContextPath(String jarPath) {
        return jarPath.substring(0, jarPath.lastIndexOf("/"));
    }
    
    /**
     * Get context path of the jar file of this project.
     * 
     * @return context path of jar file without including jar name.
     */
    public static String getContextPath() {
        String jarPath;
        try {
            jarPath = HostFileManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            return jarPath.substring(0, jarPath.lastIndexOf("/"));
        } catch (URISyntaxException ex) {
            JOptionPane.showMessageDialog(null, "URI Syntax of context path is wrong.s");
        }
        return null;
    }
    
    /**
     * Read defined hosts profiles.
     * 
     * @param readProfiles Allow reading profile files.
     * @param readExpressions Allow reading expression files.
     * @return Content of selected hosts profile.
     */
    public static void readHfmFiles(boolean readProfiles, boolean readExpressions) {
        BufferedReader reader = null;
        try {
            profileFiles = new ArrayList<File>();
//            expressions = new ArrayList<Expression>();
            String contextPath = getContextPath(HostFileManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            File files = new File(contextPath+"/"+Config.hfmFilesName);

            if(!files.exists()) {
                files.mkdir();
            }
            for (final File fileEntry : files.listFiles()) {
                //read from second row in tree hierarchy(profiles folder)
                if (readProfiles && fileEntry.isDirectory() && fileEntry.getName().equals(Config.hfmProfilesName)) {
                    for(final File f : fileEntry.listFiles()) {
                        profileFiles.add(f);
//                        System.out.println(f.getName());
                    }
                } else {
                    //read first row in tree hierarchy
                    if(readExpressions && fileEntry.getName().equals(Config.hfmExpressionsNameExt)) {
                        fileEntry.getAbsolutePath();
                        //Read prepared hfm files.
                        ArrayList<Expression> fastExprsList = Utility.readListJson(fileEntry.getAbsolutePath(), new TypeToken<ArrayList<Expression>>() {}.getType());
                        
                        //Check existence of new expressions in old expressions list
                        for(int i=0; i<fastExprsList.size(); i++) {
                            secondloop: for(int j=0; j<expressions.size(); j++) {
                                if(fastExprsList.get(i).getDnsName().equals(expressions.get(j).getDnsName())){ //If dns exists with given dns name.
                                    if(fastExprsList.get(i).getIpList() != null) {
                                        for(int k=fastExprsList.get(i).getIpList().size()-1; 0 <= k; k--) {
                                        //                                    for(ExtendedDnsIp ip : exList.get(i).getIpList()) {
    //                                        if(!expressions.get(i).getIpList().contains(ip)) { //Check if hfm expression is contained in expressions.
    //                                            expressions.get(i).addIp(ip);
    //                                            exList.get(i).removeIp(ip); //Remove added 
    //                                            if(exList.get(i).getIpList().isEmpty()) { //If no ip remain in the list remove expression from list.
    //                                                exList.remove(i);
    //                                                i--; //Set new i value.
    //                                            }
    //                                        }
                                            if(!expressions.get(j).getIpList().contains(fastExprsList.get(i).getIpList().get(k))) { //Check if hfm expression is contained in expressions.
                                                expressions.get(j).addIp(fastExprsList.get(i).getIpList().get(k)); //Add ip to expressions if not exists.
                                            } else {
                                                System.out.println("ex:"+fastExprsList.get(i).getIpList().get(k));
                                            }
                                            fastExprsList.get(i).removeIp(fastExprsList.get(i).getIpList().get(k)); //Remove added 
                                            if(fastExprsList.get(i).getIpList().isEmpty()) { //If no ip remain in the list remove expression from list.
                                                fastExprsList.remove(i);
                                                i--; //Set new i value.
                                                break secondloop;
                                            }
                                        }
                                    }
                                    break secondloop;
                                }
                            }
                        }

                        expressions.addAll(fastExprsList); //Add expressions to right pane if they haven't been added yet.
                        
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Some hfm_files could not found.");
        } catch(URISyntaxException ure) {
            JOptionPane.showMessageDialog(null, "Folder path format is not supported(Consult developer).");
        } catch(IOException ioe) {
            JOptionPane.showMessageDialog(null, "File could not be read..");
        } catch(JsonSyntaxException jse) {
            JOptionPane.showMessageDialog(null, "Expressions file json syntax is faulty.Delete it or correct it.");
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Reader could not be closed.");
            }
        }
    }
    
    /**
     * Remove expression by looking its dns name.
     * 
     * @param dns Dns name of the expression.
     */
    public static void removeExpression(String dns) {
        for(int i=0; i<expressions.size(); i++) {
            if(expressions.get(i).getDnsName().equals(dns)) {
                expressions.remove(i);
                break;
            }
        }
    }
    
    /**
     * Sorts expressions according to their lexical order.
     */
    public static void sortExpressions() {
       Collections.sort(expressions);
    }
    
}
