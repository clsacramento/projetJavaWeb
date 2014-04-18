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
import java.util.Date;

/**
 *
 * @author Damien
 */
public class Memory implements IPhysicalMemory{

    private String used;
    private String free;
    private String total;
    private Date date;
    
    public Memory (String total,String used,String free){
        this.total = total;
        this.used = used;
        this.free = free;
        
        this.date = new Date();
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
    
    @Override
    public String toString(){
        return (this.total != null ? this.total : "" )+
                (this.used != null ? this.used+"," : "" )+
                (this.free != null ? this.free : "" );
    }

    @Override
    public Date getDate() {
        return this.date;
    }
}
