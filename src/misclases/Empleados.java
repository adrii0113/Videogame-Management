/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misclases;

/**
 *
 * @author Administrador
 */
public class Empleados {
    
    //Variables 
    private int id;
    private String email;
    private String contraseña;
    private String nombreUsuario;
    private String apellidos;
    private String nombre;

    //Constructor sin parametros 
    public Empleados() {
        
    }
    //Constructor con parametros
    public Empleados(int id, String email, String contraseña, String nombreUsuario, String apellidos, String nombre) {
        this.id = id;
        this.email = email;
        this.contraseña = contraseña;
        this.nombreUsuario = nombreUsuario;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }
    
    //Getters y seters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
    
    
    

}
