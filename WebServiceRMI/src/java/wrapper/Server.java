/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wrapper;

import errors.rmi.NoActivityMonitorServerException;
import errors.rmi.NoRMIServiceException;
import errors.rmi.NoServerConnectionException;
import interfaces.IActivityMonitor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import osutils.CPU;
import osutils.Memory;

/**
 * A wrapper for the RMI Server
 * 
 * Encapsulates IActivityMonitor interface enables the indirect access to it.
 * Allowing in then, to treat the possible RMI Exceptions properly.
 * @author cynthia
 */
public class Server {
    /**
     * Name or IP address of the RMI Server to connect to.
     */
    private final String host;
    /**
     * The Activity Monitor Remote interface
     */
    private IActivityMonitor monitor;

    /**
     * 
     * @param host 
     */
    public Server(String host) {
        this.host = host;
    }
    
    /**
     * Get server's CPU usage.
     * Connects to the server and get its result
     * @return an instance of a CPU containing the server current CPU state
     * @throws IOException
     * @throws InterruptedException
     * @throws NoServerConnectionException
     * @throws NoActivityMonitorServerException
     * @throws NoRMIServiceException 
     */
    public CPU getCPU() throws IOException, InterruptedException, NoServerConnectionException, NoActivityMonitorServerException, NoRMIServiceException {
        try {
            this.monitor = this.connect();
            return this.monitor.getCPU();
        } catch (NotBoundException ex) {
            throw new NoActivityMonitorServerException(this.host);
        } catch (MalformedURLException ex) {
            throw new MalformedURLException("The hostName/ipAddress << "+this.host+" >> is not valid.");
        } catch (RemoteException ex) {
//            throw new NoActivityMonitorServerException(this.host);
            throw new NoRMIServiceException(this.host);
        }
        
    }
    /**
     * Get server's Physical Memory usage.
     * Connects to the server and get its result
     * @return Memory an instance of a Memory containing the server current Memory state
     * @throws IOException
     * @throws InterruptedException
     * @throws NoServerConnectionException
     * @throws NoActivityMonitorServerException
     * @throws NoRMIServiceException 
     */
    public Memory getPhysicalMemory() throws IOException, InterruptedException, NoServerConnectionException, NoActivityMonitorServerException, NoRMIServiceException {
        try {
            this.monitor = this.connect();
            return this.monitor.getPhysicalMemory();
        }  catch (NotBoundException ex) {
            throw new NoActivityMonitorServerException(this.host);
        } catch (MalformedURLException ex) {
            throw new MalformedURLException("The hostName/ipAddress << "+this.host+" >> is not valid.");
        } catch (RemoteException ex) {
//            throw new NoActivityMonitorServerException(this.host);
            throw new NoRMIServiceException(this.host);
        }
        
    }
    /**
     * Get server's list of running processes.
     * Connects to the server and get its result
     * @return a list of Process instances containing the server current 
     * running processes.
     * @throws IOException
     * @throws InterruptedException
     * @throws NoServerConnectionException
     * @throws NoActivityMonitorServerException
     * @throws NoRMIServiceException 
     */
    public List<osutils.Process> getListOfProcess() throws IOException, InterruptedException, NoServerConnectionException, NoActivityMonitorServerException, NoRMIServiceException {
        try {
            this.monitor = this.connect();
            return this.monitor.getListOfProcesses();
        } 
//        catch(java.rmi.ConnectException ex){
//            throw new NoServerConnectionException(host);
//        } 
        catch (NotBoundException ex) {
            throw new NoActivityMonitorServerException(this.host);
        } catch (MalformedURLException ex) {
            throw new MalformedURLException("The hostName/ipAddress << "+this.host+" >> is not valid.");
        } catch (RemoteException ex) {
//            throw new NoActivityMonitorServerException(this.host);
            throw new NoRMIServiceException(this.host);
        }
        
    }

    /**
     * Gets the name or address of the server
     * @return host
     */
    public String getHost() {
        return host;
    }
    
    /**
     * @deprecated please ignore
     * @param command
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    private Object makeRmiCall(String command) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method m = this.getClass().getDeclaredMethod(command, new Class[] {});
        return m.invoke(this, new Object[] {});
    }
    
    /**
     * Connection to the RMI Server
     * 
     * @return IActivityMonitor remote interface for remote calls to the server.
     * @throws NotBoundException
     * @throws MalformedURLException
     * @throws RemoteException 
     */
    private IActivityMonitor connect() throws NotBoundException, MalformedURLException, RemoteException 
    {
            String url = "//"+this.host+"/ActivityMonitor";
            IActivityMonitor iam = (IActivityMonitor) Naming.lookup(url);
            return iam;
        
    }
    
}
