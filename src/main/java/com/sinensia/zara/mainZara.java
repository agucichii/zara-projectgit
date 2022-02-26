package com.sinensia.zara;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sinensia.entidades.Datos;
import com.sinensia.servicios.Metodos;

public class mainZara {

	public static void main(String[] args) {

		try {

			Metodos met = new Metodos();
			ArrayList<Datos> valores = met.leerValores(
					"C:\\Users\\aguci\\eclipse-workspace\\zara-project\\src\\main\\resources\\stocks-ITX.csv");

			ArrayList<LocalDate> diaVier = met.diaViernes(valores);
			ArrayList<Datos> compraAcc = met.diaCompra(valores, diaVier);
			met.cantAcc(compraAcc);
			met.ventAcc(valores);
			System.out.println(diaVier);
		} catch (IOException e) {

		}

	}

}
