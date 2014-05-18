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
 * Class which implements a process.
 * @author cynthia
 */
@XmlRootElement(name = "process")
public class Process implements Serializable{
    private String pid;
    private String name;
    private String usingCPU;
    private String cpuTime;
    private String state;
    private String usingMemory;
    private String user;
    
    private Date date;

    public Process(){
        this.pid = "test";
        this.name = "process";
    }
    
    public Process(String pid,String name,String usingCPU,String cpuTime,String state,String usingMemory,String user){
        this.pid=pid;
        this.name=name;
        this.usingCPU=usingCPU;
        this.cpuTime=cpuTime;
        this.state=state;
        this.usingMemory=usingMemory;
        this.user=user;
        
        this.date = new Date();
    }
    
    
    @XmlAttribute
    public String getPID(){
        return pid;
    }

    
    @XmlElement
    public String getName(){
        return name;
    }

    @XmlElement
    public String getUsingCPU(){
        return usingCPU;
    }

    @XmlElement
    public String getCPUTime(){
        return cpuTime;
    }

    @XmlElement
    public String getState(){
        return state;
    }

    @XmlElement
    public String getUsingMemory(){
        return usingMemory;
    }

    @XmlElement
    public String getUser(){
        return user;
    }
    
    @XmlAttribute(name="date")
    public String getStrDate(){
        return this.date.toString();
    }
    
    public Date getDate(){
        return this.date;        
    }
    
    @Override
    public String toString(){
        return pid+","+name+","+user;
    }
}
