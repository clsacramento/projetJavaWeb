/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import interfaces.IPhysicalMemory;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Damien
 */
public class Memory extends UnicastRemoteObject implements IPhysicalMemory,Serializable{

    private String used;
    private String free;
    private String total;
    
    public Memory (String total,String used,String free) throws RemoteException{
        this.total = total;
        this.used = used;
        this.free = free;
    }
    @Override
    public String getUsed() throws RemoteException {
        return used;
    }

    @Override
    public String getFree() throws RemoteException {
        return free;
    }

    @Override
    public String getTotal() throws RemoteException {
        return total;
    }
    
}
