package org.common.dto;

import java.io.Serializable;
import java.io.InputStream;

public class DatosBasicosCFD implements Serializable {

	private static final long serialVersionUID = 8385906767938302195L;
	private String version;
	private String certificado;
	private String sello;
	private String serie;
	private String folio;
	private String noAprobacion;
	private String anoAprobacion;
	private String emisorRfc;
	private String receptorRfc;
	private String noCertificado;
	
	public DatosBasicosCFD() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCertificado() {
		return certificado;
	}
	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}
	public String getSello() {
		return sello;
	}
	public void setSello(String sello) {
		this.sello = sello;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getNoAprobacion() {
		return noAprobacion;
	}
	public void setNoAprobacion(String noAprobacion) {
		this.noAprobacion = noAprobacion;
	}
	public String getAnoAprobacion() {
		return anoAprobacion;
	}
	public void setAnoAprobacion(String anoAprobacion) {
		this.anoAprobacion = anoAprobacion;
	}
	public String getEmisorRfc() {
		return emisorRfc;
	}
	public void setEmisorRfc(String emisorRfc) {
		this.emisorRfc = emisorRfc;
	}
	public String getReceptorRfc() {
		return receptorRfc;
	}
	public void setReceptorRfc(String receptorRfc) {
		this.receptorRfc = receptorRfc;
	}
	public String getNoCertificado() {
		return noCertificado;
	}
	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}



	@Override
	public String toString() {
		return "DatosBasicosCFD [version=" + version + ", certificado="
				+ certificado + ", sello=" + sello + ", serie=" + serie
				+ ", folio=" + folio + ", noAprobacion=" + noAprobacion
				+ ", anoAprobacion=" + anoAprobacion + ", emisorRfc="
				+ emisorRfc + ", receptorRfc=" + receptorRfc
				+ ", noCertificado=" + noCertificado +"]";
	}


	public DatosBasicosCFD(String version, String certificado, String sello,
			String serie, String folio, String noAprobacion,
			String anoAprobacion, String emisorRfc, String receptorRfc,
			String noCertificado) {
		super();
		this.version = version;
		this.certificado = certificado;
		this.sello = sello;
		this.serie = serie;
		this.folio = folio;
		this.noAprobacion = noAprobacion;
		this.anoAprobacion = anoAprobacion;
		this.emisorRfc = emisorRfc;
		this.receptorRfc = receptorRfc;
		this.noCertificado = noCertificado;
	}


	

}
