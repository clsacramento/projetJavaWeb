/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.database;

/**
 * Exception thrown when problem in connecting to database.
 * @author cynthia
 */
public class DataBaseConnectionException extends Exception{
    /**
     * Creates the exception with the explaining message plus the error message
     * gotten to provide additional information.
     * @param extraInformation 
     */
    public DataBaseConnectionException(String extraInformation) {
        super("Error during connection to database. Extra Information:\n"+extraInformation);
    }
    
}
