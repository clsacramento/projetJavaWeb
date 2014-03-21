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
public class ActivityMonitorWindows implements IActivityMonitor{
    /**
     * Attention pour lancer les scripts powershell sous windows il faut lancer une console powershell en administrateur et exécuter Set-ExectionPolicy Unrestricted
     */
    private static String command="cmd.exe /c powershell.exe C:\\Users\\Damien\\Documents\\cmd.ps1";
    private java.lang.Process p;

    @Override
    public ArrayList<IProcess> getListOfProcesses() throws IOException,InterruptedException {
        ArrayList<IProcess> arrayProcess  = new ArrayList<>();
        p = Runtime.getRuntime().exec(ActivityMonitorWindows.command);
//        int waitFor = p.waitFor();

        BufferedReader reader = 
             new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        reader.readLine();
        while ((line = reader.readLine())!= null) {
            Process process = new Process();
            System.out.println(line);
            sb.append(line + "\n");
            String str[] = line.split("\\s+");
            String pid;
            String name;
            String mem;
            String proc;
            try {
                pid = str[0];
                name = str[1];
                mem = str [2];
                proc = str [3];
            }catch (ArrayIndexOutOfBoundsException e){
                pid = null;
                name = null;
                mem = null;
                proc = null;
            }
                    
               process.setProcess(pid,name,null,proc,null,mem,null);
               arrayProcess.add(process);
            
        }
        return arrayProcess;
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
