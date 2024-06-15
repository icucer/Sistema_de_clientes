package cl.praxis.servicio;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.utilidades.Utilidad;
import cl.praxis.vista.Menu;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ClienteServicio {
    private List<Cliente> listaClientes = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private Cliente cliente = new Cliente();
    private Utilidad utilidad = new Utilidad();

    public void listarClientes() {
        if (!listaClientes.isEmpty()) {
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
        } else
            System.out.println("La lista de clientes está vacía.");
    }

    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
            listaClientes.add(cliente);
            System.out.println("Cliente agregado con exito.");
        } else
            System.out.println("Cliente nulo no se puede agregar.");
    }

    public void editarCliente(Cliente cliente) {
        int opcion = Menu.opcionEdicionCliente();
        if (cliente != null) {
            if (opcion == 2) {
                System.out.print("Nombre cliente - " + cliente.getNombreCliente() + ". " + "Ingrese el nuevo nombre: ");
                cliente.setNombreCliente(sc.nextLine());
                System.out.print("Apellido cliente - " + cliente.getApellidoCliente() + ". " + "Ingrese el nuevo apellido: ");
                cliente.setApellidoCliente(sc.nextLine());
                System.out.print("Años como cliente - " + cliente.getAniosCliente() + ". " + "Ingrese los nuevos años: ");
                cliente.setAniosCliente(Integer.parseInt(sc.nextLine()));
                System.out.println("Datos del cliente editados con exito.");
            } else if (opcion == 1) {
                System.out.println("Categoria actual del cliente - " + cliente.getNombreCategoria());
                CategoriaEnum categoria = utilidad.seleccionarCategoria();
                cliente.setNombreCategoria(categoria);
                System.out.println("Categoria del cliente editado con exito.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}
