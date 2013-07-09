package org.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.util.encoders.Base64;
import org.common.dao.dto.Csd;
import org.common.dao.dto.FoliosCFD;
import org.common.dto.DatosBasicosCFD;
import org.common.services.xml.Error;
import org.w3c.dom.Document;

public class ApplicationUtil {
	private static final Logger logger = Logger.getLogger(ApplicationUtil.class);

	public static String XSLT_VERSION(String version){
		String defaultXSLT = "";
		try {
			if (version.equals(Constants.CFD_V2)) {
				defaultXSLT = Constants.XSLT_V2;
				logger.debug("XSLT V2.0 "+defaultXSLT);
			}else if (version.equals(Constants.CFD_V3)) {
				defaultXSLT = Constants.XSLT_V3;
				logger.debug("XSLT V3.0 "+defaultXSLT);
			}else if (version.equals(Constants.CFD_V22)) {
				defaultXSLT = Constants.XSLT_V22;
				logger.debug("XSLT V2.2 "+defaultXSLT);
			}else if (version.equals(Constants.CFD_V32)) {
				defaultXSLT = Constants.XSLT_V32;
				logger.debug("XSLT V3.2 "+defaultXSLT);
			}else {
				
			}
					
		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		
		return defaultXSLT;
	}
	
	public static String[] XSD_VERSION(String version){
		String[] defaultXSLT = {""};
		try {
			if (version.equals(Constants.CFD_V2)) {
				defaultXSLT = Constants.XSD_V2;
				logger.debug("XSD V2.0 "+defaultXSLT.toString());
			}else if (version.equals(Constants.CFD_V3)) {
				defaultXSLT = Constants.XSD_V3;
				logger.debug("XSD V3.0 "+defaultXSLT.toString());
			}else if (version.equals(Constants.CFD_V22)) {
				defaultXSLT = Constants.XSD_V22;
				logger.debug("XSD V2.2 "+defaultXSLT.toString());
			}else if (version.equals(Constants.CFD_V32)) {
				defaultXSLT = Constants.XSD_V32;
				logger.debug("XSD V3.2 "+defaultXSLT.toString());
			}else {
				
			}
					
		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		
		return defaultXSLT;
	}
	
	
	 public static boolean isValidCertificate(DatosBasicosCFD datosBasicosCFD){
		 boolean isValid = false;
		 if(datosBasicosCFD.getCertificado() == null && datosBasicosCFD.getAnoAprobacion().equals("")){
			 logger.debug("No sera posible realizar las validaciones ya que no hay certificado en el xml");
		 }else{
			 isValid = true;
		 }
		 return isValid;
	 }
	 
	 public static boolean ValidaSello(byte[] cadenaOriginal, String certificado, String sello){
		 boolean isValid = false;
		 try{
			 
			 //Para sacar el certificado
			 byte[] pubkey = Base64.decode(certificado.getBytes()); 
			 ASN1Sequence as = (ASN1Sequence) ASN1Sequence.fromByteArray(pubkey); 
			 X509CertificateStructure x509CertStruct = X509CertificateStructure.getInstance(as);
			 //TODO validar que el certificado sea valido. 
			 ByteArrayOutputStream baos = new ByteArrayOutputStream(1024); 
			 ASN1OutputStream out = new ASN1OutputStream(baos); 
			 out.writeObject(x509CertStruct); 
			 out.close(); 
			 byte[] x509 = baos.toByteArray(); 
			 InputStream in = new ByteArrayInputStream(x509); 
			 Certificate x509Cert =  CertificateFactory.getInstance("X.509").generateCertificate(in); 
			 logger.debug( x509Cert.getPublicKey().getAlgorithm() ) ;
			 PublicKey pubKey =  x509Cert.getPublicKey();
			 //TODO no se si vale la pena poner MD5 para menores del 2010
			 Signature sig = Signature.getInstance("SHA1withRSA");
			 sig.initVerify(pubKey);
			 byte[] signature = Base64.decode(sello);
             sig.update(cadenaOriginal);
           
             isValid = sig.verify(signature);	 
			 logger.debug("Resultado de validacion del sello es: "+isValid);

		 }catch(SignatureException e){
			 logger.fatal(e.getMessage(),e);
			 e.printStackTrace();
		 }catch(Exception e){
			 logger.fatal(e.getMessage(),e);
			 e.printStackTrace();
		 }
		 
		 return isValid;
	 }
	 
	 public static byte[] inputStreamToByte(InputStream is){
		byte[] b = new byte[1024];
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 try {
				int bytesRead;
				while (( bytesRead = is.read(b) ) != -1) {
				   bos.write(b, 0, bytesRead);
				}
				bytes = bos.toByteArray();
				
		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		 return bytes;
	 }
	 
	 
	 public static String getXpathValue(String value,  InputStream xmlFile){
		 String valorNodo = "";
			try {
				ResourceBundle xpathProps = ResourceBundle.getBundle("org/common/resources/xpath");
				logger.debug("Entrando a xpath");
				DocumentBuilderFactory domFactory =  DocumentBuilderFactory.newInstance();
				domFactory.setNamespaceAware(true); 
				DocumentBuilder builder;
				builder = domFactory.newDocumentBuilder( );
				Document doc = builder.parse(xmlFile);
				XPath xpath = XPathFactory.newInstance().newXPath();

				String xpathString = xpathProps.getString(value);
				logger.debug("Expresion xpath :"+xpathString);
				valorNodo =  xpath.evaluate(xpathString, doc);
				logger.debug("String xpath :"+valorNodo);
				logger.debug("Terminando xpath");
			} catch (Exception e) {
				logger.fatal(e.getMessage(),e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return valorNodo;
	 }
	 
	 public static DatosBasicosCFD getXpathDatosBasicos(InputStream xmlFile){
		 DatosBasicosCFD datosBasicosCFD = new DatosBasicosCFD();
			try {
				ResourceBundle xpathProps = ResourceBundle.getBundle("org/common/resources/xpath");
				logger.debug("Entrando a xpath");
				DocumentBuilderFactory domFactory =  DocumentBuilderFactory.newInstance();
				domFactory.setNamespaceAware(true); 
				DocumentBuilder builder;
				builder = domFactory.newDocumentBuilder( );
				Document doc = builder.parse(xmlFile);
				XPath xpath = XPathFactory.newInstance().newXPath();

				datosBasicosCFD.setAnoAprobacion(xpath.evaluate(xpathProps.getString(Constants._CFD_ANOAPROBACION) , doc));
				datosBasicosCFD.setCertificado(xpath.evaluate(xpathProps.getString(Constants._CFD_CERTIFICADO) , doc));
				datosBasicosCFD.setEmisorRfc(xpath.evaluate(xpathProps.getString(Constants._CFD_EMISOR_RFC) , doc));
				datosBasicosCFD.setFolio(xpath.evaluate(xpathProps.getString(Constants._CFD_FOLIO ), doc));
				datosBasicosCFD.setNoAprobacion(xpath.evaluate(xpathProps.getString(Constants._CFD_NOAPROBACION) , doc));
				datosBasicosCFD.setNoCertificado(xpath.evaluate(xpathProps.getString(Constants._CFD_NOCERTIFICADO) , doc));
				datosBasicosCFD.setReceptorRfc(xpath.evaluate(xpathProps.getString(Constants._CFD_RECEPTOR_RFC) , doc));
				datosBasicosCFD.setSello(xpath.evaluate(xpathProps.getString(Constants._CFD_SELLO) , doc));
				datosBasicosCFD.setSerie(xpath.evaluate(xpathProps.getString(Constants._CFD_SERIE) , doc));
				datosBasicosCFD.setVersion(xpath.evaluate(xpathProps.getString(Constants._CFD_VERSION) , doc));
		
				logger.debug(datosBasicosCFD.toString());
				logger.debug("Terminando xpath");
			} catch (Exception e) {
				logger.fatal(e.getMessage(),e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return datosBasicosCFD;
	 }
	 
	 
	 public static List<Error> validaFolioSerieDB(DatosBasicosCFD datosCFD, FoliosCFD foliosCFD){
		List<Error> listaErrores = new ArrayList<Error>();
		 try {
			if(!datosCFD.getSerie().equals(foliosCFD.getSerie())){
				 org.common.services.xml.Error errores = new Error();
				 errores.setDescripcion("TODO");
				 errores.setClave("La serie no es la misma a la registrada en el SAT");
				 listaErrores.add(errores);
			}
			logger.debug(foliosCFD.getFolioini() + "  -- " + datosCFD.getFolio()  );
			logger.debug(foliosCFD.getFoliofin() + "  -- " + datosCFD.getFolio()  );
			
			if( ! ( new Integer(datosCFD.getFolio() )  >= (new Integer(foliosCFD.getFolioini())  ) 
					&&   ( new Integer(datosCFD.getFolio() ) <=  new Integer(foliosCFD.getFoliofin())   )) ){
				
				 org.common.services.xml.Error errores = new Error();
				 errores.setDescripcion("TODO");
				 errores.setClave("El folio no pertence al rango registrado en el SAT");
				 listaErrores.add(errores);
			}else{
				logger.debug("Dentro del rango de folios ...");
			}
			 
		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		 return listaErrores;
		 
	 }
	 
	 public static List<Error> validaCsdeDB(DatosBasicosCFD datosCFD, Csd csd){
		List<Error> listaErrores = new ArrayList<Error>();
		 try {
			 if(!datosCFD.getEmisorRfc().equals(csd.getRfc())){
				 org.common.services.xml.Error errores = new Error();
				 errores.setDescripcion("TODO");
				 errores.setClave("El RFC del emisor no es igual al RFC del certificado emitido por el SAT");
				 listaErrores.add(errores);
			 }
			 //TODO validar la fecha del certificado ya me dio gueva.
			 if(csd.getEdoCert().equals("R")){
				 org.common.services.xml.Error errores = new Error();
				 errores.setDescripcion("TODO");
				 errores.setClave("El no.certificado: "+csd.getNoSerie() + " esta revocado");
				 listaErrores.add(errores);
			 }
			 if(csd.getEdoCert().equals("c")){
				 org.common.services.xml.Error errores = new Error();
				 errores.setDescripcion("TODO");
				 errores.setClave("El no.certificado: "+csd.getNoSerie() + " esta cancelado");
				 listaErrores.add(errores);
				 
			 }	
			 
		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		 return listaErrores;
		 
	 }
	 
	 
	 
}
