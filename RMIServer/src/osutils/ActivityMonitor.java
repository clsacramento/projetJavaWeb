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
public class ActivityMonitor implements IActivityMonitor{
    private static String command="ps -ax -O %cpu,%mem,user,time,wq,stat,rss";
    private java.lang.Process p;

    @Override
    public ArrayList<IProcess> getListOfProcesses() throws IOException,InterruptedException {
        p = Runtime.getRuntime().exec(ActivityMonitor.command);
//        int waitFor = p.waitFor();

        BufferedReader reader = 
             new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";			
        while ((line = reader.readLine())!= null) {
            System.out.println(line);
            sb.append(line + "\n");
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
