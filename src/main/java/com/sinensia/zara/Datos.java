package com.sinensia.zara;

import java.time.LocalDate;

public class Datos {

	LocalDate CurrentDate;
	Double cierre;
	Double apertura;

	public Datos(LocalDate CurrentDate, Double cierre, Double apertura) {
		this.CurrentDate = CurrentDate;
		this.cierre = cierre;
		this.apertura = apertura;
	}

	public LocalDate getCurrentDate() {
		return CurrentDate;
	}

	public void setCurrentDate(LocalDate CurrentDate) {
		this.CurrentDate = CurrentDate;
	}

	public Double getCierre() {
		return cierre;
	}

	public void setCierre(Double cierre) {
		this.cierre = cierre;
	}

	public Double getApertura() {
		return apertura;
	}

	public void setApertura(Double apertura) {
		this.apertura = apertura;
	}

	@Override
	public String toString() {
		return "Datos{" + "CurrentDate=" + CurrentDate + ", cierre=" + cierre + ", apertura=" + apertura + '}';
	}

}