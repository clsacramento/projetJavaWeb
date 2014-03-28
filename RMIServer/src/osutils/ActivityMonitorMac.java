/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import interfaces.IProcess;
import interfaces.IActivityMonitor;
import interfaces.ICPU;
import interfaces.IPhysicalMemory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Activity Monitor for Mac OSX.
 * @author cynthia
 */
public class ActivityMonitorMac extends UnicastRemoteObject implements IActivityMonitor{    
    //top -stats pid,command,cpu,th,pstate,time -l 1
    
    public ActivityMonitorMac() throws RemoteException {
        
    }
    
    /**
     * Command to get list of process
     */
    private static String processListCommand="ps -ax -O %cpu,%mem,user,wq,rss";
    
    private static String cpuAndMemoryCommand = "top -R -l 1 ";

    @Override
    public ArrayList<IProcess> getListOfProcesses() throws IOException,InterruptedException {
        ArrayList<IProcess> arrayProcesses = new ArrayList<>();
        java.lang.Process p = Runtime.getRuntime().exec(ActivityMonitorMac.processListCommand);

        BufferedReader reader = 
             new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;			
        while ((line = reader.readLine())!= null) {
            //PID  %CPU %MEM USER     WQ    RSS   TT  STAT  TIME COMMAND
            String str[] = line.split("\\s+");
            
            String pid = str[1];
            String cpu = str[2];
            String mem = str[3];
            String user = str[4];
            String wq = str[5];
            String rss = str[6];
            String tt = str[7];
            String stat = str[8];
            String time = str[9];
            String cmd = str[10].replaceAll("/.+/", "");
            
            IProcess lineProcess = new Process(pid, cmd, cpu, time, stat, mem, user);
            
            
            
            System.out.println(""+lineProcess);
            
            arrayProcesses.add(lineProcess);
        }
        return arrayProcesses;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IPhysicalMemory getPhysicalMemory() throws IOException {
        java.lang.Process p = Runtime.getRuntime().exec(ActivityMonitorMac.cpuAndMemoryCommand);

        BufferedReader reader = 
             new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        
        int ignoreLines = 6;
        
        for(int i = 0; i < ignoreLines; i++)
        {
            reader.readLine();
        }
        line = reader.readLine().replaceAll("PhysMem: ", "");
        
        String str[] = line.split(",");
        
        String used = str[0].replaceAll(" \\(.*\\)", "");
        String free = str[1].replace(".", "");
        
        IPhysicalMemory mem = new Memory(null, used, free);
        
        return mem;
    }

    @Override
    public ICPU getCPU() throws IOException {
        java.lang.Process p = Runtime.getRuntime().exec(ActivityMonitorMac.cpuAndMemoryCommand);

        BufferedReader reader = 
             new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        
        int ignoreLines = 3;
        
        for(int i = 0; i < ignoreLines; i++)
        {
            reader.readLine();
        }
        
        line = reader.readLine().replaceAll("CPU usage: ", "");
        
        String str[] = line.split(",");
        
        String user = str[0];
        String sys = str[1];
        String idle = str[2];
        
        ICPU cpu = new CPU(null, user, sys, idle);
        
        return cpu;
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
