/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.rmi;

/**
 * This exception is thrown in the case where the RMI server does respond to a
 * user request. 
 * In this case it is sure the RMI registry is up and running. In addiction
 * ActivityMonitor is registered, but the system is still unable to get a 
 * response from the server. The most probable reason is that the RMIServer
 * project is currently running.
 * @author cynthia
 */
public class ServerDidNotRespondException extends Exception{
    /**
     * Explaining message containing the host and the command the user was trying
     * to access.
     * @param String host to which the unseccuful connection was tried
     * @param String cmd the command (getCPU, getListOfProcesses or 
     * getPhysicalMemory) to which there was no answer
     */
    public ServerDidNotRespondException(String host,String cmd)
    {
        super("RMI Server << " + host + " >> did not respond to "+cmd+" command. Is the server running?");
    }
    
}
