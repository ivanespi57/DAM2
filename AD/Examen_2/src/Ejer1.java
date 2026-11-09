import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejer1{
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.3.175/festival_musica";
        String user = "ivan";
        String pass = "ivan";

        Scanner sc = new Scanner(System.in);

        try (
            Connection conexion = DriverManager.getConnection(url, user, pass);
        ){
            int opcion;
            do {
                System.out.println();
                System.out.println("¡Bienvenido al ADfest 2026!");
                System.out.println("1. Consultar grupos por género musical");
                System.out.println("2. Comprar entradas para el festival");
                System.out.println("3. Solicitar reembolso y devolver entradas");
                System.out.println("4. Consultar número de entradas por cliente");
                System.out.println("0. Salir");
                System.out.println("Elige una opción:");
                opcion = Integer.parseInt(sc.nextLine());

                try {
                    switch (opcion) {
                        case 1:
                            consultarGrupos(conexion, sc);
                            break;
                        case 2:
                            comprarEntradas(conexion, sc);
                            break;
                        case 3:
                            solicitarReembolso(conexion, sc);
                            break;
                        case 4:

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


    private static void consultarGrupos(Connection conexion, Scanner sc) throws SQLException{
        System.out.println("Nombre del género musical: ");
        String genero = sc.nextLine();

        String sql = "SELECT G.nombre AS nomGru, E.nombre AS nomEsc, A.orden FROM Grupo_Musical G, Actuacion A, Escenario E WHERE genero = ? AND G.id = A.id_grupo AND A.id_escenario = E.id";

        try (
            PreparedStatement sent = conexion.prepareStatement(sql);
        ){
            sent.setString(1, genero);
            ResultSet rs = sent.executeQuery();

            if (rs.next()){ 
                String nomGru = rs.getString("nomGru");
                String nomEsc = rs.getString("nomEsc");
                int orden = rs.getInt("orden");

                System.out.println("Nombre del grupo: "+ nomGru +"\nGénero musical: "+ genero +"\nNombre del escenario: "+ nomEsc +"\nOrden de actuación: "+ orden);
            }else{
                System.out.println("No existe ese género en el festival: "+ genero);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void comprarEntradas(Connection conexion, Scanner sc) throws SQLException{
        System.out.println("Introduzca el DNI");
        String dni = sc.next();
        System.out.println("Introduzca la cantidad de entradas que desea comprar: ");
        int cantEntradas = sc.nextInt();

        String sql = "INSERT INTO Entrada (codigo, asistente, tipo, precio, dni_comprador) VALUES (?,?,?,?,?)";

        for (int i = 0; i < cantEntradas; i++){
            conexion.setAutoCommit(false);

            try (PreparedStatement sent = conexion.prepareStatement(sql)){

                System.out.println("Introduce el código de la entrada (Formato ENTXXX): ");
                String codigo = sc.next();
                
                if (codigo.length() == 6 && codigo.startsWith("ENT")){
                    System.out.println("Introduce el nombre del asistente: ");
                    String nomAsist = sc.nextLine();
                    sc.nextLine();

                    System.out.println("Introduce el tipo de entrada: ");
                    String tipo = sc.next();
                    double precio = 0;
                    if (tipo == "General" || tipo == "VIP"){
                        if (tipo == "General"){
                            precio = 75.00;
                        }else if(tipo == "VIP"){
                            precio = 120.00;
                        }
                    }

                    sent.setString(1, codigo);
                    sent.setString(2, nomAsist);
                    sent.setString(3, tipo);
                    sent.setString(5, dni);
                }

                conexion.commit();
                System.out.println("Entradas compradas");
            } catch (Exception e) {
                conexion.rollback();
                System.out.println("Error en la transferencia. Cambios deshechos.");
            }
        }
    }

    private static void solicitarReembolso(Connection conexion, Scanner sc) throws SQLException{
        System.out.println("Dime el código de entrada: ");
        String codigo = sc.nextLine();

        String sql = "DELETE FROM Entrada WHERE codigo = ?";

        try (PreparedStatement sent = conexion.prepareStatement(sql)){
            sent.setString(1, codigo);

            sent.executeQuery();
            System.out.println("Entrada borrada con éxito");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

