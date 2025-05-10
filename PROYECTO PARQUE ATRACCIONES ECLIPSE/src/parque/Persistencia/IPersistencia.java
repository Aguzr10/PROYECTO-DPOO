package parque.Persistencia;

import java.util.List;

import parque.Administración.Empleados;
import parque.Atracción.Atracciones;
import parque.Servicios.LugarServicio;
import parque.Tiquetes.Tiquete;


public interface IPersistencia {
    void guardarEmpleados(List<Empleados> empleados);
    List<Empleados> cargarEmpleados();

    void guardarAtracciones(List<Atracciones> atracciones);
    List<Atracciones> cargarAtracciones();

    void guardarTiquetes(List<Tiquete> tiquetes);
    List<Tiquete> cargarTiquetes();

    void guardarLugares(List<LugarServicio> lugares);
    List<LugarServicio> cargarLugares();
}