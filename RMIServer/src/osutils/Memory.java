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
@XmlRootElement(name = "memory")
public class Memory implements Serializable{

    private String used;
    private String free;
    private String total;
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
    public String getUsed(){
        return used;
    }

    @XmlElement
    public String getFree(){
        return free;
    }

    @XmlElement
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
    public String getStrDate(){
        return this.date.toString();
    }
    
    public Date getDate() {
        return this.date;
    }
}
