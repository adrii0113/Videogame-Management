/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosClases;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import sql.conexion;
import misclases.Clientes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAW
 */
public class MetodosClientes {
    
     //Preparamos la conexion y las sentencias
    
    conexion cc = new conexion();
    Connection conM = cc.conexion();
 
    
    PreparedStatement ps;
    ResultSet rs;
    
    
    public boolean añadirCliente(Clientes cl){
        
        String sentencia = "insert into clientes(dniCliente,nombre,apellidos,direccion,telefono) values (?,?,?,?,?)";
        
        try {
         
            
            ps = conM.prepareStatement(sentencia);
            
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getApellidos());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getTelefono());
            ps.execute();
            
            return true;
            
            
        } catch (SQLException e) {
            
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Este cliente ya esta registrado");
            return false;
        }
       
    }
    
    
    
    public boolean eliminarCliente(String dni){
        
        String sentencia = "delete from clientes where dniCliente = ?";
        
        try {
           
            ps = conM.prepareStatement(sentencia);
            
            ps.setString(1, dni);
            ps.execute();
            
            return true;
            
            
        } catch (SQLException e) {
            
            System.out.println(e.toString());
            
            return false;
        }
       
        
    }
    
    
    public boolean modificarClientes(Clientes client){
       
        String sentencia = "update clientes set nombre=?, apellidos=?, direccion=?, telefono=? where dniCliente = ?";
        try {
            ps = conM.prepareStatement(sentencia);
            
         
            ps.setString(1, client.getNombre());
            ps.setString(2, client.getApellidos());
            ps.setString(3, client.getDireccion());
            ps.setString(4, client.getTelefono());
            ps.setString(5, client.getDni());
            ps.execute();
           return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    
    public List mostrarClientes(){
        List<Clientes> listaClientes = new ArrayList();
        String sentencia = "select * from clientes";
        try {
           
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Clientes cli = new Clientes();
                cli.setDni(rs.getString("dniCliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellidos(rs.getString("apellidos"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                
                listaClientes.add(cli);
                
            }
        } catch (SQLException e) {
            
            System.out.println(e.toString());
        }
        
        return listaClientes;
        
    }
    
    
    
}
