/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;
import com.emergentes.modelo.Registro;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge
 */
@WebServlet(name = "Principal", urlPatterns = {"/Principal"})
public class Principal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String opcion = request.getParameter("op");
        Registro objreg = new Registro();
        int id, pos;
        HttpSession ses = request.getSession();
        ArrayList<Registro> lista = (ArrayList<Registro>) ses.getAttribute("listareg");

        switch (opcion) {
            case "nuevo":
                request.setAttribute("op", opcion);
                request.setAttribute("miobjreg", objreg);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);
                objreg = lista.get(pos);
                request.setAttribute("op", opcion);
                request.setAttribute("miobjreg", objreg);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                //obtener posociondel elemento de la collecion
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request, id);
                if (pos >= 0) {
                    lista.remove(pos);
                }
                //Actuaslizamos la lista
                request.setAttribute("listareg", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
                request.setAttribute("listareg", lista);
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Registro> lista = (ArrayList<Registro>) ses.getAttribute("listareg");
        Registro objreg = new Registro();
        objreg.setId(id);
        objreg.setNombre(request.getParameter("nombre"));
        objreg.setPeso(Integer.parseInt(request.getParameter("peso")));
        objreg.setTalla(Double.parseDouble(request.getParameter("talla")));
        objreg.setVacuna(request.getParameter("vacuna"));
        System.out.println("id: " + id);
        if (id == 0) {
            //Coloca el id
            int idNuevo = obtenerId(request);
            objreg.setId(idNuevo);

            lista.add(objreg);
            System.out.println(objreg.toString());

        } else {
            //edicion
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objreg);
            System.out.println(objreg.toString());

        }

        System.out.println("Enviando as index");
        request.setAttribute("listareg", lista);
        response.sendRedirect("index.jsp");

    }

    public int buscarPorIndice(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        List<Registro> lista = (List<Registro>) ses.getAttribute("listareg");
        int pos = -1;
        if (lista != null) {
            for (Registro ele : lista) {
                ++pos;
                if (ele.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }

    public int obtenerId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Registro> lista = (ArrayList<Registro>) ses.getAttribute("listareg");
        int idn = 0;
        for (Registro ele : lista) {
            idn = ele.getId();
        }
        return idn + 1;
    }
        
    

}