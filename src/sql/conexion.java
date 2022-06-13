/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class conexion {
    
   Connection conectar = null;
   //guardo en varibles lo que le pasamos al driver para conectarse
   String ruta = "jdbc:mysql://localhost:3306/tiendavideojuegos1";
   String usuario = "root";
   String pass ="";
   
   public Connection conexion(){
       
       try {
           
           conectar=(Connection) DriverManager.getConnection(ruta,usuario,pass);
          
           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "ERROR DE CONEXION" + e.getMessage());
       }
       
       return conectar;
       
   }
   
   public Connection getConnection(){
       
       return conectar;
   }
}
