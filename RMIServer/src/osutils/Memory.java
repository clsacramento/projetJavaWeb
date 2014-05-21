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
 * Physical Memory state
 * 
 * This class provides informations on the server physical memory usage.
 * @author Damien
 */
@XmlRootElement(name = "memory")
public class Memory implements Serializable{
    /**
     * Amount in Mo of memory used
     */
    private String used;
    /**
     * Fre space in Mo
     */
    private String free;
    /**
     * The total memory (Mo)
     */
    private String total;
    /**
     * Timestamp of the moment these informations were read.
     */
    private Date date;
    
    public Memory(){
        this.used="";
        this.free="";
        this.total="";
        this.date = new Date();
    }
    
    public Memory (String total,String used,String free){
        this.total = total;
        this.used = used;
        this.free = free;
        
        this.date = new Date();
    }
    @XmlElement
    public void setUsed(String used){
        
        this.used = used;
    }
    public String getUsed(){
        return used;
    }

    @XmlElement
    public void setFree(String free){
        this.free = free;
    }
    public String getFree(){
        return free;
    }

    @XmlElement
    public void setTotal(String total){
        this.total = total;
    }
    public String getTotal(){
        return total;
    }
    
    @Override
    public String toString(){
        return (this.total != null ? this.total+", " : "" )+
                (this.used != null ? this.used+", " : "" )+
                (this.free != null ? this.free : "" );
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
