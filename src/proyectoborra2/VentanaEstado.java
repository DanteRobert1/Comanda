/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectoborra2;

import java.sql.Connection;
import Conexion.ConexionBD;
import static Conexion.ConexionBD.getConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Equipo4
 */
public class VentanaEstado extends javax.swing.JFrame {
    ConexionBD conexion;
    private static int idSucursal;
    private static int idMesa;
    private static int estado;
    
    /**
     * Creates new form Ventana321
     */
    public VentanaEstado(int idSucursal, int idMesa) throws SQLException, InterruptedException {
        initComponents();
        conexion = new ConexionBD();
        this.idSucursal = idSucursal;   //Se asigna la sucursal que recibio la ventana a la variable global de aqui
        this.idMesa = idMesa;           //Se asigna el id de la mesa que recibio la ventana a la variable global de aqui
        consultaEstado();               //Se consulta el estado de la mesa
        apariencia();                   //Se manda a llamar apariencia para mostrar la pantalla que le corresponde al estado de la mesa
    }
       
    /**
     * Metodo para modificar la apariencia de la ventana dependiendo del estado de la mesa
     * @throws InterruptedException 
     */
    private void apariencia() throws InterruptedException{
        switch (estado) {
            case 1:
            case 2:
            case 3:
                jLblMensaje.setText("Bienvenidos!");
                jBtnAccion.setText("Pedir");
                break;  //En los casos 1,2,3 solo se muestra la pantalla de bienvenida
            case 4:
            case 5:
                jLblMensaje.setText("Su comida se esta preparando");
                jBtnAccion.setVisible(false);
                esperarComidaLista();
                break;  //En los estados 4 y 5 se muestra la pantalla de esperando comida
            case 6:
            case 7:
            case 8:
                jLblMensaje.setText("Su comida ya esta lista");
                jBtnAccion.setText("Pagar");
                jBtnAccion.setVisible(true);
                break;  //En los casos 6,7 y 8 se muestra la pantalla de comida lista y da el boton para "pagar"/reiniciar el proceso
            default:
                break; 
        }
    }

    /**
     * Metodo para consultar el estado actual de la mesa en la tabla
     * @throws SQLException 
     */
    private void consultaEstado() throws SQLException{
        int vEstado = -1;
        try (Connection conn = getConnection()){
            String query = "SELECT estado from mesa where idMesa = ?;"; //Instruccion de SQL para ver el estado de la mesa actual
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,idMesa);
            ResultSet rs = ps.executeQuery();
            rs.next();
            vEstado = rs.getInt(1);
        } catch (SQLException ex){
            System.out.println("Error: " + ex); //En caso de error se imprime el error
        }
        this.estado =  vEstado; //El estado que tiene la mesa dentro de la base de datos se le asigna a la variable global de la ventana
    }
    
    /**
     * Metodo para cambiar el estado de la mesa al sisguiente paso
     */
    public static void cambiarEstado(){
        String query = "";
        try (Connection conn = getConnection()){
            switch (estado){
                case 1:
                case 2:
                case 3:
                    query = "UPDATE mesa SET estado = 4 where idMesa = ?";
                    break;  //En estados 1,2 o 3 se manda la mesa al estado 4, pidiendo (Ya que ahi significa que estan pidiendo)
                case 4:
                    query = "UPDATE mesa SET estado = 5 where idMesa = ?";
                    break;  //Cuando el estado sea 4, se cambia a 5 para mostrar que la mesa ya esta esperando su comida
                case 5:
                case 6:
                case 7:
                case 8:
                    query = "UPDATE mesa SET estado = 1 where idMesa = ?";
                    break;  //En los casos 5,6,7 y 8 se brinca directamente al estado 1 ya que se "pago"
                default:
                    break;
            }
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idMesa);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en cambiarEstado: " + ex); //En caso de error se imprime el error
        }
    }
    
    /**
     * Consulta el estado de la mesa cada 30 segundos para poder ver cuando este lista la comida
     * @throws InterruptedException 
     */
    private void esperarComidaLista() throws InterruptedException{
        //Crea el temporalizador para que se repita X instruccion (llamado tiempo)
        Timer tiempo = new Timer("ConsultaEstado");
        //Creacion del trabajo que se va a realizar repepitivamente (llamado validacion)
        TimerTask consulta = new TimerTask() {
            @Override
            public void run() {
                //Consulta el estado de la mesa
                try (Connection conn = getConnection()){
                    consultaEstado();
                } catch (SQLException ex){
                    System.out.println(ex); //En caso de error se imprime el error
                }
                //Si la mesa se encuentra en estado de comida lista
                if (estado == 6){
                    try {
                        apariencia();
                        //Se termina el temporalizador y continua el proceso del restaurante
                        Thread.sleep(0);
                        tiempo.cancel();
                        
                    } catch (InterruptedException ex) {
                        System.out.println(ex); //En caso de error se imprime el error
                    }
                }
            }
        };
        //Se inicia el temporalizador y cada 15 segundos se realiza el proceso de consulta
        tiempo.scheduleAtFixedRate(consulta,0,15000L);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLblMensaje = new javax.swing.JLabel();
        jBtnAccion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/CapturaIcono.JPG"))); // NOI18N
        jLabel3.setText("jLabel1");
        jLabel3.setMaximumSize(new java.awt.Dimension(149, 140));
        jLabel3.setMinimumSize(new java.awt.Dimension(149, 140));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/CapturaIcono.JPG"))); // NOI18N
        jLabel5.setText("jLabel1");
        jLabel5.setMaximumSize(new java.awt.Dimension(149, 140));
        jLabel5.setMinimumSize(new java.awt.Dimension(149, 140));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(32, 17, 72));

        jLblMensaje.setFont(new java.awt.Font("Barlow ExtraLight", 2, 36)); // NOI18N
        jLblMensaje.setForeground(new java.awt.Color(85, 231, 255));
        jLblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblMensaje.setText("Mensaje");

        jBtnAccion.setBackground(new java.awt.Color(153, 204, 255));
        jBtnAccion.setFont(new java.awt.Font("Barlow Medium", 1, 18)); // NOI18N
        jBtnAccion.setForeground(new java.awt.Color(102, 0, 102));
        jBtnAccion.setText("Accion");
        jBtnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAccionActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/CapturaIcono.JPG"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(149, 140));
        jLabel1.setMinimumSize(new java.awt.Dimension(149, 140));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/CapturaIcono.JPG"))); // NOI18N
        jLabel2.setText("jLabel1");
        jLabel2.setMaximumSize(new java.awt.Dimension(149, 140));
        jLabel2.setMinimumSize(new java.awt.Dimension(149, 140));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/CapturaIcono.JPG"))); // NOI18N
        jLabel4.setText("jLabel1");
        jLabel4.setMaximumSize(new java.awt.Dimension(149, 140));
        jLabel4.setMinimumSize(new java.awt.Dimension(149, 140));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RecursosLogo/CapturaIcono.JPG"))); // NOI18N
        jLabel6.setText("jLabel1");
        jLabel6.setMaximumSize(new java.awt.Dimension(149, 140));
        jLabel6.setMinimumSize(new java.awt.Dimension(149, 140));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 709, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(416, 416, 416)
                .addComponent(jBtnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(121, Short.MAX_VALUE)
                        .addComponent(jLblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jBtnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Boton que realiza acciones dependiendo en que estado esta la mesa
     * @param evt click en el boton
     */
    private void jBtnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAccionActionPerformed
        try {
            cambiarEstado();    //Primero se actualiza el estado de la mesa en la base de datos al que le sigue, pero no el que esta aqui como variable global
            switch (estado) {
                case 1:
                case 2:
                case 3:
                    new VentanaMenu(idSucursal,idMesa).setVisible(true);
                    this.dispose();
                    break;  //Para los estados 1,2 y 3 de la variable global se abre el menu para realizar un pedido y se cierra esta ventana
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    try {
                        consultaEstado();
                    } catch (SQLException ex) {
                        System.out.println(ex); //En caso de error se imprime el error
                    }
                    apariencia();
                    break;  //Para los estados 4-8, primero se consulta el estado actual de la mesa en la BD y luego se actualiza la apariencia de la ventana para el estado
                default:
                    break;
            }
            // TODO add your handling code here:
        } catch (InterruptedException ex) {
            System.out.println(ex); //En caso de error se imprime el error
        }
    }//GEN-LAST:event_jBtnAccionActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEstado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VentanaEstado(idSucursal, idMesa).setVisible(true);
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex);//En caso de error se imprime el error
                } catch (InterruptedException ex) {
                    System.out.println(ex);//En caso de error se imprime el error
                }
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLblMensaje;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
