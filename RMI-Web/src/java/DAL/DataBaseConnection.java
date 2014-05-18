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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author cynthia
 */
public class DataBaseConnection {
    private static String dbFile = "dbconn.json";
    
    private static Connection connection = null;

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

    public static void query() throws DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
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

    public static ArrayList<HashMap> query(String query, String strFields) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        String [] fields = strFields.split(",");
        return DataBaseConnection.query(DataBaseConnection.getStatement(query), fields);
    }

    public static ArrayList<HashMap> query(String query, String[] fields) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        return DataBaseConnection.query(DataBaseConnection.getStatement(query), fields);
    }

    public static PreparedStatement getStatement(String query) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        Connection conn = DataBaseConnection.connect();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt;
    }
    
    public static PreparedStatement getStatement(String query, int option) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        Connection conn = DataBaseConnection.connect();
        PreparedStatement stmt = conn.prepareStatement(query, option);
        return stmt;
    }

    public static PreparedStatement getStatement(Connection conn, String query) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt;
    }
    
    public static ArrayList<HashMap> query(PreparedStatement stmt, String strFields) throws SQLException {
        String [] fields = strFields.split(",");
        return DataBaseConnection.query(stmt, fields);
    }

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
