package cl.praxis.servicio;

import cl.praxis.modelo.Cliente;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExportarTxt extends Exportador{
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName + ".txt"))) {
            for (Cliente cliente : listaClientes) {
                writer.println("RUN del Cliente: " + cliente.getRunCliente());
                writer.println("Nombre del Cliente: " + cliente.getNombreCliente());
                writer.println("Apellido del Cliente: " + cliente.getApellidoCliente());
                writer.println("Años como Cliente: " + cliente.getAniosCliente());
                writer.println("Categoría del Cliente: " + cliente.getNombreCategoria());
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
