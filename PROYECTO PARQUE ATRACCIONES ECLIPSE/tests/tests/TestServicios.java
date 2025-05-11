package tests;
import parque.Servicios.*;
import parque.Tiquetes.Tiquete;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestServicios {
	// ATRIBUTOS E INSTANCIAS A USAR
	private String tipo, login, password, metodoCompra, nombre, turno, lugarAsignado, rol, tipoFuncion;
	private List<String> tipoPersonal;
	private List<Tiquete> tiquetesComprados;
	private double descuentoEmpleado;
	private LocalDateTime ultimaLimpieza, ultimoMantenimiento;
	
	private Cafeteria cafeteria;
	private Taquilla taquilla;
	private Cajero cajero;
	private Tienda tienda;
	
	void setUp() {
		cafeteria = new Cafeteria(tipo, tipoPersonal);
		taquilla = new Taquilla(tipo, tipoPersonal);
		tienda = new Tienda(tipo, tipoPersonal);
		
		cajero = new Cajero(login, password, tiquetesComprados, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado, tipoFuncion);
	}
}
