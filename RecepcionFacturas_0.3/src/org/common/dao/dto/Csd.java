package org.common.dao.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class Csd implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1874566378850183076L;
	private String noSerie;
	private Timestamp fechaini;
	private Timestamp fechafin;
	private String rfc;
	private String edoCert;
	private Long idCsd;
	public String getNoSerie() {
		return noSerie;
	}
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}
	public Timestamp getFechaini() {
		return fechaini;
	}
	public void setFechaini(Timestamp fechaini) {
		this.fechaini = fechaini;
	}
	public Timestamp getFechafin() {
		return fechafin;
	}
	public void setFechafin(Timestamp fechafin) {
		this.fechafin = fechafin;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getEdoCert() {
		return edoCert;
	}
	public void setEdoCert(String edoCert) {
		this.edoCert = edoCert;
	}
	public Long getIdCsd() {
		return idCsd;
	}
	public void setIdCsd(Long idCsd) {
		this.idCsd = idCsd;
	}
	@Override
	public String toString() {
		return "Csd [noSerie=" + noSerie + ", fechaini=" + fechaini
				+ ", fechafin=" + fechafin + ", rfc=" + rfc + ", edoCert="
				+ edoCert + ", idCsd=" + idCsd + "]";
	}
	public Csd() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
