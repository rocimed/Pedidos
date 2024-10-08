/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.PedidoModel;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author romar
 */
@WebServlet(name = "guardarPedidoServlet", urlPatterns = {"/guardarPedidoServlet"})
public class guardarPedidoServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
        try {
            request.setCharacterEncoding("UTF-8");
            // Obtener datos del formulario
            String platillo = request.getParameter("platillo");
            String direccion = request.getParameter("direccion");
            String cantidadPlatillos = request.getParameter("cantidadPlatillos");
            String fecha = request.getParameter("fechaPedido");
            String horaPedido = request.getParameter("horaPedido") + ":00";  // Agregar segundos ":00"
            String precio = request.getParameter("precio");
            
            // Convertir la fecha a "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaFormateada = sdf.parse(fecha); // Convertir la fecha del string al formato correcto
            
            // Convertir la hora a Time
            Time hora = Time.valueOf(horaPedido);  // Esta conversión ahora será válida
            
            // Crear un objeto PedidoModel
            PedidoModel pedido = new PedidoModel(
                    platillo,
                    Integer.parseInt(cantidadPlatillos),
                    direccion,
                    Double.parseDouble(precio),
                    fechaFormateada,  // Usar la fecha formateada correctamente
                    hora  // Usar la hora convertida
            );
            
            // Convertir el objeto Pedido a JSON usando Gson
            Gson gson = new Gson();
            String pedidoJson = gson.toJson(pedido);
            
            // Codificar el JSON para almacenarlo en la cookie
            String pedidoData = URLEncoder.encode(pedidoJson, StandardCharsets.UTF_8.toString());
            
            // Crear una cookie que guarde los datos en formato JSON
            Cookie pedidoCookie = new Cookie("pedido", pedidoData);
            
            // Definir la vida útil de la cookie (5 minutos)
            pedidoCookie.setMaxAge(60 * 5);  // 5 minutos
            pedidoCookie.setPath("/");
            
            // Añadir la cookie a la respuesta
            response.addCookie(pedidoCookie);
            
            // Redirigir a la página de confirmación del pedido
            response.sendRedirect(request.getContextPath() + "/jsp/confirmacion.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(guardarPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para guardar datos del pedido en una cookie";
    }// </editor-fold>

}
