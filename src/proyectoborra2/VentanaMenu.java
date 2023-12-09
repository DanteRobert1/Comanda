/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectoborra2;
import java.sql.Connection;
import Conexion.ConexionBD;
import static Conexion.ConexionBD.getConnection;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 *Proyecto Comanda
 * @author Equipo4
 */
public class VentanaMenu extends javax.swing.JFrame {

    ConexionBD conexion; // Objeto para manejar la conexión a la base de datos   
    private DefaultListModel<String> modeloLista;  // Modelo para almacenar una lista de Strings
    private int id; // Identificador genérico para el id de comida
    private static int idComanda; // Identificador de la comanda actual
    private static int idRecibo; // Identificador del recibo asociado a la comanda 
    private static int folioReserva; // Identificador del recibo asociado a la comanda
    private int idBebidas; // Identificador específico para las bebidas
    private double total = 0.0; // Variable para almacenar el total acumulado
    private static int idMesa; // Identificador de la mesa 
    private static int idSucursal; // Identificador de la sucursal 
    private static double iva = 0.16; //Valor de iva que se va a manejar
    
    /**
     * Creates new form Ventana
     * Esta ventana lo que hace es que simular la interfaz grafica que se llevara acabo para las comandas
     */
    public VentanaMenu(int idSucursal, int idMesa) {
        // Asignar valores a las variables idSucursal e idMesa
        this.idSucursal=idSucursal;
        this.idMesa = idMesa;
        // Inicializar componentes de la interfaz gráfica
        initComponents();
        inicializarObjetos();
        // Crear un modelo de lista por defecto y asignarlo al componente Pedido (JList)
        modeloLista = new DefaultListModel<>();
        Pedido.setModel(modeloLista);
        // Inicializar la conexión a la base de datos
        conexion = new ConexionBD(); 
    }
    
    /**
     * Antes que nada la idea de este proyecto es simular en tomar la comanda de un cliente, como sabemos tiene que tener varios platillos.
     * 
     * Bebibas para que pueda funcionar, aparte de postres, claro algunos ingredientes se acabaran antes de poder tomar la orden
     * es por eso que en este codigo muestra un menu con los nombre de cada platillo y bebida, muestra el orden y el conteo que se esta generando
     * Aparte de que si quiere saber los ingredientes puede verlos, aparte de que en caso de que se equivoque el cliente, puede eliminar platillos que no querian
     * 
     * Abra una lista que llevara para saber los platillos y abajo un conteo total de lo que van a pagar, sobra decir que los precios se veran en cada momento.
     * 
     * Al momento de enviar, se le preguntara si quiere continuar, si le da en si, podra pedir la orden, aparte de dar con la orden, se imprimira su ticket de orden.
     * 
     * Se enviara una comanda, el recibo y tambien el platilloporcomanda y bebida, para que asi sepan que es lo que quieren, cuanto cuesta y cual es la cantidad que necesitan.
     */
     private void inicializarObjetos() {
        // Lo que ponemos es un ciclo con 7 variables que seran los platillos para anailazarlos todos
        try {
            for (int i = 1; i <= 7; i++) {
                boolean existencia = verificarDisponibilidadTotalPlatillo(i); // manda a llamar un proceso para verificar la existencia de los ingredientes de los platillos
                if (!existencia){  
                // Si no hay ingredientes se bloquean los botones y se cambia la imagen
                    ImageIcon iconoN;
                    switch (i){
                        case 1:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\RamenN.png");
                            JbtnRamen.setIcon(iconoN);
                            JbtnRamen.setEnabled(false);
                            break;
                        case 2:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\CurryN.png");
                            JbtnCurry.setIcon(iconoN);
                            JbtnCurry.setEnabled(false);
                            break;
                        case 3:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\SushiN.png");
                            JbtnSushi.setIcon(iconoN);
                            JbtnSushi.setEnabled(false);
                            break;
                        case 4:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\SalmonMarinadoN.png");
                            JbtnSalmon.setIcon(iconoN);
                            JbtnSalmon.setEnabled(false);
                            break;
                        case 6:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\MochisN.png");
                            JbtnMochis.setIcon(iconoN);
                            JbtnMochis.setEnabled(false);
                            break;
                        case 7:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\BubbleWaffleN.png");
                            JbtnWafle.setIcon(iconoN);
                            JbtnWafle.setEnabled(false);  
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        // Esto funciona igual pero ahora serian las bebidas
        try{
            for (int j = 1; j <= 4; j++) {
                boolean existenciaB = verificarDisponibilidadTotalBebidas(j); // Lo mismo, solo que en vez de ingredientes, vera si no hay bebidas disponibles 
                if (!existenciaB){
                    ImageIcon iconoN;
                    // Si no hay se bloquea las imagenes y cambie las imagenes 
                    switch (j){
                        case 1:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\TeVerdeN.png");
                            JbtnTé.setIcon(iconoN);
                            JbtnTé.setEnabled(false);
                            break;
                        case 2:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\SakeN.png");
                            JbtnSake.setIcon(iconoN);
                            JbtnSake.setEnabled(false);
                            break;
                        case 3:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\CalpisN.png");
                            JbtnCalpis.setIcon(iconoN);
                            JbtnCalpis.setEnabled(false);
                            break;
                        case 4:
                            iconoN = new ImageIcon ("src\\ImagenesNew\\Imagenes\\RefrescoN.png");
                            JbtnRefresco.setIcon(iconoN);
                            JbtnRefresco.setEnabled(false);
                    }
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);     
        }
     }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        JbtnEnviar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Pedido = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        JlblTotal = new javax.swing.JLabel();
        JbtnEliminar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        JbtnRamen = new javax.swing.JButton();
        JbtnCurry = new javax.swing.JButton();
        JbtnSushi = new javax.swing.JButton();
        JbtnSalmon = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        JbtnMochis = new javax.swing.JButton();
        JbtnWafle = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JbtnRefresco = new javax.swing.JButton();
        JbtnCalpis = new javax.swing.JButton();
        JbtnTé = new javax.swing.JButton();
        JbtnSake = new javax.swing.JButton();
        jbtnIngreRamen = new javax.swing.JButton();
        jbtnIngreCurry = new javax.swing.JButton();
        jbtnIngreSushi = new javax.swing.JButton();
        jbtnIngreSalmon = new javax.swing.JButton();
        jbtnIngreWaffle = new javax.swing.JButton();
        jbtnIngreMochis = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");

        jPanel4.setBackground(new java.awt.Color(32, 17, 72));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(85, 231, 255), 5, true));

        JbtnEnviar.setBackground(new java.awt.Color(204, 204, 204));
        JbtnEnviar.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        JbtnEnviar.setForeground(new java.awt.Color(32, 17, 72));
        JbtnEnviar.setText("Enviar");
        JbtnEnviar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(85, 231, 255), 2, true));
        JbtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEnviarActionPerformed(evt);
            }
        });

        Pedido.setBackground(new java.awt.Color(70, 124, 203));
        Pedido.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(85, 231, 255), 1, true));
        Pedido.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        Pedido.setForeground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setViewportView(Pedido);

        jLabel7.setFont(new java.awt.Font("Bahiana", 2, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(85, 231, 255));
        jLabel7.setText("Orden:");

        JlblTotal.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 24)); // NOI18N
        JlblTotal.setForeground(new java.awt.Color(85, 231, 255));
        JlblTotal.setText("$");

        JbtnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        JbtnEliminar.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        JbtnEliminar.setForeground(new java.awt.Color(32, 17, 72));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(85, 231, 255), 2, true));
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JbtnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JlblTotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JlblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JbtnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel1.setBackground(new java.awt.Color(32, 17, 72));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(85, 231, 255), 5, true));

        JbtnRamen.setBackground(new java.awt.Color(153, 255, 153));
        JbtnRamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/RamenD.png"))); // NOI18N
        JbtnRamen.setFocusPainted(false);
        JbtnRamen.setFocusable(false);
        JbtnRamen.setRequestFocusEnabled(false);
        JbtnRamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnRamenActionPerformed(evt);
            }
        });

        JbtnCurry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/CurryD.png"))); // NOI18N
        JbtnCurry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnCurryActionPerformed(evt);
            }
        });

        JbtnSushi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/SushiD.png"))); // NOI18N
        JbtnSushi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSushiActionPerformed(evt);
            }
        });

        JbtnSalmon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/SalmonMarinadoD.png"))); // NOI18N
        JbtnSalmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSalmonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(85, 231, 255));
        jLabel4.setText("Platillos");

        JbtnMochis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/MochisD.png"))); // NOI18N
        JbtnMochis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnMochisActionPerformed(evt);
            }
        });

        JbtnWafle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/BubbleWaffleD.png"))); // NOI18N
        JbtnWafle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnWafleActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(85, 231, 255));
        jLabel5.setText("Postres");

        jLabel6.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(85, 231, 255));
        jLabel6.setText("Bebidas");

        JbtnRefresco.setBackground(new java.awt.Color(153, 255, 153));
        JbtnRefresco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/RefrescoD.png"))); // NOI18N
        JbtnRefresco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnRefrescoActionPerformed(evt);
            }
        });

        JbtnCalpis.setBackground(new java.awt.Color(153, 255, 153));
        JbtnCalpis.setForeground(new java.awt.Color(153, 255, 153));
        JbtnCalpis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/CalpisD.png"))); // NOI18N
        JbtnCalpis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnCalpisActionPerformed(evt);
            }
        });

        JbtnTé.setBackground(new java.awt.Color(153, 255, 153));
        JbtnTé.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/TeVerdeD.png"))); // NOI18N
        JbtnTé.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnTéActionPerformed(evt);
            }
        });

        JbtnSake.setBackground(new java.awt.Color(153, 255, 153));
        JbtnSake.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/SakeD.png"))); // NOI18N
        JbtnSake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSakeActionPerformed(evt);
            }
        });

        jbtnIngreRamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreRamen.setContentAreaFilled(false);
        jbtnIngreRamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreRamenActionPerformed(evt);
            }
        });

        jbtnIngreCurry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreCurry.setContentAreaFilled(false);
        jbtnIngreCurry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreCurryActionPerformed(evt);
            }
        });

        jbtnIngreSushi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreSushi.setContentAreaFilled(false);
        jbtnIngreSushi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreSushiActionPerformed(evt);
            }
        });

        jbtnIngreSalmon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreSalmon.setContentAreaFilled(false);
        jbtnIngreSalmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreSalmonActionPerformed(evt);
            }
        });

        jbtnIngreWaffle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreWaffle.setContentAreaFilled(false);
        jbtnIngreWaffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreWaffleActionPerformed(evt);
            }
        });

        jbtnIngreMochis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesNew/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreMochis.setContentAreaFilled(false);
        jbtnIngreMochis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreMochisActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(85, 231, 255));
        jLabel8.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(85, 231, 255));
        jLabel8.setText("$ 370");

        jLabel9.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(85, 231, 255));
        jLabel9.setText("$ 230");

        jLabel10.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(85, 231, 255));
        jLabel10.setText("$ 200");

        jLabel11.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(85, 231, 255));
        jLabel11.setText("$ 400");

        jLabel12.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(85, 231, 255));
        jLabel12.setText(" $180");

        jLabel13.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(85, 231, 255));
        jLabel13.setText("$ 80");

        jLabel14.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(85, 231, 255));
        jLabel14.setText("$ 60");

        jLabel15.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(85, 231, 255));
        jLabel15.setText("$ 20");

        jLabel16.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(85, 231, 255));
        jLabel16.setText("$ 120");

        jLabel17.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(85, 231, 255));
        jLabel17.setText("$ 70");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/CapturaIcono.JPG"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addComponent(JbtnRefresco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnIngreMochis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JbtnMochis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(jbtnIngreRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(JbtnRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JbtnWafle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbtnIngreWaffle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(2, 2, 2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(JbtnSake, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(30, 30, 30)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jbtnIngreCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(JbtnCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(37, 37, 37)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel10)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jbtnIngreSushi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(JbtnSushi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32))
                                            .addComponent(JbtnTé, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(72, 72, 72)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jbtnIngreSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(JbtnSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(JbtnCalpis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(2, 2, 2)))))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JbtnRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnIngreRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(66, 66, 66)
                        .addComponent(JbtnMochis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtnIngreMochis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(82, 82, 82))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JbtnSushi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jbtnIngreSushi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JbtnCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jbtnIngreCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JbtnSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jbtnIngreSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JbtnWafle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtnIngreWaffle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))))
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JbtnRefresco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JbtnCalpis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JbtnTé, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JbtnSake, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este boton lo que hace es enviar a la lista el platillo calpis, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnCalpisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnCalpisActionPerformed
        idBebidas = 3; 
        agregarALista("Calpis");
        AgarrarPreciosBebidas();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnCalpisActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista el platillo Ramen, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnRamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnRamenActionPerformed
        id = 1;
        agregarALista("Ramen");
        AgarrarPrecios();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnRamenActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista la Bebida Refresco, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnRefrescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnRefrescoActionPerformed
        idBebidas = 4;
        agregarALista("Refresco");
        AgarrarPreciosBebidas();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnRefrescoActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista el platillo Curry, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnCurryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnCurryActionPerformed
        id = 2;
        agregarALista("Curry Japones");
        AgarrarPrecios();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnCurryActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista el platillo Sushi, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnSushiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSushiActionPerformed
        id= 3; 
        agregarALista("Sushi de carne y bacon");
        AgarrarPrecios();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnSushiActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista el platillo Salmon, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnSalmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSalmonActionPerformed
        id = 4;
        agregarALista("Salmon marinado japonés");
        AgarrarPrecios();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnSalmonActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista la bebida mochis de coco, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnMochisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnMochisActionPerformed
        id = 6;
        agregarALista("Mochis de coco");
        AgarrarPrecios();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnMochisActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista el platillo Waffle, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnWafleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnWafleActionPerformed
        id = 7;
        agregarALista("Bubble Waffle");
        AgarrarPrecios();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnWafleActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista la bebida te verde, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnTéActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnTéActionPerformed
        idBebidas = 1;
        agregarALista("Te Verde");
        AgarrarPreciosBebidas();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnTéActionPerformed

    /**
     *  Este boton lo que hace es enviar a la lista la bebida Sake, aparte de ponerle su prosepero ID, aparte de agarrar el precio para que vaya contando
     * @param evt 
     */
    private void JbtnSakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSakeActionPerformed
        idBebidas = 2;
        agregarALista("Sake");
        AgarrarPreciosBebidas();
        JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnSakeActionPerformed

    /**
     * Este boton manda a llamar la funcion eliminar elementos para que pueda eliminar los platillos o bebidas agregados a la lista
     * @param evt 
     */
    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        eliminarElementoSeleccionado();
    }//GEN-LAST:event_JbtnEliminarActionPerformed

    /**
     * Lo que hace este boton es enviar una confirmacion si realmente quiere enviar esa horden 
     * @param evt 
     */
    private void JbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEnviarActionPerformed
        try {
            ConfirmacionTasSeguro();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JbtnEnviarActionPerformed

    /**
     * Este boton mostrara los ingredientes del ramen, aparte de mandar su id
     * @param evt 
     */
    private void jbtnIngreRamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreRamenActionPerformed
        try {
            id = 1;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreRamenActionPerformed

    /**
     * Este boton mostrara los ingredientes del Curry, aparte de mandar su id
     * @param evt 
     */
    private void jbtnIngreCurryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreCurryActionPerformed
        try {
            id = 2;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreCurryActionPerformed

    /**
     * Este boton mostrara los ingredientes del sushi, aparte de mandar su id
     * @param evt 
     */
    private void jbtnIngreSushiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreSushiActionPerformed
        try {
            id = 3;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreSushiActionPerformed

    /**
     * Este boton mostrara los ingredientes del Salmon, aparte de mandar su id
     * @param evt 
     */
    private void jbtnIngreSalmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreSalmonActionPerformed
        try {
            id = 4;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreSalmonActionPerformed

    /**
     * Este boton mostrara los ingredientes del Waffle, aparte de mandar su id
     * @param evt 
     */
    private void jbtnIngreWaffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreWaffleActionPerformed
        try {
            id = 7;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreWaffleActionPerformed

    /**
     * Este boton mostrara los ingredientes del Mochis, aparte de mandar su id
     * @param evt 
     */
    private void jbtnIngreMochisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreMochisActionPerformed
        try {
            id = 6;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreMochisActionPerformed
  
    /**
     * Sacamos el precio de la bebida desde la base de datos
     * @param idBebida
     * @return 
     */
    public int obtenerPrecioBebidaDesdeBD(int idBebida) {
        int precio = 0;
            try (Connection conn = getConnection()) {
                // Consulta para obtener el precio de un platillo por su ID
                String query = "SELECT precio FROM bebida WHERE idBebida = ?";
                /*
                Se hace un select donde sacamos el precio de la bebida gracias a su idbebida que tenemos
                */
                PreparedStatement ps = conn.prepareStatement(query); //Ejecutar consulta
                ps.setInt(1, idBebida);
                ResultSet rs = ps.executeQuery();
                // Verificar si se encontró el precio del platillo y obtenerlo
                if (rs.next()) {
                    precio = rs.getInt("precio");
                } else {
                    System.out.println("Precio de la bebida no encontrado en la base de datos.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return precio;
    }    
    
    /**
     * Sacamos el precio del platillo desde la base de datos
     * @param idPlato para identificar el plato 
     * @return 
     */
    public int obtenerPrecioPlatoDesdeBD(int idPlato) {
        int precio = 0;
        try (Connection conn = getConnection()) {
            // Consulta para obtener el precio de un platillo por su ID
            String query = "SELECT precio FROM platillo WHERE idPlatillo = ?";
            /*
            Se hace un select donde sacamos el precio del platillo gracias a su id que tenemos
            */
            PreparedStatement ps = conn.prepareStatement(query); //Ejecutar consulta
            ps.setInt(1, idPlato);
            ResultSet rs = ps.executeQuery();
            // Verificar si se encontró el precio del platillo y obtenerlo
            if (rs.next()) {
                precio = rs.getInt("precio");
            } else {
                System.out.println("Precio del plato no encontrado en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precio;
    }

    /**
     * Método para obtener la cantidad disponible de un ingrediente en el almacén
     * @param idIngrediente para identificar el ingrediente
     * @return
     * @throws SQLException 
     */
    public static double obtenerCantidadDisponible(int idIngrediente) throws SQLException {
        // Declara e inicializa la variable cantidadDisponible como un valor double, inicialmente en 0, para almacenar la cantidad del ingrediente disponible.
        double cantidadDisponible = 0;
        // Define la consulta SQL que busca obtener la cantidad de un ingrediente específico basado en su identificador (idIngrediente) en la tabla ingredientesalmacen.
        String query = "SELECT cantidad FROM ingredientesalmacen WHERE idIngrediente = ?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) { //Ejecutar consulta
            // Asigna el valor del identificador del ingrediente a la consulta parametrizada.
            ps.setInt(1, idIngrediente);
             // Ejecuta la consulta SQL y recupera el resultado en un objeto ResultSet.
            ResultSet rs = ps.executeQuery();
             // Comprueba si hay algún resultado en el ResultSet y, en caso afirmativo, asigna la cantidad recuperada del ingrediente a la variable cantidadDisponible.
            if (rs.next()) {
                cantidadDisponible = rs.getDouble("cantidad");
            }
        } catch (SQLException e) {
            // Maneja las excepciones de tipo SQLException en caso de error durante la ejecución de la consulta SQL, proporcionando un mensaje de error detallado.
            throw new SQLException("Error al obtener la cantidad disponible del ingrediente", e);
        }
        // Devuelve la cantidad disponible del ingrediente almacenada en la variable cantidadDisponible.
        return cantidadDisponible;
    }

    /**
     * Sacamos los ingredientes que tiene un platillo
     * @param idPlatillo para identificar el platillo
     * @return
     * @throws SQLException 
     */
    public static ArrayList<String> obtenerIngredientesPorID(int idPlatillo) throws SQLException {
        ArrayList<String> ingredientes = new ArrayList<>();
        try (Connection conn = getConnection()) {
            // Consulta para obtener los ingredientes de un platillo por su ID
            String query = "SELECT i.nombre from ingrediente i join ingredientesporplatillo p on i.idIngrediente = p.idIngrediente  WHERE p.idPlatillo = ?";            
            /*
            Se hace un select del nombre de ingrediente de la tabla ingredientesporplatillo donde el idingredientes este relacionado al idplatillo
            */
            PreparedStatement ps = conn.prepareStatement(query); //Ejecutar consulta
            ps.setInt(1, idPlatillo); // se usa solo el idplatillo que se nos proporciona 
            ResultSet rs = ps.executeQuery(); 
            // Iterar sobre los resultados y añadir los ingredientes a la lista
            while (rs.next()) {
                String ingrediente = rs.getString("nombre");
                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {// Imprime la traza de la excepción en caso de error
            e.printStackTrace();
        }        
        return ingredientes; // Devuelve la lista de ingredientes
    }    
    
    /**
     * Método para obtener los ingredientes requeridos para un platillo
     * @param idPlatillo para identificar el platillo
     * @return
     * @throws SQLException 
     */
    public static Map<Integer, Double> obtenerIngredientesRequeridos(int idPlatillo) throws SQLException {
        Map<Integer, Double> ingredientesRequeridos = new HashMap<>();
        // Consulta para obtener los ingredientes necesarios para el platillo por su ID
        String query = "SELECT idIngrediente, cantidad FROM ingredientesporplatillo WHERE idPlatillo = ?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) { //Ejecutar consulta
            // Prepara la consulta SQL para obtener los ingredientes necesarios para un platillo.
            ps.setInt(1, idPlatillo);
            // Ejecuta la consulta SQL y recupera los resultados en un objeto ResultSet.
            ResultSet rs = ps.executeQuery();
            // Itera a través de los resultados obtenidos en el ResultSet.
            while (rs.next()) {
                // Obtiene el identificador del ingrediente y la cantidad requerida para el platillo.
                int idIngrediente = rs.getInt("idIngrediente");
                double cantidadRequerida = rs.getDouble("cantidad");
                // Almacena los ingredientes requeridos y sus cantidades en un mapa (ingredientesRequeridos).
                ingredientesRequeridos.put(idIngrediente, cantidadRequerida);
            }
        } catch (SQLException e) {
            // Maneja las excepciones de tipo SQLException en caso de error durante la ejecución de la consulta SQL.
            throw new SQLException("Error al obtener los ingredientes requeridos para el platillo", e);
        }
        // Retorna los ingredientes requeridos para el platillo.
        return ingredientesRequeridos;
    }

    /**
     * Método para verificar la disponibilidad total de ingredientes para un platillo específico
     * @param idPlatillo para identificar el platillo
     * @return  Valor boolean que identifica si hay suficientes ingredientes para el platillo
     * @throws SQLException 
     */
    public static boolean verificarDisponibilidadTotalPlatillo(int idPlatillo) throws SQLException {
        boolean existencia = true;
        // Obtiene los ingredientes requeridos y sus cantidades para el platillo específico
        Map<Integer, Double> ingredientesRequeridos = obtenerIngredientesRequeridos(idPlatillo);
        // Verificar la disponibilidad total de cada ingrediente requerido para el platillo
        for (Map.Entry<Integer, Double> entry : ingredientesRequeridos.entrySet()) {
            int idIngrediente = entry.getKey();
            double cantidadRequerida = entry.getValue();
            double cantidadDisponible = obtenerCantidadDisponible(idIngrediente);
            // Se compara la cantida disponible en el almacen con la cantidad requerida del platillo
            if (cantidadDisponible < cantidadRequerida) {
                // System.out.println("No hay suficientes ingredientes para el platillo con ID " + idPlatillo);
                // Si no hay platillos regresara un falso que significara que no hay
                existencia = false;
                break;
           } else {
                //System.out.println("Hay suficientes ingredientes para el platillo con ID " + idPlatillo);
            }
        }
        // Retorna la existencia del platillo para confirmar que si hay ingredientes
        return existencia;
    }
    
    /**
     * Obtiene la cantidad disponible que hay en el almacen para las bebidas
     * @param idBebida para identificar la bebida
     * @return
     * @throws SQLException 
     */
    public static double obtenerCantidadDisponibleBebida(int idBebida) throws SQLException {
        double cantidadDisponible = 0; // Variable para almacenar la cantidad disponible de la bebida
        // Consulta SQL para obtener la cantidad disponible de la bebida en el almacén
        String query = "SELECT cantidad FROM bebidasalmacen WHERE idBebida = ?";
        try (Connection conn = getConnection(); // Establece la conexión a la base de datos
            PreparedStatement ps = conn.prepareStatement(query)) { // Prepara la declaración SQL
            // Establece el parámetro del ID de la bebida en la consulta SQL
            ps.setInt(1, idBebida);
            // Ejecuta la consulta SQL y obtiene el resultado
            ResultSet rs = ps.executeQuery();
            // Verifica si se encontró algún resultado
            if (rs.next()) {
                // Obtiene la cantidad disponible de la bebida
                cantidadDisponible = rs.getDouble("cantidad");
            }
        } catch (SQLException e) {
            // En caso de error, lanza una excepción con un mensaje descriptivo donde hay 
            throw new SQLException("Error al obtener la cantidad disponible de la bebida", e);
        }
        return cantidadDisponible; // Retorna la cantidad disponible de la bebida en el almacén
    }
    
    /**
     * Verifica la disponibilidad de la bebida, para saber si hay o no
     * @param idBebida para identificar la bebida
     * @return
     * @throws SQLException 
     */
    public static boolean verificarDisponibilidadTotalBebidas(int idBebida) throws SQLException {
        boolean existenciaB = true; 
        // Obtiene los ingredientes requeridos y sus cantidades para el platillo específico
        double cantidadDisponible = obtenerCantidadDisponibleBebida(idBebida);
        if (cantidadDisponible <=0 ) {
            // En caso de que no encuentre ingredientes devuelve un falso de que no hay
            existenciaB = false;
        }
        // retorna un true que significa que si hay disponibilidad de bebidas
        return existenciaB;
    }

    
    /**
     * Funcion que permite agarrar los ingredientes del platillo mediante su id
     * @throws SQLException 
     */
    private void AgarrarIngrediente() throws SQLException {
        int idPlatillo = id; // Obtienes el ID del platillo 
        JFrame ventanaIngredientes = new JFrame("Ingredientes"); // Se abre una ventana
        ventanaIngredientes.setLayout(new BorderLayout());
        // Llamas al método de tu clase ConexionBD para obtener los ingredientes del platillo
        ArrayList<String> ingredientes = obtenerIngredientesPorID(idPlatillo);
        // Haces una ventana para poder imprimir los ingredientes
        JTextArea areaIngredientes = new JTextArea();
        for (String ingrediente : ingredientes) {
            areaIngredientes.append(ingrediente +"\n");
        }
        ventanaIngredientes.add(new JScrollPane(areaIngredientes), BorderLayout.CENTER);
        ventanaIngredientes.setSize(300, 200);
        ventanaIngredientes.setLocationRelativeTo(this);
        ventanaIngredientes.setVisible(true);
        id = 0; // Se iguala a 0 para poder reutilizarlo para cualquier id
        // JOptionPane.showMessageDialog(this, areaIngredientes, "Ingredientes del Platillo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Cada vez que se agarre los platillos se pueda agarrar los precios y ponerlos
     */
    private void AgarrarPrecios(){
        int idPlatillo = id; // Se está asignando el valor de id a la variable del platillo para identificarlo para enviarlo a la conexion
        int precioPlato = obtenerPrecioPlatoDesdeBD(idPlatillo); // Se envia el idPlatillo, se utilizara para sacar el precio, el resultado sera asignado a precioplato
        total += precioPlato; // Se suma el precio que se lleva acomulado al que se agarro
        id = 0; // Se establece el valor de id en cero
    }
    
    /**
     * Cada vez que se envie las bebidas se pueda agarrar los precios y ponerlos
     */
    private void AgarrarPreciosBebidas(){
        int idbebi = idBebidas; // Se está asignando el valor de id a la variable de la bebida para identificarlo para enviarlo a la conexion
        int precioBebida = obtenerPrecioBebidaDesdeBD(idbebi); // Se envia el idBebida, se utilizara para sacar el precio, el resultado sera asignado a precioplato
        total += precioBebida; // Se suma el precio que se lleva acomulado al que se agarro
        idBebidas = 0; // Se establece el valor de id en cero de bebidas
    }
    
    /**
     * Se agrega al Jlist el platillo o bebida que se selecciono
     * @param elemento 
     */
    public void agregarALista(String elemento) {
        modeloLista.addElement(elemento); // Se agrega el elemento que se selecciono (El elemento son los string de cada boton (Ramen,Bebida etc))
    }
    
    /**
     * Se elemina lo seleccionado sea Bebida o Platillo
     */
    private void eliminarElementoSeleccionado() { 
        int indiceSeleccionado = Pedido.getSelectedIndex(); // Agarra el elemento seleccionado en el Jlist
        if (indiceSeleccionado != -1) { 
        String elementoSeleccionado = (String) modeloLista.getElementAt(indiceSeleccionado); // Agarra el elemento y lo guarda en un string 
        // Se agarra la funcion para poder compararlo con otro string para saber que se eliminara enviando el elemento que se seleccionó
        double  precioARestar = obtenerPrecioDelElemento(elementoSeleccionado);
        total -= precioARestar; // Se resta el precio del elemento seleccionado junto con lo que se lleva acomulado
        // Eliminar el platillo y su precio de los modelos
        modeloLista.remove(indiceSeleccionado);
        // Actualizar la visualización del total
        JlblTotal.setText("$"+ total);
        }
    }
    
    /**
     * Se Compara el elemento obtenido para saber el precio a restar
     * @param Elemento para saber el elemento a comparar
     * @return 
     */
    private double obtenerPrecioDelElemento(String Elemento) {
        // Aquí simulas obtener el precio del platillo
        // Reemplaza esta lógica con tu manera real de obtener el precio del platillo
        if (Elemento.equals("Ramen")) {
            return 370.0;
        } else if (Elemento.equals("Curry Japonés")) {
            return 230.0;
        }else if (Elemento.equals("Sushi de carne y bacon")) {
            return 200.0;
        }else if (Elemento.equals("Salmon marinado japonés")) {
            return 400.0;
        }else if (Elemento.equals("Mochis de coco")) {
            return 180.0;
        }else if (Elemento.equals("Bubble Waffle")) {
            return 80.0;
        }else if (Elemento.equals("Refresco")) {
            return 20.0;
        }else if (Elemento.equals("Te Verde")) {
            return 60.0;
        }else if (Elemento.equals("Sake")) {
            return 120.0;
        }else if (Elemento.equals("Calpis")) {
            return 70.0;
        }
        return 0.0;
    }
    
    /**
     * Una ventana que se mostrara cuando quiera enviar la comanda, preguntando si esta seguro de su decicion
     * @throws SQLException 
     */
    private void ConfirmacionTasSeguro() throws SQLException{
        double Iva;
        double Final;
        Final = (total*iva) + total;
        int confirmacion = JOptionPane.showConfirmDialog(this,"¿Estás seguro de continuar? pagaras $"+Final+"pesos", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            // El usuario ha hecho clic en "Sí", realiza la acción que deseas
            mostrarVentanaDeConfirmacion(Final);
            try {
                new VentanaEstado(idSucursal,idMesa).setVisible(true);
                VentanaEstado.cambiarEstado();
                
                this.dispose();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } else {
            // El usuario ha hecho clic en "No" o ha cerrado el diálogo
            // Puedes realizar alguna otra acción si lo deseas
            // Por ejemplo, no hacer nada o mostrar un mensaje de cancelación
            JOptionPane.showMessageDialog(this, "Operación cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Se muestra una ventana donde dice que ya esta el pedido hecho, pero lo mas importante es que se envia a la base de datos una comanda,el recibo a imprimir y enviar las bebida y platillos por comanda
     * @param Final para utilizar el final a pagar
     * @throws SQLException 
     */
    private void mostrarVentanaDeConfirmacion(double Final) throws SQLException {
        LocalDateTime fechaHoraActual = LocalDateTime.now(); // Poder saber la hora y la fecha actaul
        Connection conn = ConexionBD.getConnection(); // Conectar a la Base de datos
        String queru = "SELECT MAX(idComanda) + 1 as idComanda FROM comanda;";
        try (PreparedStatement max = conn.prepareStatement(queru)) { //Ejecutar consulta
            ResultSet maxVar = max.executeQuery();
            if (maxVar.next()) {
                idComanda = maxVar.getInt("idComanda");
            }
        }catch (SQLException e) {
            // Manejo de errores (reemplaza con tu manejo específico de excepciones)
            e.printStackTrace();
        }
        String quere = "SELECT MAX(idRecibo) + 1 as idRecibo FROM recibo";
        try (PreparedStatement maxRecibo = conn.prepareStatement(quere)) { //Ejecutar consulta
            ResultSet maxRe = maxRecibo.executeQuery();
            if (maxRe.next()) {
                idRecibo = maxRe.getInt("idRecibo");
            }
        }catch (SQLException e) {
            // Manejo de errores (reemplaza con tu manejo específico de excepciones)
            e.printStackTrace();
        }
        //Obtener el FolioReserva
        String queryObtenerFolio = "SELECT FolioReserva FROM reservacion r JOIN mesa m ON m.idMesa = r.idMesa WHERE r.fecha = CURRENT_DATE() AND TIMEDIFF(CURRENT_TIME(), r.hora) < '00:20:00' AND m.Estado = 2 AND m.idMesa = ?";
        /*
        Se están seleccionando los FolioReserva de la tabla reservacion.
        Se está haciendo un JOIN entre las tablas reservacion y mesa usando idMesa.
        Se aplican múltiples condiciones utilizando el WHERE:
        r.fecha = CURRENT_DATE(): Busca reservaciones que tengan la fecha actual.
        TIMEDIFF(CURRENT_TIME(), r.hora) < '00:20:00': Verifica si la diferencia entre la hora actual y la hora de la reserva es menor a 20 minutos.
        m.Estado = 2: Filtra las mesas que tienen un estado igual a 2.
        m.idMesa = 1: Se limita la búsqueda a la mesa con idMesa que se haya seleccionado.
        */
        try (PreparedStatement pstmtFolio = conn.prepareStatement(queryObtenerFolio)) { //Ejecutar consulta
            pstmtFolio.setInt(1, idMesa); // Se agrega el id de la mesa que se use
            ResultSet rsFolio = pstmtFolio.executeQuery(); // Agarra el resultado para usarlo
            if (rsFolio.next()) {
                folioReserva = rsFolio.getInt("FolioReserva"); // Se asigna el resulta en folio de Reserva
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // En caso de que marque error
        }
        if (folioReserva <= 0){
            String queryObtenerFolioNO = "SELECT MAX(FolioReserva) + 1 as FolioReserva FROM reservacion";
            try (PreparedStatement pstmtFolioExtra = conn.prepareStatement(queryObtenerFolioNO)) { //Ejecutar consulta
                ResultSet rsFolioPorsi = pstmtFolioExtra.executeQuery(); // Agarra el resultado para usarlo
                if (rsFolioPorsi.next()) {
                    folioReserva = rsFolioPorsi.getInt("FolioReserva"); // Se asigna el resulta en folio de Reserva
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // En caso de que marque error
            }
            String queryNuevaReserv = "insert into reservacion values (?,NULL,CURRENT_DATE,CURRENT_TIME,?)";
            try (PreparedStatement pstReserva = conn.prepareStatement(queryNuevaReserv)) { //Ejecutar consulta
                pstReserva.setInt(1, folioReserva); // Se asigna el folio de reserva ya hecho anteriormente en la comanda
                pstReserva.setDouble(2,idMesa);
                pstReserva.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }      
        }
        String quers = "INSERT INTO comanda VALUES (?, CURRENT_DATE, CURRENT_TIME,?)";
         /*
        Se están seleccionando los FolioReserva de la tabla reservacion.
        Se está haciendo un JOIN entre las tablas reservacion y mesa usando idMesa.
        Se aplican múltiples condiciones utilizando el WHERE:
        r.fecha = CURRENT_DATE(): Busca reservaciones que tengan la fecha actual.
        TIMEDIFF(CURRENT_TIME(), r.hora) < '00:20:00': Verifica si la diferencia entre la hora actual y la hora de la reserva es menor a 20 minutos.
        m.Estado = 2: Filtra las mesas que tienen un estado igual a 2.
        m.idMesa = 1: Se limita la búsqueda a la mesa con idMesa que se haya seleccionado.
          */
        try (PreparedStatement pstmt = conn.prepareStatement(quers)) { //Ejecutar consulta
            pstmt.setInt(1,idComanda);  // Se agrega el id de la comanda
            pstmt.setInt(2,folioReserva);    //Se agrega el folio de la reserva relacionada con la comanda
            pstmt.executeUpdate(); // Agarra el resultado para usarlo
        } catch (SQLException ex){
            System.out.println(ex);
        }
        // Inserción en la tabla recibo
        String queryInsercion = "INSERT INTO recibo VALUES (?, ?, ?, CURRENT_DATE, CURRENT_TIME, ?, ?)";
        /* 
        Se esta enviando un insert de la tabla recibo donde la variables seran el idComanda, el folio de reserva, la fecha , hora, totalsiniva, totalconiva
        LAs variables los sacamos de neatbeans, lo mas nuevo seria las funciones CURRENT_DATE, CURRENT_TIME, uno es para sacar la fecha de ese momento que se haga la consulta, lo mismo para la hora
        */
        try (PreparedStatement pstmtInsercion = conn.prepareStatement(queryInsercion)) { //Ejecutar consulta
            pstmtInsercion.setInt(1,idRecibo);
            pstmtInsercion.setInt(2, idComanda); // Se asigna un idcomanda
            pstmtInsercion.setInt(3, folioReserva); // Se asigna el folio de reserva ya hecho anteriormente en la comanda
            pstmtInsercion.setDouble(4, total); // se asigna el total almacenado en la comanda
            pstmtInsercion.setDouble(5, Final); // se asigna doto junto con el iva 
            pstmtInsercion.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }      

        /////////////////////////////////////////////////////////////////
        // Se agarra la fecha que se uso en ese momento
        int dia = fechaHoraActual.getDayOfMonth(); 
        int mes = fechaHoraActual.getMonthValue();
        int anio = fechaHoraActual.getYear();
        // Se asigna la hora completa
        int hora = fechaHoraActual.getHour();
        int minuto = fechaHoraActual.getMinute();
        int segundo = fechaHoraActual.getSecond();
        String filename = "Recibo" + idRecibo + ".pdf"; // Documento para el pdf(Recibo)
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            // Hacemos el formato del documento
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 700);
                // Ponemos los detalles que se pondran en el recibo
                contentStream.showText("Detalles del Recibo:" + idRecibo);
                contentStream.newLine();
                contentStream.showText("idComanda: " + idComanda); 
                contentStream.newLine();
                contentStream.showText("Folio de Reservacion:"+ folioReserva);
                contentStream.newLine();
                contentStream.showText("Fecha:" + dia + "/" + mes + "/" + anio);
                contentStream.newLine();
                contentStream.showText("Hora:"+ hora + ":" + minuto + ":" + segundo);
                contentStream.newLine();
                contentStream.showText("Total sin IVA " + total);
                contentStream.newLine();
                contentStream.showText("Total con IVA: " + Final);
                contentStream.newLine();
                contentStream.endText();
            }
            document.save(filename);
            // Abrir el PDF generado
            if (Desktop.isDesktopSupported()) {
                try {
                    File pdfFile = new File(filename);
                    Desktop.getDesktop().open(pdfFile);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al abrir el archivo PDF.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede abrir el archivo PDF en este sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al generar el archivo PDF.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }   
        enviarComanda(juntarLista());
        limpiarLista(); // Limpia la lista 
        inicializarObjetos(); 
    }
    
    /**
     * Encontrar elementos duplicados y generar una lista con las cantidades de cada elemento.
     * @return 
     */
    public ArrayList<String[]> juntarLista() {
        // Lista que contendrá los elementos únicos con sus cantidades
    ArrayList<String[]> lista = new ArrayList<>();
    // Recorre el modeloLista para procesar los elementos
    for (int i = 0; i < modeloLista.getSize(); i++) {
        String orden = modeloLista.getElementAt(i);
        boolean encontrado = false;
        // Comprobar si el elemento ya está en la lista
        for (String[] copia : lista) {
            if (copia[0].equals(orden)) {
                encontrado = true;
                break;
            }
        }
        // Si no está en la lista, cuenta cuántas veces aparece y agrega el elemento con su recuento
        if (!encontrado) {
            int cuenta = 0;
            for (int j = 0; j < modeloLista.getSize(); j++) {
                if (modeloLista.getElementAt(j).equals(orden)) {
                    cuenta++;
                }
            }
            String[] arreglo = {orden, String.valueOf(cuenta)};
            lista.add(arreglo);
        }
    }
        // Retorna la lista con los elementos encontrados
        return lista;
    }
    
    /**
     * Procesa la lista de elementos y los inserta en la base de datos según si son bebidas o platillos.
     * @param lista para identificar el elemento de la lista a usar
     * @throws SQLException 
     */
    private static void enviarComanda(ArrayList<String[]> lista) throws SQLException {
        // Obtener el ID de comanda general
        int idCma = idComanda;
        // Variables para los ID de comanda específicos para platillos y bebidas
        int idCmaPlatillo = idCma;
        int idCmaBebida = idCma;
        // Iterar sobre la lista de elementos
        for (int i = 0; i < lista.size(); i++) {
            try (Connection conn = getConnection()) {
                String nombre = lista.get(i)[0];
                int cantidad = Integer.parseInt(lista.get(i)[1]);
                String query;
                int idCmaActual;
                // Si es una bebida, se usa el procedimiento almacenado para insertar en la tabla correspondiente
                if (esBebida(nombre)) {
                    query = "call insertarbebidasporcomanda (?,(SELECT idBebida FROM bebida WHERE nombre = ?),?)";
                    /*
                    Se usa un stored procedure ya incluido en la base de datos, se usa para incluir el idComanda, el id bebida mediante el nombre sacado del Jlist, 
                    la cantidad que quiere, el precio unitario con solo saber el id se saca solo gracias al procedure
                    */
                    idCmaActual = idCmaBebida;
                } else {
                    // Si es un platillo, se usa el procedimiento almacenado para insertar en la tabla correspondiente
                    query = "call insertarplatillosporcomanda (?,(SELECT idPlatillo FROM platillo WHERE nombre = ?),?)";
                    /*
                    Se usa un stored procedure ya incluido en la base de datos, se usa para incluir el idComanda, el id platillo mediante el nombre sacado del Jlist, 
                    la cantidad que quiere, el precio unitario con solo saber el id se saca solo gracias al procedure
                    */
                    idCmaActual = idCmaPlatillo;
                }
                // Preparar y ejecutar la consulta SQL
                PreparedStatement ps = conn.prepareStatement(query); //Ejecutar consulta
                ps.setInt(1, idCmaActual);
                ps.setString(2, nombre);
                ps.setInt(3, cantidad);
                //ps.setString(4, nombre);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al insertar en la base de datos: " + ex);
            }
        }
    }
    
    /**
     * Se usa para la comparativa para saber si son bebidas o no
     * @param nombre para comparar el elemento si es bebida o no
     * @return 
     */
    private static boolean esBebida(String nombre) {
        List<String> bebidas = Arrays.asList("Calpis", "Sake", "Te Verde", "Refresco"); // Se utilizara para saber si son bebidas o no gracias a ellos
        return bebidas.contains(nombre); // retorna una un true si coincide con los nombres , si no se envia un false
    }
   
    /**
     * Borra la lista y tambien los costos que llevan almacenados
     */
    private void limpiarLista() {
        modeloLista.removeAllElements(); // Remueve todo lo de la lista
        total = 0; // Pone en 0 el total almacenado
        JlblTotal.setText("Total: $" + total);
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run(){
                new VentanaMenu(idSucursal, idMesa).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnCalpis;
    private javax.swing.JButton JbtnCurry;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JButton JbtnEnviar;
    private javax.swing.JButton JbtnMochis;
    private javax.swing.JButton JbtnRamen;
    private javax.swing.JButton JbtnRefresco;
    private javax.swing.JButton JbtnSake;
    private javax.swing.JButton JbtnSalmon;
    private javax.swing.JButton JbtnSushi;
    private javax.swing.JButton JbtnTé;
    private javax.swing.JButton JbtnWafle;
    private javax.swing.JLabel JlblTotal;
    private javax.swing.JList<String> Pedido;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnIngreCurry;
    private javax.swing.JButton jbtnIngreMochis;
    private javax.swing.JButton jbtnIngreRamen;
    private javax.swing.JButton jbtnIngreSalmon;
    private javax.swing.JButton jbtnIngreSushi;
    private javax.swing.JButton jbtnIngreWaffle;
    // End of variables declaration//GEN-END:variables
}