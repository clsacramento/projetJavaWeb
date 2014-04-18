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
import java.util.HashMap;

/**
 *
 * @author cynthia
 */
public class RequestDAO {
    public static int insertRequest(int serverId, int userId, int typeRequestId,String date) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseConnectionException, DataBaseInformationFileParsingException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("insert into request (id_server,id_user,id_type_request,timestamp) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        
        stmt.setInt(1,serverId);
        stmt.setInt(2, userId);
        stmt.setInt(3, typeRequestId);
        stmt.setString(4, date);
        
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            int id =rs.getInt(1);
            
            return id;
        }
        
        return 0;        
    }
    
    public static int insertRequestCPU(int requestId, String total, String userLoad,String systemLoad, String idle) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseConnectionException, DataBaseInformationFileParsingException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("insert into request (id_server,id_user,id_type_request,timestamp) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        
        stmt.setInt(1,requestId);
        stmt.setString(2, total);
        stmt.setString(3, userLoad);
        stmt.setString(4, systemLoad);
        stmt.setString(5, idle);
        
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            int id =rs.getInt(1);
            
            return id;
        }
        
        return 0;        
    }
}
