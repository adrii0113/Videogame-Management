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
public class Juegos {
    
    //Variables
    private String idJuego;
    private String nombre;
    private String productora;
    private String plataforma;
    private String genero;
    private float precio;
    private String proveedor;
    private int stock;
   
    //Constructor
    public Juegos() {
    }
    //Constructoe con parametros

    public Juegos(String idJuego, String nombre, String productora, String plataforma, String genero, float precio, String proveedor, int stock) {
        this.idJuego = idJuego;
        this.nombre = nombre;
        this.productora = productora;
        this.plataforma = plataforma;
        this.genero = genero;
        this.precio = precio;
        this.proveedor = proveedor;
        this.stock = stock;
    }
    

   
    //Getters y seters
    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

   
    
    
    
    
    
}
