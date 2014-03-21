/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiserver;

import interfaces.IActivityMonitor;
import osutils.ActivityMonitorMac;
import osutils.ActivityMonitorWindows;

/**
 *
 * @author cynthia
 */
public class ServerOS {

    /**
     * os.name pour Mac
     */
    public static final String MAC_OS = "Mac";
    

    /**
     * os.name pour Windows
     */
    public static final String WINDOWS_OS = "Windows";
    

    /**
     * os.name pour Linux
     */
    public static final String LINUX_OS = "Linux";
    
    
    /**
     * Detect Server OS and returns specific ActivityMonitor
     * @return IActivityMonitor
     */
    public static IActivityMonitor getOSActivityMonitor()
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
