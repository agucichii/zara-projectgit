package com.sinensia.zara;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Locale;

public class metodos {


    private final ArrayList<Datos> valores = new ArrayList<Datos>();
    private final ArrayList<Datos> compraAcc = new ArrayList<Datos>();
    private Double SumActions = 0.0;
    public static final String SEPARATOR = ";";

    public void leerValores() throws IOException {
        boolean FirstLine = true;
        Locale loc = new Locale("es", "ES");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", loc);
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\aguci\\Desktop\\CursoTrabajoJava\\PruebaExamen\\stocks-ITX.csv"))) {
			String line = br.readLine();
			while (null != line) {
			    String[] fields = line.split(SEPARATOR);
			    line = br.readLine();
			    if (FirstLine) {
			        FirstLine = false;
			        continue;
			    }
			    Datos datos = new Datos(LocalDate.parse(fields[0], formatter), Double.parseDouble(fields[1]), Double.parseDouble(fields[2]));
			    valores.add(datos);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
    }

    public void diaCompra() {
        boolean prueba = false;
        Datos olderCloser = null;
        for (Datos dias : valores) {
            int lastdayweek = -dias.getCurrentDate().with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek().getValue() - 2;
            int rest = lastdayweek % (-7);
            LocalDate lastFriday = dias.getCurrentDate().with(TemporalAdjusters.lastDayOfMonth()).plusDays(rest);
            if (lastFriday.isBefore(dias.getCurrentDate()) || lastFriday.isEqual(dias.getCurrentDate())) {
                if (lastFriday.isEqual(dias.getCurrentDate())) {
                    compraAcc.add(dias);
                    prueba = false;
                } else {
                    prueba = true;
                }
            } else if (dias.getCurrentDate().isBefore(lastFriday) && prueba == true) {
                compraAcc.add(olderCloser);
                prueba = false;
            }
            olderCloser = dias;
        }
    }

    public void cantAcc() {

        for (Datos datos : compraAcc) {
            SumActions = Math.round(((Math.round((50 - 50 * 0.02) / datos.getApertura() * 1000.0) / 1000.0) + SumActions) * 1000.0) / 1000.0;
        }
        System.out.println("La compra de acciones fué de: " + SumActions);
    }

    public void ventAcc() {
        Double venta = Math.round(SumActions * valores.get(0).getCierre() * 1000.0) / 1000.0;
        System.out.println("La venta en su ciere al precio de " + valores.get(0).getCierre() + "€, dió un total de " + venta + "€");
    }
	
}
