/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiserver;

import interfaces.IActivityMonitor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author cynthia
 */
public class RMIServer {
    

    /**
     * Ne pas oublier la registry :
     * -Djava.rmi.server.hostname=192.168.80.10
     * rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        try { 
            IActivityMonitor iam = ServerOS.getOSActivityMonitor();
//            iam.getListOfProcesses();
//            iam.getPhysicalMemory();
//            iam.getCPU();
//            System.out.println(iam.getCPU().toString());
            Naming.rebind("//192.168.80.14/ActivityMonitor", iam);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
