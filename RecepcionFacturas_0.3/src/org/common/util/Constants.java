package org.common.util;

public class Constants {

	public static final String XSLT_V2 = "org/common/resources/cadenaoriginal_2_0.xslt";
	public static final String XSLT_V3 = "org/common/resources/cadenaoriginal_3_0.xslt";
	public static final String XSLT_V22 = "org/common/resources/cadenaoriginal_2_2.xslt";
	public static final String XSLT_V32 = "org/common/resources/cadenaoriginal_3_2.xslt";
	public static final String XSLT_TIMBRE = "org/common/resources/cadenaoriginal_TFD_1_0.xslt";
	public static final String CFD_V2 = "2.0";
	public static final String CFD_V3 = "3.0";
	public static final String CFD_V22 = "2.2";
	public static final String CFD_V32 = "3.2";
	public static final String CFD_TIMBRE = "1.0";
	public static final String[] XSD_V2 ={"C:/xsd/v2/cfdv2.xsd"};
	public static final String[] XSD_V3 ={"C:/xsd/v3/cfdv3.xsd","C:/xsd/v3/TimbreFiscalDigital.xsd"};
	public static final String[] XSD_V22 ={"C:/xsd/v2/cfdv22.xsd"};
	public static final String[] XSD_V32 ={"C:/xsd/v3/cfdv32.xsd","C:/xsd/v3/TimbreFiscalDigital.xsd"};
	
	public static final String _CFD_VERSION = "cfd.version";
	public static final String _CFD_CERTIFICADO = "cfd.certificado";
	public static final String _CFD_SELLO = "cfd.sello";
	public static final String _CFD_SERIE = "cfd.serie";
	public static final String _CFD_FOLIO = "cfd.folio";
	public static final String _CFD_NOAPROBACION = "cfd.noAprobacion";
	public static final String _CFD_ANOAPROBACION = "cfd.anoAprobacion";
	public static final String _CFD_EMISOR_RFC = "cfd.emisor.rfc";
	public static final String _CFD_RECEPTOR_RFC = "cfd.receptor.rfc";
	public static final String _CFD_NOCERTIFICADO = "cfd.noCertificado";
	public static final String _ERROR_CVE_SIN_CERTIFICDO = "F001";
	public static final String _ERROR_DSC_SIN_CERTIFICDO = "No es posible continuar con las validaciones ya que el CFD no contiene certificado.";
	public static final String _ERROR_CVE_SELLO = "F002";
	public static final String _ERROR_DSC_SELLO = "La cadena origina no coincide con el sello digital del CFD, CFD no valido.";
	public static final String _ERROR_CVE_FOLIOSERIE = "F003";
	public static final String _ERROR_DSC_FOLIOSERIE = "No existen el folio y serie para ese RFC";
	public static final String _ERROR_CVE_NOCERTIFICADO = "F004";
	public static final String _ERROR_DSC_NOCERTIFICADO = "No existe el numero de serie para el certificado y RFC";
	
	  public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
	  public static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
	  public static final String SCHEMA_DOMAIN = "http://www.w3.org/TR/xml-schema-1";
	  public static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	  public static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	  public static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
	  public static final String ERROR_PARSING_ILEGAL_CONTENT_IN_PROLOG = "http://www.w3.org/TR/1998/REC-xml-19980210#ContentIllegalInProlog";
	  public static final String ERROR_PARSING_ILEGALIINTRAILINGMISC = "http://www.w3.org/TR/1998/REC-xml-19980210#ContentIllegalInTrailingMisc";
	  public static final String ADDENDA_COMODIN_COINCIDENCIA_ESTRICTO = "cvc-complex-type.2.4.c: el comodín de coincidencia es estricto";
			
}
