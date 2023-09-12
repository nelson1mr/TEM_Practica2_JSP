<%@page import="com.emergentes.modelo.Tarea" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("listatarea") == null){
        ArrayList<Tarea> lisaux = new ArrayList<Tarea>();
        session.setAttribute("listatarea",lisaux);
    }
    ArrayList<Tarea> lista = (ArrayList<Tarea>) session.getAttribute("listatarea");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Gestor de Tareas</h1>
        <h2>Nombre: Nelson Mamani Ramos</h2>
        <p><a href="MainServlet?op=nuevo">Nueva tarea</a></p>
        <table border="1" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>Tarea</th>
                <th>Completado</th>
                <th>Operación</th>
            </tr>
            <%
            if (lista != null) {
                for(Tarea item : lista){
            %>
                    <tr>
                        <td><%= item.getId()%></td>
                        <td><%= item.getNombre() %></td>
                        <td><input type="checkbox" name="estado"></td>
                        <td>
                            <a href="MainServlet?op=editar&id=<%= item.getId()%>">Editar</a> 
                            <a href="MainServlet?op=eliminar&id=<%= item.getId()%>" onclick="return(confirm('Esta seguro de eliminar?'))"
                               >Eliminar</a>
                        </td>
                    </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>
