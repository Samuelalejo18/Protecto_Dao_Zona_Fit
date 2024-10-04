package zona_fit;

import zona_fit.conexion.Conexion;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection con= Conexion.getConexion();
        if(con!=null){
            System.out.println("Conexion exitosa "+ con);
        }else{
            System.out.println("Conexion no exitosa");
        }
    }
}