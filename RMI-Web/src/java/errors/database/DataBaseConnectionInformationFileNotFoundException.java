/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package errors.database;

/**
 * Exception thrown when attempting to connect to a database but json file with
 * connection informations is missing.
 * @author cynthia
 */
public class DataBaseConnectionInformationFileNotFoundException extends Exception{

    public DataBaseConnectionInformationFileNotFoundException(String jsonFile) {
        super("DataBase connection information file << "+jsonFile+" >> not found.");
    }
    
}
