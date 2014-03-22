/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import interfaces.ICPU;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Damien
 */
public class CPU extends UnicastRemoteObject implements ICPU,Serializable {

    private String total;
    private String userLoad;
    private String systemLoad;
    private String idle;
    
    
    public CPU (String total,String userLoad,String systemLoad,String idle) throws RemoteException{
        this.total = total;
        this.userLoad = userLoad;
        this.systemLoad = systemLoad;
        this.idle = idle;
    }
    
    @Override
    public String getTotalUsed() throws RemoteException {
        return total;
    }

    @Override
    public String getUserLoad() throws RemoteException {
        return userLoad;
    }

    @Override
    public String getSystemLoad() throws RemoteException {
        return systemLoad;
    }

    @Override
    public String getIdle() throws RemoteException {
        return idle;
    }
    
}
