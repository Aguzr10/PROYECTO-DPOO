package tests;
import parque.Administración.*;
import parque.Categorías.Categoria;
import parque.Tiquetes.Tiquete;
import parque.Tiquetes.TiqueteBasico;

import java.util.ArrayList;
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
				   
				   nombreEmpleado = "Daneidy Barrera",
				   loginEmpleado = "daneidy.852",
				   passwordEmpleado = "dioDz4422",
				   
				   loginCliente = "diomedez.dz20",
				   passwordCliente = "daGom4422",
				   
				   lugarAsignado = "cafetería",
				   turno = "Doble turno",
				   rol = "cocinera/cajera",
				   metodoCompra = "taquilla virtual";
	private double descuentoEmpleado = 0.15;
	
	TiqueteBasico tiqueteOro = new TiqueteBasico(Categoria.ORO, false, 70000.0, false),
				  tiqueteDiamante = new TiqueteBasico(Categoria.DIAMANTE, false, 100000.0, false);
	
	private List<Tiquete> tiquetesComprados = new ArrayList<>(), 
	                      historialCompras = new ArrayList<>();
	
	private Administrador admin;
	private Cocinero cocinero;
	private Cliente cliente;
	               
	// TESTS
	@BeforeAll
	void setUp() {	
		admin = new Administrador(loginAdmin, passwordAdmin, tiquetesComprados, metodoCompra, descuentoEmpleado, nombreAdmin);
		cocinero = new Cocinero(loginEmpleado, passwordEmpleado, tiquetesComprados, metodoCompra,
				                nombreEmpleado, turno, lugarAsignado, rol, descuentoEmpleado);

		cliente = new Cliente(loginCliente, passwordCliente, tiquetesComprados, metodoCompra, historialCompras);
		cliente.agregarCompra(tiqueteOro);
		cliente.agregarCompra(tiqueteDiamante);
	}
	
	@Test
	void testSerializarAdmin() {
		assertEquals("ADMINISTRADOR;l.diaz7;luisDM223;Taquilla;0.15;Luis Díaz",
				admin.serializar(), "La serialización del Administrador no fue la esperada!");
	}
	
	@Test
	void testSerializarCocinero() {
		assertEquals("daneidy.852,danB332,Taquilla,Daneidy Barrera,Doble turno,cafetería,cocinera/cajera,0.15,",
				cocinero.serializar(), "La serialización del Cocinero no fue la esperada!");
	}
	@Test
	void testAutenticar() {
		assertTrue(cliente.autenticar(passwordCliente), "La autenticación falló!");
	}
	
	@Test
	void testSerializarCliente() {
		assertEquals("diomedez.dz20,daGom4422,taquilla virtual,2,ORO,false,70000.0,DIAMANTE,false,100000.0,false", 
				cliente.serializar(), "La serialización del Cliente no fue la esperada!");
	}
	
	@Test
	void testMostrarHistoria() {
		assertEquals("Historial de compras de doimedez.dz20: - ORO - DIAMANTE",
				cliente.getHistorialCompras(), "El historial de compras no fue el esperado!");
	}

	@Test
	void testModificarEmpleadoPorAdmin() {
	    admin.modificarEmpleado(cocinero, "chef ejecutivo");
	    assertEquals("chef ejecutivo", cocinero.getRol(), "El administrador no logró modificar el rol del empleado!");
	}

	@AfterAll
	void tearDown() throws Exception {}
}
