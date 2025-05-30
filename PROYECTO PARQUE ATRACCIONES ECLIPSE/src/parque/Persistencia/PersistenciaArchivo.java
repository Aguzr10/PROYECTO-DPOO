package parque.Persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import parque.Administración.Empleados;
import parque.Administración.Cliente;
import parque.Administración.Administrador;
import parque.Atracción.Atracciones;
import parque.Tiquetes.Tiquete;
import parque.Servicios.LugarServicio;

public class PersistenciaArchivo implements IPersistencia {
    private static final String BASE_PATH = "datos/";
    
    // Constructor que crea el directorio si no existe
    public PersistenciaArchivo() {
        crearDirectorioSiNoExiste();
    }
    
    private void crearDirectorioSiNoExiste() {
        File directorio = new File(BASE_PATH);
        if (!directorio.exists()) {
            boolean creado = directorio.mkdirs();
            if (creado) {
                System.out.println("Directorio 'datos' creado exitosamente.");
            } else {
                System.err.println("No se pudo crear el directorio 'datos'.");
            }
        }
    }
    
    private String ruta(String archivo) {
        return BASE_PATH + archivo;
    }
    
    private <T> void guardarObjeto(String archivo, List<T> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta(archivo)))) {
            out.writeObject(lista);
            System.out.println("Archivo " + archivo + " guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + archivo);
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private <T> List<T> cargarObjeto(String archivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta(archivo)))) {
            List<T> lista = (List<T>) in.readObject();
            System.out.println("Archivo " + archivo + " cargado exitosamente. Elementos: " + lista.size());
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + archivo + " no existe. Se retorna lista vacía.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar " + archivo + ". Se retorna lista vacía.");
            return new ArrayList<>();
        }
    }
    
    @Override
    public void guardarEmpleados(List<Empleados> empleados) {
        guardarObjeto("empleados.dat", empleados);
    }
    
    @Override
    public List<Empleados> cargarEmpleados() {
        return cargarObjeto("empleados.dat");
    }
    
    @Override
    public void guardarClientes(List<Cliente> clientes) {
        guardarObjeto("clientes.dat", clientes);
    }
    
    @Override
    public List<Cliente> cargarClientes() {
        return cargarObjeto("clientes.dat");
    }
    
    @Override
    public void guardarAdministradores(List<Administrador> administradores) {
        guardarObjeto("administradores.dat", administradores);
    }
    
    @Override
    public List<Administrador> cargarAdministradores() {
        return cargarObjeto("administradores.dat");
    }
    
    @Override
    public void guardarAtracciones(List<Atracciones> atracciones) {
        guardarObjeto("atracciones.dat", atracciones);
    }
    
    @Override
    public List<Atracciones> cargarAtracciones() {
        return cargarObjeto("atracciones.dat");
    }
    
    @Override
    public void guardarTiquetes(List<Tiquete> tiquetes) {
        guardarObjeto("tiquetes.dat", tiquetes);
    }
    
    @Override
    public List<Tiquete> cargarTiquetes() {
        return cargarObjeto("tiquetes.dat");
    }
    
    @Override
    public void guardarLugares(List<LugarServicio> lugares) {
        guardarObjeto("lugares.dat", lugares);
    }
    
    @Override
    public List<LugarServicio> cargarLugares() {
        return cargarObjeto("lugares.dat");
    }
}
