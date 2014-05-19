/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Damien
 */
@XmlRootElement(name = "cpu")
public class CPU implements Serializable {

    private String totalUsed;
    private String userLoad;
    private String systemLoad;
    private String idle;
    private Date date;
    
    public CPU(){
        this.totalUsed="";
        this.userLoad="";
        this.systemLoad="";
        this.idle="";
        this.date = new Date();
    }
    
    public CPU (String total,String userLoad,String systemLoad,String idle) {
        this.totalUsed = total;
        this.userLoad = userLoad;
        this.systemLoad = systemLoad;
        this.idle = idle;
        
        this.date = new Date();
    }
    
    @XmlElement
    public void setTotalUsed(String totalUsed){
        this.totalUsed = totalUsed;
    }
    public String getTotalUsed() {
        return totalUsed;
    }

    @XmlElement
    public void setUserLoad(String userLoad){
        this.userLoad = userLoad;
    }
    public String getUserLoad() {
        return userLoad;
    }

    @XmlElement
    public void setSystemLoad(String systemLoad){
        this.systemLoad = systemLoad;
    }
    public String getSystemLoad() {
        return systemLoad;
    }

    @XmlElement
    public void setIdle(String idle){
        this.idle = idle;
    }
    public String getIdle() {
        return idle;
    }
    
    
    @Override
    public String toString(){
        return (this.totalUsed != null ? this.totalUsed+", "  : "" )+
                (this.systemLoad != null ? this.systemLoad+"," : "" )+
                (this.userLoad != null ? this.userLoad+"," : "" )+
                (this.idle != null ? this.idle : "" );
    }

    @XmlAttribute(name="date")
    public void setLongDate(long time){
        this.date = new Date(time);
    }
    public long getLongDate(){
        if(this.date == null){
            this.date = new Date();
        }
        return this.date.getTime();
    }
    
    public Date getDate() {
        return this.date;
    }
}
