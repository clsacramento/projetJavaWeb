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
public class NotFoundObjectException extends Exception {

    public NotFoundObjectException(String objectType, String field, String value) {
        super("No << "+objectType+" >> object found with << "+field+" >> set to << "+value+" >>");
    }
    
}
