/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Data Access Object for the User
 * @author Damien
 */
public class UserDAO {
    /**
     * Selects a user having the given login and password
     * @param login
     * @param password
     * @return HashMap mapping its id and login to their values
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static HashMap selectUser(String login, String password) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from user where login=? and password=?");
        stmt.setString(1, login);
        stmt.setString(2, password);
        ArrayList<HashMap> result = DataBaseConnection.query(stmt, "id_user,login");
        if(!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }
}
