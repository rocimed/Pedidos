<%-- 
    Document   : mostrar
    Created on : 2/10/2024, 09:30:14 PM
    Author     : romar
--%>
<%@page import="java.sql.*"%>
<%@page import="Config.ConectionBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Pedidos</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                height: 100vh;
            }
            .container {
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 80%;
                max-width: 800px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: center;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .button {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                border: none;
                cursor: pointer;
                transition: background-color 0.3s ease;
                margin-right: 10px;
            }
            .button:hover {
                background-color: #0056b3;
            }
            .button-group {
                display: flex;
                justify-content: flex-end;
                margin-top: 20px;
            }
            .clear-button {
                background-color: #d9534f;
            }
            .clear-button:hover {
                background-color: #c9302c;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Listado de Pedidos</h1>
            <table>
                <tr>
                    <th>Platillo</th>
                    <th>Dirección</th>
                    <th>Cantidad</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                    <th>Precio</th>
                </tr>

                <%
                    // Conexión y consulta a la base de datos para mostrar los pedidos
                    ConectionBD conexion = new ConectionBD();
                    Connection conn = conexion.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos");

                    while (rs.next()) {
                %>
                    <tr>
                        <td><%= rs.getString("platillo") %></td>
                        <td><%= rs.getString("direccion") %></td>
                        <td><%= rs.getInt("cantidadPlatillos") %></td>
                        <td><%= rs.getString("fechaPedido") %></td>
                        <td><%= rs.getString("horaPedido") %></td>
                        <td>$<%= rs.getDouble("precio") %></td>
                    </tr>
                <%
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                %>
            </table>

            <div class="button-group">
                <!-- Botón para generar un nuevo pedido -->
                <form action="${pageContext.request.contextPath}/jsp/registro.jsp">
                    <button type="submit" class="button">Generar Nuevo Pedido</button>
                </form>

                <!-- Botón para limpiar (usa el doGet del confirmarPedidoServlet) -->
                <form method="GET" action="${pageContext.request.contextPath}/confirmarPedidoServlet">
                    <button type="submit" class="button clear-button">Limpiar</button>
                </form>
            </div>
        </div>
    </body>
</html>

