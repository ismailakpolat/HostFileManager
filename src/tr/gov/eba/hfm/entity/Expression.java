/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.entity;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;

/**
 *
 * @author ismailakpolat
 */
public class Expression implements Comparable {
    @Expose
    private String dnsName;
//    private ArrayList<String> ipList;
//    private HashMap<String, Boolean> ipList;
    
    @Expose
    private ArrayList<ExtendedDnsIp> ipList;
//    private boolean isActive;

    public Expression(String dnsName, ArrayList<ExtendedDnsIp> ipList) {
        this.dnsName = dnsName;
        this.ipList = ipList;
//        this.isActive = isActive;
    }

    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }

    public ArrayList<ExtendedDnsIp> getIpList() {
        return ipList;
    }
    
//    public void isIpActive(String ip) {
//        ipList.get(ip);
//    }

//    public String getIp(int index) {
//        return ipList.get(index);
//    }
    
//    /**
//     * Checks if ip is already added, then adds or not according to it.
//     * 
//     * @param ip Ip address.
//     */
//    public void addIp(String ip) {
//        for(String s : ipList) {
//            if(s.equals(ip)) { //If ip is already exists return.
//                return;
//            }
//        }
//        ipList.add(ip); //If ip is not in the list, add it to the list.
//    }
    
//    /**
//     * Checks if ip is already added, then adds or not according to it.
//     * 
//     * @param ip Ip address.
//     */
//    public void addIp(String ip) {
//        for(String s : ipList) {
//            if(s.equals(ip)) { //If ip is already exists return.
//                return;
//            }
//        }
//        ipList.add(ip); //If ip is not in the list, add it to the list.
//    }
   
    /**
     * Checks if ip is already added, then adds or not according to it.
     * 
     * @param ip Ip address.
     */
    public void addIp(ExtendedDnsIp ip) {
        for(ExtendedDnsIp s : ipList) {
            if(s.getName().equals(ip.getName())) { //If ip is already exists return.
                return;
            }
        }
        
        ipList.add(ip); //If ip is not in the list, add it to the list.
    }
    
    /**
     * Checks if ip is already added, then adds or not according to it.
     * 
     * @param ip Ip address.
     */
    public void addIp(int index, ExtendedDnsIp ip) {
        for(ExtendedDnsIp s : ipList) {
            if(s.getName().equals(ip.getName())) { //If ip is already exists return.
                return;
            }
        }
        if(index>=ipList.size())
            ipList.add(ip);
        else
            ipList.add(index, ip); //If ip is not in the list, add it to the list.
    } 
    
    /**
     * Removes ip equals to ipList.get(index).
     * 
     * @param index Index of ip to be removed.
     */
    public void removeIp(int index) {
        ipList.remove(index);
    }

    /**
     * Removes ip equals to given object.
     * 
     * @param  obj Extended dns ip.
     */
    public void removeIp(ExtendedDnsIp obj) {
        ipList.remove(obj);
    }    
    
    @Override
    public int compareTo(Object o) {
        if(this.getDnsName().compareToIgnoreCase(((Expression)o).getDnsName())<-1)
            return -1;
        else if(this.getDnsName().compareToIgnoreCase(((Expression)o).getDnsName())==0)
            return 0;
        else
            return 1;
    }
    
}
