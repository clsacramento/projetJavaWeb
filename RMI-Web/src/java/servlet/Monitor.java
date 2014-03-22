/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import interfaces.IActivityMonitor;
import interfaces.IProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
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
        try {
            iam = (IActivityMonitor) Naming.lookup("//" + request.getParameter("url") + "/ActivityMonitor");
        } catch (NotBoundException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (memExist) {
            request.setAttribute("memExist", true);
            request.setAttribute("totalMem", iam.getPhysicalMemory().getTotal());
            request.setAttribute("usedMem", iam.getPhysicalMemory().getUsed());
            request.setAttribute("freeMem", iam.getPhysicalMemory().getFree());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
