package parque.Administración;

import java.util.ArrayList;
import java.util.List;

import parque.Tiquetes.Tiquete;

public class Cliente extends Usuario {

    private List<Tiquete> historialCompras;

    public Cliente(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, String nombre, List<Tiquete> historialCompras) {
        super(login, password, tiquetesComprados, metodoCompra, nombre); 
        this.historialCompras = historialCompras != null ? historialCompras : new ArrayList<>();
    }

    public List<Tiquete> getHistorialCompras() {
        return historialCompras;
    }

    public void agregarCompra(Tiquete tiquete) {
        if (tiquete != null) {
            historialCompras.add(tiquete);
            if (tiquetesComprados != null) {
                tiquetesComprados.add(tiquete);
            }
        }
    }

    public void mostrarHistorial() {
        System.out.println("Historial de compras de " + getNombre() + ":"); 
        for (Tiquete t : historialCompras) {
            System.out.println(" - " + t.getCategoriaTiquete());
        }
    }
}
