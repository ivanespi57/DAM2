package UD2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Ejer5 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.3.175/banco";
        String user = "ivan";
        String pass = "ivan";

        Scanner sc = new Scanner(System.in);

        try (
            Connection conexion = DriverManager.getConnection(url, user, pass);
        ){
            int opcion;
            do {
                System.out.println();
                System.out.println("===== MENÚ =====");
                System.out.println("1. Comprobar autocommit");
                System.out.println("2. Activar el autocommit");
                System.out.println("3. Desactivar el autocommit");
                System.out.println("4. Realizar un ingreso");
                System.out.println("5. Realizar una retirada");
                System.out.println("6. Consultar el saldo");
                System.out.println("7. Hacer commit de las operaciones");
                System.out.println("0. Salir");
                System.out.println("Elige una opción:");
                opcion = Integer.parseInt(sc.nextLine());

                try {
                    switch (opcion) {
                        case 1:
                            comprobarAutocommit(conexion);
                            break;
                        case 2:
                            conexion.setAutoCommit(true);
                            break;
                        case 3:
                            conexion.setAutoCommit(false);
                            break;
                        case 4:
                            ingreso(conexion, sc);
                            break;
                        case 5:
                            retirada(conexion, sc);
                            break;
                        case 6:
                            consultarSaldo(conexion, sc);
                            break;
                        case 7:
                            conexion.commit();
                            System.out.println("Commit realizado correctamente");
                            break;
                        case 0:
                            System.out.println("Fin del programa");
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                } catch (SQLException e) {
                    System.out.println("Error en la operación: " + e.getMessage());
                }
            } while (opcion != 0);
        } catch (SQLException e) {
            System.out.println("Error al conectar con MySQL");
            e.printStackTrace();
        }
    }

    private static void comprobarAutocommit(Connection conexion) throws SQLException{
        if (conexion.getAutoCommit()) {
            System.out.println("El autocommit está activado");
        }else{
            System.out.println("El autocommit está desactivado");
        }
    }

    private static void ingreso(Connection conexion, Scanner sc) throws SQLException{
        System.out.println("Id de la cuenta: ");
        int id = sc.nextInt();
        System.out.println("Cantidad a ingresar: ");
        double cant = sc.nextDouble();

        String sql = "UPDATE cuenta SET saldo = saldo + ? WHERE id = ?";

        try(
            PreparedStatement sent = conexion.prepareStatement(sql);
        ) {
            sent.setDouble(1, cant);
            sent.setInt(2, id);
            int filas = sent.executeUpdate();

            if (filas > 0){
                System.out.println("Ingreso de "+ cant + " € realizada en la cuenta "+ id);
            }else{
                System.out.println("No existe ninguna cuenta con id "+ id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void retirada(Connection conexion, Scanner sc) throws SQLException {
        System.out.println("Id de la cuenta: ");
        int id = sc.nextInt();
        System.out.println("Cantidad a ingresar: ");
        double cant = sc.nextDouble();

        String sql = "UPDATE cuenta SET saldo = saldo - ? WHERE id = ?";

        try (
            PreparedStatement sent = conexion.prepareStatement(sql);
        ){
            sent.setDouble(1, cant);
            sent.setInt(2, id);
            int filas = sent.executeUpdate();

            if (filas > 0){
                System.out.println("Retirada de "+ cant +" € realizada en la cuenta "+ id);
            }else{
                System.out.println("No existe ninguna cuenta con id "+ id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarSaldo(Connection conexion, Scanner sc) throws SQLException{
        System.out.println("Id de la cuenta: ");
        int id = sc.nextInt();

        String sql = "SELECT saldo FROM cuenta WHERE id = ?";

        try (
            PreparedStatement sent = conexion.prepareStatement(sql);
        ){
            sent.setInt(1, id);
            ResultSet rs = sent.executeQuery();

            if (rs.next()){
                double saldo = rs.getDouble("saldo");
                System.out.println("Saldo de la cuenta "+ id +": "+ saldo + " €");
            }else{
                System.out.println("No existe ninguna cuenta con id "+ id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}