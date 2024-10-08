/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Config.ConectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import com.google.gson.Gson;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import Model.PedidoModel;


/**
 *
 * @author romar
 */
@WebServlet(name = "confirmarPedidoServlet", urlPatterns = {"/confirmarPedidoServlet"})
public class confirmarPedidoServlet extends HttpServlet {

    Connection conn;
    PreparedStatement ps;

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet confirmarPedidoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet confirmarPedidoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("pedido".equals(cookie.getName())) {
                    cookie.setMaxAge(0); // Esto borra la cookie
                    cookie.setPath("/"); // Asegúrate de que el path coincida
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        // Redirigir o enviar respuesta después de eliminar la cookie
        response.sendRedirect(request.getContextPath() + "/jsp/registro.jsp");

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
        
        request.setCharacterEncoding("UTF-8");
        
 Cookie[] cookies = request.getCookies();
        String pedidoJson = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("pedido".equals(cookie.getName())) {
                    pedidoJson = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8.toString());
                    break;
                }
            }
        }

        // Deserializar el JSON de la cookie a un objeto PedidoModel3
        Gson gson = new Gson();
        PedidoModel pedido = gson.fromJson(pedidoJson, PedidoModel.class);

        // Guardar en la base de datos
        try {
            ConectionBD conexion = new ConectionBD();
            Connection conn = conexion.getConnection();

            String sql = "INSERT INTO pedidos (platillo, direccion, cantidadPlatillos, fechaPedido, horaPedido, precio) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, pedido.getPlatillo());
            ps.setString(2, pedido.getDireccion());
            ps.setInt(3, pedido.getCantidadPlatillos());
            ps.setDate(4, new java.sql.Date(pedido.getFechaPedido().getTime()));
            ps.setTime(5, pedido.getHoraPedido());
            ps.setDouble(6, pedido.getPrecio());

            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas > 0) {
                // Redirigir a la página mostrar.jsp
                response.sendRedirect(request.getContextPath() + "/jsp/mostrar.jsp");
            } else {
                response.getWriter().println("Error al guardar el pedido.");
            }

            ps.close();
            conn.close();
        } catch (SQLException e) {
            response.getWriter().println("Error al guardar el pedido en la base de datos: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para confirmar y almacenar los datos del pedido, luego eliminar la cookie";
    }// </editor-fold>

}
