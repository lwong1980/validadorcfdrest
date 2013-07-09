package org.common.services.ws.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.common.services.ValidaCFDUtil;
import org.common.services.ws.ValidadorCFD;
import org.common.util.BeanFactory;

@WebService(targetNamespace = "http://ws.services.common.org/", endpointInterface = "org.common.services.ws.ValidadorCFD", portName = "ValidadorCFDImplPort", serviceName = "ValidadorCFDImplService")
public class ValidadorCFDImpl implements ValidadorCFD {
	private static final Logger logger = Logger.getLogger(ValidadorCFDImpl.class);
	private ValidaCFDUtil validacionesCFD;

	public List<org.common.services.xml.Error> validaXML(byte [] xml, Integer correlationID){
		List<org.common.services.xml.Error>  lista = new ArrayList<org.common.services.xml.Error>();
		
		try {	
			logger.debug("Entrando al WS Validador ...");
			InputStream xmlIn = new ByteArrayInputStream(xml);
	
			ValidaCFDUtil validaCFDUtil =  BeanFactory.getValidaCFDUtilService();
			lista = validaCFDUtil.validaCFD(xmlIn, new String("1"));
		}catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		return lista ;

	}

	public ValidaCFDUtil getValidacionesCFD() {
		return validacionesCFD;
	}

	public void setValidacionesCFD(ValidaCFDUtil validacionesCFD) {
		this.validacionesCFD = validacionesCFD;
	}


}
