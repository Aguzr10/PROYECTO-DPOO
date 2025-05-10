package parque.Persistencia;

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
    public void guardarEmpleados(List<parque.Administración.Empleados> empleados) {
        guardarObjeto("empleados.dat", empleados);
    }

    @Override
    public List<parque.Administración.Empleados> cargarEmpleados() {
        return cargarObjeto("empleados.dat");
    }

    @Override
    public void guardarAtracciones(List<parque.Atracción.Atracciones> atracciones) {
        guardarObjeto("atracciones.dat", atracciones);
    }

    @Override
    public List<parque.Atracción.Atracciones> cargarAtracciones() {
        return cargarObjeto("atracciones.dat");
    }

    @Override
    public void guardarTiquetes(List<parque.Tiquetes.Tiquete> tiquetes) {
        guardarObjeto("tiquetes.dat", tiquetes);
    }

    @Override
    public List<parque.Tiquetes.Tiquete> cargarTiquetes() {
        return cargarObjeto("tiquetes.dat");
    }

    @Override
    public void guardarLugares(List<parque.Servicios.LugarServicio> lugares) {
        guardarObjeto("lugares.dat", lugares);
    }

    @Override
    public List<parque.Servicios.LugarServicio> cargarLugares() {
        return cargarObjeto("lugares.dat");
    }
}
