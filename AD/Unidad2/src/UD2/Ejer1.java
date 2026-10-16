package UD2;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejer1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.3.175/tienda";
        String user = "ivan";
        String pass = "ivan";

        try (
            Connection conexion = DriverManager.getConnection(url, user, pass);
            Statement stmt = conexion.createStatement();
        ){
            String query1 = "INSERT INTO producto (nombre, precio, stock) VALUES (Teclado mecánico, 59.90, 15)";
            String query2 = "INSERT INTO producto (nombre, precio, stock) VALUES (Ratón inalámbrico, 24.50, 30)";
            String query3 = "INSERT INTO producto (nombre, precio, stock) VALUES (Monitor 24 pulgadas, 149.99, 8)";
            String query4 = "INSERT INTO producto (nombre, precio, stock) VALUES (Auriculares Bluetooth, 39.95, 20)";

            stmt.executeUpdate(query1);
            System.out.println("Producto 'Teclado mecánico' insertado");
            stmt.executeUpdate(query2);
            System.out.println("Producto 'Ratón inalámbrico' insertado");
            stmt.executeUpdate(query3);
            System.out.println("Producto 'Monitor 24 pulgadas' insertado");
            stmt.executeUpdate(query4);
            System.out.println("Producto 'Auriculares Bluetooth' insertado");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString("nombre");

                System.out.println("Hola, tu id es " + id + " y tu nombre es " + nombre);
            }
            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
        }
    }
}

