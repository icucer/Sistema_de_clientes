package cl.praxis.test;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.servicio.ClienteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteServicioTest {
    private ClienteServicio clienteServicio;

    @BeforeEach
    void setUp() {
        clienteServicio = new ClienteServicio();
    }

    @Test
    void agregarClienteTest() {
        Cliente cliente = new Cliente("1-9", "Juan", "Perez", 5, CategoriaEnum.ACTIVO);
        clienteServicio.agregarCliente(cliente);
        List<Cliente> clientes = clienteServicio.getListaClientes();
        assertEquals(1, clientes.size());
        assertEquals(cliente, clientes.get(0));
    }

    @Test
    void agregarClienteNullTest() {
        clienteServicio.agregarCliente(null);
        List<Cliente> clientes = clienteServicio.getListaClientes();
        assertEquals(0, clientes.size());
    }

}
