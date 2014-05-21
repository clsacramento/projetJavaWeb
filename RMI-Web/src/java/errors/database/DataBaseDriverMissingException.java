/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.database;

/**
 * Exception thrown when trying to connect to database, but the driver is missing.
 * @author cynthia
 */
public class DataBaseDriverMissingException extends Exception{
    /**
     * Message explaining and containing the name of the missing driver.
     * @param missingDriver 
     */
    public DataBaseDriverMissingException(String missingDriver) {
        super("Database driver << "+missingDriver+" >> is missing.\n" + 
                "Try to add database driver package (jar) to the project.");
    }
    
}
