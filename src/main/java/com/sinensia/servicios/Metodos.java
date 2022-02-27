package com.sinensia.servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.log4j.Logger;
import com.sinensia.entidades.Datos;

public class Metodos {

	private static Logger LogJava = Logger.getLogger(Metodos.class);

	private Double SumActions = 0.0;
	public static final String SEPARATOR = ";";

	public ArrayList<Datos> leerValores(String ruta) throws IOException {

		ArrayList<Datos> valores = new ArrayList<Datos>();
		boolean FirstLine = true;
		Locale loc = new Locale("es", "ES");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", loc);
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String line = br.readLine();
			while (null != line) {
				String[] fields = line.split(SEPARATOR);
				line = br.readLine();
				if (FirstLine) {
					FirstLine = false;
					continue;
				}
				Datos datos = new Datos(LocalDate.parse(fields[0], formatter), Double.parseDouble(fields[1]),
						Double.parseDouble(fields[2]));
				valores.add(datos);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			LogJava.error("An exception! Error!", e);
		}
		return valores;
	}

	public ArrayList<LocalDate> diaViernes(ArrayList<Datos> valores) {
		ArrayList<LocalDate> diaVier = new ArrayList<LocalDate>();
		for (Datos dias : valores) {
			int lastdayweek = -dias.getCurrentDate().with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek().getValue()
					- 3;
			int rest = lastdayweek % (-7);
			LocalDate lastFriday = dias.getCurrentDate().with(TemporalAdjusters.lastDayOfMonth()).plusDays(rest)
					.plusDays(1);
			if (diaVier.contains(lastFriday)) {
			} else {
				diaVier.add(lastFriday);
			}
		}

		diaVier.remove(0);
		return diaVier;
	}

	public ArrayList<Datos> diaCompra(ArrayList<Datos> valores, ArrayList<LocalDate> diaVier) {
		ArrayList<Datos> compraAcc = new ArrayList<Datos>();
		boolean prueba = false;
		Datos olderCloser = null;
		for (LocalDate diaCompra : diaVier) {
			int maxDia = 20;
			while (maxDia <= 20) {
				for (Datos dias : valores) {
					if (diaCompra.isBefore(dias.getCurrentDate()) || diaCompra.isEqual(dias.getCurrentDate())) {
						if (diaCompra.isEqual(dias.getCurrentDate())) {
							compraAcc.add(dias);
							prueba = false;
						} else {
							prueba = true;
						}
					} else if (dias.getCurrentDate().isBefore(diaCompra) && prueba == true) {
						compraAcc.add(olderCloser);
						prueba = false;
					}
					olderCloser = dias;
					maxDia++;
				}
			}
		}
		return compraAcc;
	}

	public Double cantAcc(ArrayList<Datos> compraAcc) {

		for (Datos datos : compraAcc) {
			SumActions = Math.round(
					((Math.round((50 - 50 * 0.02) / datos.getApertura() * 1000.0) / 1000.0) + SumActions) * 1000.0)
					/ 1000.0;
		}
		System.out.println("La compra de acciones fué de: " + SumActions);
		return SumActions;
	}

	public Double ventAcc(ArrayList<Datos> valores) {
		Double venta = Math.round(SumActions * valores.get(0).getCierre() * 1000.0) / 1000.0;
		System.out.println("La venta en su ciere al precio de " + valores.get(0).getCierre() + "€, dió un total de "
				+ venta + "€");
		return venta;
	}

}
