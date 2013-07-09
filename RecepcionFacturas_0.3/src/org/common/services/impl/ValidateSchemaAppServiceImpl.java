
package org.common.services.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.apache.xerces.impl.XMLErrorReporter;
import org.common.services.ValidateSchemaAppService;
import org.common.services.xml.DefaultHandlerError;
import org.common.services.xml.Error;
import org.common.services.xml.XMLFormatOutput;
import org.common.services.xml.XMLHandlerError;
import org.common.util.ApplicationUtil;
import org.common.util.Constants;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ValidateSchemaAppServiceImpl implements ValidateSchemaAppService {
	
	public static final Logger logger = Logger.getLogger(ValidateSchemaAppServiceImpl.class);
	private static XMLErrorReporter reporter = new XMLErrorReporter();
	private static List<Error> listaErrores = new ArrayList<Error>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.metlife.interware.recepcion.appservice.impl.ValidateSchemaAppService
	 * #validateSchema()
	 */

	public List<Error> validateSchema(InputStream xml,String lang) throws Exception {
		logger.debug("Validando Schema XML ...");
		try {
			// Selecciona el idioma;
			Locale locale = new Locale(lang);
			XMLHandlerError xmlError = new XMLHandlerError();
			DefaultHandlerError dError = new DefaultHandlerError();
			//String[] fileName = {"C:/xsd/v3/cfdv3.xsd","C:/xsd/v3/TimbreFiscalDigital.xsd"};
			String[] fileName = {"C:/xsd/v2/cfdv2.xsd"};

			SAXParserFactory spf = SAXParserFactory.newInstance();			
			spf.setNamespaceAware(true);
			spf.setXIncludeAware(true);
			spf.setValidating(true);			
			SAXParser sp = spf.newSAXParser();
			
			sp.setProperty(Constants.JAXP_SCHEMA_LANGUAGE,Constants.W3C_XML_SCHEMA);
			// Trae el tipo de XSD
				//XSD
			sp.setProperty(Constants.JAXP_SCHEMA_SOURCE, fileName);
									
			reporter.putMessageFormatter(Constants.ERROR_REPORTER, new XMLFormatOutput());
			reporter.putMessageFormatter(Constants.SCHEMA_DOMAIN, new XMLFormatOutput());
			reporter.setLocale(locale);
			reporter.setProperty(Constants.ERROR_HANDLER, xmlError);
			sp.setProperty(Constants.ERROR_REPORTER, reporter);
			InputSource source = new InputSource(xml);
			
			//sp.parse(new InputSource(new ByteArrayInputStream(stripAddenda( xml ).getBytes("UTF8"))), dError);
			//TODO verificar los namespaces
			sp.parse(source, dError);
			
			listaErrores = xmlError.getList();

		} catch (SAXException se) {
			logger.error("Ocurrio una excepcion Al parsear el archivo ");
			logger.error("El mensaje de error es  " + se.getMessage());
			if(se.getMessage().equals(Constants.ERROR_PARSING_ILEGAL_CONTENT_IN_PROLOG)){
				logger.info("El error corresponde a un Contenido Ilegal -> Se intentara parsear el archivo nuevamente...");
				try {
					//return parseBOM(xml, type, lang);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else{
				se.printStackTrace();
				logger.error(se.toString());
				Error error = new Error();
				error.setClave(se.getMessage());
				error.setDescripcion(se.getLocalizedMessage());
				listaErrores.add(error);
			}
			
		} catch (Exception e) {
			logger.error(e.toString());
			Error error = new Error();
			error.setClave(e.getMessage());
			error.setDescripcion(e.getLocalizedMessage());
			listaErrores.add(error);
		}
		logger.debug("Terminando validacion de schema ...");
		return listaErrores;
	}


	public List<Error> validateSchema(InputStream xml,String lang,String version) throws Exception {
		logger.debug("Validando Schema XML ...");
		try {
			// Selecciona el idioma;
			Locale locale = new Locale(lang);
			XMLHandlerError xmlError = new XMLHandlerError();
			DefaultHandlerError dError = new DefaultHandlerError();
			//String[] fileName = {"C:/xsd/v3/cfdv3.xsd","C:/xsd/v3/TimbreFiscalDigital.xsd"};
			String[] fileName = ApplicationUtil.XSD_VERSION(version);

			SAXParserFactory spf = SAXParserFactory.newInstance();			
			spf.setNamespaceAware(true);
			spf.setXIncludeAware(true);
			spf.setValidating(true);			
			SAXParser sp = spf.newSAXParser();
			
			sp.setProperty(Constants.JAXP_SCHEMA_LANGUAGE,Constants.W3C_XML_SCHEMA);
			// Trae el tipo de XSD
				//XSD
			sp.setProperty(Constants.JAXP_SCHEMA_SOURCE, fileName);
									
			reporter.putMessageFormatter(Constants.ERROR_REPORTER, new XMLFormatOutput());
			reporter.putMessageFormatter(Constants.SCHEMA_DOMAIN, new XMLFormatOutput());
			reporter.setLocale(locale);
			reporter.setProperty(Constants.ERROR_HANDLER, xmlError);
			sp.setProperty(Constants.ERROR_REPORTER, reporter);
			InputSource source = new InputSource(xml);
			
			//sp.parse(new InputSource(new ByteArrayInputStream(stripAddenda( xml ).getBytes("UTF8"))), dError);
			//TODO verificar los namespaces
			sp.parse(source, dError);
			
			listaErrores = xmlError.getList();

		} catch (SAXException se) {
			logger.error("Ocurrio una excepcion Al parsear el archivo ");
			logger.error("El mensaje de error es  " + se.getMessage());
			if(se.getMessage().equals(Constants.ERROR_PARSING_ILEGAL_CONTENT_IN_PROLOG)){
				logger.info("El error corresponde a un Contenido Ilegal -> Se intentara parsear el archivo nuevamente...");
				try {
					//return parseBOM(xml, type, lang);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else{
				se.printStackTrace();
				logger.error(se.toString());
				Error error = new Error();
				error.setClave(se.getMessage());
				error.setDescripcion(se.getLocalizedMessage());
				listaErrores.add(error);
			}
			
		} catch (Exception e) {
			logger.error(e.toString());
			Error error = new Error();
			error.setClave(e.getMessage());
			error.setDescripcion(e.getLocalizedMessage());
			listaErrores.add(error);
		}
		logger.debug("Terminando validacion de schema ...");
		return listaErrores;
	}


	
//	public List validateSchema(File xmlFile, int type,String lang) throws BusinessException {
//		logger.debug("validateSchema...");
//		try {
//			// Selecciona el idioma;
//			Locale locale = new Locale(lang);
//			XMLHandlerError xmlError = new XMLHandlerError();
//			xmlError.setType(type);
//			DefaultHandlerError dError = new DefaultHandlerError();
//			String fileName = getXSD(type);
//			
//			SAXParserFactory spf = SAXParserFactory.newInstance();			
//			spf.setNamespaceAware(true);
//			spf.setXIncludeAware(true);
//			spf.setValidating(true);			
//			SAXParser sp = spf.newSAXParser();
//
//			sp.setProperty(Constants.JAXP_SCHEMA_LANGUAGE,Constants.W3C_XML_SCHEMA);
//			// Trae el tipo de XSD
//			logger.debug(fileName);
//			if( type == Constants.SATV3 ){
//				sp.setProperty(Constants.JAXP_SCHEMA_SOURCE, new String[]{fileName, getXSD(Constants.TIMBRE_FISCAL_DIGITAL)});
//			} else if(type == Constants.SATV2) {
//				sp.setProperty(Constants.JAXP_SCHEMA_SOURCE, new String[]{fileName, getXSD(Constants.COMPLEMENTO_TERCERO_AUTORIZADO)});
//			} else {
//				sp.setProperty(Constants.JAXP_SCHEMA_SOURCE, fileName);
//			}
//			reporter.putMessageFormatter(Constants.ERROR_REPORTER, new XMLFormatOutput());
//			reporter.putMessageFormatter(Constants.SCHEMA_DOMAIN, new XMLFormatOutput());
//			reporter.setLocale(locale);
//			reporter.setProperty(Constants.ERROR_HANDLER, xmlError);
//			sp.setProperty(Constants.ERROR_REPORTER, reporter);
//			Reader reader = BOMUtil.getReader(xmlFile, "UTF8");
//			
//			//InputSource source = new InputSource(reader);
//			
//			//sp.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))))
//			sp.parse(new InputSource(new ByteArrayInputStream(stripAddenda( Base64Binary.readertoString(reader)).getBytes("utf-8"))), dError);
//			//sp.parse(source, dError);
//			listaErrores = xmlError.getList();
//
//		} catch (SAXException se) {
//			se.printStackTrace();
//			logger.error(se.toString());
//			Error error = new Error();
//			error.setClave(se.getMessage());
//			error.setDescripcion(se.getLocalizedMessage());
//			listaErrores.add(error);
//		} catch (Exception e) {
//			logger.error(e.toString());
//			Error error = new Error();
//			error.setClave(e.getMessage());
//			error.setDescripcion(e.getLocalizedMessage());
//			listaErrores.add(error);
//		}
//		logger.debug("Schema validation finished...");
//		return listaErrores;
//	}


//	public static String stripAddenda(String xmlFile) {
//		ParametrosConfigAppService parametrosConfigAppService = BeanFactory
//				.getParametrosConfigAppService();
//		ParametrosConfig parametro = null;
//		
//		String sourceXML = xmlFile;
//		try {
//			//logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@");
//			//logger.debug(sourceXML);
//			//logger.debug("Antes");
//			logger.debug("Verifica si hay Addenda");
//			DocumentBuilderFactory factory = DocumentBuilderFactory
//					.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xmlFile.getBytes("utf-8"))));
//			TransformerFactory tFactory = TransformerFactory.newInstance();
//			Transformer tFormer = tFactory.newTransformer();
//			Element element  = null;	            
//            element = (Element) doc.getElementsByTagName("Addenda").item(0);            
//            if (element == null ){
//                element = (Element) doc.getElementsByTagName("cfdi:Addenda").item(0);
//            }
//
//			if (element != null) {
//				logger.debug("Existe el nodo Addenda");
//				//logger.debug("Omitiendo del parser ...");
//				// Remove the node
//				element.getParentNode().removeChild(element);
//				// Normalize the DOM tree to combine all adjacent nodes
//				doc.normalize();
//				Source source = new DOMSource(doc);
//				parametro = parametrosConfigAppService.getByFk(Constants.SAT_FILES_DIRECTORY);
//				String tmpFile = parametro.getVvalue() + String.valueOf(System.currentTimeMillis())
//						+ ".xml";
//				logger.debug("Creando temporal xml " + tmpFile);
//
//				Result dest = new StreamResult(new File(tmpFile));
//				;
//				tFormer.transform(source, dest);
//				// Guardo en byte el archivo
//				byte[] xmlTransformadoSinAddenda = Base64Binary
//						.getBytesFromFile(new File(tmpFile));
//				String xmlString = new String(xmlTransformadoSinAddenda, "UTF8");
//				// Pasa a un StringReader
//				//StringReader strReader = new StringReader(xmlString);
//				sourceXML = xmlString;
//				logger.debug("Eliminando temporal xml : " + tmpFile);
//				boolean dropped = new File(tmpFile).delete();
//				logger.debug("Fue eliminado : " + dropped);
//				logger.debug("Terminado omitir Addenda ...");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		//logger.debug(sourceXML);
//		//logger.debug("Despues");
//
//		return sourceXML;
//
//	}


}
