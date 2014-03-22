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
public interface ICPU  extends Remote{
    public String getTotalUsed() throws RemoteException;
    public String getUserLoad() throws RemoteException;
    public String getSystemLoad() throws RemoteException;
    public String getIdle() throws RemoteException;
}
