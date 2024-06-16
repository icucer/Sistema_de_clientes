package cl.praxis.utilidades;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import lombok.*;

import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Utilidad {
    private static Scanner sc = new Scanner(System.in);

    // Método para limpiar la pantalla alternativo
    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Método para realizar una pausa en la ejecución
    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static Cliente buscarCliente(List<Cliente> listaClientes) {
        System.out.print("Ingrese el RUN del Cliente a editar: ");
        String runCliente = sc.nextLine();
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(runCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public static CategoriaEnum seleccionarCategoria() {
        int opcion = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("Ingrese la categoría del cliente,\n");
                System.out.println("1. ACTIVO");
                System.out.println("2. INACTIVO");
                System.out.print("Ingrese una opcion: ");
                opcion = Integer.parseInt(sc.nextLine());

                if (opcion == 1 || opcion == 2) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
            }
        }

        CategoriaEnum categoria = null;
        switch (opcion) {
            case 1:
                categoria = CategoriaEnum.ACTIVO;
                break;
            case 2:
                categoria = CategoriaEnum.INACTIVO;
                break;
        }
        return categoria;
    }

    public static int seleccionarOS() {
        int opcionOS = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("\n----------Selecciona el sistema operativo----------");
                System.out.println("\n1. Linux o Mac");
                System.out.println("2. Windows");
                System.out.print("Ingrese una opcion: ");
                opcionOS = Integer.parseInt(sc.nextLine());
                System.out.print("\n---------------------------------------------------\n");

                if (opcionOS == 1 || opcionOS == 2) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
            }
        }
        return opcionOS;
    }

    public static int seleccionarTipoArchivo() {
        int opcionArchivo = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("\n----------Seleccione el formato del archivo----------");
                System.out.println("\n1. Archivo con extension CSV");
                System.out.println("2. Archivo con extension TXT");
                System.out.print("Ingrese una opcion: ");
                opcionArchivo = Integer.parseInt(sc.nextLine());
                System.out.print("\n-----------------------------------------------------\n");

                if (opcionArchivo == 1 || opcionArchivo == 2) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
            }
        }
        return opcionArchivo;
    }

    public static String solicitarRuta() {
        String ruta;
        System.out.println("------------------Ruta del archivo------------------");
        System.out.print("Ingrese la ruta: ");
        ruta = sc.nextLine();
        System.out.println("----------------------------------------------------");
        return ruta;

    }
}
