package com.practica.genericas;


import com.practica.excecption.EmsInvalidNumberOfDataException;

public class Persona {
	private String nombre, apellidos, documento, email, direccion, cp;
	FechaHora fechaNacimiento;

    public Persona() {

	}

	public Persona(String nombre, String apellidos, String documento, String email, String direccion,
			String cp, FechaHora fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.documento = documento;
		this.email = email;
		this.direccion = direccion;
		this.cp = cp;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public FechaHora getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(FechaHora fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		FechaHora fecha = getFechaNacimiento();
		String cadena = "";
		// Documento
		cadena += String.format("%s;", getDocumento());
		// Nombre y apellidos
		cadena += String.format("%s,%s;", getApellidos(), getNombre());
		// correo electrónico
		cadena += String.format("%s;", getEmail());
        // Direccion y código postal
		cadena += String.format("%s,%s;", getDireccion(), getCp());
        // Fecha de nacimiento
		cadena+=String.format("%02d/%02d/%04d\n", fecha.getFecha().getDia(), 
        		fecha.getFecha().getMes(), 
        		fecha.getFecha().getAnio());

		return cadena;
	}

	public static Persona parsePerson(String[] data) throws EmsInvalidNumberOfDataException {
        final int MAX_DATA_PERSONA = 8;
		// Because persona attributes start at index 1 we need to add 1 to the number of attributes of the class.

        if (data.length != MAX_DATA_PERSONA) {
			throw new EmsInvalidNumberOfDataException("Invalid number of fields for PERSONA");
		}
		String document = data[1];
		String name = data[2];
		String surnames = data[3];
		String email = data[4];
		String address = data[5];
		String postCode = data[6];
		FechaHora dateOfBirth = FechaHora.parseDateTime(data[7]);

		return new Persona(name, surnames, document, email, address, postCode, dateOfBirth);
	}
}
