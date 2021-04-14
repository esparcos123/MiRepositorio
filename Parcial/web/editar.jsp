<%-- 
    Document   : editar
    Created on : 13-04-2021, 09:01:44 PM
    Author     : HP
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Registro"%>
<%
    Registro reg = (Registro) request.getAttribute("miobjreg");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:if test="${requestScope.op == 'nuevo'}" var="res" scope="request">
            <h1>Nuevo Registro</h1>
        </c:if>
        <c:if test="${requestScope.op == 'modificar'}" var="res" scope="request">
            <h1>Modificar Registro</h1> 
        </c:if>
        <form action="Principal" method="post">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="<%= reg.getId()%>" size="2" readonly/></td>
                </tr>

                <tr>
                    <td>NOMBRE</td>
                    <td><input type="text" name="nombre" value="<%= reg.getNombre()%>" /></td>
                </tr>

                <tr>
                    <td>PESO</td>
                    <td><input type="text" name="peso" value="<%= reg.getPeso()%>" /></td>
                </tr>
                <tr>
                    <td>TALLA</td>
                    <td><input type="text" name="talla" value="<%= reg.getTalla()%>"/></td>
                </tr>
                <tr>
                    <td>VACUNA</td>
                    <td>
                        <%
                            if (reg.getVacuna().equals("si")) {
                        %>
                        <input type="radio" name="estado" value="Si" checked>SI<br>
                        <input type="radio" name="estado" value="No">NO
                        <%                        } else {
                        %>
                        <input type="radio" name="estado" value="Si" >SI<br>
                        <input type="radio" name="estado" value="No" checked>NO
                        <%
                            }
                        %>

                    </td>
                </tr>

                <tr>

                    <td><input type="submit" value="Enviar"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>