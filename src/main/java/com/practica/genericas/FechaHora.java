package com.practica.genericas;

import java.time.LocalDateTime;

public class FechaHora implements Comparable<FechaHora>{
	public static class Fecha {
		private int dia, mes, anio;
		 
		public Fecha(int dia, int mes, int anio) {
			super();
			this.dia = dia;
			this.mes = mes;
			this.anio = anio;
		}

		public int getDia() {
			return dia;
		}

		public void setDia(int dia) {
			this.dia = dia;
		}

		public int getMes() {
			return mes;
		}

		public void setMes(int mes) {
			this.mes = mes;
		}

		public int getAnio() {
			return anio;
		}

		public void setAnio(int anio) {
			this.anio = anio;
		}

		@Override
		public String toString() {
            return String.format("%2d/%02d/%4d",dia,mes,anio);
		}

		public static Fecha parseDate(String data) throws IllegalArgumentException{
			String[] dateValues = data.split("/");
			if (dateValues.length != 3) {
				throw new IllegalArgumentException(
					"String provided is not in the expected date format (dd/mm/yyyy)"
				);
			}

			int day = Integer.parseInt(dateValues[0]);
			int month = Integer.parseInt(dateValues[1]);
			int year = Integer.parseInt(dateValues[2]);

            return new Fecha(day, month, year);
		}
	}

	public static class Hora {
		private int hora, minuto;

		public Hora(int hora, int minuto) {
			super();
			this.hora = hora;
			this.minuto = minuto;
		}

		public int getHora() {
			return hora;
		}

		public void setHora(int hora) {
			this.hora = hora;
		}

		public int getMinuto() {
			return minuto;
		}

		public void setMinuto(int minuto) {
			this.minuto = minuto;
		}

		public static Hora parseTime(String data) {
			String[] timeValues = data.split(":");
			if (timeValues.length != 2) {
				throw new IllegalArgumentException(
					"String provided is not in the expected time format (hh:mm)"
				);
			}

			int hour = Integer.parseInt(timeValues[0]);
			int minute = Integer.parseInt(timeValues[1]);

			return new Hora(hour, minute);
		}

		@Override
		public String toString() {
			return String.format("%02d:%02d", hora,minuto);
		}
		

	}

	Fecha fecha;
	Hora hora;
	
	public FechaHora(Fecha fecha, Hora hora) {
		super();
		this.fecha = fecha;
		this.hora = hora;
	}

	public FechaHora(int dia, int mes, int anio, int hora, int minuto) {
		this.fecha = new Fecha(dia, mes, anio);
		this.hora = new Hora(hora, minuto);
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	public static FechaHora parseDateTime(String data) throws IllegalArgumentException {
		Fecha date = Fecha.parseDate(data);
		Hora time = new Hora(0, 0);
		return new FechaHora(date, time);
	}

	public static FechaHora parseDateTime(String fecha, String hora) throws IllegalArgumentException{
		Fecha date = Fecha.parseDate(fecha);
		Hora time = Hora.parseTime(hora);
		return new FechaHora(date, time);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FechaHora fecha = (FechaHora) obj;
		return getFecha().getDia() == fecha.getFecha().getDia() && getFecha().getMes() == fecha.getFecha().getMes()
				&& getFecha().getAnio() == fecha.getFecha().getAnio()
				&& getHora().getHora() == fecha.getHora().getHora()
				&& getHora().getMinuto() == fecha.getHora().getMinuto();
	}

	@Override
	public int compareTo(FechaHora o) {
		LocalDateTime dateTime1= LocalDateTime.of(this.getFecha().getAnio(), this.getFecha().getMes(), this.getFecha().getDia(), 
				this.getHora().getHora(), this.getHora().getMinuto());
		LocalDateTime dateTime2= LocalDateTime.of(o.getFecha().getAnio(), o.getFecha().getMes(), o.getFecha().getDia(), 
				o.getHora().getHora(), o.getHora().getMinuto());
		
		return dateTime1.compareTo(dateTime2);
	}
	
	
}
