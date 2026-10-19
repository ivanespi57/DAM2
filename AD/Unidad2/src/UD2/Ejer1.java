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
            String query1 = "INSERT INTO producto (nombre, precio, stock) VALUES ('Teclado mecánico', 59.90, 15)";
            String query2 = "INSERT INTO producto (nombre, precio, stock) VALUES ('Ratón inalámbrico', 24.50, 30)";
            String query3 = "INSERT INTO producto (nombre, precio, stock) VALUES ('Monitor 24 pulgadas', 149.99, 8)";
            String query4 = "INSERT INTO producto (nombre, precio, stock) VALUES ('Auriculares Bluetooth', 39.95, 20)";

            stmt.executeUpdate(query1);
            System.out.println("Producto 'Teclado mecánico' insertado");
            stmt.executeUpdate(query2);
            System.out.println("Producto 'Ratón inalámbrico' insertado");
            stmt.executeUpdate(query3);
            System.out.println("Producto 'Monitor 24 pulgadas' insertado");
            stmt.executeUpdate(query4);
            System.out.println("Producto 'Auriculares Bluetooth' insertado");

            System.out.println();
            System.out.println("Productos tras la inserción:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM producto");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                System.out.println(id + " - " + nombre + " - " + precio + " € - stock: " + stock);
            }

            String query5 = "UPDATE producto SET precio = 29.90 WHERE nombre = 'Ratón inalámbrico'";
            String query6 = "UPDATE producto SET stock = 10 WHERE nombre = 'Auriculares Bluetooth'";

            stmt.executeUpdate(query5);
            System.out.println();
            System.out.println("Precio del 'Ratón inalámbrico' actualizado");
            stmt.executeUpdate(query6);
            System.out.println("Stock de 'Auriculares Bluetooth' actualizado");

            System.out.println();
            System.out.println("Productos actualizados:");
            String query7 = "SELECT * FROM producto "
                    + "WHERE nombre = 'Ratón inalámbrico' OR nombre = 'Auriculares Bluetooth'";
            rs = stmt.executeQuery(query7);
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                System.out.println(id + " - " + nombre + " - " + precio + " € - stock: " + stock);
            }

            String query8 = "DELETE FROM producto WHERE nombre = 'Teclado mecánico'";
            stmt.executeUpdate(query8);
            System.out.println();
            System.out.println("Producto 'Teclado mecánico' eliminado");

            System.out.println();
            System.out.println("Productos a la venta:");
            rs = stmt.executeQuery("SELECT * FROM producto");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                System.out.println(id + " - " + nombre + " - " + precio + " € - stock: " + stock);
            }

            System.out.println();
            System.out.println("Stock de cada producto:");
            rs = stmt.executeQuery("SELECT nombre, stock FROM producto");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int stock = rs.getInt("stock");
                System.out.println(nombre + ": " + stock + " unidades");
            }

            String query9 = "SELECT SUM(stock) AS stock_total, SUM(precio * stock) AS valor_total FROM producto";
            rs = stmt.executeQuery(query9);
            while (rs.next()) {
                int stockTotal = rs.getInt("stock_total");
                double valorTotal = rs.getDouble("valor_total");
                System.out.println();
                System.out.println("Stock total de productos: " + stockTotal + " unidades");
                System.out.println("Valor total de venta del stock: " + valorTotal + " €");
            }

            System.out.println();
            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
            e.printStackTrace();
        }
    }
}