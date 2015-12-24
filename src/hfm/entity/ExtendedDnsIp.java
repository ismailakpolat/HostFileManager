/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfm.entity;

import com.google.gson.annotations.Expose;

/**
 *
 * @author ismailakpolat
 */
public class ExtendedDnsIp {
    @Expose
    private String name;
    private boolean isActive;

    public ExtendedDnsIp(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
