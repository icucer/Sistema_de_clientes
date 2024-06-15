package cl.praxis.servicio;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ArchivoServicio {
    public void cargarDatos(String fileName, List<Cliente> listaClientes) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Cliente cliente = new Cliente(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), CategoriaEnum.valueOf(datos[4]));
                listaClientes.add(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
