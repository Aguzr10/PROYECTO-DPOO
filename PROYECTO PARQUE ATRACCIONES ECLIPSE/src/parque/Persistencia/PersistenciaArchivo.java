package parque.Persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import parque.Administración.Empleados;
import parque.Atracción.Atracciones;
import parque.Tiquetes.Tiquete;
import parque.Servicios.LugarServicio;

public class PersistenciaArchivo implements IPersistencia {

   
    private static final String BASE_PATH = "datos/";

    
    private String ruta(String archivo) {
        return BASE_PATH + archivo;
    }

   
    private <T> void guardarObjeto(String archivo, List<T> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta(archivo)))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + archivo);
            e.printStackTrace();
        }
    }

   
    @SuppressWarnings("unchecked")
    private <T> List<T> cargarObjeto(String archivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta(archivo)))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudo cargar " + archivo + " (quizás no existe). Se retorna lista vacía.");
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
