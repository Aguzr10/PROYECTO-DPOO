package parque;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Tiquete> historialCompras;

    public Cliente(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, List<Tiquete> historialCompras) {
        super(login, password, tiquetesComprados, metodoCompra);
        this.historialCompras = historialCompras;
    }

    public List<Tiquete> getHistorialCompras() {
        return historialCompras;
    }

    public void agregarCompra(Tiquete tiquete) {
        if (tiquete != null && !historialCompras.contains(tiquete)) {
            historialCompras.add(tiquete);
        }
    }

    // Constructor para pruebas: Este constructor es para facilitar las pruebas sin tener que proporcionar todos los datos.
    // No forma parte del diseño UML original y solo se debe usar para facilitar la creación de objetos en los tests.
    public Cliente(String login) {
        super(login, "", null, "");
        this.historialCompras = new ArrayList<>();
    }

    // Método adicional para pruebas: Permite agregar un tiquete directamente al historial.
    // Es solo para pruebas y no forma parte de la lógica de negocio original.
    public void setTiquete(Tiquete tiquete) {
        this.historialCompras.add(tiquete);
    }
}
