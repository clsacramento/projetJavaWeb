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
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Data Base Connection
 * 
 * Encapsulation of jdbc mysql elements for database querying.
 * @author cynthia
 */
public class DataBaseConnection {
    /**
     * The name of a file (in json format) containing the information needed to
     * access the db server (its address, the database name, user and password0.
     */
    private static String dbFile = "dbconn.json";
    
    /**
     * A pointer to the connection.
     */
    private static Connection connection = null;

    /**
     * Returns a pointer to the mysql connection.
     * If connection is not set, reads dbFile to retrieve connection information 
     * and open it. If it's already opened, just returns it.
     * @return a pointer to the connection
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static Connection connect() throws DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        if(DataBaseConnection.connection == null)
        {
            HashMap json = DataBaseConnection.getCredentials();
        
            DataBaseConnection.connection = DataBaseConnection.connect((String)json.get("host"), 
                Integer.parseInt((String) json.get("port")), 
                (String)json.get("database"), 
                (String)json.get("user"), 
                (String)json.get("password"));
        }
        return DataBaseConnection.connection;
    }

    /**
     * Reads dbFile and parse it to an HashMap
     * @return HashMap<String field, String value>
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseInformationFileParsingException 
     */
    public static HashMap<String, String> getCredentials() throws DataBaseConnectionInformationFileNotFoundException, DataBaseInformationFileParsingException {
        InputStream br = null;
        HashMap json = new HashMap();
        try {
            String path = "";//System.getProperty("user.dir");
             //ConnectBD.class.getResource(dbFile).toString();
            
            InputStream stream;
//            stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(dbFile);
            stream = DataBaseConnection.class.getResourceAsStream(dbFile);
//            System.out.println(path);
//            br = new BufferedReader(new FileReader(path+"/Warden/conf/"+dbFile));
             br = stream;
        } catch(Exception e)
        {
            throw new DataBaseConnectionInformationFileNotFoundException(dbFile);
        }
        try {
            StringBuilder sb = new StringBuilder();
            int b = br.read();
            char c = (char) b;

            while (b != -1) {
                sb.append(c);
//                System.out.print(String.valueOf(c));
//                sb.append("\n");
                b =  br.read();
                c = (char)b;
            }
            
            Object obj=JSONValue.parse("["+sb.toString()+"]");
            JSONArray array=(JSONArray)obj;
            JSONObject jsonObj = (JSONObject)array.get(0);
//            System.out.println(jsonObj.get("port"));
            return jsonObj;
        } catch (Exception e) {
            throw new DataBaseInformationFileParsingException(e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Returns a connection to a server specified in the arguments instead of
     * the configuration file.
     * @param urlServer name or ip addres of the server
     * @param port it is listening to
     * @param database the name of the database
     * @param user
     * @param password
     * @return Connection pointer
     * @throws DataBaseDriverMissingException
     * @throws DataBaseConnectionException 
     */
    public static Connection connect(String urlServer, int port, String database, String user, String password) throws DataBaseDriverMissingException, DataBaseConnectionException {
        DataBaseConnection.testDriver();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + urlServer + ":" + port + "/" + database + "?"
                    + "user=" + user + "&password=" + password);
        } catch (SQLException ex) {
            // handle any errors
            throw new DataBaseConnectionException(ex.getMessage());
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
        } 
        return conn;
    }

    /**
     * @deprecated This function is to test. Professor, please ignore it.
     * 
     * It connects to the database, select users from its corresponding table
     * and prints it.
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
     static void query() throws DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        try {
            String[] fields = {"id_user", "login", "password"};
            ArrayList<HashMap> result = DataBaseConnection.query(DataBaseConnection.getStatement("SELECT * FROM user"), fields);
            for (HashMap row : result) {
                for (String field : fields) {
                    System.out.print(field + ": " + row.get(field) + " ");
                }
                System.out.print("\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Queries the database and gets the query result
     * @param query SQL
     * @param strFields the fields to retrieve (colon separated)
     * @return a list of HashMap<field,value> one per table row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static ArrayList<HashMap> query(String query, String strFields) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        String [] fields = strFields.split(",");
        return DataBaseConnection.query(DataBaseConnection.getStatement(query), fields);
    }
    
    /**
     * Queries the database and gets the query result
     * @param query
     * @param fields to retrieve (array)
     * @return a list of HashMap<field,value> one per table row
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static ArrayList<HashMap> query(String query, String[] fields) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        return DataBaseConnection.query(DataBaseConnection.getStatement(query), fields);
    }

    /**
     * Gets a prepared statement so it is possible to add arguments to the query
     * Arguments added will replace '?'in the SQL query
     * @param query SQL
     * @return PreparedStatement
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static PreparedStatement getStatement(String query) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        Connection conn = DataBaseConnection.connect();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt;
    }
    /**
     * Gets a prepared statement so it is possible to add arguments to the query
     * Arguments added will replace '?'in the SQL query
     * @param query SQL
     * @param option to give the statement the capability of retrieving autoGeneratedKeys
     * @return PreparedStatement
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static PreparedStatement getStatement(String query, int option) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        Connection conn = DataBaseConnection.connect();
        PreparedStatement stmt = conn.prepareStatement(query, option);
        return stmt;
    }

    /**
     * Get a statement for a given connection
     * @param conn connection pointer
     * @param query SQL
     * @return PreparedStatement
     * @throws SQLException 
     */
    public static PreparedStatement getStatement(Connection conn, String query) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt;
    }
    
    /**
     * Executes de SQL query in the given statement returning the specified
     * strFields
     * @param stmt PreparedStatement
     * @param strFields a colon separated String containing the fields to get
     * @return a list of HashMap<field,value> one per table row
     * @throws SQLException 
     */
    public static ArrayList<HashMap> query(PreparedStatement stmt, String strFields) throws SQLException {
        String [] fields = strFields.split(",");
        return DataBaseConnection.query(stmt, fields);
    }

    /**
     * Executes de SQL query in the given statement returning the specified
     * strFields
     * A special treatment is given to a field calle timestamp, which is retrieved
     * as long instead of a String.
     * @param stmt PreparedStatement
     * @param fields array containing the fields to get
     * @return a list of HashMap<field,value> one per table row
     * @throws SQLException 
     */
    public static ArrayList<HashMap> query(PreparedStatement stmt, String[] fields) throws SQLException {
        ResultSet rs = null;

        ArrayList result = new ArrayList();

        if (stmt.execute()) {
            rs = stmt.getResultSet();
        }
        while (rs.next()) {
            HashMap row = new HashMap();
            for (String field : fields) {
                if(field.equals("timestamp")){
                    row.put(field, rs.getTimestamp(field));
                }
                else{
                    String value = rs.getString(field);
                    row.put(field, value);
                }
            }
            result.add(row);
        }
        return result;
    }

    /**
     * Tests if mysql jdbc driver is available. If ok, nothing happens, otherwse
     * an exception is thrown
     * @throws DataBaseDriverMissingException 
     */
    public static void testDriver() throws DataBaseDriverMissingException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            throw new DataBaseDriverMissingException("com.mysql.jdbc.Driver");
        }
    }

    public static void main(String[] args)  {
        try {
            DataBaseConnection.query();
        } catch (Exception ex) {
            System.out.println("Error message : "+ex.getMessage());
        }
    }
}
