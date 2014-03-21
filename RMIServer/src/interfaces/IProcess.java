/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

/**
 *
 * @author cynthia
 */
public interface IProcess {
    /**
     * 
     * @return int process id
     */
    public int getPID();
    /**
     * 
     * @return String process command name
     */
    public String getName();
    /**
     * %CPU
     * @return float process CPU usage 
     */
    public float getUsingCPU();
    /**
     * CPU Time in hh:mm:ss
     * @return String process CPU time
     */
    public String getCPUTime();
    /**
     * WQ
     * @return int process thread count
     */
    public int getThreads();
//    public int getPort();
    public String getUsingMemory();
    /**
     * user name
     * @return String user who started the process 
     */
    public String getUser();
}