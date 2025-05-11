package parque.Administración;

import java.io.Serializable;
import java.util.List;

import parque.Tiquetes.Tiquete;

public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String login;
    protected String password;
    protected List<Tiquete> tiquetesComprados;
    protected String metodoCompra;
    protected String nombre;  

    public Usuario(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra, String nombre) {
        this.login = login;
        this.password = password;
        this.tiquetesComprados = tiquetesComprados;
        this.metodoCompra = metodoCompra;
        this.nombre = nombre;  
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tiquete> getTiquetesComprados() {
        return tiquetesComprados;
    }

    public void setTiquetesComprados(List<Tiquete> tiquetesComprados) {
        this.tiquetesComprados = tiquetesComprados;
    }

    public String getMetodoCompra() {
        return metodoCompra;
    }

    public void setMetodoCompra(String metodoCompra) {
        this.metodoCompra = metodoCompra;
    }

    public String getNombre() { 
        return nombre;
    }

    public void setNombre(String nombre) {  
        this.nombre = nombre;
    }

    public boolean autenticar(String password) {
        return this.password.equals(password);
    }

    public void comprarTiquete(Tiquete t) {
        if (tiquetesComprados != null) {
            tiquetesComprados.add(t);
        }
    }

    
    public String serializar() {
        StringBuilder sb = new StringBuilder();
        sb.append(login).append(",")
          .append(password).append(",")
          .append(metodoCompra)
          .append(",").append(nombre);  
        
        
        if (tiquetesComprados != null && !tiquetesComprados.isEmpty()) {
            sb.append(",").append(tiquetesComprados.size());
            for (Tiquete t : tiquetesComprados) {
                sb.append(",").append(t.serializar()); 
            }
        } else {
            sb.append(",0");
        }
        
        return sb.toString();
    }
}
