/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiserver;

import interfaces.IActivityMonitor;
import java.rmi.RemoteException;
import osutils.ActivityMonitorMac;
import osutils.ActivityMonitorWindows;

/**
 * Server Operating System
 * 
 * Detects the server OS to provide the suitable Activity Monitor
 * @author cynthia
 */
public class ServerOS {

    /**
     * Value used to the detect Mac OS
     * os.name for Mac
     */
    public static final String MAC_OS = "Mac";
    

    /**
     * Value to detect Windows systems
     * os.name for Windows
     */
    public static final String WINDOWS_OS = "Windows";
    

    /**
     * String to detect Linux/Unix systems
     * os.name for Linux
     */
    public static final String LINUX_OS = "Linux";
    
    
    /**
     * Detect Server OS and returns specific ActivityMonitor
     * @return IActivityMonitor
     * @throws java.rmi.RemoteException
     */
    public static IActivityMonitor getOSActivityMonitor() throws RemoteException
    {
        IActivityMonitor iam;
        
        
        String osName = System.getProperty("os.name");
        
        String[] detailOS = osName.split(" ");
        
        String os = detailOS[0];
        
        System.out.println(os);
        
        switch(os)
        {
            case MAC_OS:
                iam = new ActivityMonitorMac();
                break;
            case WINDOWS_OS:
                iam = new ActivityMonitorWindows();
                break;
            default:
                iam = null;
                break;
        }
        
        return iam;
    }
}
