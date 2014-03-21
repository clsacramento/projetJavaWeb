/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.io.IOException;
import java.util.ArrayList;

/**
 * OS Activity Monitor Interface which provides functions to gather information
 * about processes running, memory and CPU used.
 * @author cynthia
 */
public interface IActivityMonitor {
    /**
     * 
     * @return ArrayList running processes list
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public ArrayList<IProcess> getListOfProcesses() throws IOException,InterruptedException;
    /**
     * 
     * @return IPhysicalMemory memory usage information.
     */
    public IPhysicalMemory getPhysicalMemory();
    /**
     * 
     * @return ICPU CPU usage information
     */
    public ICPU getCPU();
}
