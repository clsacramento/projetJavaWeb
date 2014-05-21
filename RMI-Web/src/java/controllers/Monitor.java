/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import errors.database.DataBaseConnectionException;
import errors.database.DataBaseConnectionInformationFileNotFoundException;
import errors.database.DataBaseDriverMissingException;
import errors.database.DataBaseInformationFileParsingException;
import errors.fail.UnexpectedErrorException;
import errors.remote.RemoteErrorException;
import errors.rest.ExceptionXml;
import errors.rmi.NoActivityMonitorServerException;
import errors.rmi.NoRMIServiceException;
import errors.rmi.ServerRunTimeInternalErrorException;
import errors.rmi.ServerDidNotRespondException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import models.RequestCPU;
import models.RequestMemory;
import models.RequestProcess;
import models.Server;
import models.User;
import osutils.CPU;
import osutils.Memory;

/**
 *
 * @author Damien
 */
public class Monitor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * Connects to REST web service and execute requests according to what
     * the user chose.
     * 
     * When the request is successful, it is registered in the history for later
     * consultation.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws errors.database.DataBaseConnectionInformationFileNotFoundException
     * @throws java.sql.SQLException
     * @throws errors.database.DataBaseDriverMissingException
     * @throws errors.database.DataBaseInformationFileParsingException
     * @throws errors.database.DataBaseConnectionException
     * @throws errors.rmi.NoActivityMonitorServerException
     * @throws errors.rmi.NoRMIServiceException
     * @throws errors.rmi.ServerDidNotRespondException
     * @throws errors.rmi.ServerRunTimeInternalErrorException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataBaseConnectionInformationFileNotFoundException, SQLException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException, NoActivityMonitorServerException, NoRMIServiceException, ServerDidNotRespondException, ServerRunTimeInternalErrorException {
        //response.setContentType("text/html;charset=UTF-8");
        
        try{
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if(user == null || user.getId()==0){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
        boolean cpuExist = true;
        boolean memExist = true;
        boolean processExist = true;
        String[] checkbox = request.getParameterValues("cpu");
        if (checkbox == null) {
            cpuExist = false;
        }
        checkbox = request.getParameterValues("mem");
        if (checkbox == null) {
            memExist = false;
        }
        checkbox = request.getParameterValues("process");
        if (checkbox == null) {
            processExist = false;
        }
        
        
        String host = request.getParameter("url");
        Server server = new Server(host);

        RestClientController restClient = new RestClientController();
        
           
        if (cpuExist) {
            request.setAttribute("cpuExist", true);
            
                CPU cp = restClient.getCPU(CPU.class, "", host);
                
                if("".equals(cp.getTotalUsed())){
                    ExceptionXml eXml = restClient.getLastException(ExceptionXml.class);
                    throw new RemoteErrorException(eXml);
                }
                
                request.setAttribute("icpu", cp);
                
                RequestCPU rCPU = new RequestCPU(server, user, "getCPU", cp);
                rCPU.saveRequestDetails();
                
            
           
        } 
        
        if (memExist) {
            request.setAttribute("memExist", true);
                Memory im = restClient.getPhysicalMemory(Memory.class, "", host);
                
                if("".equals(im.getTotal())){
                    ExceptionXml eXml = restClient.getLastException(ExceptionXml.class);
                    throw new RemoteErrorException(eXml);
                }
                
                request.setAttribute("imem", im);
                
                RequestMemory rMem = new RequestMemory(server, user, "getPhysicalMemory", im);
                rMem.saveRequestDetails();
            
           
        }
        if (processExist) {
            request.setAttribute("processExist", true);
            ProcessesList plist = restClient.getProcessList(ProcessesList.class, "", host);
                List<osutils.Process> pr = plist.getList();
                
                if(pr==null||pr.isEmpty()){
                    ExceptionXml eXml = restClient.getLastException(ExceptionXml.class);
                    throw new RemoteErrorException(eXml);
                }
                
                request.setAttribute("iprocess", pr);
                
                RequestProcess rPro = new RequestProcess(server, user, "getListOfProcesses", pr);
                rPro.saveRequestDetails();
            
           
        }
        
        if(server.getId()==0){
            ServerController.insertServer(host);
        }
        
        request.getRequestDispatcher("/WEB-INF/monitor.jsp").forward(request, response);
        
        }  catch(DataBaseConnectionException | DataBaseConnectionInformationFileNotFoundException | DataBaseDriverMissingException | DataBaseInformationFileParsingException | RemoteErrorException ex){
             Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", ex);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
        catch ( IOException | SQLException | ServletException | ClientErrorException ex){
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", new UnexpectedErrorException(ex));
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DataBaseConnectionInformationFileNotFoundException | SQLException | DataBaseDriverMissingException | DataBaseInformationFileParsingException | DataBaseConnectionException | NoActivityMonitorServerException | NoRMIServiceException | ServerDidNotRespondException | ServerRunTimeInternalErrorException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", ex);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } 
    }
    
    
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DataBaseConnectionInformationFileNotFoundException | SQLException | DataBaseDriverMissingException | DataBaseInformationFileParsingException | DataBaseConnectionException | NoActivityMonitorServerException | NoRMIServiceException | ServerDidNotRespondException | ServerRunTimeInternalErrorException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", ex);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/**
 * A class to wrap a List<osutils.Process> and retrieve the values from REST
 * web service XML
 * @author cynthia
 */
@XmlRootElement(name="processes")
class ProcessesList{
    
    /**
     * process list
     */
     List<osutils.Process> list;

//    public ProcessesList(){}
//    
//    
//    public ProcessesList(List<osutils.Process> list){
//    	this.list=list;
//    }

    @XmlElement(name = "process") 
    public List<osutils.Process> getList(){
    	return list;
    }
    
    public void setList(List<osutils.Process> list){
        this.list = list;
    }
}