/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author cynthia
 */
public interface IProcess extends Serializable {
    /**
     * 
     * @return int process id
     * @throws java.rmi.RemoteException
     */
    public String getPID();
    /**
     * 
     * @return String process command name
     * @throws java.rmi.RemoteException
     */
    public String getName();
    /**
     * %CPU
     * @return float process CPU usage 
     * @throws java.rmi.RemoteException 
     */
    public String getUsingCPU();
    /**
     * CPU Time in hh:mm:ss
     * @return String process CPU time
     * @throws java.rmi.RemoteException
     */
    public String getCPUTime();
    /**
     * WQ
     * @return int process thread count
     * @throws java.rmi.RemoteException
     */
    public String getState();
    
    public String getUsingMemory();
    /**
     * user name
     * @return String user who started the process 
     * @throws java.rmi.RemoteException 
     */
    public String getUser();
    
    /**
     * Date of object creation
     * @return Date
     */
    public Date getDate();
}
