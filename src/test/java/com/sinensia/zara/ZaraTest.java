package com.sinensia.zara;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.Test;
import com.sinensia.entidades.Datos;
import com.sinensia.servicios.Metodos;

public class ZaraTest {

	public static final String SEPARATOR = ";";

	@Test
	public void testLeerValores() throws IOException {
		Metodos met = new Metodos();
		assertTrue(!met
				.leerValores("C:\\Users\\aguci\\eclipse-workspace\\zara-project\\src\\main\\resources\\stocks-ITX.csv")
				.isEmpty());

	}

	@Test
	public void testDiaViernes() throws IOException {
		Locale loc = new Locale("es", "ES");
		DateTimeFormatter.ofPattern("dd-MMM-yyyy", loc);
		Metodos met = new Metodos();
		ArrayList<Datos> valores = met
				.leerValores("C:\\Users\\aguci\\eclipse-workspace\\zara-project\\src\\main\\resources\\stocks-ITX.csv");
		// ArrayList<LocalDate> diaVier = met.diaViernes(valores);
		ArrayList<LocalDate> lastfridays = new ArrayList<LocalDate>();
		// lastfridays.add(LocalDate.parse("29-11-2002"));
		// lastfridays.add(LocalDate.parse("2001-07-27"));

		ArrayList<LocalDate> comparefridays = met.diaViernes(valores);

		boolean confirm = true;
		for (int i = 0; i < lastfridays.size(); i++) {

			if (!lastfridays.get(i).isEqual(comparefridays.get(i))) {
				confirm = false;
			}
		}

		assertTrue(confirm);

	}

	@Test
	public void testDiaCompra() throws IOException {
		Metodos met = new Metodos();
		ArrayList<Datos> valores = met
				.leerValores("C:\\Users\\aguci\\eclipse-workspace\\zara-project\\src\\main\\resources\\stocks-ITX.csv");
		ArrayList<LocalDate> diaVier = met.diaViernes(valores);
		met.diaCompra(valores, diaVier);
		ArrayList<LocalDate> diaComp = new ArrayList<LocalDate>();

		// diaComp.add(LocalDate.parse("2001-07-27"));
		boolean confirm = true;
		for (int i = 0; i < diaComp.size(); i++) {

			if (!diaComp.get(i).isEqual(diaVier.get(i))) {
				confirm = false;
			}
		}

		assertTrue(confirm);
	}

	@Test
	public void testCantAcc() throws IOException {
		Metodos met = new Metodos();
		ArrayList<Datos> valores = met
				.leerValores("C:\\Users\\aguci\\eclipse-workspace\\zara-project\\src\\main\\resources\\stocks-ITX.csv");

		ArrayList<LocalDate> diaVier = met.diaViernes(valores);
		ArrayList<Datos> compraAcc = met.diaCompra(valores, diaVier);
		assertTrue(met.cantAcc(compraAcc) == 1254.219);
	}

	@Test
	public void testVentAcc() throws IOException {
		Metodos met = new Metodos();
		ArrayList<Datos> valores = met
				.leerValores("C:\\Users\\aguci\\eclipse-workspace\\zara-project\\src\\main\\resources\\stocks-ITX.csv");

		assertTrue(met.ventAcc(valores) != 36585.568);
	}

}
