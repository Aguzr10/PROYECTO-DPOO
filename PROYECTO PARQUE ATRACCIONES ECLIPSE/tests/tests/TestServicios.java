package tests;
import parque.Categorías.Categoria;
import parque.Servicios.*;
import parque.Tiquetes.Tiquete;
import parque.Tiquetes.TiqueteBasico;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestServicios {
	// ATRIBUTOS E INSTANCIAS A USAR
	private static String tipoCocina = "Cocina",
				   tipoPersonalCocinero = "Cocinero",
				   tipoCajero = "Cajero", // Aplica para personal también
				   tipoTaquilla = "Taquilla",
				   tipoPersonalTaquillero = "Taquillero",
				   
				   login = "daneidy.B52",
				   password = "danB332",
				   metodoCompra = "taquilla virtual",
				   nombre = "Daneidy Barrera",
				   turno = "Doble turno",
				   lugarAsignado = "cafetería",
				   rol = "cocinera/cajera",
				   tipoFuncion = "Cocinero";
	
	private static List<String> tipoPersonalCafeteria = new ArrayList<String>(),
						 tipoPersonalTaquilla = new ArrayList<String>(), 
						 tipoPersonalTienda = new ArrayList<String>();
	
	private static Tiquete tiqueteFamiliar = new TiqueteBasico(Categoria.FAMILIAR, false, 50000.0, false);;
	private static List<Tiquete> tiquetesComprados = new ArrayList<Tiquete>();
	private static double descuentoEmpleado = 0.15;
	private static LocalDateTime ultimaLimpieza = LocalDateTime.of(2025, 5, 11, 17, 45),
						  ultimoMantenimiento = LocalDateTime.of(2025, 5, 11, 14, 30);
	
	private static Cafeteria cafeteria;
	private static Taquilla taquilla;
	private static Cajero cajero;
	private static Tienda tienda;
	
	@BeforeAll
	public static void setUp() {
		tipoPersonalCafeteria.add(tipoPersonalCocinero);
		tipoPersonalCafeteria.add(tipoCajero);
		tipoPersonalTaquilla.add(tipoPersonalTaquillero);
		tipoPersonalTienda.add(tipoCajero);
		
		cafeteria = new Cafeteria(tipoCocina, tipoPersonalCafeteria);
		taquilla = new Taquilla(tipoTaquilla, tipoPersonalTaquilla);
		tienda = new Tienda(tipoCajero, tipoPersonalTienda);
		
		tiquetesComprados.add(tiqueteFamiliar);
		tiquetesComprados.add(tiqueteFamiliar);
		tiquetesComprados.add(tiqueteFamiliar);
		
		cajero = new Cajero(login, password, tiquetesComprados, metodoCompra, nombre, turno, lugarAsignado, rol, descuentoEmpleado, tipoFuncion);
	}
	
	@Test
	void testNecesitaCajero() {
		assertTrue(cafeteria.necesitaCajero(), "No se pudo determinar la necesidad de cajero correctamente!");
		assertFalse(tienda.necesitaCajero(), "No se pudo determinar la necesidad de cajero correctamente!");
	}
	
	@Test
	void testNecesitaCocinero() {
		assertTrue(cafeteria.necesitaCocinero(), "No se pudo determinar la necesidad de cocinero correctamente!");
		assertFalse(tienda.necesitaCajero(), "No se pudo determinar la necesidad de cocinero correctamente!");
	}
	
	@Test
	void testSerializarCafeteria() {
		assertEquals("Cocina,Cocinero;Cajero;",cafeteria.serializar(), "La serialización de Cocina no fue la esperada!");
	}
	
	@Test
	void testSerializarTaquilla() {
		assertEquals("Taquilla,Taquillero;",taquilla.serializar(), "La serialización de Taquilla no fue la esperada!");
	}
	
	@Test
	void testSerializarTienda() {
		assertEquals("Tienda,Cajero;", tienda.serializar(), "La serialización de Cajero no fue la esperada!");
	}
	
	void testSerializarCajero() {
		assertEquals(
				"daneidy.B52,danB332,taquilla virtual,Daneidy Barrera,Doble turno,cafetería,cocinera/cajera,0.15",
				cajero.serializar(), "La serialización de Cajero no fue la esperada!");
	}
	
	@AfterAll
	public static void tearDown() throws Exception {}
}
