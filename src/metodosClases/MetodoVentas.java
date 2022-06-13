
package metodosClases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import sql.conexion;
import misclases.Ventas;

import misclases.Clientes;

public class MetodoVentas {
    
    
    //Preparamos la conexion y las sentencias
    
    
     conexion cc = new conexion();
    Connection conM = cc.conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    int estado;
    
    
    public int registrarVenta(Ventas vent){
        
        String sentencia ="insert into ventas(fechaVenta,importe,metodoPago,idJuego,dniCliente,dniEmpleado) values(?,?,?,?,?,?)";
       
        try {
            ps = conM.prepareStatement(sentencia);
            ps.setString(1, vent.getFechaVenta());
            ps.setInt(2, vent.getImporte());
            ps.setString(3, vent.getMetodoPago());
            ps.setString(4, vent.getIdJuego());
            ps.setString(5, vent.getDniCliente());
            ps.setString(6, vent.getDniEmpleado());
            
            ps.execute();
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Esta venta ya esta registrada");
        }
        return estado;
    }
    
    
    public boolean eliminarVenta(int id){
        
        String sentencia ="delete from ventas where idVenta = ?";
        
        try {
            ps = conM.prepareStatement(sentencia);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
              return false;
        }
      
    }
    
    
   
    
    public List mostrarVentas(){
        List<Ventas> listaVentas = new ArrayList<>();
        
        String sentencia = "select * from ventas";
        
        try {
            
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Ventas venta= new Ventas();
                venta.setIdVenta(rs.getInt("idVenta"));
                
                venta.setFechaVenta(rs.getString("fechaVenta"));
                venta.setImporte(rs.getInt("importe"));
                venta.setMetodoPago(rs.getString("metodoPago"));
                venta.setIdJuego(rs.getString("idJuego"));
                venta.setDniCliente(rs.getString("dniCliente"));
                venta.setDniEmpleado(rs.getString("dniEmpleado"));
                
                listaVentas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        
        return listaVentas;
        
    }
     public void empleadoDespegable(JComboBox empleado1){
        
        String sentencia = "Select * from empleados";
        try {
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while(rs.next()){
                empleado1.addItem(rs.getString("dniEmpleado"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
    }
     public void juegoDespegable(JComboBox juego1){
        
        String sentencia = "Select * from juegos";
        try {
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while(rs.next()){
                juego1.addItem(rs.getString("idJuego"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
    }
     
      public void clienteDespegable(JComboBox cliente1){
        
        String sentencia = "Select * from clientes";
        try {
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                
                cliente1.addItem(rs.getString("dniCliente"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
    }
     
    
    
    
    
}
