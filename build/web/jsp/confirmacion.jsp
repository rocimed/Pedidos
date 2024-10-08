<%-- 
    Document   : confirmacion
    Created on : 2/10/2024, 09:30:43 PM
    Author     : romar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="Model.PedidoModel" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Pedido</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .container {
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 400px;
            }
            h1 {
                text-align: center;
                color: #333;
            }
            p {
                font-size: 16px;
                color: #555;
            }
            .button-group {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            .button {
                padding: 10px 20px;
                background-color: #c60df3;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                border: none;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .button:hover {
                background-color: #218838;
            }
            .cancel {
                background-color: #d9534f;
            }
            .cancel:hover {
                background-color: #c9302c;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Confirmar Pedido</h1>

<%
    // Obtener las cookies
    Cookie[] cookies = request.getCookies();
    String pedidoJson = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pedido")) {
                pedidoJson = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
                break;
            }
        }
    }

    Gson gson = new Gson();
    PedidoModel pedido = gson.fromJson(pedidoJson, PedidoModel.class);
%>

<p><strong>Platillo:</strong> <%= pedido.getPlatillo() %></p>
<p><strong>Cantidad:</strong> <%= pedido.getCantidadPlatillos() %></p>
<p><strong>Dirección:</strong> <%= pedido.getDireccion() %></p>
<p><strong>Fecha del Pedido:</strong> <%= pedido.getFechaPedido() %></p>
<p><strong>Hora del Pedido:</strong> <%= pedido.getHoraPedido() %></p>
<p><strong>Precio Total:</strong> $<%= pedido.getPrecio() %></p>


            <div class="button-group">
                <!-- Botón para confirmar el pedido (usa el doPost) -->
                <form method="POST" action="${pageContext.request.contextPath}/confirmarPedidoServlet">
                    <button type="submit" class="button">Confirmar Pedido</button>
                </form>

                <!-- Botón para cancelar (redirige a registro.jsp) -->
                <form method="GET" action="${pageContext.request.contextPath}/registro.jsp">
                    <button type="submit" class="button cancel">Cancelar</button>
                </form>
            </div>
        </div>
    </body>
</html>
