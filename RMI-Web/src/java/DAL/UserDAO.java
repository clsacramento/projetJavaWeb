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
 *
 * @author Damien
 */
public class UserDAO {
    public static HashMap selectServer(String login, String password) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
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
