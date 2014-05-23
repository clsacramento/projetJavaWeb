/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.mysql.jdbc.Statement;
import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Data Access Object for the User
 *
 * @author Damien
 */
public class UserDAO {

    /**
     * Selects a user having the given login and password
     *
     * @param login
     * @param password
     * @return HashMap mapping its id and login to their values
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException
     */
    public static HashMap selectUser(String login, String password) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from user where login=? and password=?");
        stmt.setString(1, login);
        stmt.setString(2, password);
        ArrayList<HashMap> result = DataBaseConnection.query(stmt, "id_user,login");
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public static ArrayList<HashMap> selectUsers() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        return DataBaseConnection.query("select * from user", "id_user,login");

    }

    public static void delUser(String id) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {

        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("delete from user where id_user=?", Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, Integer.parseInt(id));

        stmt.executeUpdate();
    }

    public static HashMap insertUser(String login, String password) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("insert into user (login,password) values (?,?)",Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, login);
        stmt.setString(2, password);
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            int id =rs.getInt(1);
            
            HashMap userDAO = new HashMap();
            userDAO.put("id_user", ""+id);
            userDAO.put("login", login);
            
            return userDAO;
        }
        return null;
    }
}
