package entities;

public class Entrada {
	private int id;
	private int asistente_id;
	private int show_id;
	private String codigo;
	private String nombre;
	private String apellido;
	private String tipo_doc;
	private String documento;
	private boolean validez;

	public Entrada(int id, int asistente_id, int show_id, String codigo, String nombre, String apellido, String tipo_doc,
			String documento, boolean validez) {
		this.asistente_id = asistente_id;
		this.show_id = show_id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo_doc = tipo_doc;
		this.documento = documento;
		this.validez = validez;
	}
	
	public Entrada(int asistente_id, int show_id, String codigo, String nombre, String apellido, String tipo_doc,
			String documento, boolean validez) {
		this.asistente_id = asistente_id;
		this.show_id = show_id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo_doc = tipo_doc;
		this.documento = documento;
		this.validez = validez;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAsistente_id() {
		return asistente_id;
	}

	public void setAsistente_id(int asistente_id) {
		this.asistente_id = asistente_id;
	}

	public int getShow_id() {
		return show_id;
	}

	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public boolean isValidez() {
		return validez;
	}

	public void setValidez(boolean validez) {
		this.validez = validez;
	}

}
