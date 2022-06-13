
package metodosClases;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sql.conexion;
import misclases.Proveedores;


public class MetodosProveedores {
    
    //Preparamos la conexion y las sentencias
   
     conexion cc = new conexion();
    Connection conM = cc.conexion();
   
    
   
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean AñadirProveedor(Proveedores pro){
        
        String sentencia = "insert into proveedores(id_proveedor,nombre,telefono,direccion,email) values (?,?,?,?,?)";
        
        try {
        
            ps = conM.prepareStatement(sentencia);
            
            ps.setString(1, pro.getId());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getTelefono());
            ps.setString(4, pro.getDireccion());
            ps.setString(5, pro.getEmail());
            
            ps.execute();
            
            return  true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Este proveedor ya esta registrado");
            return false;
        }
        
        
    }
    
    
    public boolean eliminarProveedor(String id){
        
        String sentencia = "delete from proveedores where id_proveedor = ?";
        try {
           
            ps = conM.prepareStatement(sentencia);
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
       
    }
    
    public boolean modificarProveedores(Proveedores pro){
        
        String sentencia ="update proveedores set nombre=?, telefono=?, direccion=?, email=? where id_proveedor=?";
        
        
        try {
            
            ps = conM.prepareStatement(sentencia);
            
          
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getTelefono());
            ps.setString(3, pro.getDireccion());
            ps.setString(4, pro.getEmail());
            ps.setString(5, pro.getId());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    public ArrayList<Proveedores> MostrarProveedores(){
        
        ArrayList<Proveedores> listaProveedores = new ArrayList<>();
        String sentencia = "select * from proveedores";
        
        try {
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                Proveedores pro = new Proveedores();
                pro.setId(rs.getString("id_proveedor"));
                pro.setNombre(rs.getString("nombre"));
                pro.setTelefono(rs.getString("telefono"));
                pro.setDireccion(rs.getString("direccion"));
                pro.setEmail(rs.getString("email"));
                
                listaProveedores.add(pro);
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        return listaProveedores;
        
    }
    
}
