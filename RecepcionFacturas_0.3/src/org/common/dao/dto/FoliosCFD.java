package org.common.dao.dto;

import java.io.Serializable;

public class FoliosCFD  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7394766012979624612L;
	private String serie;
	private Integer folioini;
	private Integer foliofin;
	private Integer noAprobacion;
	private Integer anoAprobacion;
	private String rfc;
	private Integer idFolio;
	
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Integer getFolioini() {
		return folioini;
	}
	public void setFolioini(Integer folioini) {
		this.folioini = folioini;
	}
	public Integer getFoliofin() {
		return foliofin;
	}
	public void setFoliofin(Integer foliofin) {
		this.foliofin = foliofin;
	}
	public Integer getNoAprobacion() {
		return noAprobacion;
	}
	public void setNoAprobacion(Integer noAprobacion) {
		this.noAprobacion = noAprobacion;
	}
	public Integer getAnoAprobacion() {
		return anoAprobacion;
	}
	public void setAnoAprobacion(Integer anoAprobacion) {
		this.anoAprobacion = anoAprobacion;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public Integer getIdFolio() {
		return idFolio;
	}
	public void setIdFolio(Integer idFolio) {
		this.idFolio = idFolio;
	}
	public FoliosCFD(String serie, Integer folioini, Integer foliofin,
			Integer noAprobacion, Integer anoAprobacion, String rfc,
			Integer idFolio) {
		super();
		this.serie = serie;
		this.folioini = folioini;
		this.foliofin = foliofin;
		this.noAprobacion = noAprobacion;
		this.anoAprobacion = anoAprobacion;
		this.rfc = rfc;
		this.idFolio = idFolio;
	}
	public FoliosCFD() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FoliosCFD [serie=" + serie + ", folioini=" + folioini
				+ ", foliofin=" + foliofin + ", noAprobacion=" + noAprobacion
				+ ", anoAprobacion=" + anoAprobacion + ", rfc=" + rfc
				+ ", idFolio=" + idFolio + ", getSerie()=" + getSerie()
				+ ", getFolioini()=" + getFolioini() + ", getFoliofin()="
				+ getFoliofin() + ", getNoAprobacion()=" + getNoAprobacion()
				+ ", getAnoAprobacion()=" + getAnoAprobacion() + ", getRfc()="
				+ getRfc() + ", getIdFolio()=" + getIdFolio() + "]";
	}

	
	

}
