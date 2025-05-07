package parque;

import java.io.Serializable;
import java.util.List;

public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String login;
    protected String password;
    protected List<Tiquete> tiquetesComprados;
    protected String metodoCompra;

    public Usuario(String login, String password, List<Tiquete> tiquetesComprados, String metodoCompra) {
        this.login = login;
        this.password = password;
        this.tiquetesComprados = tiquetesComprados;
        this.metodoCompra = metodoCompra;
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

    public boolean autenticar(String password) {
        return this.password.equals(password);
    }

    public void comprarTiquete(Tiquete t) {
        if (tiquetesComprados != null) {
            tiquetesComprados.add(t);
        }
    }
}
