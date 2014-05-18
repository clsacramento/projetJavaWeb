/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wrapper;

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
    private String host;
    private IActivityMonitor monitor;

    public Server(String host) throws NoActivityMonitorServerException, MalformedURLException, NoRMIServiceException {
        this.host = host;
        this.monitor = this.connect();
    }
    
    public IActivityMonitor getMonitor(){
        return this.monitor;
    }
        
    

    public String getHost() {
        return host;
    }
    
    private IActivityMonitor connect() throws NoActivityMonitorServerException, MalformedURLException, NoRMIServiceException
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
