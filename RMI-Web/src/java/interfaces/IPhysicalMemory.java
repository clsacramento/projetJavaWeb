/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author cynthia
 */
public interface IPhysicalMemory extends Remote{
    public String getUsed() throws RemoteException;
    public String getFree() throws RemoteException;
    public String getTotal() throws RemoteException;

}
