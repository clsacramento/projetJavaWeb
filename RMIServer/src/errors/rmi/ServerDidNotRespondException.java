/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.rmi;

/**
 *
 * @author cynthia
 */
public class ServerDidNotRespondException extends Exception{
    public ServerDidNotRespondException(String host,String cmd)
    {
        super("RMI Server << " + host + " >> did not respond to "+cmd+" command. Is the server running?");
    }
    
}
