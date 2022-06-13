
package Vistas;
import InformeExcel.Excel;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import misclases.Proveedores;
import metodosClases.MetodosProveedores;
import metodosClases.*;
import java.util.List;
import misclases.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.glass.events.KeyEvent;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextField;


public class menuPrincipal extends javax.swing.JFrame {
    //Instanciamos la clase cliente y sus metodoss
    Clientes cli = new Clientes();
    MetodosClientes añadir = new MetodosClientes();
    DefaultTableModel tabla = new DefaultTableModel();
    
    
    //Instancio las clases que necsito
    Proveedores pro = new Proveedores();
    MetodosProveedores mPro = new MetodosProveedores();
    
    //Clases y metodos de los Juegos
    Juegos jueg = new Juegos();
    MetodosJuegos metJueg = new MetodosJuegos();
    
    //Clases y metodos de las Ventas
    Ventas vent = new Ventas();
    MetodoVentas metVent = new MetodoVentas();
    
    //Clase Informacion
    
    Informacion info = new Informacion();
    //Metodos listar datos en las tablas
    
    //Clientes
    
    public void mostrarClientes(){
        
        List<Clientes> ListaClientes = añadir.mostrarClientes();
        tabla = (DefaultTableModel) tablaClientes.getModel();
        Object[] obj = new Object[5];
        
        for (int i = 0; i < ListaClientes.size(); i++) {
            obj[0] = ListaClientes.get(i).getDni();
            obj[1] = ListaClientes.get(i).getNombre();
            obj[2] = ListaClientes.get(i).getApellidos();
            obj[3] = ListaClientes.get(i).getDireccion();
            obj[4] = ListaClientes.get(i).getTelefono();
            
            tabla.addRow(obj);  
        }
        tablaClientes.setModel(tabla);
    }
    
    //Proveedores
    public void mostrarProveedores(){
        
        List<Proveedores> ListaProveedores = mPro.MostrarProveedores();
        tabla = (DefaultTableModel) tablaProveedores.getModel();
        Object[] obj = new Object[5];
        
        for (int i = 0; i < ListaProveedores.size(); i++) {
            
            obj[0] = ListaProveedores.get(i).getId();
            obj[1] = ListaProveedores.get(i).getNombre();
            obj[2] = ListaProveedores.get(i).getTelefono();
            obj[3] = ListaProveedores.get(i).getDireccion();
            obj[4] = ListaProveedores.get(i).getEmail();
            
            tabla.addRow(obj);
        }
        tablaProveedores.setModel(tabla);
    }
    
    //Juegos
    
    public void mostrarJuegos(){
        
        List<Juegos> ListaJuegos = metJueg.mostrarJuegos();
        tabla = (DefaultTableModel) tablaJuegos.getModel();
         Object[] obj = new Object[8];
         
         
         for (int i = 0; i < ListaJuegos.size(); i++) {
            
            obj[0] = ListaJuegos.get(i).getIdJuego();
            obj[1] = ListaJuegos.get(i).getNombre();
            obj[2] = ListaJuegos.get(i).getProductora();
            obj[3] = ListaJuegos.get(i).getPlataforma();
            obj[4] = ListaJuegos.get(i).getGenero();
            obj[5] = ListaJuegos.get(i).getProveedor();
            obj[6] = ListaJuegos.get(i).getPrecio();
            obj[7] = ListaJuegos.get(i).getStock();
            
            
            tabla.addRow(obj);
        }
         
         tablaJuegos.setModel(tabla);
    }
     public void mostrarVentas(){
        
        List<Ventas> ListaVentas = metVent.mostrarVentas();
        tabla = (DefaultTableModel) tablaVentasCompleta.getModel();
         Object[] obj = new Object[7];
         
         
         for (int i = 0; i < ListaVentas.size(); i++) {
            
            obj[0] = ListaVentas.get(i).getIdVenta();
            
            obj[1] = ListaVentas.get(i).getFechaVenta();
            obj[2] = ListaVentas.get(i).getImporte();
            obj[3] = ListaVentas.get(i).getMetodoPago();
            obj[4] = ListaVentas.get(i).getIdJuego();
            obj[5] = ListaVentas.get(i).getDniCliente();
            obj[6] = ListaVentas.get(i).getDniEmpleado();
            tabla.addRow(obj);
        }
         
         tablaVentasCompleta.setModel(tabla);
    }
    //main
    public menuPrincipal() {
        initComponents();
        setLocationRelativeTo(this);
        this.botonDesplegable11.setSelected(true);
        this.setResizable(true);
         
        
        metJueg.proveedorDespegable(proveedorJuego);
        metVent.empleadoDespegable(desplegableEmpleadoVenta);
        txtFecha.setText(fecha());
       //importe.setText();
        metVent.juegoDespegable(desplegableJuegoVenta);
        
       //metVent.clienteDespegable(desplegableClientes);
        
        //Mostramos el catalogo de Juegos como bienvenida cada vez que el usuario inicie sesion
        limpiarTabla();
        mostrarJuegos();
        gestionMenu.setSelectedIndex(1);
    }
    
    //Generar factura en formato pdf
    private void generarPdf(){
        
        try {
            FileOutputStream archivo;
            File file = new File("src/PDF/venta.pdf");
            archivo = new FileOutputStream(file);
            Document docu = new Document();
            PdfWriter.getInstance(docu, archivo); 
            docu.open();
            Image imge = Image.getInstance("src/imagees/box.png");
            
            Paragraph fecha = new Paragraph();
            Font fuente = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date fechaSistema = new Date();
            fecha.add("Fecha:"+  new SimpleDateFormat("dd-MM-yyyy").format(fechaSistema)+"\n\n");
            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            Encabezado.addCell(imge);
            
            String identificacion = idTiendaInfo.getText();
            String nombre = nombreTienda.getText();
            String direccion = direccionTienda.getText();
            String telefono = telefonoTienda.getText();
            Encabezado.addCell("");
            Encabezado.addCell("Identificacion: " + identificacion + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nDireccion: " + direccion );
            Encabezado.addCell(fecha);
            docu.add(Encabezado);
        
            
            Paragraph cli = new Paragraph();
            cli.add(Chunk.NEWLINE);
            cli.add("Datos de la venta" + "\n\n");
            docu.add(cli);
            
            PdfPTable tablacli = new PdfPTable(6);
            tablacli.setWidthPercentage(100);
            tablacli.getDefaultCell().setBorder(0);
            float[] Columnacli = new float[]{20f, 50f, 30f, 40f, 60f, 30f};
            tablacli.setWidths(Columnacli);
            tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            PdfPCell cl1 = new PdfPCell(new Phrase("Identificacion", fuente));
            PdfPCell cl2 = new PdfPCell(new Phrase("Fecha", fuente));
            PdfPCell cl3 = new PdfPCell(new Phrase("Importe", fuente));
            PdfPCell cl4 = new PdfPCell(new Phrase("Metodo pago", fuente));
             PdfPCell cl5 = new PdfPCell(new Phrase("Juego", fuente));
              PdfPCell cl6 = new PdfPCell(new Phrase("Cliente", fuente));
               PdfPCell cl7 = new PdfPCell(new Phrase("Empleado", fuente));
            
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
             cl5.setBorder(0);
              cl6.setBorder(0);
               cl7.setBorder(0);
            tablacli.addCell(cl1);
            tablacli.addCell(cl2);
            tablacli.addCell(cl3);
            tablacli.addCell(cl4);
             tablacli.addCell(cl5);
              tablacli.addCell(cl6);
               tablacli.addCell(cl7);
            
            
         
            PdfPTable tablapro = new PdfPTable(7);
            tablapro.setWidthPercentage(100);
            tablapro.getDefaultCell().setBorder(0);
            float[] Columnapro = new float[]{10f, 50f, 15f, 20f, 30f, 25f, 35f};
            tablapro.setWidths(Columnapro);
            tablapro.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell pro1 = new PdfPCell(new Phrase("ID", fuente));
            PdfPCell pro2 = new PdfPCell(new Phrase("Fecha", fuente));
            PdfPCell pro3 = new PdfPCell(new Phrase("Importe", fuente));
            PdfPCell pro4 = new PdfPCell(new Phrase("Metodo", fuente));
            PdfPCell pro5 = new PdfPCell(new Phrase("Juego", fuente));
            PdfPCell pro6 = new PdfPCell(new Phrase("Cliente", fuente));
            PdfPCell pro7 = new PdfPCell(new Phrase("Empleado", fuente));
            
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            pro5.setBorder(0);
            pro6.setBorder(0);
            pro7.setBorder(0);
            
            pro1.setBackgroundColor(BaseColor.DARK_GRAY);
            pro2.setBackgroundColor(BaseColor.DARK_GRAY);
            pro3.setBackgroundColor(BaseColor.DARK_GRAY);
            pro4.setBackgroundColor(BaseColor.DARK_GRAY);
            pro5.setBackgroundColor(BaseColor.DARK_GRAY);
            pro6.setBackgroundColor(BaseColor.DARK_GRAY);
            pro7.setBackgroundColor(BaseColor.DARK_GRAY);
            
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            tablapro.addCell(pro4);
            tablapro.addCell(pro5);
            tablapro.addCell(pro6);
            tablapro.addCell(pro7);
            
            for (int i = 0; i < tablaVentasCompleta.getRowCount(); i++) {
                String producto = tablaVentasCompleta.getValueAt(i, 0).toString();
                String fechaVenta = tablaVentasCompleta.getValueAt(i, 1).toString();
                String importe = tablaVentasCompleta.getValueAt(i, 2).toString();
                String metodo = tablaVentasCompleta.getValueAt(i, 3).toString();
                String juego = tablaVentasCompleta.getValueAt(i, 4).toString();
                String cliente = tablaVentasCompleta.getValueAt(i, 5).toString();
                String empleado = tablaVentasCompleta.getValueAt(i, 6).toString();
                
                tablapro.addCell(producto);
                tablapro.addCell(fechaVenta);
                tablapro.addCell(importe);
                tablapro.addCell(metodo);
                tablapro.addCell(juego);
                tablapro.addCell(cliente);
                tablapro.addCell(empleado);
            }
            docu.add(tablapro);

          
            docu.close();
            archivo.close();
            Desktop.getDesktop().open(file);
            
      
            
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }
    
    //Metodos para limpiar la tabla de datos.
    public void limpiarTabla(){
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tabla.removeRow(i);
            i = i-1;
        }
    }
    
    //Este metodo sirve para limpiar la tabla Clientes especificamente, esto se hace para que si insertamos un nuevo cliente durante la ejecucion del programa, los campos seleecionables del apartado de Ventas no se actulizaran.
      public void limpiarTablaClientes(JTable tabla){
        try {
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error al limpiar la tabla","Error",JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    //Meotodo limpiar los jtext filed del apartado Clientes 
    private void limpiarDatosClientes(){
        
        //Utilizo los metodos set de la clase Clientes para asignarsle una cadena vacia como texto
        dniCliente.setText("");
        nombreCliente.setText("");
        apellidosCliente.setText("");
        direccionCliente.setText("");
        telefonoCliente.setText("");
    }
    
     //Meotodo limpiar los jtext filed del apartado Proveedores
    private void limpiarDatosProveedores(){
        //Utilizo los metodos set de la clase Clientes para asignarsle una cadena vacia como texto
        idProveedor.setText("");
        nombreProveedor.setText("");
        telefonoProveedor.setText("");
        direccionProveedor.setText("");
        emailProveedor.setText("");
    }
    
    //Meotodo limpiar los jtext filed del apartado Juegos
    private void limpiarDatosJuegos(){
        //Utilizo los metodos set de la clase Juegos para asignarsle una cadena vacia como texto
        idjuego.setText("");
        nombreJuego.setText("");
        desarrolladoraJuego.setText("");
        plataforma.setText("");
        genero.setText("");
        proveedorJuego.setSelectedItem("");
        precioJuego.setText("");
        stockJuegos.setText("");
    }
    
    //Meotodo limpiar los jtext filed del apartado Ventas
    private void limpiarDatosVentas(){
       
        
        IDVENTA.setText("");
        
        metodoPago.setSelectedItem("");
        desplegableJuegoVenta.setSelectedItem("");
        desplegableClientes.setSelectedItem("");
        desplegableEmpleadoVenta.setSelectedItem("");

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        menuOpciones = new javax.swing.JPanel();
        btnSalir = new rsbuttom.RSButtonMetro();
        btnTablaClientes = new rsbuttom.RSButtonMetro();
        botonDesplegable11 = new rsbuttom.RSButtonMetro();
        btnVentas = new rsbuttom.RSButtonMetro();
        btnConfiguracion = new rsbuttom.RSButtonMetro();
        btnCatalogo = new rsbuttom.RSButtonMetro();
        txtcorreo = new javax.swing.JTextField();
        gestionMenu = new javax.swing.JTabbedPane();
        panelProveedores = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        idProveedor = new javax.swing.JTextField();
        nombreProveedor = new javax.swing.JTextField();
        telefonoProveedor = new javax.swing.JTextField();
        direccionProveedor = new javax.swing.JTextField();
        emailProveedor = new javax.swing.JTextField();
        añadirProveedor = new rsbuttom.RSButtonMetro();
        eliminarProveedor = new rsbuttom.RSButtonMetro();
        actualizarProveedor = new rsbuttom.RSButtonMetro();
        limpiarDatos = new rsbuttom.RSButtonMetro();
        panelCatalogo = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idjuego = new javax.swing.JTextField();
        nombreJuego = new javax.swing.JTextField();
        desarrolladoraJuego = new javax.swing.JTextField();
        plataforma = new javax.swing.JTextField();
        genero = new javax.swing.JTextField();
        proveedorJuego = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaJuegos = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        precioJuego = new javax.swing.JTextField();
        añadirJuego = new rsbuttom.RSButtonMetro();
        eliminarJuego = new rsbuttom.RSButtonMetro();
        actualizarJuego = new rsbuttom.RSButtonMetro();
        limpiarDatos1 = new rsbuttom.RSButtonMetro();
        generarExcel = new rsbuttom.RSButtonMetro();
        stockJuegos = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        panelClientes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dniCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        añadirClientes = new rsbuttom.RSButtonMetro();
        eliminarClientes = new rsbuttom.RSButtonMetro();
        actualizarClientes = new rsbuttom.RSButtonMetro();
        actualizarClientes1 = new rsbuttom.RSButtonMetro();
        jLabel10 = new javax.swing.JLabel();
        nombreCliente = new javax.swing.JTextField();
        apellidosCliente = new javax.swing.JTextField();
        direccionCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        panelNuevaVenta = new javax.swing.JPanel();
        IDVENTA = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaVentasCompleta = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        idVenta = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        btnEliminarVentas = new rsbuttom.RSButtonMetro();
        btnAñadirVenta = new rsbuttom.RSButtonMetro();
        limpiarDatosVentas = new rsbuttom.RSButtonMetro();
        jLabel41 = new javax.swing.JLabel();
        desplegableEmpleadoVenta = new javax.swing.JComboBox<>();
        nuevoClienteVenta = new javax.swing.JButton();
        desplegableJuegoVenta = new javax.swing.JComboBox<>();
        desplegableClientes = new javax.swing.JComboBox<>();
        contadorCantidad = new javax.swing.JSpinner();
        btnGenerarPdf = new rsbuttom.RSButtonMetro();
        metodoPago = new javax.swing.JComboBox<>();
        importe = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        panelInformacion = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        nombreTienda = new javax.swing.JTextField();
        idTiendaInfo = new javax.swing.JTextField();
        direccionTienda = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        telefonoTienda = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1497, 807));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(218, 90, 81));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(16, 36, 61));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SISTEMA DE GESTION");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 80));

        menuOpciones.setBackground(new java.awt.Color(16, 36, 61));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/salida.png"))); // NOI18N
        btnSalir.setText("CERRAR SESION");
        btnSalir.setColorHover(new java.awt.Color(16, 36, 61));
        btnSalir.setColorNormal(new java.awt.Color(16, 36, 61));
        btnSalir.setColorPressed(new java.awt.Color(16, 36, 61));
        btnSalir.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnTablaClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/objetivo.png"))); // NOI18N
        btnTablaClientes.setText("GESTION CLIENTES");
        btnTablaClientes.setColorHover(new java.awt.Color(16, 36, 61));
        btnTablaClientes.setColorNormal(new java.awt.Color(16, 36, 61));
        btnTablaClientes.setColorPressed(new java.awt.Color(16, 36, 61));
        btnTablaClientes.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnTablaClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTablaClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaClientesActionPerformed(evt);
            }
        });

        botonDesplegable11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/inventario.png"))); // NOI18N
        botonDesplegable11.setText("PROVEEDORES");
        botonDesplegable11.setColorHover(new java.awt.Color(16, 36, 61));
        botonDesplegable11.setColorNormal(new java.awt.Color(16, 36, 61));
        botonDesplegable11.setColorPressed(new java.awt.Color(16, 36, 61));
        botonDesplegable11.setColorTextNormal(new java.awt.Color(218, 90, 81));
        botonDesplegable11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonDesplegable11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDesplegable11ActionPerformed(evt);
            }
        });

        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/ventas.png"))); // NOI18N
        btnVentas.setText("GESTION VENTAS");
        btnVentas.setColorHover(new java.awt.Color(16, 36, 61));
        btnVentas.setColorNormal(new java.awt.Color(16, 36, 61));
        btnVentas.setColorPressed(new java.awt.Color(16, 36, 61));
        btnVentas.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/settings_48px.png"))); // NOI18N
        btnConfiguracion.setText("INFORMACION");
        btnConfiguracion.setColorHover(new java.awt.Color(16, 36, 61));
        btnConfiguracion.setColorNormal(new java.awt.Color(16, 36, 61));
        btnConfiguracion.setColorPressed(new java.awt.Color(16, 36, 61));
        btnConfiguracion.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnConfiguracion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        btnCatalogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/video-game.png"))); // NOI18N
        btnCatalogo.setText("GESTION CATALOGO");
        btnCatalogo.setColorHover(new java.awt.Color(16, 36, 61));
        btnCatalogo.setColorNormal(new java.awt.Color(16, 36, 61));
        btnCatalogo.setColorPressed(new java.awt.Color(16, 36, 61));
        btnCatalogo.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnCatalogo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuOpcionesLayout = new javax.swing.GroupLayout(menuOpciones);
        menuOpciones.setLayout(menuOpcionesLayout);
        menuOpcionesLayout.setHorizontalGroup(
            menuOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonDesplegable11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTablaClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCatalogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addComponent(btnConfiguracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        menuOpcionesLayout.setVerticalGroup(
            menuOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonDesplegable11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTablaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        getContentPane().add(menuOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 750));

        txtcorreo.setBackground(new java.awt.Color(33, 45, 62));
        txtcorreo.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        txtcorreo.setForeground(new java.awt.Color(218, 90, 81));
        txtcorreo.setBorder(null);
        txtcorreo.setCaretColor(new java.awt.Color(218, 90, 81));
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });
        getContentPane().add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelProveedores.setBackground(new java.awt.Color(16, 36, 61));
        panelProveedores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(218, 90, 81));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Gestión de proveedores");
        panelProveedores.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 1255, 66));

        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_proveedor", "Nombre", "Telefono", "Direccion", "Email"
            }
        ));
        tablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProveedores);

        panelProveedores.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 124, 911, -1));

        jLabel11.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(218, 90, 81));
        jLabel11.setText("ID Proveedor");
        panelProveedores.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 144, -1, -1));

        jLabel12.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(218, 90, 81));
        jLabel12.setText("Nombre");
        panelProveedores.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 174, 105, -1));

        jLabel13.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(218, 90, 81));
        jLabel13.setText("Telefono");
        panelProveedores.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 204, 105, -1));

        jLabel14.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(218, 90, 81));
        jLabel14.setText("Dirección");
        panelProveedores.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 234, 105, -1));

        jLabel15.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(218, 90, 81));
        jLabel15.setText("Email");
        panelProveedores.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 264, 105, -1));

        idProveedor.setBackground(new java.awt.Color(33, 45, 62));
        idProveedor.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        idProveedor.setForeground(new java.awt.Color(218, 90, 81));
        idProveedor.setBorder(null);
        idProveedor.setCaretColor(new java.awt.Color(218, 90, 81));
        panelProveedores.add(idProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 144, 147, -1));

        nombreProveedor.setBackground(new java.awt.Color(33, 45, 62));
        nombreProveedor.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        nombreProveedor.setForeground(new java.awt.Color(218, 90, 81));
        nombreProveedor.setBorder(null);
        nombreProveedor.setCaretColor(new java.awt.Color(218, 90, 81));
        panelProveedores.add(nombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 174, 147, -1));

        telefonoProveedor.setBackground(new java.awt.Color(33, 45, 62));
        telefonoProveedor.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        telefonoProveedor.setForeground(new java.awt.Color(218, 90, 81));
        telefonoProveedor.setBorder(null);
        telefonoProveedor.setCaretColor(new java.awt.Color(218, 90, 81));
        panelProveedores.add(telefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 204, 147, -1));

        direccionProveedor.setBackground(new java.awt.Color(33, 45, 62));
        direccionProveedor.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        direccionProveedor.setForeground(new java.awt.Color(218, 90, 81));
        direccionProveedor.setBorder(null);
        direccionProveedor.setCaretColor(new java.awt.Color(218, 90, 81));
        panelProveedores.add(direccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 234, 147, -1));

        emailProveedor.setBackground(new java.awt.Color(33, 45, 62));
        emailProveedor.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        emailProveedor.setForeground(new java.awt.Color(218, 90, 81));
        emailProveedor.setBorder(null);
        emailProveedor.setCaretColor(new java.awt.Color(218, 90, 81));
        panelProveedores.add(emailProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 264, 147, -1));

        añadirProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/supplier.png"))); // NOI18N
        añadirProveedor.setText("Añadir proveedor");
        añadirProveedor.setColorHover(new java.awt.Color(16, 36, 61));
        añadirProveedor.setColorNormal(new java.awt.Color(16, 36, 61));
        añadirProveedor.setColorPressed(new java.awt.Color(16, 36, 61));
        añadirProveedor.setColorTextNormal(new java.awt.Color(218, 90, 81));
        añadirProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        añadirProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirProveedorActionPerformed(evt);
            }
        });
        panelProveedores.add(añadirProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(223, 636, 181, 72));

        eliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/delete.png"))); // NOI18N
        eliminarProveedor.setText("Eliminar proveedor");
        eliminarProveedor.setColorHover(new java.awt.Color(16, 36, 61));
        eliminarProveedor.setColorNormal(new java.awt.Color(16, 36, 61));
        eliminarProveedor.setColorPressed(new java.awt.Color(16, 36, 61));
        eliminarProveedor.setColorTextNormal(new java.awt.Color(218, 90, 81));
        eliminarProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProveedorActionPerformed(evt);
            }
        });
        panelProveedores.add(eliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 636, 190, 72));

        actualizarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/browser.png"))); // NOI18N
        actualizarProveedor.setText("Actualizar proveedor");
        actualizarProveedor.setColorHover(new java.awt.Color(16, 36, 61));
        actualizarProveedor.setColorNormal(new java.awt.Color(16, 36, 61));
        actualizarProveedor.setColorPressed(new java.awt.Color(16, 36, 61));
        actualizarProveedor.setColorTextNormal(new java.awt.Color(218, 90, 81));
        actualizarProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarProveedorActionPerformed(evt);
            }
        });
        panelProveedores.add(actualizarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(689, 636, 204, 72));

        limpiarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/computer.png"))); // NOI18N
        limpiarDatos.setText("Limpiar datos");
        limpiarDatos.setColorHover(new java.awt.Color(16, 36, 61));
        limpiarDatos.setColorNormal(new java.awt.Color(16, 36, 61));
        limpiarDatos.setColorPressed(new java.awt.Color(16, 36, 61));
        limpiarDatos.setColorTextNormal(new java.awt.Color(218, 90, 81));
        limpiarDatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limpiarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarDatosActionPerformed(evt);
            }
        });
        panelProveedores.add(limpiarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 636, 204, 72));

        gestionMenu.addTab("Proveedores", panelProveedores);

        panelCatalogo.setBackground(new java.awt.Color(16, 36, 61));

        jPanel10.setBackground(new java.awt.Color(16, 36, 61));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(218, 90, 81));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestión del catálogo");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1285, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addContainerGap())
        );

        idjuego.setBackground(new java.awt.Color(33, 45, 62));
        idjuego.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        idjuego.setForeground(new java.awt.Color(218, 90, 81));
        idjuego.setBorder(null);
        idjuego.setCaretColor(new java.awt.Color(218, 90, 81));

        nombreJuego.setBackground(new java.awt.Color(33, 45, 62));
        nombreJuego.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        nombreJuego.setForeground(new java.awt.Color(218, 90, 81));
        nombreJuego.setBorder(null);
        nombreJuego.setCaretColor(new java.awt.Color(218, 90, 81));

        desarrolladoraJuego.setBackground(new java.awt.Color(33, 45, 62));
        desarrolladoraJuego.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        desarrolladoraJuego.setForeground(new java.awt.Color(218, 90, 81));
        desarrolladoraJuego.setBorder(null);
        desarrolladoraJuego.setCaretColor(new java.awt.Color(218, 90, 81));

        plataforma.setBackground(new java.awt.Color(33, 45, 62));
        plataforma.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        plataforma.setForeground(new java.awt.Color(218, 90, 81));
        plataforma.setBorder(null);
        plataforma.setCaretColor(new java.awt.Color(218, 90, 81));

        genero.setBackground(new java.awt.Color(33, 45, 62));
        genero.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        genero.setForeground(new java.awt.Color(218, 90, 81));
        genero.setBorder(null);
        genero.setCaretColor(new java.awt.Color(218, 90, 81));

        proveedorJuego.setBackground(new java.awt.Color(33, 45, 62));
        proveedorJuego.setEditable(true);
        proveedorJuego.setForeground(new java.awt.Color(218, 90, 81));

        tablaJuegos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Desarrolladora", "Plataforma", "Genero", "Proveedor", "Precio", "Stock"
            }
        ));
        tablaJuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaJuegosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaJuegos);

        jLabel17.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(218, 90, 81));
        jLabel17.setText("ID Juego");

        jLabel18.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(218, 90, 81));
        jLabel18.setText("Nombre");

        jLabel19.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(218, 90, 81));
        jLabel19.setText("Desarrolladora");

        jLabel20.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(218, 90, 81));
        jLabel20.setText("Plataforma");

        jLabel21.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(218, 90, 81));
        jLabel21.setText("Genero");

        jLabel22.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(218, 90, 81));
        jLabel22.setText("Proveedor");

        jLabel23.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(218, 90, 81));
        jLabel23.setText("Precio");

        precioJuego.setBackground(new java.awt.Color(33, 45, 62));
        precioJuego.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        precioJuego.setForeground(new java.awt.Color(218, 90, 81));
        precioJuego.setBorder(null);
        precioJuego.setCaretColor(new java.awt.Color(218, 90, 81));

        añadirJuego.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/addgame.png"))); // NOI18N
        añadirJuego.setText("Añadir juego");
        añadirJuego.setColorHover(new java.awt.Color(16, 36, 61));
        añadirJuego.setColorNormal(new java.awt.Color(16, 36, 61));
        añadirJuego.setColorPressed(new java.awt.Color(16, 36, 61));
        añadirJuego.setColorTextNormal(new java.awt.Color(218, 90, 81));
        añadirJuego.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        añadirJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirJuegoActionPerformed(evt);
            }
        });

        eliminarJuego.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/deletegame.png"))); // NOI18N
        eliminarJuego.setText("Eliminar juego");
        eliminarJuego.setColorHover(new java.awt.Color(16, 36, 61));
        eliminarJuego.setColorNormal(new java.awt.Color(16, 36, 61));
        eliminarJuego.setColorPressed(new java.awt.Color(16, 36, 61));
        eliminarJuego.setColorTextNormal(new java.awt.Color(218, 90, 81));
        eliminarJuego.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarJuegoActionPerformed(evt);
            }
        });

        actualizarJuego.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/browser.png"))); // NOI18N
        actualizarJuego.setText("Actualizar juego");
        actualizarJuego.setColorHover(new java.awt.Color(16, 36, 61));
        actualizarJuego.setColorNormal(new java.awt.Color(16, 36, 61));
        actualizarJuego.setColorPressed(new java.awt.Color(16, 36, 61));
        actualizarJuego.setColorTextNormal(new java.awt.Color(218, 90, 81));
        actualizarJuego.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarJuegoActionPerformed(evt);
            }
        });

        limpiarDatos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/computer.png"))); // NOI18N
        limpiarDatos1.setText("Limpiar datos");
        limpiarDatos1.setColorHover(new java.awt.Color(16, 36, 61));
        limpiarDatos1.setColorNormal(new java.awt.Color(16, 36, 61));
        limpiarDatos1.setColorPressed(new java.awt.Color(16, 36, 61));
        limpiarDatos1.setColorTextNormal(new java.awt.Color(218, 90, 81));
        limpiarDatos1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limpiarDatos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarDatos1ActionPerformed(evt);
            }
        });

        generarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/excel.png"))); // NOI18N
        generarExcel.setText("Generar excel");
        generarExcel.setColorHover(new java.awt.Color(16, 36, 61));
        generarExcel.setColorNormal(new java.awt.Color(16, 36, 61));
        generarExcel.setColorPressed(new java.awt.Color(16, 36, 61));
        generarExcel.setColorTextNormal(new java.awt.Color(218, 90, 81));
        generarExcel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        generarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarExcelActionPerformed(evt);
            }
        });

        stockJuegos.setBackground(new java.awt.Color(33, 45, 62));
        stockJuegos.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        stockJuegos.setForeground(new java.awt.Color(218, 90, 81));
        stockJuegos.setBorder(null);
        stockJuegos.setCaretColor(new java.awt.Color(218, 90, 81));

        jLabel32.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(218, 90, 81));
        jLabel32.setText("Stock");

        javax.swing.GroupLayout panelCatalogoLayout = new javax.swing.GroupLayout(panelCatalogo);
        panelCatalogo.setLayout(panelCatalogoLayout);
        panelCatalogoLayout.setHorizontalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(idjuego, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nombreJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(desarrolladoraJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(plataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(40, 40, 40)
                        .addComponent(proveedorJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(precioJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(stockJuegos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(añadirJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(eliminarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(actualizarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(limpiarDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(generarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCatalogoLayout.setVerticalGroup(
            panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCatalogoLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(idjuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(nombreJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(desarrolladoraJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(plataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(panelCatalogoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(proveedorJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(precioJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stockJuegos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)))
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(81, 81, 81)
                .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limpiarDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCatalogoLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(añadirJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(actualizarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        gestionMenu.addTab("Catalogo", panelCatalogo);

        panelClientes.setBackground(new java.awt.Color(16, 36, 61));

        tablaClientes.setBorder(new javax.swing.border.MatteBorder(null));
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dni", "Nombre", "Apelldios", "Direccion", "Telefono"
            }
        ));
        tablaClientes.setToolTipText("");
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        jLabel4.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(218, 90, 81));
        jLabel4.setText("Dni");

        jLabel5.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(218, 90, 81));
        jLabel5.setText("Nombre");

        dniCliente.setBackground(new java.awt.Color(33, 45, 62) );
        dniCliente.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N

        dniCliente.setForeground(new java.awt.Color(218, 90, 81));

        dniCliente.setBorder(null);

        dniCliente.setCaretColor(new java.awt.Color(218, 90, 81));
        dniCliente.setBackground(new java.awt.Color(33, 45, 62));
        dniCliente.setForeground(new java.awt.Color(218, 90, 81));
        dniCliente.setBorder(null);

        jLabel6.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(218, 90, 81));
        jLabel6.setText("Apellidos");

        jLabel7.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(218, 90, 81));
        jLabel7.setText("Direccion");

        jLabel8.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(218, 90, 81));
        jLabel8.setText("Telefono");

        añadirClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/add(1).png"))); // NOI18N
        añadirClientes.setText("Añadir clientes");
        añadirClientes.setColorHover(new java.awt.Color(16, 36, 61));
        añadirClientes.setColorNormal(new java.awt.Color(16, 36, 61));
        añadirClientes.setColorPressed(new java.awt.Color(16, 36, 61));
        añadirClientes.setColorTextNormal(new java.awt.Color(218, 90, 81));
        añadirClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        añadirClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirClientesActionPerformed(evt);
            }
        });

        eliminarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/denied.png"))); // NOI18N
        eliminarClientes.setText("Eliminar clientes");
        eliminarClientes.setColorHover(new java.awt.Color(16, 36, 61));
        eliminarClientes.setColorNormal(new java.awt.Color(16, 36, 61));
        eliminarClientes.setColorPressed(new java.awt.Color(16, 36, 61));
        eliminarClientes.setColorTextNormal(new java.awt.Color(218, 90, 81));
        eliminarClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarClientesActionPerformed(evt);
            }
        });

        actualizarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/refresh.png"))); // NOI18N
        actualizarClientes.setText("Actualizar clientes");
        actualizarClientes.setColorHover(new java.awt.Color(16, 36, 61));
        actualizarClientes.setColorNormal(new java.awt.Color(16, 36, 61));
        actualizarClientes.setColorPressed(new java.awt.Color(16, 36, 61));
        actualizarClientes.setColorTextNormal(new java.awt.Color(218, 90, 81));
        actualizarClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarClientesActionPerformed(evt);
            }
        });

        actualizarClientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/computer.png"))); // NOI18N
        actualizarClientes1.setText("Limpiar datos");
        actualizarClientes1.setColorHover(new java.awt.Color(16, 36, 61));
        actualizarClientes1.setColorNormal(new java.awt.Color(16, 36, 61));
        actualizarClientes1.setColorPressed(new java.awt.Color(16, 36, 61));
        actualizarClientes1.setColorTextNormal(new java.awt.Color(218, 90, 81));
        actualizarClientes1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizarClientes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarClientes1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(218, 90, 81));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Gestión de clientes");

        nombreCliente.setBackground(new java.awt.Color(33, 45, 62));
        nombreCliente.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        nombreCliente.setForeground(new java.awt.Color(218, 90, 81));
        nombreCliente.setBorder(null);
        nombreCliente.setCaretColor(new java.awt.Color(218, 90, 81));
        nombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreClienteActionPerformed(evt);
            }
        });

        apellidosCliente.setBackground(new java.awt.Color(33, 45, 62));
        apellidosCliente.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        apellidosCliente.setForeground(new java.awt.Color(218, 90, 81));
        apellidosCliente.setBorder(null);
        apellidosCliente.setCaretColor(new java.awt.Color(218, 90, 81));
        apellidosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidosClienteActionPerformed(evt);
            }
        });

        direccionCliente.setBackground(new java.awt.Color(33, 45, 62));
        direccionCliente.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        direccionCliente.setForeground(new java.awt.Color(218, 90, 81));
        direccionCliente.setBorder(null);
        direccionCliente.setCaretColor(new java.awt.Color(218, 90, 81));
        direccionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccionClienteActionPerformed(evt);
            }
        });

        telefonoCliente.setBackground(new java.awt.Color(33, 45, 62));
        telefonoCliente.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        telefonoCliente.setForeground(new java.awt.Color(218, 90, 81));
        telefonoCliente.setBorder(null);
        telefonoCliente.setCaretColor(new java.awt.Color(218, 90, 81));
        telefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1368, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(53, 53, 53)
                        .addComponent(dniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(17, 17, 17)
                        .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(apellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(direccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(añadirClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(eliminarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(actualizarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(actualizarClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(panelClientesLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(dniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelClientesLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(apellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(direccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(telefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelClientesLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(111, 111, 111)
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(añadirClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualizarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualizarClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        gestionMenu.addTab("Clientes", panelClientes);

        panelNuevaVenta.setBackground(new java.awt.Color(16, 36, 61));
        panelNuevaVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        IDVENTA.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        IDVENTA.setForeground(new java.awt.Color(218, 90, 81));
        IDVENTA.setBorder(null);
        IDVENTA.setCaretColor(new java.awt.Color(218, 90, 81));
        panelNuevaVenta.add(IDVENTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 104, 147, 22));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(218, 90, 81));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Gestión de ventas");
        panelNuevaVenta.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1265, -1));

        tablaVentasCompleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idVenta", "Fecha", "Importe", "Metodo Pago", "Juego", "Cliente", "Empleado"
            }
        ));
        tablaVentasCompleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasCompletaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaVentasCompleta);

        panelNuevaVenta.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 257, 1246, 370));

        jLabel29.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(218, 90, 81));
        jLabel29.setText("Metodo Pago");
        panelNuevaVenta.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 76, -1, -1));

        idVenta.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        idVenta.setForeground(new java.awt.Color(218, 90, 81));
        idVenta.setText("idVenta");
        panelNuevaVenta.add(idVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 76, -1, -1));

        jLabel36.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(218, 90, 81));
        jLabel36.setText("Fecha");
        panelNuevaVenta.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 76, -1, -1));

        jLabel39.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(218, 90, 81));
        jLabel39.setText("Cliente");
        panelNuevaVenta.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 76, -1, -1));

        jLabel40.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(218, 90, 81));
        jLabel40.setText("Juego");
        panelNuevaVenta.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(477, 76, -1, -1));

        btnEliminarVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/quitarVenta.png"))); // NOI18N
        btnEliminarVentas.setText("Eliminar venta");
        btnEliminarVentas.setColorHover(new java.awt.Color(16, 36, 61));
        btnEliminarVentas.setColorNormal(new java.awt.Color(16, 36, 61));
        btnEliminarVentas.setColorPressed(new java.awt.Color(16, 36, 61));
        btnEliminarVentas.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnEliminarVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentasActionPerformed(evt);
            }
        });
        panelNuevaVenta.add(btnEliminarVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 645, 181, 72));

        btnAñadirVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/add-to-basket.png"))); // NOI18N
        btnAñadirVenta.setText("Añadir venta");
        btnAñadirVenta.setColorHover(new java.awt.Color(16, 36, 61));
        btnAñadirVenta.setColorNormal(new java.awt.Color(16, 36, 61));
        btnAñadirVenta.setColorPressed(new java.awt.Color(16, 36, 61));
        btnAñadirVenta.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnAñadirVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAñadirVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirVentaActionPerformed(evt);
            }
        });
        panelNuevaVenta.add(btnAñadirVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 645, 181, 72));

        limpiarDatosVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/computer.png"))); // NOI18N
        limpiarDatosVentas.setText("Limpiar datos");
        limpiarDatosVentas.setColorHover(new java.awt.Color(16, 36, 61));
        limpiarDatosVentas.setColorNormal(new java.awt.Color(16, 36, 61));
        limpiarDatosVentas.setColorPressed(new java.awt.Color(16, 36, 61));
        limpiarDatosVentas.setColorTextNormal(new java.awt.Color(218, 90, 81));
        limpiarDatosVentas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limpiarDatosVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarDatosVentasActionPerformed(evt);
            }
        });
        panelNuevaVenta.add(limpiarDatosVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 650, 181, 72));

        jLabel41.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(218, 90, 81));
        jLabel41.setText("Empleado");
        panelNuevaVenta.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(891, 76, -1, -1));

        desplegableEmpleadoVenta.setBackground(new java.awt.Color(33, 45, 62));
        desplegableEmpleadoVenta.setEditable(true);
        desplegableEmpleadoVenta.setForeground(new java.awt.Color(218, 90, 81));
        desplegableEmpleadoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desplegableEmpleadoVentaActionPerformed(evt);
            }
        });
        panelNuevaVenta.add(desplegableEmpleadoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(891, 106, 136, 24));

        nuevoClienteVenta.setBackground(new java.awt.Color(16, 36, 61));
        nuevoClienteVenta.setText("Nuevo cliente?");
        nuevoClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoClienteVentaActionPerformed(evt);
            }
        });
        panelNuevaVenta.add(nuevoClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 136, 147, -1));

        desplegableJuegoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desplegableJuegoVentaActionPerformed(evt);
            }
        });
        panelNuevaVenta.add(desplegableJuegoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 108, 147, -1));

        panelNuevaVenta.add(desplegableClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 108, 143, -1));
        panelNuevaVenta.add(contadorCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 108, 72, -1));

        btnGenerarPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagees/generarPdf.png"))); // NOI18N
        btnGenerarPdf.setText("Generar factura");
        btnGenerarPdf.setColorHover(new java.awt.Color(16, 36, 61));
        btnGenerarPdf.setColorNormal(new java.awt.Color(16, 36, 61));
        btnGenerarPdf.setColorPressed(new java.awt.Color(16, 36, 61));
        btnGenerarPdf.setColorTextNormal(new java.awt.Color(218, 90, 81));
        btnGenerarPdf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGenerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPdfActionPerformed(evt);
            }
        });
        panelNuevaVenta.add(btnGenerarPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 650, 181, 72));

        metodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transferencia", "Tarjeta de credito", "Efectivo" }));
        panelNuevaVenta.add(metodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 108, 143, -1));
        panelNuevaVenta.add(importe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1037, 108, 83, -1));
        panelNuevaVenta.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 106, 141, -1));

        jLabel42.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(218, 90, 81));
        jLabel42.setText("Importe");
        panelNuevaVenta.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(1033, 76, -1, -1));

        jLabel43.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(218, 90, 81));
        jLabel43.setText("Cantidad");
        panelNuevaVenta.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 76, -1, -1));

        gestionMenu.addTab("Gestion venta", panelNuevaVenta);

        panelInformacion.setBackground(new java.awt.Color(16, 36, 61));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(218, 90, 81));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Información de la tienda");

        nombreTienda.setBackground(new java.awt.Color(33, 45, 62));
        nombreTienda.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        nombreTienda.setForeground(new java.awt.Color(218, 90, 81));
        nombreTienda.setText("TiendaVIDJ");
        nombreTienda.setBorder(null);
        nombreTienda.setCaretColor(new java.awt.Color(218, 90, 81));

        idTiendaInfo.setBackground(new java.awt.Color(33, 45, 62));
        idTiendaInfo.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        idTiendaInfo.setForeground(new java.awt.Color(218, 90, 81));
        idTiendaInfo.setText("6GND84NF");
        idTiendaInfo.setBorder(null);
        idTiendaInfo.setCaretColor(new java.awt.Color(218, 90, 81));

        direccionTienda.setBackground(new java.awt.Color(33, 45, 62));
        direccionTienda.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        direccionTienda.setForeground(new java.awt.Color(218, 90, 81));
        direccionTienda.setText("Calle del Pozo");
        direccionTienda.setBorder(null);
        direccionTienda.setCaretColor(new java.awt.Color(218, 90, 81));

        jLabel25.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(218, 90, 81));
        jLabel25.setText("Identificación tienda:");

        jLabel26.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(218, 90, 81));
        jLabel26.setText("Nombre de la tienda");

        jLabel27.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(218, 90, 81));
        jLabel27.setText("Dirección:");

        jLabel28.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(218, 90, 81));
        jLabel28.setText("Telefono:");

        telefonoTienda.setBackground(new java.awt.Color(33, 45, 62));
        telefonoTienda.setFont(new java.awt.Font("Gotham Thin", 0, 18)); // NOI18N
        telefonoTienda.setForeground(new java.awt.Color(218, 90, 81));
        telefonoTienda.setText("983456789");
        telefonoTienda.setBorder(null);
        telefonoTienda.setCaretColor(new java.awt.Color(218, 90, 81));

        javax.swing.GroupLayout panelInformacionLayout = new javax.swing.GroupLayout(panelInformacion);
        panelInformacion.setLayout(panelInformacionLayout);
        panelInformacionLayout.setHorizontalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 1285, Short.MAX_VALUE)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idTiendaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(direccionTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInformacionLayout.setVerticalGroup(
            panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformacionLayout.createSequentialGroup()
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelInformacionLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(idTiendaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))))
                .addGap(96, 96, 96)
                .addGroup(panelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(direccionTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(telefonoTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(460, Short.MAX_VALUE))
        );

        gestionMenu.addTab("Configuracion", panelInformacion);

        getContentPane().add(gestionMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 1290, 770));

        jPanel2.setBackground(new java.awt.Color(218, 90, 81));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 20, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        gestionMenu.setSelectedIndex(4);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        
        //eliminamos todos lo que haya dentro del jcomobox para seguidamente volver a llenarlo con los datos actulizados
        desplegableClientes.removeAllItems();
        metVent.clienteDespegable(desplegableClientes);
        
        desplegableJuegoVenta.removeAllItems();
        metVent.juegoDespegable(desplegableJuegoVenta);
        
        gestionMenu.setSelectedIndex(3);
        limpiarTabla();
        mostrarVentas();
        
    }//GEN-LAST:event_btnVentasActionPerformed

    private void botonDesplegable11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDesplegable11ActionPerformed
        
        gestionMenu.setSelectedIndex(0);
        limpiarDatosProveedores();
        limpiarTabla();
        mostrarProveedores();
       
        
    }//GEN-LAST:event_botonDesplegable11ActionPerformed

    private void btnTablaClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablaClientesActionPerformed
        limpiarTabla();
        mostrarClientes();
       gestionMenu.setSelectedIndex(2);
       
    }//GEN-LAST:event_btnTablaClientesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        this.setVisible(false);
        dispose();
        IniciarSesion1 inicioPostLogOut = new IniciarSesion1();
        inicioPostLogOut.setVisible(true);

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoActionPerformed
        
        limpiarDatosClientes();
        proveedorJuego.removeAllItems();
        metJueg.proveedorDespegable(proveedorJuego);
        
        limpiarTabla();
        mostrarJuegos();
        
        gestionMenu.setSelectedIndex(1);
    }//GEN-LAST:event_btnCatalogoActionPerformed

    private void actualizarClientes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarClientes1ActionPerformed
        limpiarDatosClientes();
    }//GEN-LAST:event_actualizarClientes1ActionPerformed

    private void actualizarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarClientesActionPerformed

        //Comprobamos si el usuario ha seleccionado algun cliente
        if ("".equals(dniCliente.getText())){
            //En caso de que no haya seleccionado ningun cliente, mostramos un mensaje de error

            JOptionPane.showMessageDialog(rootPane, "No has seleccionado ningun cliente","Error",JOptionPane.ERROR_MESSAGE);
            
        } else {

            if (!"".equals(dniCliente.getText()) || !"".equals(nombreCliente.getText()) || !"".equals(apellidosCliente.getText()) || !"".equals(direccionCliente.getText()) || !"".equals(telefonoCliente.getText())){

                cli.setDni(dniCliente.getText());
                cli.setNombre(nombreCliente.getText());
                cli.setApellidos(apellidosCliente.getText());
                cli.setDireccion(direccionCliente.getText());
                cli.setTelefono(telefonoCliente.getText());

                añadir.modificarClientes(cli);
                JOptionPane.showMessageDialog(null, "Cliente actualizado");

                limpiarTabla();
                limpiarDatosClientes();
                mostrarClientes();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Los campos estan vacios","Error",JOptionPane.ERROR_MESSAGE);
                
            }
        }
    }//GEN-LAST:event_actualizarClientesActionPerformed

    private void eliminarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarClientesActionPerformed
        if(!"".equals(dniCliente.getText())){

            int validacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos seleccionados?");

            if (validacion == 0){
                String dni = dniCliente.getText();
                añadir.eliminarCliente(dni);
                limpiarTabla();
                limpiarDatosClientes();
                mostrarClientes();
            } else {

                JOptionPane.showMessageDialog(null, "No has seleccionado ningun cliente");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "No has seleccionado ningun cliente","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_eliminarClientesActionPerformed

    private void añadirClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirClientesActionPerformed
        // ESTE BOTON SE UTILIZARA PARA HACER EL INSERT DE LOS DATOS DE LOS CLIENTES
        if (!"".equals(dniCliente.getText()) && !"".equals(nombreCliente.getText()) && !"".equals(apellidosCliente.getText()) && !"".equals(direccionCliente.getText()) && !"".equals(telefonoCliente.getText())){

            cli.setDni(dniCliente.getText());
            cli.setNombre(nombreCliente.getText());
            cli.setApellidos(apellidosCliente.getText());
            cli.setDireccion(direccionCliente.getText());
            cli.setTelefono(telefonoCliente.getText());
            añadir.añadirCliente(cli);
            //JOptionPane.showMessageDialog(null, "Cliente añadido correctamente");
            limpiarTabla();
            limpiarDatosClientes();
            mostrarClientes();

        } else {

            JOptionPane.showMessageDialog(null, "Hay algun campo sin rellenar");
        }
    }//GEN-LAST:event_añadirClientesActionPerformed

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        int fila = tablaClientes.rowAtPoint(evt.getPoint());
        dniCliente.setText(tablaClientes.getValueAt(fila, 0).toString());
        nombreCliente.setText(tablaClientes.getValueAt(fila, 1).toString());
        apellidosCliente.setText(tablaClientes.getValueAt(fila, 2).toString());
        direccionCliente.setText(tablaClientes.getValueAt(fila, 3).toString());
        telefonoCliente.setText(tablaClientes.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void añadirProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirProveedorActionPerformed
       
        if (!"".equals(idProveedor.getText()) && !"".equals(nombreProveedor.getText()) && !"".equals(telefonoProveedor.getText()) && !"".equals(direccionProveedor.getText()) && !"".equals(emailProveedor.getText())){

            pro.setId(idProveedor.getText());
            pro.setNombre(nombreProveedor.getText());
            pro.setTelefono(telefonoProveedor.getText());
            pro.setDireccion(direccionProveedor.getText());
            pro.setEmail(emailProveedor.getText());
           
            mPro.AñadirProveedor(pro);
            //JOptionPane.showMessageDialog(null, "Proveedor añadido correctamente");
            limpiarTabla();
            limpiarDatosProveedores();
            mostrarProveedores();
            
          
        } else {
            
            JOptionPane.showMessageDialog(null, "Hay algun campo sin rellenar");
            
        }
    }//GEN-LAST:event_añadirProveedorActionPerformed

    private void eliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProveedorActionPerformed
        
        if(!"".equals(idProveedor.getText())){

            int validacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos seleccionados?");

            if (validacion == 0){
                String id = idProveedor.getText();
                mPro.eliminarProveedor(id);
                limpiarTabla();
                limpiarDatosProveedores();
                mostrarProveedores();
            } else {
                JOptionPane.showMessageDialog(rootPane, "No has seleccionado ningun proveedor","Error",JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "No has seleccionado ningun proveedor","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_eliminarProveedorActionPerformed

    private void actualizarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarProveedorActionPerformed
         if ("".equals(idProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleecione una fila");
        } else {
            if (!"".equals(idProveedor.getText()) || !"".equals(nombreProveedor.getText()) || !"".equals(telefonoProveedor.getText()) || !"".equals(direccionProveedor.getText()) || !"".equals(emailProveedor.getText())) {
               
                pro.setId(idProveedor.getText());
               
                pro.setNombre(nombreProveedor.getText());
                pro.setTelefono(telefonoProveedor.getText());
                
                pro.setDireccion(direccionProveedor.getText());
                pro.setEmail(emailProveedor.getText());
                //pro.setId(Integer.parseInt(txtIdProveedor.getText()));
                mPro.modificarProveedores(pro);
                JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                
                limpiarTabla();
                mostrarProveedores();
               //LimpiarProveedor();
               
               

            }
        }
    }//GEN-LAST:event_actualizarProveedorActionPerformed

    private void limpiarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarDatosActionPerformed
        limpiarDatosProveedores();
    }//GEN-LAST:event_limpiarDatosActionPerformed

    private void tablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedoresMouseClicked
        int fila = tablaProveedores.rowAtPoint(evt.getPoint());
        idProveedor.setText(tablaProveedores.getValueAt(fila, 0).toString());
        nombreProveedor.setText(tablaProveedores.getValueAt(fila, 1).toString());
        telefonoProveedor.setText(tablaProveedores.getValueAt(fila, 2).toString());
        direccionProveedor.setText(tablaProveedores.getValueAt(fila, 3).toString());
        emailProveedor.setText(tablaProveedores.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_tablaProveedoresMouseClicked
                                                                            

    private void añadirJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirJuegoActionPerformed
        
         if (!"".equals(idjuego.getText()) && !"".equals(nombreJuego.getText()) && !"".equals(desarrolladoraJuego.getText()) && !"".equals(plataforma.getText()) && !"".equals(genero.getText()) && !"".equals(proveedorJuego.getSelectedItem()) && !"".equals(precioJuego.getText()) && !"".equals(stockJuegos.getText())){

            jueg.setIdJuego(idjuego.getText());
            jueg.setNombre(nombreJuego.getText());
            jueg.setProductora(desarrolladoraJuego.getText());
            jueg.setPlataforma(plataforma.getText());
            jueg.setGenero(genero.getText());
            jueg.setPrecio(Float.parseFloat(precioJuego.getText()));
            jueg.setProveedor(proveedorJuego.getSelectedItem().toString());
            jueg.setStock(Integer.parseInt(stockJuegos.getText()));
            metJueg.añadirJuego(jueg);
            limpiarDatosJuegos();
            limpiarTabla();
            mostrarJuegos();
            
            
           // JOptionPane.showMessageDialog(null, "Juego añadido correctamente");
           
        } else {
             
             JOptionPane.showMessageDialog(null, "Hay algun campo sin rellenar");
         }
    }//GEN-LAST:event_añadirJuegoActionPerformed

    private void eliminarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarJuegoActionPerformed
         if(!"".equals(idjuego.getText())){

            int validacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos seleccionados?");

            if (validacion == 0){
                String id = idjuego.getText();
                metJueg.eliminarJuego(id);
                limpiarTabla();
                limpiarDatosJuegos();
                mostrarJuegos();
              
            } else {

                JOptionPane.showMessageDialog(null, "No has seleccionado ningun juego");
            }

        } else {
             JOptionPane.showMessageDialog(null, "No has seleccionado ningun juego");
         }
    }//GEN-LAST:event_eliminarJuegoActionPerformed

    private void actualizarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarJuegoActionPerformed
        if ("".equals(idjuego.getText())) {
            JOptionPane.showMessageDialog(null, "No hay ningun juego seleccionado");
        } else {
            
            if (!"".equals(idjuego.getText()) || !"".equals(nombreJuego.getText()) || !"".equals(desarrolladoraJuego.getText()) || !"".equals(plataforma.getText()) || !"".equals(genero.getText()) || !"".equals(proveedorJuego.getSelectedItem().toString()) || !"".equals(precioJuego.getText())){
                jueg.setIdJuego(idjuego.getText());
                jueg.setNombre(nombreJuego.getText());
                jueg.setProductora(desarrolladoraJuego.getText());
                jueg.setPlataforma(plataforma.getText());
                jueg.setGenero(genero.getText());
                jueg.setProveedor(proveedorJuego.getSelectedItem().toString());
                jueg.setPrecio(Float.parseFloat(precioJuego.getText()));
                 jueg.setStock(Integer.parseInt(stockJuegos.getText()));
                
                metJueg.actualizarJuegos(jueg);
               
                JOptionPane.showMessageDialog(null, "Juego modificado");
                limpiarTabla();
                limpiarDatosJuegos();
                mostrarJuegos();
                
            }
        }
    }//GEN-LAST:event_actualizarJuegoActionPerformed

    private void limpiarDatos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarDatos1ActionPerformed
        
     limpiarDatosJuegos();
        
    }//GEN-LAST:event_limpiarDatos1ActionPerformed

    private void tablaJuegosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaJuegosMouseClicked
       int fila = tablaJuegos.rowAtPoint(evt.getPoint());
        idjuego.setText(tablaJuegos.getValueAt(fila, 0).toString());
        nombreJuego.setText(tablaJuegos.getValueAt(fila, 1).toString());
        desarrolladoraJuego.setText(tablaJuegos.getValueAt(fila, 2).toString());
        plataforma.setText(tablaJuegos.getValueAt(fila, 3).toString());
        genero.setText(tablaJuegos.getValueAt(fila, 4).toString());
        proveedorJuego.setSelectedItem(tablaJuegos.getValueAt(fila, 5).toString());
        precioJuego.setText(tablaJuegos.getValueAt(fila, 6).toString());
        stockJuegos.setText(tablaJuegos.getValueAt(fila, 7).toString());
        
        
    }//GEN-LAST:event_tablaJuegosMouseClicked

    private void generarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarExcelActionPerformed

        Excel.reporte();
        
    }//GEN-LAST:event_generarExcelActionPerformed

    private void nuevoClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoClienteVentaActionPerformed
        limpiarTabla();
        mostrarClientes();
        gestionMenu.setSelectedIndex(2);
    }//GEN-LAST:event_nuevoClienteVentaActionPerformed

    private void limpiarDatosVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarDatosVentasActionPerformed
         IDVENTA.setText("");
        
        metodoPago.setSelectedItem("");
        desplegableJuegoVenta.setSelectedItem("");
        desplegableClientes.setSelectedItem("");
        desplegableEmpleadoVenta.setSelectedItem("");
    }//GEN-LAST:event_limpiarDatosVentasActionPerformed
    public static String fecha(){
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatofecha.format(fecha);
    }
    
   
    private void btnAñadirVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirVentaActionPerformed

        if (!"".equals(IDVENTA.getText()) && !"".equals(metodoPago.getSelectedItem().toString()) && !"".equals(desplegableJuegoVenta.getSelectedItem().toString()) && !"".equals(desplegableClientes.getSelectedItem().toString()) && !"".equals(desplegableEmpleadoVenta.getSelectedItem().toString())){
            try {
                
            String value = contadorCantidad.getValue() + "";
            int cont =Integer.parseInt(value);
            int importe1 =Integer.parseInt(importe.getText());
            int resutl = importe1*cont;
            
            //vent.setImporte();
            
            vent.setIdVenta(Integer.parseInt(IDVENTA.getText()));
            vent.setImporte(resutl);
            vent.setFechaVenta(txtFecha.getText());
            vent.setMetodoPago(metodoPago.getSelectedItem().toString());
            vent.setIdJuego(desplegableJuegoVenta.getSelectedItem().toString());
            vent.setDniCliente(desplegableClientes.getSelectedItem().toString());
            vent.setDniEmpleado(desplegableEmpleadoVenta.getSelectedItem().toString());
            
            metVent.registrarVenta(vent);
            limpiarDatosVentas();
            limpiarTabla();
            mostrarVentas();
            
            
            JOptionPane.showMessageDialog(null, "Venta añadida correctamente");
            } catch (HeadlessException | NumberFormatException e) {
                
                System.out.println(e);
            }
          
            
            
        } else {
            
            JOptionPane.showMessageDialog(rootPane, "Hay algun campo sin rellenar","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnAñadirVentaActionPerformed

    private void btnEliminarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentasActionPerformed
    if(!"".equals(IDVENTA.getText())){

            int validacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar los datos seleccionados?");

            if (validacion == 0){
                int id = Integer.parseInt(IDVENTA.getText());
                metVent.eliminarVenta(id);
                
                limpiarTabla();
                limpiarDatosVentas();
                mostrarVentas();
                JOptionPane.showMessageDialog(rootPane, "Venta eliminada con exito");
              
            } else {

                JOptionPane.showMessageDialog(null, "No has seleccionado ninguna venta");
            }

        } else {
             JOptionPane.showMessageDialog(null, "No has seleccionado ninguna venta");
         }
    }//GEN-LAST:event_btnEliminarVentasActionPerformed

    private void tablaVentasCompletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasCompletaMouseClicked

        int fila = tablaVentasCompleta.rowAtPoint(evt.getPoint());
        //Aqui hay que poner los campos de texto que estaran en la pestaña de las Ventas.
      
        IDVENTA.setText(tablaVentasCompleta.getValueAt(fila, 0).toString());
        txtFecha.setText(tablaVentasCompleta.getValueAt(fila, 1).toString());
        importe.setText(tablaVentasCompleta.getValueAt(fila, 2).toString());
        metodoPago.setSelectedItem(tablaVentasCompleta.getValueAt(fila, 3).toString());
        desplegableJuegoVenta.setSelectedItem(tablaVentasCompleta.getValueAt(fila, 4).toString());
        desplegableClientes.setSelectedItem(tablaVentasCompleta.getValueAt(fila, 5).toString());
        desplegableEmpleadoVenta.setSelectedItem(tablaVentasCompleta.getValueAt(fila, 6).toString());
       
       
    }//GEN-LAST:event_tablaVentasCompletaMouseClicked

    private void btnGenerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPdfActionPerformed
        
        //llamammos al metodo para generar el reporte en pdf
        generarPdf();
        
        JOptionPane.showMessageDialog(null, "Factura generada");
    }//GEN-LAST:event_btnGenerarPdfActionPerformed
    
    private void desplegableJuegoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desplegableJuegoVentaActionPerformed
       
        
        
        
    }//GEN-LAST:event_desplegableJuegoVentaActionPerformed

    private void desplegableEmpleadoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desplegableEmpleadoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_desplegableEmpleadoVentaActionPerformed

    private void nombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreClienteActionPerformed

    private void apellidosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidosClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidosClienteActionPerformed

    private void direccionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccionClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionClienteActionPerformed

    private void telefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoClienteActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDVENTA;
    private rsbuttom.RSButtonMetro actualizarClientes;
    private rsbuttom.RSButtonMetro actualizarClientes1;
    private rsbuttom.RSButtonMetro actualizarJuego;
    private rsbuttom.RSButtonMetro actualizarProveedor;
    private javax.swing.JTextField apellidosCliente;
    private rsbuttom.RSButtonMetro añadirClientes;
    private rsbuttom.RSButtonMetro añadirJuego;
    private rsbuttom.RSButtonMetro añadirProveedor;
    private rsbuttom.RSButtonMetro botonDesplegable11;
    private rsbuttom.RSButtonMetro btnAñadirVenta;
    private rsbuttom.RSButtonMetro btnCatalogo;
    private rsbuttom.RSButtonMetro btnConfiguracion;
    private rsbuttom.RSButtonMetro btnEliminarVentas;
    private rsbuttom.RSButtonMetro btnGenerarPdf;
    private rsbuttom.RSButtonMetro btnSalir;
    private rsbuttom.RSButtonMetro btnTablaClientes;
    private rsbuttom.RSButtonMetro btnVentas;
    private javax.swing.JSpinner contadorCantidad;
    private javax.swing.JTextField desarrolladoraJuego;
    private javax.swing.JComboBox<String> desplegableClientes;
    private javax.swing.JComboBox<String> desplegableEmpleadoVenta;
    private javax.swing.JComboBox<String> desplegableJuegoVenta;
    private javax.swing.JTextField direccionCliente;
    private javax.swing.JTextField direccionProveedor;
    private javax.swing.JTextField direccionTienda;
    private javax.swing.JTextField dniCliente;
    private rsbuttom.RSButtonMetro eliminarClientes;
    private rsbuttom.RSButtonMetro eliminarJuego;
    private rsbuttom.RSButtonMetro eliminarProveedor;
    private javax.swing.JTextField emailProveedor;
    private rsbuttom.RSButtonMetro generarExcel;
    private javax.swing.JTextField genero;
    private javax.swing.JTabbedPane gestionMenu;
    private javax.swing.JTextField idProveedor;
    private javax.swing.JTextField idTiendaInfo;
    private javax.swing.JLabel idVenta;
    private javax.swing.JTextField idjuego;
    private javax.swing.JTextField importe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private rsbuttom.RSButtonMetro limpiarDatos;
    private rsbuttom.RSButtonMetro limpiarDatos1;
    private rsbuttom.RSButtonMetro limpiarDatosVentas;
    private javax.swing.JPanel menuOpciones;
    private javax.swing.JComboBox<String> metodoPago;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JTextField nombreJuego;
    private javax.swing.JTextField nombreProveedor;
    private javax.swing.JTextField nombreTienda;
    private javax.swing.JButton nuevoClienteVenta;
    private javax.swing.JPanel panelCatalogo;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelInformacion;
    private javax.swing.JPanel panelNuevaVenta;
    private javax.swing.JPanel panelProveedores;
    private javax.swing.JTextField plataforma;
    private javax.swing.JTextField precioJuego;
    private javax.swing.JComboBox<String> proveedorJuego;
    private javax.swing.JTextField stockJuegos;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTable tablaJuegos;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTable tablaVentasCompleta;
    private javax.swing.JTextField telefonoCliente;
    private javax.swing.JTextField telefonoProveedor;
    private javax.swing.JTextField telefonoTienda;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtcorreo;
    // End of variables declaration//GEN-END:variables
}
