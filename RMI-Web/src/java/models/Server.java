/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import DAL.ServerDAO;
import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Server
 * @author cynthia
 */
public class Server {
    private int id;
    private String host;
    
    
    /**
     * Constructs the server from parameters
     * @param id
     * @param host 
     */
    public Server(int id, String host) {
        this.id = id;
        this.host = host;
    }
        
    /**
     * Constructs a server host name/ip 
     * The id is retrieved from the database and set to 0 if host does not
     * exist
     * @param host
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws SQLException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public Server(String host) throws DataBaseConnectionInformationFileNotFoundException, SQLException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException {
        this.host = host;
        this.id = 0;
        HashMap server = ServerDAO.selectServer(host);
        if(server!=null)
        {
            this.id = Integer.parseInt((String) server.get("id_server"));
        }
//        else{
//            this.id = Integer.parseInt((String)ServerDAO.insertHost(host).get("id_server"));
//        }
    }
    /**
     * Constructs Server from field/value DAO mapping
     * @param serverDAO 
     */
    public Server(HashMap serverDAO){
        this.id = Integer.parseInt((String)serverDAO.get("id_server"));
        this.host = (String)serverDAO.get("host");
    }

    
    public int getId() {
        return id;
    }

    public String getHost() {
        return host;
    }
    
    
    
}
