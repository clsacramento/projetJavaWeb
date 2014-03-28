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
import java.util.ArrayList;

/**
 * Activity Monitor for Mac OSX.
 * @author cynthia
 */
public class ActivityMonitorMac implements IActivityMonitor{    
    //top -stats pid,command,cpu,th,pstate,time -l 1
    
    /**
     * Command to get list of process
     */
    private static String processListCommand="ps -ax -O %cpu,%mem,user,wq,rss";
    private java.lang.Process p;

    @Override
    public ArrayList<IProcess> getListOfProcesses() throws IOException,InterruptedException {
        ArrayList<IProcess> arrayProcesses = new ArrayList<>();
        p = Runtime.getRuntime().exec(ActivityMonitorMac.processListCommand);

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
    public IPhysicalMemory getPhysicalMemory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ICPU getCPU() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
