package parque;

import java.io.*;
import java.util.List;

public class PersistenciaArchivo implements IPersistencia {

    private <T> void guardarObjeto(String archivo, List<T> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> cargarObjeto(String archivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void guardarEmpleados(List<parque.Empleados> empleados) {
        guardarObjeto("empleados.dat", empleados);
    }

    @Override
    public List<parque.Empleados> cargarEmpleados() {
        return cargarObjeto("empleados.dat");
    }

    @Override
    public void guardarAtracciones(List<parque.Atracciones> atracciones) {
        guardarObjeto("atracciones.dat", atracciones);
    }

    @Override
    public List<parque.Atracciones> cargarAtracciones() {
        return cargarObjeto("atracciones.dat");
    }

    @Override
    public void guardarTiquetes(List<parque.Tiquete> tiquetes) {
        guardarObjeto("tiquetes.dat", tiquetes);
    }

    @Override
    public List<parque.Tiquete> cargarTiquetes() {
        return cargarObjeto("tiquetes.dat");
    }

    @Override
    public void guardarLugares(List<parque.LugarServicio> lugares) {
        guardarObjeto("lugares.dat", lugares);
    }

    @Override
    public List<parque.LugarServicio> cargarLugares() {
        return cargarObjeto("lugares.dat");
    }
}
