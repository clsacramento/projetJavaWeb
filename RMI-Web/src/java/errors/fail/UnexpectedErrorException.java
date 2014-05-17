/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.fail;

/**
 *
 * @author cynthia
 */
public class UnexpectedErrorException extends Exception{
    public UnexpectedErrorException(Exception ex){
        super("Sorry, this error was not expected. Error type : "+ex.getClass().getSimpleName()+"; Description : "+ex.getMessage()+"\nIf this information is not enough, please ask the administrator to check the server's logs.");
    }
}
