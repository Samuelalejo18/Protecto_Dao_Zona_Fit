package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConexion() {
        Connection conexion = null;
        String nombreBaseDatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + nombreBaseDatos;
        String usuario = "root";
        String password = "123456789";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("ERROR DE CONEXION A LA BASE DE DATOS: " + e.getMessage());
        }
        return conexion;
    }
}
