<%-- 
    Document   : pedido
    Created on : 2/10/2024, 09:29:52 PM
    Author     : romar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido de Comida</title>
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
            h1 {
                text-align: center;
                color: #333;
            }
            .form-container {
                background-color: #d1faff;
                padding: 50px;
                padding-top: 150px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 400px;
            }
            label {
                font-size: 16px;
                color: #333;
                display: block;
                margin-bottom: 8px;
            }
            input[type="text"], input[type="number"], input[type="date"], input[type="time"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 14px;
            }
            input[type="submit"] {
                width: 100%;
                background-color: #c60df3;
                color: #fff;
                padding: 10px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            input[type="submit"]:hover {
                background-color: #218838;
            }
            .form-group {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h1>Formulario de Pedido de Comida</h1>
            <!-- Se envía el formulario al servlet "guardarPedidoServlet" que procesará el pedido -->
            <form method="POST" action="${pageContext.request.contextPath}/guardarPedidoServlet">
                <div class="form-group">
                    <label>Nombre del Platillo:</label>
                    <input type="text" name="platillo" id="platillo" required />
                </div>
                <div class="form-group">
                    <label>Cantidad de Platillos:</label>
                    <input type="number" name="cantidadPlatillos" id="cantidadPlatillos" min="1" required />
                </div>
                <div class="form-group">
                    <label>Precio total del pedido:</label>
                    <input type="number" step="0.01" name="precio" id="precio" required />
                </div>
                <div class="form-group">
                    <label>Dirección de Entrega del pedido:</label>
                    <input type="text" name="direccion" id="direccion" required />
                </div>
                <div class="form-group">
                    <label>Fecha del Pedido:</label>
                    <input type="date" name="fechaPedido" id="fechaPedido" required />
                </div>
                <div class="form-group">
                    <label>Hora del Pedido:</label>
                    <input type="time" name="horaPedido" id="horaPedido" required />
                </div>
                <div class="form-group">
                    <input type="submit" value="Realizar Pedido" />
                </div>
            </form>
        </div>
    </body>
</html>
