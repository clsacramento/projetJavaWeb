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
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("insert into cpu (id_request,total,user_load,system_load,idle) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        
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
    
    public static int insertRequestMemory(int requestId, String used, String free,String total) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseConnectionException, DataBaseInformationFileParsingException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("insert into memory (id_request,used,free,total) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        
        stmt.setInt(1,requestId);
        stmt.setString(2, used);
        stmt.setString(3, free);
        stmt.setString(4, total);
        
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            int id =rs.getInt(1);
            
            return id;
        }
        
        return 0;        
    }
    
    public static int insertRequestProcess(int requestId, String PID, String name,String usingCPU, String cpuTime, String state, String usingMemory, String user) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseConnectionException, DataBaseInformationFileParsingException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("insert into process (id_request,PID,name,using_cpu,cpu_time,state,using_memory,user) values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        
        stmt.setInt(1,requestId);
        stmt.setString(2, PID);
        if(name.length()>100){
            name = name.substring(0, 95)+"...";
        }
        stmt.setString(3, name);
        stmt.setString(4, usingCPU);
        stmt.setString(5, cpuTime);
        stmt.setString(6, state);
        stmt.setString(7, usingMemory);
        stmt.setString(8, user);
        
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            int id =rs.getInt(1);
            
            return id;
        }
        
        return 0;        
    }
}
