package org.common.services.xml;

import java.io.Serializable;

public class Error  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6292859140996469068L;
	private String descripcion;
	private String clave;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	@Override
	public String toString() {
		return "Error [descripcion=" + descripcion + ", clave=" + clave + "]";
	}
	public Error() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
