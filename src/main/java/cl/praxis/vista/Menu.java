package cl.praxis.vista;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.servicio.ArchivoServicio;
import cl.praxis.servicio.ClienteServicio;
import cl.praxis.servicio.ExportarCsv;
import cl.praxis.servicio.ExportarTxt;
import cl.praxis.utilidades.Utilidad;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private ClienteServicio clienteServicio = new ClienteServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();
    private ExportarCsv exportarCsv = new ExportarCsv();
    private ExportarTxt exportarTxt = new ExportarTxt();
    private String nombreFicheroExportar = "Clientes";
    private String nombreFicheroImportar = "DBClientes.csv";
    private static Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {

        int opcion;
        do {
            System.out.println(" \n +-----------------------------+");
            System.out.println(" |            MENU:            |");
            System.out.println(" +-----------------------------+");
            System.out.println(" |   1.    Listar Clientes     |");
            System.out.println(" |   2.    Agregar Cliente     |");
            System.out.println(" |   3.    Editar Cliente      |");
            System.out.println(" |   4.    Cargar Datos        |");
            System.out.println(" |   5.    Exportar Datos      |");
            System.out.println(" |   6.        Salir           |");
            System.out.println(" +-----------------------------+");
            System.out.print("\nIngrese una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());


                switch (opcion) {
                    case 1:
                        clienteServicio.listarClientes();
                        break;
                    case 2:
                        agregarCliente();
                        break;
                    case 3:
                        Cliente cliente = Utilidad.buscarCliente(clienteServicio.getListaClientes());
                        clienteServicio.editarCliente(cliente);
                        break;
                    case 4:
                        importarDatos();
                        break;
                    case 5:
                        exportarDatos();
                        break;
                    case 6:
                        terminarPrograma();
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese una opcion valida.");
                opcion = -1;
            }
        } while (opcion != 6);
    }

    private void agregarCliente() {
        System.out.println("-------------------Crear cliente-------------------");
        System.out.print("Ingrese el RUN del Cliente: ");
        String run = scanner.nextLine();
        System.out.print("Ingrese el Nombre del Cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el Apellido del Cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese los Años como Cliente: ");
        int anios = Integer.parseInt(scanner.nextLine());
        CategoriaEnum categoria = Utilidad.seleccionarCategoria();
        System.out.println("---------------------------------------------------");

        Cliente cliente = new Cliente(run, nombre, apellido, anios, categoria);
        clienteServicio.agregarCliente(cliente);
    }

    public static int opcionEdicionCliente() {
        int opcion = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.println("\n-------------------Editar cliente-------------------");
                System.out.println("Seleccione qué desea hacer.");
                System.out.println("\n1 Cambiar el estado del cliente");
                System.out.println("2 Editar los datos ingresados del cliente");
                System.out.print("\nIngrese opcion: ");
                opcion = Integer.parseInt(scanner.nextLine());
                System.out.println("----------------------------------------------------");

                if (opcion == 1 || opcion == 2) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
            }
        }
        return opcion;
    }

    private void importarDatos() {
        int opcionOS = Utilidad.seleccionarOS();
        String rutaBase = Utilidad.solicitarRuta();
        String rutaCompleta;

        switch (opcionOS) {
            case 1:
                // Linux o Mac
                rutaCompleta = Paths.get(rutaBase, nombreFicheroImportar).toString();
                break;
            case 2:
                // Windows
                rutaCompleta = Paths.get(rutaBase, nombreFicheroImportar).toString();
                break;
            default:
                throw new IllegalStateException("Opción de sistema operativo no válida: " + opcionOS);
        }

        if (Files.exists(Paths.get(rutaCompleta))) {
            archivoServicio.cargarDatos(rutaCompleta, clienteServicio.getListaClientes());
            System.out.println("Datos importados con éxito en la lista.");
        } else {
            System.out.println("El archivo no se encuentra en la ruta especificada. Por favor, verifique la ruta e intente nuevamente.");
        }
    }

    private void exportarDatos() {
        int opcionOS = Utilidad.seleccionarOS();
        int opcionTipoArchivo = Utilidad.seleccionarTipoArchivo();
        String rutaBase = Utilidad.solicitarRuta();
        String rutaCompleta;

        switch (opcionOS) {
            case 1:
                // Linux o Mac
                rutaCompleta = Paths.get(rutaBase, nombreFicheroExportar).toString();
                break;
            case 2:
                // Windows
                rutaCompleta = Paths.get(rutaBase, nombreFicheroExportar).toString();
                break;
            default:
                throw new IllegalStateException("Opción de sistema operativo no válida: " + opcionOS);
        }

        switch (opcionTipoArchivo) {
            case 1:
                exportarCsv.exportar(rutaCompleta, clienteServicio.getListaClientes());
                System.out.println("\nDatos de clientes exportados correctamente en formato csv.");
                System.out.println("Con la siguiente ruta: " + rutaCompleta);
                break;
            case 2:
                exportarTxt.exportar(rutaCompleta, clienteServicio.getListaClientes());
                System.out.println("\nDatos de clientes exportados correctamente en formato txt.");
                System.out.println("Con la siguiente ruta: " + rutaCompleta);
                break;
            default:
                throw new IllegalStateException("Opción de tipo de archivo no válida: " + opcionTipoArchivo);
        }
    }


    private void terminarPrograma() {
        System.out.println("Saliendo del programa...");
        System.exit(0);
    }
}