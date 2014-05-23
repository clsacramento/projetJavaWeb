/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import errors.fail.UnexpectedErrorException;
import errors.validation.InvalidArgumentException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import misc.AuthTest;

/**
 *
 * @author Damien
 */
public class DelUser extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        AuthTest testauth = new AuthTest();
        boolean authExist = testauth.auth(session);

        if (authExist) {
            try {
                String id = (String) request.getParameter("id");
                if (id == null) {
                    throw new InvalidArgumentException("id_user", id);
                }
                UsersController.deleteUser(id);
                response.setContentType("text/html;charset=UTF-8");
                request.getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", new UnexpectedErrorException(ex));
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/errorAuth.jsp").forward(request, response);
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
