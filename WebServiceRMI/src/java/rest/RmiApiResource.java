/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest;

import errors.rest.ExceptionXml;
import errors.rmi.NoActivityMonitorServerException;
import errors.rmi.NoRMIServiceException;
import interfaces.IActivityMonitor;
import java.io.IOException;
import java.net.MalformedURLException;
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
 * REST Web Service
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
     * Retrieves representation of an instance of rest.RmiApiResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        return "<xml>dumb</xml>";
//        throw new UnsupportedOperationException();
    }
    
    /**
     * Retrieves representation of an instance of rest.RmiApiResource
     * @return an instance of java.lang.String
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
     * Retrieves representation of an instance of activitymonitor.ProcesRessources
     * @param host
     * @param request
     * @return an instance of java.lang.String
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
            IActivityMonitor iam = rmi.getMonitor();
            List<osutils.Process> list = (List<osutils.Process>) iam.getListOfProcesses();
            return list;
        } catch (IOException | InterruptedException | NoActivityMonitorServerException | NoRMIServiceException ex) {
            Logger.getLogger(RmiApiResource.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session = request.getSession();
            session.setAttribute("exception", new ExceptionXml(ex));
            return new ArrayList<>();
        } 
    }
    
    /**
     * Retrieves representation of an instance of activitymonitor.ProcesRessources
     * @param host
     * @param request
     * @return an instance of java.lang.String
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
            IActivityMonitor iam = rmi.getMonitor();
            CPU cpu = (CPU) iam.getCPU();
            return cpu;
        } catch (NoActivityMonitorServerException | NoRMIServiceException | IOException | InterruptedException ex) {
            Logger.getLogger(RmiApiResource.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session = request.getSession();
            session.setAttribute("exception", new ExceptionXml(ex));
            CPU cpu = new CPU();
            return cpu;
        }
       
    }
    
    /**
     * Retrieves representation of an instance of activitymonitor.ProcesRessources
     * @param host
     * @param request
     * @return an instance of java.lang.String
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
            System.out.println("host: "+host);
            
            Server rmi = new Server(host);
            IActivityMonitor iam = rmi.getMonitor();
            Memory mem =  iam.getPhysicalMemory();
            return mem;
        } catch (NoActivityMonitorServerException | NoRMIServiceException | IOException | InterruptedException ex) {
            Logger.getLogger(RmiApiResource.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session = request.getSession();
            session.setAttribute("exception", new ExceptionXml(ex));
            Memory mem = new Memory();
            return mem;
        }
       
    }
    
    /**
     * Retrieves representation of an instance of hello.HelloRessource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/xml")
    @Path("hello")
    public XXXX getHelloWorld() {
        return new XXXX();
    }

    /**
     * PUT method for updating or creating an instance of RmiApiResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
