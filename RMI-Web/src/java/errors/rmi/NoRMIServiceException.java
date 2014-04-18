/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.rmi;

/**
 * This exception is thrown when attempting to access a RMI service in a host
 * but it does not implement RMI
 * @author cynthia
 */
public class NoRMIServiceException extends Exception{

    public NoRMIServiceException(String host) {
        super("Trying to acces a RMI service in host << "+host+" >> but no RMI service found in this host.");
//        super("Trying to acces a RMI service but RMI registry is not started.\n"+
//                "Try running registry command, for example : rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false");
    }
    
}
