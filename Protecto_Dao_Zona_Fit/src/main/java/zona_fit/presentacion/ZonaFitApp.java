package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.awt.*;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();

    }

    private static void zonaFitApp() {
        boolean salir = false;
        Scanner entrada = new Scanner(System.in);
        IClienteDAO clienteDAO = new ClienteDAO();
        //Creamos un objeto de la clase clienteDao
        while (!salir) {
            try {
                int opcion = mostrarMenu(entrada);
                salir = ejecutarOpciones(entrada, opcion, clienteDAO);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones" + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner entrada) {
        System.out.println("""
                **** zona fit (gym)
                1. Listar Clientes
                2. Buscar cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6.Salir
                Elije opcion:\s""");
        int opcion = Integer.parseInt(entrada.nextLine());
        return opcion;
    }

    private static boolean ejecutarOpciones(Scanner entrada, int opcion, IClienteDAO clienteDAO) {
        boolean salir = false;
        switch (opcion) {
            case 1 -> {
                //  1. Listar Cleintes
                System.out.println("---Listado de clientes---");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                //2.Buscar cliente por id
                System.out.println("---Buscar cliente---");
                System.out.print("Introduce el id del cliente a buscar: ");
                int id = Integer.parseInt(entrada.nextLine());
                Cliente cliente = new Cliente(id);
                boolean encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado) {
                    System.out.println("Cliente encontrado " + cliente.toString());

                } else {
                    System.out.println("Cliente no encontrado " + cliente.toString());
                }
            }
            case 3 -> {
                //3.Agregar cliente
                System.out.println("---Agregar cliente---");
                System.out.print("Introduce el nombre del cliente a agregar: ");
                String nombre = entrada.nextLine();
                System.out.print("Introduce el apellido del cliente a agregar: ");
                String apellido = entrada.nextLine();
                System.out.print("Introduce la membresia del cliente a agregar: ");
                int membresia = Integer.parseInt(entrada.nextLine());

                Cliente cliente = new Cliente(nombre, apellido, membresia);

                boolean agregado = clienteDAO.agregarCliente(cliente);
                if (agregado) {
                    System.out.println("Cliente agregado " + cliente.toString());
                } else {
                    System.out.println("Cliente no agregado " + cliente.toString());
                }
            }
            case 4 -> {
                //4. modificar cliente
                System.out.println("---Modificar cliente");
                System.out.println("Introduce el id del cliente a modificar");
                int idCliente = Integer.parseInt(entrada.nextLine());
                System.out.println("Introduce el nombre del cliente a modificar: ");
                String nombre = entrada.nextLine();
                System.out.println("Introduce el apellido del cliente a modificar: ");
                String apellido = entrada.nextLine();
                System.out.println("Introduce la membresia del cliente a modificar: ");
                int membresia = Integer.parseInt(entrada.nextLine());
                Cliente cliente = new Cliente(idCliente, nombre, apellido, membresia);
                boolean encontrado = clienteDAO.modificarCliente(cliente);
                if (encontrado) {
                    System.out.println("Cliente modificado " + cliente.toString());
                } else {
                    System.out.println("Cliente no encontrado " + cliente.toString());
                }
            }
            case 5 -> {
                //5. Eliminar cliente
                System.out.println("---Eliminar cliente---");
                System.out.println("Introduce el id del cliente a eliminar: ");
                int idCliente = Integer.parseInt(entrada.nextLine());
                Cliente cliente = new Cliente(idCliente);
                boolean eliminado = clienteDAO.eliminarCliente(cliente);
                if (eliminado) {
                    System.out.println("Cliente eliminado " + cliente.toString());
                } else {
                    System.out.println("Cliente no eliminado " + cliente.toString());
                }
            }
            case 6 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion no valida " + opcion);
        }

        return salir;
    }
}
