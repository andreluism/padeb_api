/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Dao.MunicipioDao;
import Dao.RegiaoDao;
import Dao.UFDao;
import Model.Regiao;
import Utils.DataAccessLayerException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.Integer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Andre
 */
public class Servlet extends HttpServlet implements Serializable {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DataAccessLayerException, InstantiationException, IllegalAccessException {
        // buscando os parâmetros no request
        Date datum = new Date();
        Gson gson = new Gson();
        List database = new ArrayList();
        //
        String filtro = request.getParameter("filtro");
        String nome = "";
        List<Long> idMunicipio = new ArrayList<Long>(),
                idUF = new ArrayList<Long>(),
                idRegiao = new ArrayList<Long>();
        List<Integer> idSerie = new ArrayList<Integer>(),
                tipoRede = new ArrayList<Integer>(),
                localizacao = new ArrayList<Integer>(),
                capital = new ArrayList<Integer>();
        idMunicipio.add((long)-1);
        idUF.add((long)-1);
        idRegiao.add((long)-1);
        idSerie.add(-1);
        tipoRede.add(-1);
        localizacao.add(-1);
        capital.add(-1);
        String paramSplit[] = null;
        if (request.getParameter("nome")!=null) {
            nome = request.getParameter("nome");
        }
        if (request.getParameter("idMunicipio")!=null && !request.getParameter("idMunicipio").equals("")) {
            paramSplit = request.getParameter("idMunicipio").split(";");
            int paramSplitSize = paramSplit.length;
            idMunicipio.remove(0);
            for (int i = 0; i < paramSplitSize; i++) {
                idMunicipio.add(i,Long.parseLong(paramSplit[i]));
            } 
            paramSplit = null;            
        }   
        if (request.getParameter("idUF")!=null && !request.getParameter("idUF").equals("")) {
            paramSplit = request.getParameter("idUF").split(";");
            int paramSplitSize = paramSplit.length;
            idUF.remove(0);
            for (int i = 0; i < paramSplitSize; i++) {
                idUF.add(i,Long.parseLong(paramSplit[i]));
            } 
            paramSplit = null;            
        }
        if (request.getParameter("idRegiao")!=null && !request.getParameter("idRegiao").equals("")) {
            paramSplit = request.getParameter("idRegiao").split(";");
            int paramSplitSize = paramSplit.length;
            idRegiao.remove(0);
            for (int i = 0; i < paramSplitSize; i++) {
                idRegiao.add(i,Long.parseLong(paramSplit[i]));
            } 
            paramSplit = null;            
        }
        if (request.getParameter("idSerie")!=null && !request.getParameter("idSerie").equals("")) {
            paramSplit = request.getParameter("idSerie").split(";");
            int paramSplitSize = paramSplit.length;
            idSerie.remove(0);
            for (int i = 0; i < paramSplitSize; i++) {
                idSerie.add(i,Integer.parseInt(paramSplit[i]));
            } 
            paramSplit = null;            
        }
        if (request.getParameter("tipoRede")!=null && !request.getParameter("tipoRede").equals("")) {
            paramSplit = request.getParameter("tipoRede").split(";");
            int paramSplitSize = paramSplit.length;
            tipoRede.remove(0);
            for (int i = 0; i < paramSplitSize; i++) {
                tipoRede.add(i,Integer.parseInt(paramSplit[i]));
            } 
            paramSplit = null;            
        }
        if (request.getParameter("localizacao")!=null && !request.getParameter("localizacao").equals("")) {
            paramSplit = request.getParameter("localizacao").split(";");
            int paramSplitSize = paramSplit.length;
            localizacao.remove(0);
            for (int i = 0; i < paramSplitSize; i++) {
                localizacao.add(i,Integer.parseInt(paramSplit[i]));
            } 
            paramSplit = null;            
        }
        if (request.getParameter("capital")!=null && !request.getParameter("capital").equals("")) {
            paramSplit = request.getParameter("capital").split(";");
            int paramSplitSize = paramSplit.length;
            capital.remove(0);
            for (int i = 0; i < paramSplitSize; i++) {
                capital.add(i,Integer.parseInt(paramSplit[i]));
            } 
            paramSplit = null;            
        }
        //
        if (filtro.equals("filtroRegiao"))
            database = new RegiaoDao().findAllServlet(nome, idRegiao, idSerie, tipoRede, localizacao, capital);
        if (filtro.equals("filtroEstado"))
            database = new UFDao().findAllServlet(nome, idUF, idRegiao, idSerie, tipoRede, localizacao, capital);
        if (filtro.equals("filtroMunicipio"))
            database = new MunicipioDao().findAllServlet(nome, idMunicipio, idUF, idRegiao, idSerie, tipoRede, localizacao);
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(database);
        try {
            /* TODO output your page here. You may use following sample code. */
            
            out.println(gson.toJson(database));   
            
        } finally {            
            out.close();
            System.out.println("Começo: " + datum);
            System.out.println("Fim: " + new Date());
            System.gc();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
        } catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
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
