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
public class Process extends UnicastRemoteObject implements IProcess,Serializable{
    private String pid;
    private String name;
    private String usingCPU;
    private String cpuTime;
    private String threads;
    private String usingMemory;
    private String user;

    public Process(String pid,String name,String usingCPU,String cpuTime,String threads,String usingMemory,String user) throws RemoteException {
        this.pid=pid;
        this.name=name;
        this.usingCPU=usingCPU;
        this.cpuTime=cpuTime;
        this.threads=threads;
        this.usingMemory=usingMemory;
        this.user=user;
    }
    
    @Override
    public String getPID() throws RemoteException {
        return pid;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getUsingCPU() throws RemoteException {
        return usingCPU;
    }

    @Override
    public String getCPUTime() throws RemoteException {
        return cpuTime;
    }

    @Override
    public String getThreads() throws RemoteException {
        return threads;
    }

    @Override
    public String getUsingMemory() throws RemoteException {
        return usingMemory;
    }

    @Override
    public String getUser() throws RemoteException {
        return user;
    }
    
}
