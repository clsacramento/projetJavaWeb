/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.rmi;

/**
 * Exception thrown when trying to bind to a server without ActivityMonitor
 * implementation registered in the registry
 * @author cynthia
 */
public class NoActivityMonitorServerException extends Exception{

    public NoActivityMonitorServerException(String host) {
        super("The host "+host+" did not respond to or does not support ActivityMonitor as a RMI service. Is the ActivityMonitor running?");
    }

    
}
