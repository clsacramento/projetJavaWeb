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
import errors.validation.InvalidArgumentException;
import errors.validation.ObjectNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Request;

/**
 *
 * @author cynthia
 */
@WebServlet(name = "RequestDetailsController", urlPatterns = {"/RequestDetailsController"})
public class RequestDetailsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
                HttpSession session = request.getSession();
                String idRequest = request.getParameter("id_request");
                HashMap<Integer,Request> history = (HashMap<Integer,Request>) session.getAttribute("history");
                if(idRequest==null||history==null){
                    
                    request.getRequestDispatcher("/WEB-INF/history.jsp").forward(request, response);
                }
                else{
                    try{
                        
                        int id = Integer.parseInt(idRequest);
                        Request req = history.get(id);
                        if(req == null){
                            throw new ObjectNotFoundException("history","request_id",idRequest);
                        }
                        session.setAttribute("request", req);
                        request.getRequestDispatcher("/WEB-INF/details.jsp").forward(request, response);
//                        response.sendRedirect("WEB-INF/details.jsp");
                    } catch (NumberFormatException ex){
                        throw new InvalidArgumentException("id_request", idRequest);
                    }
                }
                
                
            } catch(InvalidArgumentException | ObjectNotFoundException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", ex);
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            } catch (Exception ex){
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
