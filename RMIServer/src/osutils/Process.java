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
        this.date = new Date();
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
    public void setPID(String pid){
        this.pid = pid;
    }
    public String getPID(){
        return pid;
    }

    
    @XmlElement
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @XmlElement
    public void setUsingCPU(String usingCPU){
        this.usingCPU = usingCPU;
    }
    public String getUsingCPU(){
        return usingCPU;
    }

    @XmlElement
    public void setCPUTime(String CPUTime){
        this.cpuTime = CPUTime;
    }
    public String getCPUTime(){
        return cpuTime;
    }

    @XmlElement
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }

    @XmlElement
    public void setUsingMemory(String usingMemory){
        this.usingMemory = usingMemory;
    }
    public String getUsingMemory(){
        return usingMemory;
    }

    @XmlElement
    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return user;
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
    
    public Date getDate(){
        return this.date;        
    }
    
    @Override
    public String toString(){
        return pid+","+name+","+user;
    }
}
