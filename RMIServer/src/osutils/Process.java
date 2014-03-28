/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import interfaces.IProcess;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class which implements a process.
 * @author cynthia
 */
public class Process implements IProcess,Serializable{
    private String pid;
    private String name;
    private String usingCPU;
    private String cpuTime;
    private String state;
    private String usingMemory;
    private String user;

    Process(String pid,String name,String usingCPU,String cpuTime,String state,String usingMemory,String user){
        this.pid=pid;
        this.name=name;
        this.usingCPU=usingCPU;
        this.cpuTime=cpuTime;
        this.state=state;
        this.usingMemory=usingMemory;
        this.user=user;
    }
    
    @Override
    public String getPID(){
        return pid;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getUsingCPU(){
        return usingCPU;
    }

    @Override
    public String getCPUTime(){
        return cpuTime;
    }

    @Override
    public String getState(){
        return state;
    }

    @Override
    public String getUsingMemory(){
        return usingMemory;
    }

    @Override
    public String getUser(){
        return user;
    }
    
    @Override
    public String toString(){
        return pid+","+name+","+user;
    }
}
