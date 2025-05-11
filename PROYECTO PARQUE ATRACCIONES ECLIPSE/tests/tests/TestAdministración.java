package tests;
import parque.Administración.*;
import parque.Tiquetes.Tiquete;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestAdministración {
	// ATRIBUTOS E INSTANCIAS
	private String nombreAdmin = "Luis Díaz",
				   loginAdmin = "l.diaz7",
				   passwordAdmin = "luisDM223", 
				   nombreEmpleado, loginEmpleado, passwordEmpleado,
				   lugarAsignado, turno, rol, metodoCompra;
	private double descuentoEmpleado = 0.15;
	private List<Tiquete> tiquetesComprados;
	
	private Administrador admin;
	private Cocinero empleado;
	               
	@BeforeAll
	void setUp() {
		admin = new Administrador(loginAdmin, passwordAdmin, tiquetesComprados, metodoCompra, descuentoEmpleado, nombreAdmin);
		empleado = new Cocinero(loginEmpleado, passwordEmpleado, tiquetesComprados, metodoCompra,
				                nombreEmpleado, turno, lugarAsignado, rol, descuentoEmpleado);
	}
	
	@Test
	void testSerializarAdmin() {
		assertEquals("ADMINISTRADOR;l.diaz7;;" + passwordAdmin + ";" + metodoCompra + ";" + descuentoEmpleado + ";Luis Díaz",
				admin.serializar(), "La serialización no fue la esperada!");
	}
	
	@Test
	void testSerializarEmpleado() {
		assertEquals(loginEmpleado + "," + passwordEmpleado + "," + metodoCompra + "," + nombreEmpleado + "," +
					 turno + "," + lugarAsignado+ "," + rol + "," + descuentoEmpleado + "," + empleado,
					 empleado.serializar());
	}
	
	@AfterAll
	void tearDown() throws Exception {}
}
