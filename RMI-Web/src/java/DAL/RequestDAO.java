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
 * Data Access Object for Request
 * @author cynthia
 */
public class RequestDAO {
    /**
     * Insertion of a new request
     * @param serverId id of the host in the table server
     * @param userId id of the connected user (making the request)
     * @param typeRequestId id of the request type (getCPU, getPhysicalMemory...)
     * @param date timestamp of the request in mysql date format YYYY-MM-DD HH:MM:SS
     * @return the id of the new request, or zero if not inserted
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionException
     * @throws DataBaseInformationFileParsingException 
     */
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
    
    /**
     * Insertion of a CPU specific request
     * @param requestId its reference in the general table
     * @param total cpu usage
     * @param userLoad 
     * @param systemLoad
     * @param idle
     * @return id of the cpu request or zero if not inserted
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionException
     * @throws DataBaseInformationFileParsingException 
     */
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
    /**
     * Insertion of a Memory specific request
     * @param requestId its reference in the general table
     * @param used
     * @param free
     * @param total
     * @return id of the memory request or zero if not inserted
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionException
     * @throws DataBaseInformationFileParsingException 
     */
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
    /**
     * Insertion of a Process specific request
     * @param requestId its reference in the general table (1:N in this case)
     * @param PID
     * @param name
     * @param usingCPU
     * @param cpuTime
     * @param state
     * @param usingMemory
     * @param user
     * @return id of the process request or zero if not inserted
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionException
     * @throws DataBaseInformationFileParsingException 
     */
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
    /**
     * Selects all registered requests
     * @return an HashMap mapping field with value per row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static ArrayList<HashMap> selectRequests() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        return DataBaseConnection.query("select * from history", "id_request,id_server,host,id_user,login,timestamp,id_type_request,type_request");
    }
    /**
     * Selects all registered requests on a given date
     * @param date
     * @return an HashMap mapping field with value per row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static ArrayList<HashMap> selectRequests(String date) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from history where timestamp = ?");
        stmt.setString(1,date);
        return DataBaseConnection.query(stmt, "id_request,id_server,host,id_user,login,timestamp,id_type_request,type_request");
    }
    /**
     * Selects all registered requests of a given type
     * @param typeRequestId
     * @return an HashMap mapping field with value per row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static ArrayList<HashMap> selectRequests(int typeRequestId) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from history where id_type_request = ?");
        stmt.setInt(1, typeRequestId);
        return DataBaseConnection.query(stmt, "id_request,id_server,host,id_user,login,timestamp,id_type_request,type_request");
    }
    /**
     * Selects the process list of a given request
     * @param requestId
     * @return an HashMap mapping field with value per row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static ArrayList<HashMap> selectProcessList(int requestId) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from process where id_request = ?");
        stmt.setInt(1, requestId);
        return DataBaseConnection.query(stmt, "PID,name,using_cpu,cpu_time,state,using_memory,user");
    }
    
    /**
     * Select the CPU usage information of a given request
     * @param requestId
     * @return an HashMap mapping field with value per row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static HashMap selectCPU(int requestId) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from cpu where id_request = ?");
        stmt.setInt(1, requestId);
        ArrayList<HashMap> result = DataBaseConnection.query(stmt, "total,user_load,system_load,idle");
        if(!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }
    /**
     * Select the Memory usage information of a given request
     * @param requestId
     * @return an HashMap mapping field with value per row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static HashMap selectMemory(int requestId) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        java.sql.PreparedStatement stmt = DataBaseConnection.getStatement("select * from memory where id_request = ?");
        stmt.setInt(1, requestId);
        ArrayList<HashMap> result = DataBaseConnection.query(stmt, "used,free,total");
        if(!result.isEmpty()){
            return result.get(0);
        }
        return null;
    }
}
