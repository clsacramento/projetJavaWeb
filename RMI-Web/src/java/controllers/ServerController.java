/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import DAL.ServerDAO;
import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import models.Server;

/**
 * Server manipulation
 * @author cynthia
 */
public class ServerController {
    /**
     * Get list of servers in database.
     * @return list of Server instances
     * @throws SQLException
     * @throws DataBaseConnectionInformationFileNotFoundException
     * @throws DataBaseDriverMissingException
     * @throws DataBaseInformationFileParsingException
     * @throws DataBaseConnectionException 
     */
    public static ArrayList<Server>  getServers() throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException
    {
        ArrayList<HashMap> serversDAO = ServerDAO.selectServers();
        ArrayList<Server> servers = new ArrayList<>();
        for(HashMap serverDAO : serversDAO){
            servers.add(new Server(serverDAO));
        }
        return servers;
    }
    
    public static Server insertServer(String host) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        return new Server(ServerDAO.insertHost(host));
    }
    
    public static void deleteServer(String id) throws SQLException, DataBaseConnectionInformationFileNotFoundException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException{
        ServerDAO.delHost(id);
    }
}
