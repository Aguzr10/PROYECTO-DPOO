package parque;

public abstract class Tiquete {

    protected Categoria categoriaTiquete;
    protected boolean usoValidado;
    protected double precio;

    public Tiquete(Categoria categoriaTiquete, boolean usoValidado, double precio) {
        this.categoriaTiquete = categoriaTiquete;
        this.usoValidado = usoValidado;
        this.precio = precio;
    }

    public Categoria getCategoriaTiquete() {
        return categoriaTiquete;
    }

    public void setCategoriaTiquete(Categoria categoriaTiquete) {
        this.categoriaTiquete = categoriaTiquete;
    }

    public boolean isUsoValidado() {
        return usoValidado;
    }

    public void setUsoValidado(boolean usoValidado) {
        this.usoValidado = usoValidado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean verificarValidez() {
        // TODO
        return false;
    }

    public void marcarTiquete() {
        // TODO
    }

    // Constructor para pruebas: Este constructor es solo para pruebas.
    // Permite crear un Tiquete de manera más sencilla sin depender de una implementación concreta.
    public Tiquete(Categoria categoriaTiquete) {
        this.categoriaTiquete = categoriaTiquete;
        this.usoValidado = false;
        this.precio = 10.0; // Un precio de prueba
    }

    // Método adicional para pruebas: Permite modificar atributos para pruebas sin afectar la lógica original.
    public void setTiqueteParaPrueba(Categoria categoriaTiquete, boolean usoValidado, double precio) {
        this.categoriaTiquete = categoriaTiquete;
        this.usoValidado = usoValidado;
        this.precio = precio;
    }

    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append(categoriaTiquete).append(",");
        sb.append(usoValidado).append(",");
        sb.append(precio);
        return sb.toString();
    }

    public static String[] deserializarDatos(String linea) {
        return linea.split(",");
    }
}
