/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectoborra2;
import java.sql.Connection;
import Conexion.ConexionBD;
import static Conexion.ConexionBD.getConnection;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;


/**
 *
 * @author Daniel
 */
public class Ventana extends javax.swing.JFrame {
    private JList<String> platillo;
    ConexionBD conexion;    
    private DefaultListModel<String> modeloLista;
    private Map<String, Integer> nombrePlatilloID;
    private Map<Integer, String> idNombrePlatillo; 
    private Map<String, Double> preciosPorPlatillo;
    private int id;
    private static int idComanda;
    private int idBebidas;
    private double total = 0.0; // Variable para almacenar el total acumulado
    private double totalIva = 0.0;
    /**
     * Creates new form Ventana
     */
    public Ventana() {
        idNombrePlatillo = new HashMap<>();
        preciosPorPlatillo = new HashMap<>();

        // Simular llenar los mapas 'idNombrePlatillo' y 'preciosPorPlatillo' con datos de platillos
        idNombrePlatillo.put(1, "Ramen");
        idNombrePlatillo.put(2, "Curry");
        // Agrega más platillos si es necesario

        preciosPorPlatillo.put("Ramen", 9.99);
        preciosPorPlatillo.put("Curry", 8.49);
        
        initComponents();
        inicializarObjetos();
        modeloLista = new DefaultListModel<>();
        Pedido.setModel(modeloLista);
        conexion = new ConexionBD(); 
    }
    
     private void inicializarObjetos() {
                     try {
                         for (int i = 1; i <= 7; i++) {
           boolean existencia = verificarDisponibilidadTotalPlatillo(i);
           if (!existencia){
               ImageIcon iconoN;
           switch (i){
               case 1:
                   iconoN = new ImageIcon ("src\\Imagenes\\RamenN.jpg");
                   JbtnRamen.setIcon(iconoN);
                   JbtnRamen.setEnabled(false);
                   break;
               case 2:
                   iconoN = new ImageIcon ("src\\Imagenes\\CurryN.png");
                   JbtnCurry.setIcon(iconoN);
                   JbtnCurry.setEnabled(false);
                   break;
               case 3:
                   iconoN = new ImageIcon ("src\\Imagenes\\SushiN.jpg");
                   JbtnSushi.setIcon(iconoN);
                   JbtnSushi.setEnabled(false);
                   break;
               case 4:
                   iconoN = new ImageIcon ("src\\Imagenes\\SalmonMarinadoN.jpg");
                   JbtnSalmon.setIcon(iconoN);
                   JbtnSalmon.setEnabled(false);
                   break;
               case 6:
                   iconoN = new ImageIcon ("src\\Imagenes\\MochisN.jpg");
                   JbtnMochis.setIcon(iconoN);
                   JbtnMochis.setEnabled(false);
                   break;
               case 7:
                   iconoN = new ImageIcon ("src\\Imagenes\\BubbleWaffleN.png");
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
        try{
                         for (int j = 1; j <= 4; j++) {
           boolean existenciaB = verificarDisponibilidadTotalBebidas(j);
           if (!existenciaB){
               ImageIcon iconoN;
           switch (j){
               case 1:
                   iconoN = new ImageIcon ("src\\Imagenes\\TeVerdeN.png");
                   JbtnTé.setIcon(iconoN);
                   JbtnTé.setEnabled(false);
                   break;
               case 2:
                   iconoN = new ImageIcon ("src\\Imagenes\\SakeN.png");
                   JbtnSake.setIcon(iconoN);
                   JbtnSake.setEnabled(false);
                   break;
               case 3:
                   iconoN = new ImageIcon ("src\\Imagenes\\CalpisN.png");
                   JbtnCalpis.setIcon(iconoN);
                   JbtnCalpis.setEnabled(false);
                   break;
               case 4:
                   iconoN = new ImageIcon ("src\\Imagenes\\RefrescoN.png");
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");

        jPanel4.setBackground(new java.awt.Color(32, 17, 162));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(85, 231, 255), 5, true));

        JbtnEnviar.setBackground(new java.awt.Color(255, 0, 0));
        JbtnEnviar.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        JbtnEnviar.setForeground(new java.awt.Color(85, 231, 255));
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
        jLabel7.setText("Orden a Enviar:");

        JlblTotal.setFont(new java.awt.Font("Barlow Condensed Medium", 1, 24)); // NOI18N
        JlblTotal.setForeground(new java.awt.Color(85, 231, 255));
        JlblTotal.setText("$");

        JbtnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        JbtnEliminar.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        JbtnEliminar.setForeground(new java.awt.Color(85, 231, 255));
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
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(JlblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(JbtnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JbtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JlblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JbtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JbtnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(32, 17, 72));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(85, 231, 255), 5, true));

        JbtnRamen.setBackground(new java.awt.Color(153, 255, 153));
        JbtnRamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/RamenD.jpg"))); // NOI18N
        JbtnRamen.setFocusPainted(false);
        JbtnRamen.setFocusable(false);
        JbtnRamen.setRequestFocusEnabled(false);
        JbtnRamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnRamenActionPerformed(evt);
            }
        });

        JbtnCurry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CurryD.png"))); // NOI18N
        JbtnCurry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnCurryActionPerformed(evt);
            }
        });

        JbtnSushi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SushiD.jpg"))); // NOI18N
        JbtnSushi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSushiActionPerformed(evt);
            }
        });

        JbtnSalmon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SalmonMarinadoD.jpg"))); // NOI18N
        JbtnSalmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSalmonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(85, 231, 255));
        jLabel4.setText("Platillos:");

        JbtnMochis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MochisD.jpg"))); // NOI18N
        JbtnMochis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnMochisActionPerformed(evt);
            }
        });

        JbtnWafle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BubbleWaffleD.png"))); // NOI18N
        JbtnWafle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnWafleActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(85, 231, 255));
        jLabel5.setText("Postres:");

        jLabel6.setFont(new java.awt.Font("Bahiana", 2, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(85, 231, 255));
        jLabel6.setText("Bebidas:");

        JbtnRefresco.setBackground(new java.awt.Color(153, 255, 153));
        JbtnRefresco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/RefrescoD.png"))); // NOI18N
        JbtnRefresco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnRefrescoActionPerformed(evt);
            }
        });

        JbtnCalpis.setBackground(new java.awt.Color(153, 255, 153));
        JbtnCalpis.setForeground(new java.awt.Color(153, 255, 153));
        JbtnCalpis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CalpisD.png"))); // NOI18N
        JbtnCalpis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnCalpisActionPerformed(evt);
            }
        });

        JbtnTé.setBackground(new java.awt.Color(153, 255, 153));
        JbtnTé.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/TeVerdeD.png"))); // NOI18N
        JbtnTé.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnTéActionPerformed(evt);
            }
        });

        JbtnSake.setBackground(new java.awt.Color(153, 255, 153));
        JbtnSake.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/SakeD.png"))); // NOI18N
        JbtnSake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnSakeActionPerformed(evt);
            }
        });

        jbtnIngreRamen.setBackground(new java.awt.Color(255, 255, 255));
        jbtnIngreRamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreRamen.setContentAreaFilled(false);
        jbtnIngreRamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreRamenActionPerformed(evt);
            }
        });

        jbtnIngreCurry.setBackground(new java.awt.Color(255, 255, 255));
        jbtnIngreCurry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreCurry.setContentAreaFilled(false);
        jbtnIngreCurry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreCurryActionPerformed(evt);
            }
        });

        jbtnIngreSushi.setBackground(new java.awt.Color(255, 255, 255));
        jbtnIngreSushi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreSushi.setContentAreaFilled(false);
        jbtnIngreSushi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreSushiActionPerformed(evt);
            }
        });

        jbtnIngreSalmon.setBackground(new java.awt.Color(255, 255, 255));
        jbtnIngreSalmon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreSalmon.setContentAreaFilled(false);
        jbtnIngreSalmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreSalmonActionPerformed(evt);
            }
        });

        jbtnIngreWaffle.setBackground(new java.awt.Color(255, 255, 255));
        jbtnIngreWaffle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ingredientes.png"))); // NOI18N
        jbtnIngreWaffle.setContentAreaFilled(false);
        jbtnIngreWaffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIngreWaffleActionPerformed(evt);
            }
        });

        jbtnIngreMochis.setBackground(new java.awt.Color(255, 255, 255));
        jbtnIngreMochis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Ingredientes.png"))); // NOI18N
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/Logo Neo Tokio.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(jbtnIngreRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(61, 61, 61)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jbtnIngreCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(JbtnRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(JbtnCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(jLabel10)
                                                .addGap(17, 17, 17)
                                                .addComponent(jbtnIngreSushi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(37, 37, 37)
                                                .addComponent(JbtnSushi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(JbtnRefresco, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(JbtnTé, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(138, 138, 138)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(JbtnCalpis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(JbtnSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jbtnIngreSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JbtnMochis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jbtnIngreMochis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel12))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(37, 37, 37)
                                .addComponent(JbtnWafle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel5)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JbtnSake, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnIngreWaffle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JbtnSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JbtnCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JbtnRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9)
                                                .addComponent(jbtnIngreCurry, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(35, 35, 35))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jbtnIngreRamen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8))
                                            .addGap(30, 30, 30)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel10)
                                                .addComponent(jbtnIngreSushi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel11)
                                                .addComponent(jbtnIngreSalmon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(22, 22, 22)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(JbtnMochis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtnIngreMochis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(JbtnWafle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtnIngreWaffle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))))
                    .addComponent(JbtnSushi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JbtnRefresco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JbtnCalpis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JbtnTé, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JbtnSake, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JbtnCalpisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnCalpisActionPerformed
        idBebidas = 3; 
        agregarALista("Calpis");
        AgarrarPreciosBebidas();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnCalpisActionPerformed

    private void JbtnRamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnRamenActionPerformed

      id = 1;
      agregarALista("Ramen");
      AgarrarPrecios();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnRamenActionPerformed

    private void JbtnRefrescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnRefrescoActionPerformed
       idBebidas = 4;
        agregarALista("Refresco");
       AgarrarPreciosBebidas();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnRefrescoActionPerformed

    private void JbtnCurryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnCurryActionPerformed
       id = 2;
        agregarALista("Curry Japones");
       AgarrarPrecios();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnCurryActionPerformed

    private void JbtnSushiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSushiActionPerformed
        id= 3; 
        agregarALista("Sushi de carne y bacon");
         AgarrarPrecios();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnSushiActionPerformed

    private void JbtnSalmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSalmonActionPerformed
        id = 4;
        agregarALista("Salmon marinado japonés");
        AgarrarPrecios();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnSalmonActionPerformed

    private void JbtnMochisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnMochisActionPerformed
        id = 6;
        agregarALista("Mochis de coco");
        AgarrarPrecios();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnMochisActionPerformed

    private void JbtnWafleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnWafleActionPerformed
        id = 7;
        agregarALista("Bubble Waffle");
        AgarrarPrecios();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnWafleActionPerformed

    private void JbtnTéActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnTéActionPerformed
        idBebidas = 1;
        agregarALista("Te Verde");
        AgarrarPreciosBebidas();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnTéActionPerformed

    private void JbtnSakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnSakeActionPerformed
        idBebidas = 2;
        agregarALista("Sake");
        AgarrarPreciosBebidas();
      JlblTotal.setText("$"+ total);
    }//GEN-LAST:event_JbtnSakeActionPerformed

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
         eliminarElementoSeleccionado();
    }//GEN-LAST:event_JbtnEliminarActionPerformed

    private void JbtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEnviarActionPerformed
        try {
            ConfirmacionTasSeguro();
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JbtnEnviarActionPerformed

    private void jbtnIngreRamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreRamenActionPerformed
        try {
            id = 1;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreRamenActionPerformed

    private void jbtnIngreCurryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreCurryActionPerformed
        try {
            id = 2;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreCurryActionPerformed

    private void jbtnIngreSushiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreSushiActionPerformed
        try {
            id = 3;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreSushiActionPerformed

    private void jbtnIngreSalmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreSalmonActionPerformed
        try {
            id = 4;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreSalmonActionPerformed

    private void jbtnIngreWaffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreWaffleActionPerformed
        try {
            id = 7;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreWaffleActionPerformed

    private void jbtnIngreMochisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIngreMochisActionPerformed
        try {
            id = 6;
            AgarrarIngrediente();
        } catch (SQLException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnIngreMochisActionPerformed

        // Método para obtener la cantidad disponible de un ingrediente en el almacén
    public static double obtenerCantidadDisponible(int idIngrediente) throws SQLException {
        double cantidadDisponible = 0;
        String query = "SELECT cantidad FROM ingredientesalmacen WHERE idIngrediente = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idIngrediente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cantidadDisponible = rs.getDouble("cantidad");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la cantidad disponible del ingrediente", e);
        }

        return cantidadDisponible;
    }

    // Método para obtener los ingredientes requeridos para un platillo
    public static Map<Integer, Double> obtenerIngredientesRequeridos(int idPlatillo) throws SQLException {
        Map<Integer, Double> ingredientesRequeridos = new HashMap<>();

        // Consulta para obtener los ingredientes necesarios para el platillo por su ID
        String query = "SELECT idIngrediente, cantidad FROM ingredientesporplatillo WHERE idPlatillo = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idPlatillo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idIngrediente = rs.getInt("idIngrediente");
                double cantidadRequerida = rs.getDouble("cantidad");
                ingredientesRequeridos.put(idIngrediente, cantidadRequerida);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los ingredientes requeridos para el platillo", e);
        }

        return ingredientesRequeridos;
    }

    // Método para verificar la disponibilidad total de ingredientes para un platillo específico
    public static boolean verificarDisponibilidadTotalPlatillo(int idPlatillo) throws SQLException {
        boolean existencia = true;
        Map<Integer, Double> ingredientesRequeridos = obtenerIngredientesRequeridos(idPlatillo);
        // Verificar la disponibilidad total de cada ingrediente requerido para el platillo
        for (Map.Entry<Integer, Double> entry : ingredientesRequeridos.entrySet()) {
            int idIngrediente = entry.getKey();
            double cantidadRequerida = entry.getValue();
            double cantidadDisponible = obtenerCantidadDisponible(idIngrediente);
            
            //System.out.println("id:"+idIngrediente+ "Cantidad " + cantidadRequerida + " CantidadDispo"+cantidadDisponible );
            if (cantidadDisponible < cantidadRequerida) {
                // System.out.println("No hay suficientes ingredientes para el platillo con ID " + idPlatillo);
                existencia = false;
                break;
           } else {
                //System.out.println("Hay suficientes ingredientes para el platillo con ID " + idPlatillo);
            }
        }
       return existencia;
    }
    
    public static double obtenerCantidadDisponibleBebida(int idBebida) throws SQLException {
        double cantidadDisponible = 0;
        String query = "SELECT cantidad FROM bebidasalmacen WHERE idBebida = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idBebida);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cantidadDisponible = rs.getDouble("cantidad");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la cantidad disponible de la bebida", e);
        }

        return cantidadDisponible;
    }
    
    public static boolean verificarDisponibilidadTotalBebidas(int idBebida) throws SQLException {
    boolean existenciaB = true;
    double cantidadDisponible = obtenerCantidadDisponibleBebida(idBebida);
    if (cantidadDisponible <=0 ) {
                 // System.out.println("No hay suficientes bebidas para el platillo con ID " + idBebida);
                existenciaB = false;
           } else {
                // System.out.println("Hay suficientes bebidas para el platillo con ID " + idBebida);
            }
        
       return existenciaB;
    }
    
    
    
    
    
    
    
      private void AgarrarIngrediente() throws SQLException {
        int idPlatillo = id; // Obtienes el ID del platillo desde algún lugar
        JFrame ventanaIngredientes = new JFrame("Ingredientes");
        ventanaIngredientes.setLayout(new BorderLayout());
        // Llamas al método de tu clase ConexionBD para obtener los ingredientes del platillo
        ArrayList<String> ingredientes = conexion.obtenerIngredientesPorID(idPlatillo);
       JTextArea areaIngredientes = new JTextArea();
        for (String ingrediente : ingredientes) {
            areaIngredientes.append(ingrediente +"\n");
      }
        ventanaIngredientes.add(new JScrollPane(areaIngredientes), BorderLayout.CENTER);

        ventanaIngredientes.setSize(300, 200);
        ventanaIngredientes.setLocationRelativeTo(this);
        ventanaIngredientes.setVisible(true);
        id = 0;
       // JOptionPane.showMessageDialog(this, areaIngredientes, "Ingredientes del Platillo", JOptionPane.INFORMATION_MESSAGE);
 }
      private void AgarrarPrecios(){
       int idPlatillo = id;
       int precioPlato = conexion.obtenerPrecioPlatoDesdeBD(idPlatillo);
                total += precioPlato;
                id = 0;
      }
      
      private void AgarrarPreciosBebidas(){
       int idbebi = idBebidas;
       int precioBebida = conexion.obtenerPrecioBebidaDesdeBD(idbebi);
                total += precioBebida;
                idBebidas = 0;
      }
      
      /*
 private int obtenerIDPlatilloDesdeAlgunaParte() {
       
return 1;
    }
      */
      
     


    public void agregarALista(String elemento) {
        modeloLista.addElement(elemento);
    }
    
    private void eliminarElementoSeleccionado() { 
    int indiceSeleccionado = Pedido.getSelectedIndex();
    if (indiceSeleccionado != -1) {
    String elementoSeleccionado = (String) modeloLista.getElementAt(indiceSeleccionado);
     double  precioARestar = obtenerPrecioDelPlatillo(elementoSeleccionado);
         total -= precioARestar;

        // Eliminar el platillo y su precio de los modelos
        modeloLista.remove(indiceSeleccionado);

        // Actualizar la visualización del total
        JlblTotal.setText("$"+ total);
    }
    
}
    
    private double obtenerPrecioDelPlatillo(String platillo) {
    // Aquí simulas obtener el precio del platillo
    // Reemplaza esta lógica con tu manera real de obtener el precio del platillo
    if (platillo.equals("Ramen")) {
        return 370.0;
    } else if (platillo.equals("Curry Japonés")) {
        return 230.0;
    }else if (platillo.equals("Sushi de carne y bacon")) {
        return 200.0;
    }else if (platillo.equals("Salmon marinado japonés")) {
        return 400.0;
    }else if (platillo.equals("Mochis de coco")) {
        return 180.0;
    }else if (platillo.equals("Bubble Waffle")) {
        return 80.0;
    }else if (platillo.equals("Refresco")) {
        return 20.0;
    }else if (platillo.equals("Te Verde")) {
        return 60.0;
    }else if (platillo.equals("Sake")) {
        return 120.0;
    }else if (platillo.equals("Calpis")) {
        return 70.0;
    }
    return 0.0;
    }
    
    private void ConfirmacionTasSeguro() throws SQLException{
        double Iva;
        double Final;
        Iva = total * 0.16;
        Final = total + Iva;
        int confirmacion = JOptionPane.showConfirmDialog(this,"¿Estás seguro de continuar? pagaras $"+Final+"pesos", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // El usuario ha hecho clic en "Sí", realiza la acción que deseas
            mostrarVentanaDeConfirmacion(Final);
        } else {
            // El usuario ha hecho clic en "No" o ha cerrado el diálogo
            // Puedes realizar alguna otra acción si lo deseas
            // Por ejemplo, no hacer nada o mostrar un mensaje de cancelación
            JOptionPane.showMessageDialog(this, "Operación cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }
            
    private void mostrarVentanaDeConfirmacion(double Final) throws SQLException {
    idComanda++;
    /*
    Connection conn = ConexionBD.getConnection();
    
            String query = "INSERT INTO recibo (idComanda, fecha, hora, totalSinIVA, totalConIVA) VALUES (?, CURRENT_DATE, CURRENT_TIME, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, idComanda);
                pstmt.setDouble(2, total);
                pstmt.setDouble(3, Final);

                pstmt.executeUpdate();
    
             System.out.println("1, CURRENT_DATE, CURRENT_TIME, 255, 284");
                // La inserción se ha realizado con éxito
            }
    
         String quers = "INSERT INTO comanda (fecha, hora) VALUES (CURRENT_DATE, CURRENT_TIME)";
          try (PreparedStatement pstmt = conn.prepareStatement(quers)) {
                pstmt.executeUpdate();
            }
     */
    System.out.println("leido Princi");
       enviarComanda(juntarLista());
       limpiarLista();
       inicializarObjetos();
    }
    
    public ArrayList<String[]> juntarLista() {
    ArrayList<String[]> lista = new ArrayList<>();

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

        // Si no está en la lista, agregarlo junto con su recuento
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

    return lista;
}
  /*
        private static void enviarComanda(ArrayList<String[]> lista) throws SQLException {
              int idCma = idComanda;
              int idCmaPlatillo;
              int idCmaBebida;
              idCmaPlatillo = idCma;
              idCmaBebida = idCma;
        for (int i = 0; i < lista.size(); i++) {
            try (Connection conn = getConnection()) {
                String nombrePlatillo = lista.get(i)[0];
                int cantidad = Integer.parseInt(lista.get(i)[1]);

                String queryPla = "INSERT INTO platillosporcomanda (idComanda, idPlatillo, cantidad, precioUnitario) "
                                + "VALUES (?, (SELECT idPlatillo FROM platillo WHERE nombre = ?), ?, (SELECT precio FROM platillo WHERE nombre = ?))";

                PreparedStatement psPlatillos = conn.prepareStatement(queryPla);
                psPlatillos.setInt(1, idCmaPlatillo);
                psPlatillos.setString(2, nombrePlatillo);
                psPlatillos.setInt(3, cantidad);
                psPlatillos.setString(4, nombrePlatillo);
                psPlatillos.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al insertar en platillosporcomanda: " + ex);
            }
        }
        for (int i = 0; i < lista.size(); i++) {
            try (Connection conn = getConnection()) {
                String nombreBebida = lista.get(i)[0];
                int cantidad = Integer.parseInt(lista.get(i)[1]);

                // Insertar en la tabla bebidasporcomanda
                String queryBebidas = "INSERT INTO bebidaporcomanda VALUES (?, (SELECT idBebida FROM bebida WHERE nombre = ?), ?, (SELECT precio FROM bebida WHERE nombre = ?))";
                PreparedStatement psBebidas = conn.prepareStatement(queryBebidas);
                psBebidas.setInt(1, idCmaBebida);
                psBebidas.setString(2, nombreBebida);
                psBebidas.setInt(3, cantidad);
                psBebidas.setString(4, nombreBebida);
                psBebidas.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al insertar en bebidaporcomanda: " + ex);
            }
        }
            idCma++;  
    }
    */
    
    
    private static void enviarComanda(ArrayList<String[]> lista) throws SQLException {
    int idCma = idComanda;
    int idCmaPlatillo = idCma;
    int idCmaBebida = idCma;

    for (int i = 0; i < lista.size(); i++) {
        try (Connection conn = getConnection()) {
            String nombre = lista.get(i)[0];
            int cantidad = Integer.parseInt(lista.get(i)[1]);
            
            String query;
            int idCmaActual;

            if (esBebida(nombre)) {
                System.out.println("Bebida" + nombre);
                query = "call insertarBebidaporcomanda (?,(SELECT idBebida FROM bebida WHERE nombre = ?),?)";
                idCmaActual = idCmaBebida;
            } else {
                System.out.println("Platillo" + nombre);
                query = "call insertarplatillosporcomanda (?,(SELECT idPlatillo FROM platillo WHERE nombre = ?),?)";
                idCmaActual = idCmaPlatillo;
            }

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCmaActual);
            ps.setString(2, nombre);
            ps.setInt(3, cantidad);
          //  ps.setString(4, nombre);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al insertar en la base de datos: " + ex);
        }
    }
}
    
    private static boolean esBebida(String nombre) {
    List<String> bebidas = Arrays.asList("Calpis", "Sake", "Te Verde", "Refresco");
    /*
    for (String parte : bebidas) {
        if (bebidas.contains(parte)) {
            System.out.println("Si es bebida" + nombre);
            return true;
        }
    }
    System.out.println("No es bebida" + nombre);
    return false;
    */
    System.out.println(nombre);
    return bebidas.contains(nombre);
}
   
    //return bebidas.contains(nombre.toLowerCase());
    
    
    private void limpiarLista() {
    modeloLista.removeAllElements();
    total = 0;
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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ventana().setVisible(true);
                
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
