package com.practica.genericas;


import com.practica.excecption.EmsInvalidNumberOfDataException;

public class PosicionPersona {
	private Coordenada coordenada;
	private String documento;
	private FechaHora fechaPosicion;

	public PosicionPersona() {

	}

	public PosicionPersona(Coordenada coordenada, String documento, FechaHora fechaPosicion) {
		this.coordenada = coordenada;
		this.documento = documento;
		this.fechaPosicion = fechaPosicion;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public FechaHora getFechaPosicion() {
		return fechaPosicion;
	}
	public void setFechaPosicion(FechaHora fechaPosicion) {
		this.fechaPosicion = fechaPosicion;
	}

	public static PosicionPersona parsePosicionPersona(String[] data) throws EmsInvalidNumberOfDataException {
		final int MAX_DATA_POSICION_PERSONA = 6;
		// Data starts at index 1, so it needs to have 1 more space than there are attributes,
		// plus an extra 2 for coordinates.
		if (data.length != MAX_DATA_POSICION_PERSONA) {
			throw new EmsInvalidNumberOfDataException("La cantidad de campos de la localización es incorrecta");
		}

		String documento = data[1];
		FechaHora fechaPosicion = FechaHora.parseDateTime(data[2], data[3]);
		Coordenada coordenada = new Coordenada(Float.parseFloat(data[4]), Float.parseFloat(data[5]));
		return new PosicionPersona(coordenada, documento, fechaPosicion);
	}
	@Override
	public String toString() {
		String cadena = "";
        cadena += String.format("%s;", getDocumento());
        FechaHora fecha = getFechaPosicion();        
        cadena+=String.format("%02d/%02d/%04d;%02d:%02d;", 
	        		fecha.getFecha().getDia(), 
	        		fecha.getFecha().getMes(), 
	        		fecha.getFecha().getAnio(),
	        		fecha.getHora().getHora(),
	        		fecha.getHora().getMinuto());
        cadena+=String.format("%.4f;%.4f\n", getCoordenada().getLatitud(), 
	        		getCoordenada().getLongitud());
	
		return cadena;
	}
		
}
