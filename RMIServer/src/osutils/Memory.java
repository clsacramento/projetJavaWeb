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
public class Memory implements IPhysicalMemory{

    private String used;
    private String free;
    private String total;
    
    public Memory (String total,String used,String free){
        this.total = total;
        this.used = used;
        this.free = free;
    }
    @Override
    public String getUsed(){
        return used;
    }

    @Override
    public String getFree(){
        return free;
    }

    @Override
    public String getTotal(){
        return total;
    }
    
}
