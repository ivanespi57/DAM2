import java.util.*;


public class Main {
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        System.out.println("************************************************");
        System.out.println("************ Welcome to PaiportEats ************");
        System.out.println("************************************************");
        System.out.println("************************************************");
        System.out.println(" TU APLICACIÓN DE GESTIÓN DE COMIDA A DOMICILIO");
        System.out.println("************************************************");
        int opcion;
        do{
            try{
                opcion = mainMenu();
                switch(opcion){
                    case 1:
                        menuPlatos();
                        break;
                    case 2:
                        menuClientes();
                        break;
                    case 3:
                        menuPedidos();
                        break;
                    case 4:
                        menuEstadisticas();
                        break;
                    case 0:
                        System.out.println("¡HASTA PRONTO!");
                        break;
                    default:
                        System.out.println("Lo siento, no te he entendido, introduce una opción válida");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Lo siento, no te he entendido, introduce una opción válida");
                sc.nextLine();
                opcion = -1;
            }
        } while(opcion != 0);
    }

    public static int mainMenu(){
        System.out.println("    ************************");
        System.out.println("    **** MENU PRINCIPAL ****");
        System.out.println("    ************************");
        System.out.println("    1. Gestión de platos");
        System.out.println("    2. Gestión de clientes");
        System.out.println("    3. Gestión de pedidos");
        System.out.println("    4. Consultas y estadísticas");
        System.out.println("    0. Salir");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    public static void menuPlatos(){
        char accion;
        do{
            System.out.println("\t************************");
            System.out.println("\t**** MENU DE PLATOS ****");
            System.out.println("\t************************");
            System.out.println("\ta. Mostrar ingredientes");
            System.out.println("\tb. Añadir nuevo ingrediente");
            System.out.println("\tc. Mostrar platos");
            System.out.println("\td. Registrar nuevo plato");
            System.out.println("\te. Modificar precio de plato");
            System.out.println("\tf. Cambiar disponibilidad de un plato");
            System.out.println("\tg. Volver");
            String entry = sc.nextLine();
            accion = entry.equals("") ? 'z' : entry.charAt(0);
            switch(accion){
                case 'a':
                    break;
                case 'b':
                    break;
                case 'c':
                    break;
                case 'd':
                    break;
                case 'e':
                    break;
                case 'f':
                    break;
                case 'g':
                    break;
                default:
                    System.out.println("Por favor, selecciona una opción válida");
                    break;
            }
        }while(accion != 'g');
    }

    public static void menuClientes(){
        char accion;
        do{
            System.out.println("\t**************************");
            System.out.println("\t**** MENU DE CLIENTES ****");
            System.out.println("\t**************************");
            System.out.println("\ta. Registrar un cliente");
            System.out.println("\tb. Listar todos los clientes");
            System.out.println("\tc. Eliminar cliente");
            System.out.println("\td. Volver");
            String entry = sc.nextLine();
            accion = entry.equals("") ? 'z' : entry.charAt(0);

            switch(accion){
                case 'a':
                    break;
                case 'b':
                    break;
                case 'c':
                    break;
                case 'd':
                    break;
                default:
                    System.out.println("Por favor, selecciona una opción válida");
                    break;
            }
        }while(accion != 'd');
    }

    public static void menuPedidos(){
        char accion;
        do{
            System.out.println("\t*************************");
            System.out.println("\t**** MENU DE PEDIDOS ****");
            System.out.println("\t*************************");
            System.out.println("\ta. Crear un pedido para un cliente");
            System.out.println("\tb. Listar pedidos");
            System.out.println("\tc. Marcar pedido como entregado");
            System.out.println("\td. Ver el contenido de un pedido");
            System.out.println("\te. Cancelar un pedido");
            System.out.println("\tf. Volver");
            String entry = sc.nextLine();
            accion = entry.equals("") ? 'z' : entry.charAt(0);
            switch(accion){
                case 'a':
                    break;
                case 'b':
                    break;
                case 'c':
                    break;
                case 'd':
                    break;
                case 'e':
                    break;
                case 'f':
                    break;
                default:
                    System.out.println("Por favor, selecciona una opción válida");
                    break;
            }
        }while(accion != 'f');
    }

    public static void menuEstadisticas(){
        char accion;
        do{
            System.out.println("\t******************************");
            System.out.println("\t**** MENU DE ESTADISTICAS ****");
            System.out.println("\t******************************");
            System.out.println("\ta. Consultar pedidos realizados por un cliente");
            System.out.println("\tb. Mostrar el plato más vendido");
            System.out.println("\tc. Volver");
            String entry = sc.nextLine();
            accion = entry.equals("") ? 'z' : entry.charAt(0);
            switch(accion){
                case 'a':
                    break;
                case 'b':
                    break;
                case 'c':
                    break;
                case 'd':
                    break;
                default:
                    System.out.println("Por favor, selecciona una opción válida");
                    break;
            }
        }while(accion != 'c');
    }
}
