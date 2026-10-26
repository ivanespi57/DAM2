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
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 0:
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
}