/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.HRDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author rtataru
 */
@WebServlet(name = "AddThingsController", urlPatterns = {"/AddThingsController"})
public class AddThingsController extends HttpServlet {

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
            throws ServletException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
        response.setContentType("text/html;charset=UTF-8");
        Enumeration en = request.getParameterNames();
        List<String> l = new ArrayList<>();
        String id = "";
        Object obj = en.nextElement();
        String param = (String) obj;
        switch (param) {
            case "allergy":
                String allergy = request.getParameter(param);
                obj = en.nextElement();
                id = request.getParameter((String) obj);
                HRDAO.getInstance().addAllergy(allergy, id);
                break;
            case "delAllergy":
                String delAllergy = request.getParameter(param);
                obj = en.nextElement();
                id = request.getParameter((String) obj);
                HRDAO.getInstance().delAllergy(delAllergy, id);
                break;
            case "diagnostic":
                String diagnostic = request.getParameter(param);
                obj = en.nextElement();
                String medication = request.getParameter((String) obj);
                obj = en.nextElement();
                String treatPlan = request.getParameter((String) obj);
                obj = en.nextElement();
                String date = request.getParameter((String) obj);
                obj = en.nextElement();
                id = request.getParameter((String) obj);
                HRDAO.getInstance().addMedicalVisit(diagnostic, medication, treatPlan, date, id);
                break;
            case "imuDate":
                String imuDate = request.getParameter(param);
                obj = en.nextElement();
                String imuName = request.getParameter((String) obj);
                obj = en.nextElement();
                id = request.getParameter((String) obj);
                HRDAO.getInstance().addImu(imuDate, imuName, id);
                break;
            case "chDate":
                String chDate = request.getParameter(param);
                obj = en.nextElement();
                String imName = request.getParameter((String) obj);
                obj = en.nextElement();
                id = request.getParameter((String) obj);
                HRDAO.getInstance().changeImu(chDate, imName, id);
                break;
        }
        request.setAttribute("id", id);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        
        ArrayList<String> ana = new ArrayList<>();
        
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
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(AddThingsController.class.getName()).log(Level.SEVERE, null, ex);
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
