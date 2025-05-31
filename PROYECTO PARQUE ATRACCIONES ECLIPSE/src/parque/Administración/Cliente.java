package parque.Administración;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import parque.Tiquetes.Tiquete;

public class Cliente extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Tiquete> historialCompras;
    private String correo;  

    public Cliente(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, String nombre, List<Tiquete> historialCompras, String correo) {
        super(login, password, tiquetesComprados, metodoCompra, nombre); 
        this.historialCompras = historialCompras != null ? historialCompras : new ArrayList<>();
        this.correo = correo; 
    }

    public Cliente(String login, String nombre, String metodoCompra, String correo) {
        super(login, null, new ArrayList<>(), metodoCompra, nombre);
        this.historialCompras = new ArrayList<>();
        this.correo = correo; 
    }

    
    public String getCorreo() {
        return correo;
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

    public String generarHistorial() {
        String historial = "Historial de compras de " + login + ":";
        for (Tiquete t : historialCompras) {
            historial += " - " + t.getCategoriaTiquete();
        }
        return historial;
    }
}
