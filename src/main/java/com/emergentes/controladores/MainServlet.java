package com.emergentes.controladores;

import com.emergentes.modelo.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        
        Tarea objTarea = new Tarea();
        int id,pos;
        
        HttpSession ses = request.getSession();
        ArrayList<Tarea> lista = (ArrayList<Tarea>) ses.getAttribute("listatarea");
        
        switch (op) {
            case "nuevo":
                //enviar un objeto vacio a editar
                request.setAttribute("miobjtarea", objTarea);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                //enviar un objeto a editar pero con contenido
                id = Integer.parseInt(request.getParameter("id"));
                //averiguar la posision del elemento en la lista
                pos = buscarPorIndice(request,id);
                //obtener el objeto
                objTarea = lista.get(pos);
                //enviamos el objeto
                request.setAttribute("miobjtarea", objTarea);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                //eliminar un registro de la lista segun id
                id = Integer.parseInt(request.getParameter("id"));
                //averiguar la posision del elemento en la lista
                pos = buscarPorIndice(request,id);
                if (pos >= 0) {
                    lista.remove(pos);
                }
                request.setAttribute("miobjtarea", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Tarea>  lista = (ArrayList<Tarea>) ses.getAttribute("listatarea");
        Tarea objtar = new Tarea();
        objtar.setId(id);
        objtar.setNombre(request.getParameter("nombre"));
        objtar.setEstado(request.getParameter("estado")); 
        
        if (id == 0) {
            //nuevo registro
            int idNuevo = obtenerId(request);
            objtar.setId(idNuevo);
            lista.add(objtar);
        }else{
            //edicion de registro
            int pos = buscarPorIndice(request, id);
            lista.set(pos, objtar);
        }
        request.setAttribute("listatarea", lista);
        response.sendRedirect("index.jsp");
    }
    
    public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Tarea> lista = (ArrayList<Tarea>) ses.getAttribute("listatarea");
        
        int pos = -1;
        if (lista != null) {
            for (Tarea ele : lista) {
                ++pos;
                if (ele.getId()==id) {
                    break;
                }
            }
        }
        return pos;
    }
    
    public int obtenerId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Tarea> lista = (ArrayList<Tarea>) ses.getAttribute("listatarea");
        //buscar el ultimo id
        int idNuevo = 0;
        for(Tarea ele : lista){
            idNuevo = ele.getId();
        }
        return idNuevo + 1;
    }
}