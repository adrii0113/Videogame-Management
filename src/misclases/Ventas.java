/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misclases;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class Ventas {
     
    //Variables
    private int idVenta;
    private String fechaVenta;
    private int importe;
    private String metodoPago;
    private String idJuego;
    private String dniCliente;
    private String dniEmpleado;
    
    //Constructor sin parametros
    public Ventas() {
    }
    //Constructor con parametros

    public Ventas(int idVenta, String fechaVenta, int importe, String metodoPago, String idJuego, String dniCliente, String dniEmpleado) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.importe = importe;
        this.metodoPago = metodoPago;
        this.idJuego = idJuego;
        this.dniCliente = dniCliente;
        this.dniEmpleado = dniEmpleado;
    }
   
    //Getters y seters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    
    
    
}
