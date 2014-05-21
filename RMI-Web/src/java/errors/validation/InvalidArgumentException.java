/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.validation;

/**
 * Thrown when trying to access an object with given argument name and value pair
 * that does not exists
 * @author cynthia
 */
public class InvalidArgumentException extends Exception{
    /**
     * Message explaining and containing the given attributes
     * @param argumentName
     * @param argumentValue 
     */
    public InvalidArgumentException(String argumentName, String argumentValue){
        super("<< "+argumentValue+" >> is not a valid value for << "+argumentName+" >> argument.");
    }
}
