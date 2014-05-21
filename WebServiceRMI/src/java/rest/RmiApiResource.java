/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import errors.rest.ExceptionXml;
import interfaces.IActivityMonitor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import osutils.CPU;
import osutils.Memory;
import wrapper.Server;

/**
 * RESTful Web Service
 * This RESTful API exposes the ActivityMonitor from the RMIServer projects.
 * Its functions becomes accessible through the corresponding URI in the web service.
 * 
 * @author cynthia
 */
@Path("rmiapi")
public class RmiApiResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RmiApiResource
     */
    public RmiApiResource() {
    }
    
    /**
     * Retrieves the error which happened in the last resource call.
     * The exception is gathered from the HttpSession and transfered in XML.
     * This XML object contains the type and the message of the error.
     * @return an instance of ExceptionXml converted to XML
     */
    @GET
    @Produces("application/xml")
    @Path("lastError")
    public ExceptionXml getLastException(@Context HttpServletRequest request) {
        //TODO return proper representation object
        ExceptionXml exXml = (ExceptionXml) request.getSession().getAttribute("exception");
        
        if(exXml==null){
            return new ExceptionXml(new Exception("Good! There were no errors in this session."));
        }
        
        return exXml;
//        throw new UnsupportedOperationException();
    }
    
    /**
     * Retrieves the list of processes running in the host.
     * 
     * Connection to RMI server host and remote invocation of its getListOfProcesses
     * method.
     * @param host name or ip address of the RMI Server, localhost if empty
     * @return a list of Process instances converted to XML. The list is empty
     * if the request is not successful and session variable exception is set
     * in case any is caught.
     */
    @GET
    @Produces("text/xml")
//    @Path("getListOfProcesses{host:([^/]+?)?}")
    @Path("getListOfProcesses{p:/?}{host : ([^/]+?)?}")
    public List<osutils.Process> getProcessList(@PathParam("host") String host, @Context HttpServletRequest request) {
        
        try {
            //TODO return proper representation object
            if("".equals(host)){
                host = "localhost";
            }
            System.out.println("host: "+host);
            
            Server rmi = new Server(host);
            List<osutils.Process> list = rmi.getListOfProcess();
            request.getSession().setAttribute("exception", null);
            return list;
        } catch (Exception ex) {
            Logger.getLogger(RmiApiResource.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session = request.getSession();
            session.setAttribute("exception", new ExceptionXml(ex));
            request.getSession().setAttribute("exception", null);
            return new ArrayList<>();
        } 
    }
    
    /**
     * Retrieves CPU usage on the host
     * Connects to the RMI host and execute its getCPU method.
     * @param host name or ip address of the RMI Server, localhost if empty
     * @return an instance of CPU converted to XML. The object members are empty
     * if the request is not successful and session variable exception is set
     * in case any is caught.
     */
    @GET
    @Produces("text/xml")
    @Path("getCPU{p:/?}{host : ([^/]+?)?}")
    public CPU getCPU(@PathParam("host") String host, @Context HttpServletRequest request) {
        try {
            //TODO return proper representation object
            if("".equals(host)){
                host = "localhost";
            }
            System.out.println("host: "+host);
            
            Server rmi = new Server(host);
            CPU cpu = (CPU) rmi.getCPU();
            request.getSession().setAttribute("exception", null);
            return cpu;
        } catch (Exception ex) {
            Logger.getLogger(RmiApiResource.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session = request.getSession();
            session.setAttribute("exception", new ExceptionXml(ex));
            CPU cpu = new CPU();
            return cpu;
        }
       
    }
    
    /**
     * Retrieves Physical Memory usage on the host
     * Connects to the RMI host and execute its getPhysicalMemory method.
     * @param host name or ip address of the RMI Server, localhost if empty
     * @return an instance of Memory converted to XML. The object members are empty
     * if the request is not successful and session variable exception is set
     * in case any is caught.
     */
    @GET
    @Produces("text/xml")
    @Path("getPhysicalMemory{p:/?}{host : ([^/]+?)?}")
    public Memory getPhysicalMemory(@PathParam("host") String host, @Context HttpServletRequest request) {
        try {
            //TODO return proper representation object
            if("".equals(host)){
                host = "localhost";
            }
            System.out.println("host mem: "+host);
            
            Server rmi = new Server(host);
            Memory mem =  rmi.getPhysicalMemory();
            return mem;
        } catch (Exception ex) {
            Logger.getLogger(RmiApiResource.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session = request.getSession();
            session.setAttribute("exception", new ExceptionXml(ex));
            Memory mem = new Memory();
            return mem;
        }
       
    }
    
    

}
