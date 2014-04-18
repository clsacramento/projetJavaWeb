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
import errors.rmi.NoActivityMonitorServerException;
import errors.rmi.NoRMIServiceException;
import interfaces.IActivityMonitor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cynthia
 */
public class Server {
    private int id;
    private String host;

    public Server(int id, String host) {
        this.id = id;
        this.host = host;
    }
        
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
    
    public IActivityMonitor getMonitor() throws NoActivityMonitorServerException, MalformedURLException, NoRMIServiceException
    {
        try {
            String url = "//"+this.host+"/ActivityMonitor";
            IActivityMonitor iam = (IActivityMonitor) Naming.lookup(url);
            return iam;
        } catch (NotBoundException ex) {
            throw new NoActivityMonitorServerException(this.host);
        } catch (MalformedURLException ex) {
            throw new MalformedURLException("The hostName/ipAddress << "+this.host+" >> is not valid.");
        } catch (RemoteException ex) {
//            throw new NoActivityMonitorServerException(this.host);
            throw new NoRMIServiceException(this.host);
        }
    }
    
}
