package cl.praxis.modelo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Cliente {
    private String runCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private int aniosCliente;
    private CategoriaEnum nombreCategoria;

    @Override
    public String toString() {
        return "-------------------Datos del cliente-------------------" +
                "\nRun del cliente: " + getRunCliente() +
                "\nNombre del cliente: " + getNombreCliente() +
                "\nApellido del cliente: " + getApellidoCliente() +
                "\nAños como cliente: " + getAniosCliente() +
                "\nCategoria del cliente: " + getNombreCategoria() +
                "\n-------------------------------------------------------";
    }
}
