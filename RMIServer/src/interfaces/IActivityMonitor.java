/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import osutils.CPU;
import osutils.Memory;

/**
 * OS Activity Monitor Interface which provides functions to gather information
 * about processes running, memory and CPU used.
 * @author cynthia
 */
public interface IActivityMonitor extends Remote {
    /**
     * 
     * @return ArrayList running processes list
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.rmi.RemoteException
     */
    public List<osutils.Process> getListOfProcesses() throws IOException,InterruptedException,RemoteException;
    /**
     * 
     * @return IPhysicalMemory memory usage information.
     * @throws java.rmi.RemoteException
     * @throws java.lang.InterruptedException
     */
    public Memory getPhysicalMemory() throws IOException,InterruptedException,RemoteException;
    /**
     * 
     * @return ICPU CPU usage information
     * @throws java.rmi.RemoteException
     * @throws java.lang.InterruptedException
     */
    public CPU getCPU() throws IOException,InterruptedException,RemoteException;
}
