/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.database;

/**
 * Exception thrown when problem in parsing connection information json file.
 * The json is probably not in correct format. See exemple :
 * {
 *   "host": "[ip address or hostname]",
 *   "port": "[mysql port]",
 *   "database" : "[database name]",
 *   "user": "[database user]",
 *   "password" : "[database user password]"
 * }
 * @author cynthia
 */
public class DataBaseInformationFileParsingException extends Exception{
    /**
     * Message explaining the problem and providing the error message as extra
     * information
     * @param extraInformation 
     */
    public DataBaseInformationFileParsingException(String extraInformation) {
        super("Error parsing json connection information file.\n Extra information: "+extraInformation);
    }
    
}
