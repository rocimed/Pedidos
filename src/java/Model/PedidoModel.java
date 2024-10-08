/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.sql.Time;


/**
 *
 * @author romar
 */
public class PedidoModel {
    private String platillo;
    private int cantidadPlatillos;
    private String direccion;
    private Double precio;
    private Date fechaPedido;

    public PedidoModel() {
    }

    public PedidoModel(String platillo, int cantidadPlatillos, String direccion, Double precio, Date fechaPedido, Time horaPedido) {
        this.platillo = platillo;
        this.cantidadPlatillos = cantidadPlatillos;
        this.direccion = direccion;
        this.precio = precio;
        this.fechaPedido = fechaPedido;
        this.horaPedido = horaPedido;
    }

    public String getPlatillo() {
        return platillo;
    }

    public void setPlatillo(String platillo) {
        this.platillo = platillo;
    }

    public int getCantidadPlatillos() {
        return cantidadPlatillos;
    }

    public void setCantidadPlatillos(int cantidadPlatillos) {
        this.cantidadPlatillos = cantidadPlatillos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Time getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Time horaPedido) {
        this.horaPedido = horaPedido;
    }
    private Time horaPedido;
    
}
