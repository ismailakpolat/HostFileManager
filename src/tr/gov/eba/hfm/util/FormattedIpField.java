/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.eba.hfm.util;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author eba
 */
public class FormattedIpField extends PlainDocument {
    private int limit;
    
    public FormattedIpField() {
        super();
        this.limit = 3;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        
        if ((getLength() + str.length()) <= limit) {
            for(int i = 0; i < str.length(); i++) {
                if(!("0123456789").contains(""+str.charAt(i))) {
                    str = "";
                }
            }
            
            if(!str.equals("")) {
                super.insertString(offset, str, attr);
                if(getLength()==3) {
//                    super.remove(offset, 1); //Removes length amount of character starting from offset.
                    Integer value = 0;
                    try {
                        value = Integer.parseInt(getText(0, getLength()));
                    } catch(NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "NumberFormatException in ip field.Consult developer.");
                    }
                    if(value > 255) {
                        value = 255;
                    }
                    super.remove(0, limit);
                    super.insertString(0, value+"", attr);
                    
                }
//                Integer.parseInt(getText(0, getLength()));
                
            }
            
        }
    }
    
//    /**
//     * Checks ip and make faulty values corrected.Ip fields bigger than 255 are set to 255, and ip fields
//     * lesser than 0 are set to 0.
//     * 
//     * @param ip Ip with format ###.###.###.###
//     * @return Corrected ip. Faulty fields become 0.
//     */
//    public String checkIp(String ip) {
//        String sip1, sip2, sip3, sip4;
//        Integer ip1, ip2, ip3, ip4;
//        try {
//            sip1 = ip.substring(0, ip.indexOf("."));
//            ip = ip.substring(ip.indexOf(".")+1);
//            sip2 = ip.substring(0, ip.indexOf("."));
//            ip = ip.substring(ip.indexOf(".")+1);
//            sip3 = ip.substring(0, ip.indexOf("."));
//            sip4 = ip.substring(ip.indexOf(".")+1);
////            sip4 = ip.substring(0, ip.length());
//        } catch(Exception e) {
//            return "0.0.0.0";
//        }
//        
//        try {
//            ip1 = Integer.parseInt(sip1);
//            if(ip1>255) {
//                sip1 = "255";
//            } else if(ip1<0) {
//                sip1 = "0";
//            }
//        } catch(NumberFormatException nfe) {
//            sip1 = "0";
//        }
//        
//        try {
//            ip2 = Integer.parseInt(sip2);
//            if(ip2>255) {
//                sip2 = "255";
//            } else if(ip2<0) {
//                sip2 = "0";
//            }
//        } catch(NumberFormatException nfe) {
//            sip2 = "0";
//        }
//        
//        try {
//            ip3 = Integer.parseInt(sip3);
//            if(ip3>255) {
//                sip3 = "255";
//            } else if(ip3<0) {
//                sip3 = "0";
//            }
//        } catch(NumberFormatException nfe) {
//            sip3 = "0";
//        }
//        
//        try {
//            ip4 = Integer.parseInt(sip4);
//            if(ip4>255) {
//                sip4 = "255";
//            } else if(ip4<0) {
//                sip4 = "0";
//            }
//        } catch(NumberFormatException nfe) {
//            sip4 = "0";
//        }
//        
//        ip = sip1 + "." + sip2 + "." + sip3 + "." + sip4;
//        return ip;
//    }
    
//    public static void main(String[] args) {
//        System.out.println(new FormattedIpField().checkIp("-3.11.1.543"));
//    }
}