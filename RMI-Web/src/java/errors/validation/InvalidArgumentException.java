/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.validation;

/**
 *
 * @author cynthia
 */
public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String argumentName, String argumentValue){
        super("<< "+argumentValue+" >> is not a valid value for << "+argumentName+" >> argument.");
    }
}
