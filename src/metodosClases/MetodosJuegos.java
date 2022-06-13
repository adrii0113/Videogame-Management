
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
import misclases.Juegos;


public class MetodosJuegos {
    //Preparamos la conexion y las sentencias
    
  
     conexion cc = new conexion();
    Connection conM = cc.conexion();
    
    Connection con;
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean añadirJuego(Juegos jueg){
        
      
      String sentencia1 = "insert into juegos(idJuego,nombre,productora,Plataforma,tipo,precio,id_proveedor,stock) values(?,?,?,?,?,?,?,?)";
      
        try {
            ps = conM.prepareStatement(sentencia1);
            
            ps.setString(1, jueg.getIdJuego());
            ps.setString(2, jueg.getNombre());
            ps.setString(3, jueg.getProductora());
            ps.setString(4, jueg.getPlataforma());
            ps.setString(5, jueg.getGenero());
            ps.setFloat(6, (float) jueg.getPrecio());
            ps.setString(7, jueg.getProveedor());
            ps.setInt(8, jueg.getStock());
            
            ps.execute();
            
            return true;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Este producto ya esta en el catalogo");
            
            return false;
        }
        
          
    }
    
    
    public boolean eliminarJuego(String id){
        
        String sentencia = "delete from juegos where idJuego = ?";
        
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
    
    public boolean actualizarJuegos(Juegos jueg){
        
        String sentencia = "update juegos set nombre=?, productora=?, Plataforma=?, Tipo=?, precio=?, id_proveedor=?, stock=? where idJuego=?";
        
        try {
            ps = conM.prepareStatement(sentencia);
           
            ps.setString(1, jueg.getNombre());
            ps.setString(2, jueg.getProductora());
            ps.setString(3, jueg.getPlataforma());
            ps.setString(4, jueg.getGenero());
            ps.setFloat(5, jueg.getPrecio());
            ps.setString(6, jueg.getProveedor());
            ps.setInt(7, jueg.getStock());
             ps.setString(8, jueg.getIdJuego());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            
            System.out.println(e.toString());
            return false;
        }
    }
    
    
    public List mostrarJuegos(){
        List<Juegos> listaJuegos = new ArrayList<>();
        
        String sentencia = "select * from juegos";
        
        try {
            //con = cn.getConnection();
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while (rs.next()){
                
                Juegos jue = new Juegos();
                jue.setIdJuego(rs.getString("idJuego"));
                jue.setNombre(rs.getString("nombre"));
                jue.setProductora(rs.getString("productora"));
                jue.setPlataforma(rs.getString("Plataforma"));
                jue.setGenero(rs.getString("Tipo"));
                jue.setPrecio(rs.getFloat("precio"));
                jue.setProveedor(rs.getString("id_proveedor"));
                jue.setStock(rs.getInt("stock"));
                
                listaJuegos.add(jue);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        
        return listaJuegos;
        
    }
    
    public void proveedorDespegable(JComboBox proveedor1){
        
        String sentencia = "Select id_proveedor from proveedores";
        try {
            ps = conM.prepareStatement(sentencia);
            rs = ps.executeQuery();
            
            while(rs.next()){
                proveedor1.addItem(rs.getString("id_proveedor"));
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
    }
    
    
    public Juegos buscarJuego(String id){
        
        Juegos juego = new Juegos();
        String sentencia = "select * from juegos where idJuego =?";
        try {
            ps=conM.prepareStatement(sentencia);
            
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                
                juego.setNombre(rs.getString("nombre"));
                juego.setNombre(rs.getString("productora"));
                juego.setNombre(rs.getString("Plataforma"));
                juego.setNombre(rs.getString("tipo"));
                juego.setNombre(rs.getString("precio"));
           
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return juego;
    }
   
    
    
            
}
