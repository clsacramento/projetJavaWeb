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
public class NoServerConnectionException extends Exception{
    public NoServerConnectionException(String host){
        super("A conncetion to the host << "+host+" >> was not possible. Is this the right to server?");
    }
}
