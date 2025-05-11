package tests;
import parque.Atracción.*;
import parque.Categorías.Categoria;
import parque.Categorías.Temporada;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestAtracción {
	// ATRIBUTOS A USAR E INSTANCIAS
	private String tipo, nombre, ubicacion, nivelExclusividad, nivelRiesgo, hora;
	private List<String> tipoPersonal;
	private int cupoMaximo, empleadosEncargados, empleadosMinimos;
	private boolean disponibilidadClima, restriccionEdad;
	private Categoria tipoTiquete;
	private Temporada temporada;
	private double alturaMin, alturaMax, pesoMin, pesoMax;
	private RestriccionMedica restricccion;
	private LocalDate fecha;
	
	private AtraccionCultural cultural;
	private AtraccionMecanica mecánica;
	private Espectaculo espectáculo;
	
	@BeforeAll
	void setUp() {
		cultural = new AtraccionCultural(tipo, tipoPersonal, nombre, ubicacion, cupoMaximo, empleadosEncargados, disponibilidadClima, tipoTiquete, temporada, restriccionEdad);
		mecánica = new AtraccionMecanica(tipo, tipoPersonal, nombre, ubicacion, cupoMaximo, empleadosEncargados, disponibilidadClima, tipoTiquete, temporada, alturaMin, alturaMax, pesoMin, pesoMax, restricccion, nivelRiesgo);
		espectáculo = new Espectaculo(fecha, hora, nombre);
	}
	
	
	@AfterAll
	void tearDown() {}
}
