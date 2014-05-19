/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.remote;

import errors.rest.ExceptionXml;

/**
 *
 * @author cynthia
 */
public class RemoteErrorException extends Exception{
    public RemoteErrorException(ExceptionXml ex){
        super("An error occured in the remote server. The provided error type is << "+ex.getExceptionType()+" >> and the respective message is << "+ex.getExceptionMessage()+">>. For mor informations, please contact the remote application administrator.");
    }
}
