/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.rmi;

/**
 * This exception is thrown when no RMI related issue happend, but we still 
 * don't get a valid answer from the server. If this happens there is probably
 * a bug the RMIServer project or for some reason the server could not execute
 * the request (maybe a fault in the OS level).
 * @author cynthia
 */
public class ServerRunTimeInternalErrorException extends Exception{
    /**
     * Raise exception with the host and command information.
     * @param String host name or address
     * @param String command getCPU, getListOfProcesses or getPhyscalMemory
     */
    public ServerRunTimeInternalErrorException(String host,String command){
        super("An internal error occured in the RMI server << "
                +host
                +" >> while executing << "
                +command
                +" >>. Check server's logs form more details.");
    }
}
