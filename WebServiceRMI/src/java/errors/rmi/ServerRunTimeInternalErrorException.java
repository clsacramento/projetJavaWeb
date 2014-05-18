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
public class ServerRunTimeInternalErrorException extends Exception{
    public ServerRunTimeInternalErrorException(String host,String command){
        super("An internal error occured in the RMI server << "
                +host
                +" >> while executing << "
                +command
                +" >>. Check server's logs form more details.");
    }
}
