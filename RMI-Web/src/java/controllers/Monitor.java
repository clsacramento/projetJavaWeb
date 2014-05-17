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
import errors.rmi.NoActivityMonitorServerException;
import errors.rmi.NoRMIServiceException;
import errors.rmi.ServerRunTimeInternalErrorException;
import errors.rmi.ServerDidNotRespondException;
import interfaces.IActivityMonitor;
import interfaces.ICPU;
import interfaces.IPhysicalMemory;
import interfaces.IProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.RequestCPU;
import models.RequestMemory;
import models.RequestProcess;
import models.Server;
import models.User;

/**
 *
 * @author Damien
 */
public class Monitor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataBaseConnectionInformationFileNotFoundException, SQLException, DataBaseDriverMissingException, DataBaseInformationFileParsingException, DataBaseConnectionException, NoActivityMonitorServerException, NoRMIServiceException, ServerDidNotRespondException, ServerRunTimeInternalErrorException {
        //response.setContentType("text/html;charset=UTF-8");
        
        
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if(user == null || user.getId()==0){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
        IActivityMonitor iam = null;
        ArrayList<IProcess> processList = null;
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

        iam = server.getMonitor();
        
        if(server.getId()==0){
            server = ServerController.insertServer(host);
        }
           
        if (cpuExist) {
            request.setAttribute("cpuExist", true);
            try {
                ICPU cp = iam.getCPU();
                request.setAttribute("icpu", cp);
                
                RequestCPU rCPU = new RequestCPU(server, user, "getCPU", cp);
                rCPU.saveRequestDetails();
                
            } catch (RemoteException ex){
                throw new ServerDidNotRespondException(host,"getCPU");
            } 
            catch (    IOException | InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServerRunTimeInternalErrorException(host, "getCPU");
            }
           
        } 
        
        if (memExist) {
            request.setAttribute("memExist", true);
            try {
                IPhysicalMemory im = iam.getPhysicalMemory();
                request.setAttribute("imem", im);
                
                RequestMemory rMem = new RequestMemory(server, user, "getPhysicalMemory", im);
                rMem.saveRequestDetails();
            } catch (RemoteException ex){
                throw new ServerDidNotRespondException(host,"getPhysicalMemory");
            } catch (    IOException | InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServerRunTimeInternalErrorException(host, "getPhysicalMemory");
            }
           
        }
        if (processExist) {
            request.setAttribute("processExist", true);
            try {
                List<IProcess> pr = iam.getListOfProcesses();
                request.setAttribute("iprocess", pr);
                
                RequestProcess rPro = new RequestProcess(server, user, "getListOfProcesses", pr);
                rPro.saveRequestDetails();
            } catch (RemoteException ex){
                throw new ServerDidNotRespondException(host,"getListOfProcesses");
            } catch (    IOException | InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServerRunTimeInternalErrorException(host, "getListOfProcesses");
            }
           
        }
        
        request.getRequestDispatcher("/WEB-INF/monitor.jsp").forward(request, response);
        
        /*try (PrintWriter out = response.getWriter()) {
            try {
            processList = iam.getListOfProcesses();
        } catch (InterruptedException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Monitor</title>");      
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Monitor at " + request.getContextPath() + "</h1>");
            out.println("Total : " + iam.getPhysicalMemory().getTotal() + " Mo <br>");
            out.println("Used : " + iam.getPhysicalMemory().getUsed()+ " Mo <br>");
            out.println("Free : " + iam.getPhysicalMemory().getFree()+ " Mo <br>");
            out.println("CPU : " + iam.getCPU().getTotalUsed() + " % <br>");
            for(IProcess process : processList){
                out.println("PID : "+process.getPID()+", Nom : " + process.getName()+ "<br>");
            }
            out.println("</body>");
            out.println("</html>");
        }*/
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
