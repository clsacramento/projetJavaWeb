/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package osutils;

import interfaces.IActivityMonitor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
    /**
     * Command to get CPU and Memory usage information
     */
    private static String cpuAndMemoryCommand = "top -R -l 1 ";

    /**
     * Runs "ps" command to retrieve informations about the processes running
     * in the system.
     * Informations of each processes : PID, name (command), % of cpu using,
     * cpu time, state, memory and user
     * @return ArrayList<Process> proceses list
     * @throws IOException
     * @throws InterruptedException 
     */
    @Override
    public ArrayList<Process> getListOfProcesses() throws IOException,InterruptedException {
        ArrayList<Process> arrayProcesses = new ArrayList<>();
        java.lang.Process p = Runtime.getRuntime().exec(ActivityMonitorMac.processListCommand);

        BufferedReader reader = 
             new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;	
        
        reader.readLine();
        
        while ((line = reader.readLine())!= null) {
            //PID  %CPU %MEM USER     WQ    RSS   TT  STAT  TIME COMMAND
            
            if(line.startsWith(" "))
            {
                line = line.replaceFirst("\\s+", "");
            }
            
            String str[] = line.split("\\s+");
            
            String pid = str[0];
            String cpu = str[1];
            String mem = str[2];
            String user = str[3];
            String wq = str[4];
            String rss = str[5];
            String tt = str[6];
            String stat = str[7];
            String time = str[8];
            String cmd = str[9].replaceAll("/.+/", "");
            
            for(int i = 10; i < str.length;i++)
            {
                cmd += " "+str[i].replaceAll("/.+/", "");
            }
            
            Process lineProcess = new Process(pid, cmd, cpu, time, stat, mem, user);
            
            
            
//            System.out.println(""+lineProcess);
            
            arrayProcesses.add(lineProcess);
        }
        return arrayProcesses;
    }

    /**
     * Runs top non interactive to retrive the physical memory information.
     * This command returns used and free memory.
     * The function uses theses values to calculate the total memory.
     * @return Memory
     * @throws IOException 
     */
    @Override
    public Memory getPhysicalMemory() throws IOException {
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
        
        String str[] = line.split(", ");
        
        String used = str[0].replaceAll(" \\(.*\\)", "").replaceAll("M used", "");
        String free = str[1].replace(".", "").replaceAll("M unused", "");
        
        int u = Integer.parseInt(used);
        int f = Integer.parseInt(free);
        int total = u + f;
        
        Memory mem = new Memory(total+"", used, free);
        
        return mem;
    }

    /**
     * Runs "top" to get the CPU usage state on the server.
     * Top returns percentage o usage by the user, by the system and in idle.
     * This function calculages the toal usage by summing user and system %'s.
     * @return
     * @throws IOException 
     */
    @Override
    public CPU getCPU() throws IOException {
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
        
        String user = str[0].replaceAll("% user", "");
        String sys = str[1].replaceAll("% sys", "");
        String idle = str[2].replaceAll("% idle", "");
        
        float u = Float.parseFloat(user);
        float s = Float.parseFloat(sys);
        float total = u + s;
        
        CPU cpu = new CPU(total+"", user, sys, idle);
        
        return cpu;
    }
    
}
