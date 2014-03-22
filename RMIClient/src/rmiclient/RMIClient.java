/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmiclient;

import interfaces.IActivityMonitor;
import interfaces.IProcess;
import java.rmi.Naming;
import java.util.ArrayList;

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
            IActivityMonitor iam = (IActivityMonitor) Naming.lookup("//localhost/ActivityMonitor");
            ArrayList<IProcess> processList = iam.getListOfProcesses();
            for(IProcess process : processList){
                System.out.println("PID : "+process.getPID()+", Nom : " + process.getName());
            }
            System.out.println("Total : " + iam.getPhysicalMemory().getTotal() + " Mo");
            System.out.println("Used : " + iam.getPhysicalMemory().getUsed()+ " Mo");
            System.out.println("Free : " + iam.getPhysicalMemory().getFree()+ " Mo");
            System.out.println("CPU : " + iam.getCPU().getTotalUsed() + " %");
            
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
