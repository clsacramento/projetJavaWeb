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
public interface IProcess extends Remote{
    /**
     * 
     * @return int process id
     * @throws java.rmi.RemoteException
     */
    public String getPID() throws RemoteException;
    /**
     * 
     * @return String process command name
     * @throws java.rmi.RemoteException
     */
    public String getName() throws RemoteException;
    /**
     * %CPU
     * @return float process CPU usage 
     * @throws java.rmi.RemoteException 
     */
    public String getUsingCPU() throws RemoteException;
    /**
     * CPU Time in hh:mm:ss
     * @return String process CPU time
     * @throws java.rmi.RemoteException
     */
    public String getCPUTime() throws RemoteException;
    /**
     * WQ
     * @return int process thread count
     * @throws java.rmi.RemoteException
     */
    public String getThreads() throws RemoteException;
    
    public String getUsingMemory() throws RemoteException;
    /**
     * user name
     * @return String user who started the process 
     * @throws java.rmi.RemoteException 
     */
    public String getUser() throws RemoteException;
}
