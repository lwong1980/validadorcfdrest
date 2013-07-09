package org.common.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.common.dao.FoliosCFDDAO;
import org.common.dao.dto.Csd;
import org.common.dao.dto.FoliosCFD;
import org.common.dto.DatosBasicosCFD;
import org.common.services.ValidaCFDUtil;
import org.common.services.ValidateSchemaAppService;
import org.common.services.xml.Error;
import org.common.util.ApplicationUtil;
import org.common.util.Constants;


public class ValidaCFDUtilImpl implements ValidaCFDUtil {
	private static final Logger logger = Logger.getLogger(ValidaCFDUtilImpl.class);
	private FoliosCFDDAO cfddao;
	private ValidateSchemaAppService validaXSD;
	
	 public static void main(String[] args) {
			 InputStream xmlFile = null;

		 try {
			 File xmlFi = new File("C:/Users/lwong/Desktop/CFDI_11_FIN051111SL7.XML");
	
		 	 xmlFile = new FileInputStream(xmlFi);
		 	DatosBasicosCFD datosBasicosCFD = ApplicationUtil.getXpathDatosBasicos( xmlFile);
		 	 xmlFile = new FileInputStream(xmlFi);
		 	new ValidaCFDUtilImpl().validaCadenaSello(datosBasicosCFD, xmlFile);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
	 

	 

	  
	 private boolean validaCadenaSello(DatosBasicosCFD datosBasicosCFD, InputStream xmlFile){
		 boolean isValid = false;
		 try{
			 
			//File xmlFi = new File("C:/Users/lwong/Desktop/cfdv2.xml");
				
			//InputStream is = new FileInputStream(xmlFi);
			 ByteArrayOutputStream cadenaOriginalByte = new ByteArrayOutputStream();
			 Result out = new StreamResult(cadenaOriginalByte); 
			 InputStream xsltFile =   ValidaCFDUtilImpl.class.getClassLoader().getResourceAsStream(ApplicationUtil.XSLT_VERSION(datosBasicosCFD.getVersion()));
			 javax.xml.transform.Source xmlSource = new javax.xml.transform.stream.StreamSource(xmlFile);
			 javax.xml.transform.Source xsltSource = new javax.xml.transform.stream.StreamSource(xsltFile);
			 // create an instance of TransformerFactory
			 javax.xml.transform.TransformerFactory transFact = javax.xml.transform.TransformerFactory.newInstance(  );
			 javax.xml.transform.Transformer trans = transFact.newTransformer(xsltSource);
			//Hasta aqui solo se tiene la cadena original en bytes.
			 trans.transform(xmlSource, out);
			 logger.info(new String(cadenaOriginalByte.toByteArray(),"UTF8"));
			//Hasta aqui saco los valores de certificado y sello
			 isValid = ApplicationUtil.ValidaSello(cadenaOriginalByte.toByteArray(), datosBasicosCFD.getCertificado(), datosBasicosCFD.getSello());
			 
			
		 }catch(Exception e){
			 logger.fatal(e.getMessage(),e);
			 e.printStackTrace();
		 }
		 
		 return isValid;
	 }
		  
	 

	 
	 public List<org.common.services.xml.Error> validaCFD(InputStream xml, String correlationID){
		
		 List<Error> listaErrores = new ArrayList<Error>();
		 DatosBasicosCFD datosBasicosCFD = new DatosBasicosCFD();
		 try {
			 logger.debug("Iniciando validaciones ...");
			 logger.debug("Paso 1 .- Lee archivo xml a POJO");
			 byte[] finalXML = ApplicationUtil.inputStreamToByte(xml);
			 
			 xml = new ByteArrayInputStream(finalXML);
			 datosBasicosCFD = ApplicationUtil.getXpathDatosBasicos(xml);
			 xml.close();
			 
			 logger.debug("Los datos basicos son: "+datosBasicosCFD.toString());
			 logger.debug("Paso 2 .- Valida si hay un certificado en el CFD");
			 if (!ApplicationUtil.isValidCertificate(datosBasicosCFD)){
				 logger.debug("No existe certificado y no continuara con el proceso ");
				 org.common.services.xml.Error errores = new Error();
				 errores.setDescripcion(Constants._ERROR_DSC_SIN_CERTIFICDO);
				 errores.setClave(Constants._ERROR_CVE_SIN_CERTIFICDO);
				 listaErrores.add(errores);
				 logger.debug("Terminando validaciones ... ");
			 }else{
				 logger.debug("Paso 3.- Valida si el sello es valido vs cadena original");
				 xml = new ByteArrayInputStream(finalXML);				 
				 boolean isValid = new ValidaCFDUtilImpl().validaCadenaSello(datosBasicosCFD,  xml);
				 xml.close();
				 if(!isValid){
					 logger.debug("La cadena original con coincide con el sello del CFD ");
					 org.common.services.xml.Error errores = new Error();
					 errores.setDescripcion(Constants._ERROR_DSC_SIN_CERTIFICDO);
					 errores.setClave(Constants._ERROR_CVE_SIN_CERTIFICDO);
					 
					 listaErrores.add(errores);
					 logger.debug("continua con las validaciones ... ");
				 }
				 logger.debug("Paso 4.- Determimando version para validar folio y serie");
				 if(datosBasicosCFD.getVersion().equals(Constants.CFD_V2)){
					logger.debug("Paso 4a.- Validando version "+datosBasicosCFD.getVersion()+" folio y serie "+datosBasicosCFD.getEmisorRfc());
					FoliosCFD cfd = new FoliosCFD();
					cfd.setRfc(datosBasicosCFD.getEmisorRfc());
					cfd.setAnoAprobacion(new Integer(datosBasicosCFD.getAnoAprobacion()));
					cfd.setNoAprobacion(new Integer(datosBasicosCFD.getNoAprobacion()));
					FoliosCFD  foliosCFD= cfddao.getFoliosCFDbyRfc(cfd);
					if(foliosCFD== null){
						logger.debug("No existe el RFC");
						org.common.services.xml.Error errores = new Error();
						errores.setDescripcion(Constants._ERROR_DSC_FOLIOSERIE);
						errores.setClave(Constants._ERROR_CVE_FOLIOSERIE);
						listaErrores.add(errores);
					}else{
						listaErrores.addAll( ApplicationUtil.validaFolioSerieDB(datosBasicosCFD, foliosCFD) );
					}
					 
				 }else if (datosBasicosCFD.getVersion().equals(Constants.CFD_V3)){
					 logger.debug("Paso 4b.- Validando timbre ya que es version "+Constants.CFD_V3);
				 }else{
					 logger.debug("No hay validacion 4. ya que no hay forma de comprobar la version");
				 }
				 logger.debug("Paso 5.- Determinando si el No.Certificdo es valido para Emisor.");
				 Csd csd = new Csd();
				 csd.setNoSerie(datosBasicosCFD.getNoCertificado());
				 Csd csdResult = cfddao.getCsdbyNoSerie(csd);
				 if(csdResult== null){
					  logger.debug("No existe el No.Certificado "+csd.getNoSerie());
					  org.common.services.xml.Error errores = new Error();
					  errores.setDescripcion(Constants._ERROR_DSC_NOCERTIFICADO);
					  errores.setClave(Constants._ERROR_CVE_NOCERTIFICADO);
					  listaErrores.add(errores);
				 }else{
					 logger.debug(csdResult.toString());
					 listaErrores.addAll( ApplicationUtil.validaCsdeDB(datosBasicosCFD, csdResult) );

				 }
				 
				 logger.debug("Paso 6.- Validar contra XSD de SAT.");
				 xml = new ByteArrayInputStream(finalXML);
				 listaErrores.addAll(validaXSD.validateSchema(xml, "es",datosBasicosCFD.getVersion()));
			 }

			
		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		 logger.debug("Total de errores: "+listaErrores.size() );
		 return listaErrores;
	 }



	 


	public FoliosCFDDAO getCfddao() {
		return cfddao;
	}





	public void setCfddao(FoliosCFDDAO cfddao) {
		this.cfddao = cfddao;
	}





	public ValidateSchemaAppService getValidaXSD() {
		return validaXSD;
	}





	public void setValidaXSD(ValidateSchemaAppService validaXSD) {
		this.validaXSD = validaXSD;
	}
	
	
}
