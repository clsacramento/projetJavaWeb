/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiclient;

import interfaces.IActivityMonitor;
import interfaces.IProcess;
import java.rmi.Naming;
import java.util.List;

/**
 *
 * @author Damien
 */
public class RMIClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            IActivityMonitor iam = (IActivityMonitor) Naming.lookup("//192.168.80.14/ActivityMonitor");
            List<osutils.Process> processList = iam.getListOfProcesses();
            for(osutils.Process process : processList){
                System.out.println("PID : "+process.getPID()+", Nom : " + process.getName());
            }
            System.out.println(iam.getPhysicalMemory());
            System.out.println(iam.getCPU());
            
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
