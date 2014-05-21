/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.validation;

/**
 * This exception is thrown when trying to get an object detail that does not
 * contains the given field and value match
 * @author cynthia
 */
public class ObjectNotFoundException extends Exception {
    /**
     * Message explaining and informing the object, field and value
     * @param objectType
     * @param field
     * @param value 
     */
    public ObjectNotFoundException(String objectType, String field, String value) {
        super("No << "+objectType+" >> object found with << "+field+" >> set to << "+value+" >>");
    }
    
}
