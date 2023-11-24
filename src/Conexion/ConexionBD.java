/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import proyectoborra2.Ventana;
import com.mysql.cj.jdbc.DatabaseMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Daniel
 */
public class ConexionBD {
    
    private static final String JDBC_URL = "jdbc:mysql://148.211.124.58:3306/neotokio";
    private static final String JDBC_USER = "neotokio";
    private static final String JDBC_PASSWORD = "jFrB)(A!_s1AYwj0";
/*
    private static final String JDBC_URL = "jdbc:mysql://Rayoscompany.com:3306/Proyecto";
    private static final String JDBC_USER = "java";
    private static final String JDBC_PASSWORD = "Java_proyecto123220224";
  */  
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Cargar el driver JBDC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public static ArrayList<String> obtenerIngredientesPorID(int idPlatillo) throws SQLException {
        ArrayList<String> ingredientes = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String query = "SELECT i.nombre from ingrediente i join ingredientesporplatillo p on i.idIngrediente = p.idIngrediente  WHERE p.idPlatillo = ?";            
PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPlatillo);
            ResultSet rs = ps.executeQuery(); 
while (rs.next()) {
String ingrediente = rs.getString("nombre");
                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        
        }        
return ingredientes;
    }
    
    public int obtenerPrecioPlatoDesdeBD(int idPlato) {
        int precio = 0;
        try (Connection conn = getConnection()) {
            String query = "SELECT precio FROM platillo WHERE idPlatillo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idPlato);
            ResultSet rs = ps.executeQuery();

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
    
        public int obtenerPrecioBebidaDesdeBD(int idBebida) {
        int precio = 0;
        try (Connection conn = getConnection()) {
            String query = "SELECT precio FROM bebida WHERE idBebida = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idBebida);
            ResultSet rs = ps.executeQuery();

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


        
        /*
        public static void obtenerIngredientesPorIdPlatillo(int idPlatillo) {
    // Consulta para obtener los ingredientes necesarios para el platillo por su ID
    String query =  "SELECT i.nombre, p.cantidad FROM ingrediente i JOIN ingredientesporplatillo p ON i.idIngrediente = p.idIngrediente WHERE p.idPlatillo = ?";
    String querys =  "SELECT i.nombre, q.cantidad FROM ingrediente i JOIN ingredientesalmacen p ON i.idIngrediente = p.idIngrediente "
            + "join ingredientesporplatillo q on i.idIngrediente = q.idIngrediente WHERE p.idPlatillo = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, idPlatillo);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idIngrediente = rs.getInt("idIngrediente");
            double cantidadRequerida = rs.getDouble("cantidadRequerida");
            String nombreIngrediente = rs.getString("nombre");

            System.out.println("Para el platillo con ID " + idPlatillo + ", se requiere " + cantidadRequerida + " unidades de " + nombreIngrediente + " (ID: " + idIngrediente + ")");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
            */
       

            
        
        /*
     public boolean verificarIngredientes(Connection conn, int idPlatillo) throws SQLException {
        boolean ingredientesDisponibles = false;

        // Consulta para obtener la cantidad de ingredientes requeridos por el platillo por su ID
        String queryIngredientesRequeridos = "SELECT COUNT(cantidad) FROM ingredientesporplatillo WHERE id_platillo = ?";
        PreparedStatement psIngredientesRequeridos = conn.prepareStatement(queryIngredientesRequeridos);
        psIngredientesRequeridos.setInt(1, idPlatillo);
        ResultSet rsIngredientesRequeridos = psIngredientesRequeridos.executeQuery();

        int cantidadIngredientesRequeridos = 0;
        if (rsIngredientesRequeridos.next()) {
            cantidadIngredientesRequeridos = rsIngredientesRequeridos.getInt("cantidad_ingredientes_requeridos");
        }

        // Consulta para obtener la cantidad total de ingredientes disponibles por su ID
        String queryIngredientesTotales = "SELECT COUNT(cantidad) FROM ingredientesalmacen";
        PreparedStatement psIngredientesTotales = conn.prepareStatement(queryIngredientesTotales);
        ResultSet rsIngredientesTotales = psIngredientesTotales.executeQuery();

        int cantidadIngredientesTotales = 0;
        if (rsIngredientesTotales.next()) {
            cantidadIngredientesTotales = rsIngredientesTotales.getInt("cantidad_ingredientes_totales");
        }

        if (cantidadIngredientesRequeridos <= cantidadIngredientesTotales) {
            ingredientesDisponibles = true;
        }

        return ingredientesDisponibles;
    } 
        */
    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Conexion exitosa a la base de datos neotokio!");

            boolean continueChecking = true;
            while (continueChecking) {
                try {
                    // Obtener una lista de todas las tablas
                    DatabaseMetaData metaData = (DatabaseMetaData) connection.getMetaData();
                    String catalog = null;
                    String schemaPattern = null;
                    String tableNamePattern = null;
                    String[] types = {"TABLE"};
                    ResultSet tables = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);

                    System.out.println("Lista de tablas dentro de la base de datos:");
                    while (tables.next()) {
                        String tableName = tables.getString("TABLE_NAME");
                        System.out.println(tableName);
                    }

                    tables.close();

                    // Solicitar al usuario que seleccione una tabla
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Ingresa el nombre de la tabla para ver sus filas y atributos: ");
                    String selectedTable = scanner.nextLine();

                    // Construir y ejecutar la consulta SQL para obtener todas las filas
                    String query = "SELECT * FROM " + selectedTable;
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    // Obtener metadatos para recuperar los nombres de los atributos
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                    int columnCount = resultSetMetaData.getColumnCount();

                    // Imprimir los nombres de los atributos
                    System.out.println("Atributos de la tabla " + selectedTable + ":");
                    for (int i = 1; i <= columnCount; i++) {
                        String attributeName = resultSetMetaData.getColumnName(i);
                        System.out.println(attributeName);
                    }

                    // Imprimir las filas
                    System.out.println("Filas de la tabla " + selectedTable + ":");
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSet.getString(i) + "\t");
                        }
                        System.out.println();
                    }

                    resultSet.close();
                    statement.close();

                    // Preguntar al usuario si desea seguir checando la Base de Datos.re
                    System.out.print("¿Deseas seguir revisando la base de datos? (Si/No): ");
                    String userInput = scanner.nextLine().toLowerCase();
                    continueChecking = userInput.equals("si") || userInput.equals("si");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error al conectarse a la base de datos.");
        }
    }

    public PreparedStatement prepareStatement(String consulta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
