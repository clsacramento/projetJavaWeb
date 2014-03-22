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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Activity Monitor for Mac OSX.
 *
 * @author cynthia
 */
public class ActivityMonitorWindows extends UnicastRemoteObject implements IActivityMonitor {

    /**
     * Attention pour lancer les scripts powershell sous windows il faut lancer
     * une console powershell en administrateur et exécuter Set-ExectionPolicy
     * Unrestricted
     */
    private java.lang.Process p;

    public ActivityMonitorWindows() throws RemoteException {
    }

    @Override
    public ArrayList<IProcess> getListOfProcesses() throws IOException, InterruptedException {
        String command = "cmd.exe /c powershell.exe ../../cmd.ps1 process";
        ArrayList<IProcess> arrayProcess = new ArrayList<>();
        p = Runtime.getRuntime().exec(command);
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
            String str[] = line.split("\\s+");
            String pid = "";
            String name = "";
            String mem = "";
            String proc;
            try {
                pid = str[1];
                name = str[2];
                mem = str[3];
                proc = str[4];
            } catch (ArrayIndexOutOfBoundsException e) {
                proc = "";
            }
            if (!pid.equals("")) {
                Process process = new Process(pid, name, null, proc, null, mem, null);
                arrayProcess.add(process);
            }
        }
        return arrayProcess;
    }

    @Override
    public IPhysicalMemory getPhysicalMemory() throws RemoteException {
        String used = "";
        String free = "";
        String total = "";
        String command = "cmd.exe /c powershell.exe ../../cmd.ps1 used_mem";
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        try {
            used = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        command = "cmd.exe /c powershell.exe ../../cmd.ps1 free_mem";
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println(e);
        }
        reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        sb = new StringBuilder();
        try {
            free = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        command = "cmd.exe /c powershell.exe ../../cmd.ps1 total_mem";
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println(e);
        }
        reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        sb = new StringBuilder();
        try {
            total = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        Memory mem = new Memory("", "", "");
        try {
            mem = new Memory(total, used, free);
        } catch (RemoteException ex) {
            Logger.getLogger(ActivityMonitorWindows.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mem;
    }

    @Override
    public ICPU getCPU() throws RemoteException {
        CPU cpuTotal = new CPU("","","","");
        String command = "cmd.exe /c powershell.exe ../../cmd.ps1 cpu";
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    cpuTotal = new CPU(line,"","","");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ActivityMonitorWindows.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cpuTotal;
    }

}
