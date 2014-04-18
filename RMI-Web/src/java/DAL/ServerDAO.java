/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import com.mysql.jdbc.PreparedStatement;
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
 *
 * @author cynthia
 */
public class ServerDAO {
    public static HashMap selectServer(String host) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from server where host=?");
        stmt.setString(1, host);
        ArrayList<HashMap> result = DataBaseConnection.query(stmt, "id_server,host");
        if(!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }
    
    public static ArrayList<HashMap> selectServers() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        return DataBaseConnection.query("select * from server", "id_server,host");
    }
    
    public static HashMap insertHost(String host) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("insert into server (host) values (?)",Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, host);
        
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            int id =rs.getInt(1);
            
            HashMap serverDAO = new HashMap();
            serverDAO.put("id_server", ""+id);
            serverDAO.put("host", host);
            
            return serverDAO;
        }
        
        return null;
    }
}
