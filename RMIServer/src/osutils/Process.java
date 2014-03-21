/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import interfaces.IProcess;

/**
 * Class which implements a process.
 * @author cynthia
 */
public class Process implements IProcess{
    private String pid;
    private String name;
    private String usingCPU;
    private String cpuTime;
    private String threads;
    private String port;
    private String usingMemory;
    private String user;
    
    public void setProcess (String pid,String name,String usingCPU,String cpuTime,String threads,String usingMemory,String user){
        this.pid=pid;
        this.name=name;
        this.usingCPU=usingCPU;
        this.cpuTime=cpuTime;
        this.threads=threads;
        this.port=port;
        this.usingMemory=usingMemory;
        this.user=user;
    }
    
    @Override
    public String getPID() {
        return pid;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return name;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsingCPU() {
        return usingCPU;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCPUTime() {
        return cpuTime;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getThreads() {
        return threads;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsingMemory() {
        return usingMemory;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUser() {
        return user;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
