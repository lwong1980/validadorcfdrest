package org.common.services.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLParseException;
import org.common.util.Constants;

public class XMLHandlerError implements XMLErrorHandler {
	private static final Logger logger = Logger.getLogger(XMLHandlerError.class);
	private List<Error> list = new ArrayList<Error>();
	
	public List<Error> setValidity(XMLParseException exception, String errortype) {		
		try {
			
			if(!exception.getMessage().startsWith(Constants.ADDENDA_COMODIN_COINCIDENCIA_ESTRICTO)){
				logger.debug("Registering Exception ... " + exception.getMessage());
				Error errDT = new Error();
				errDT.setDescripcion(exception.getMessage());				
				errDT.setClave("TODO");
				getList().add(errDT);
				logger.debug(exception.getPublicId() +" "+exception.getLocalizedMessage());
			}			
		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			e.printStackTrace();
		}
		
		return getList();
	}
	@Override
	public void error(String arg0, String arg1, XMLParseException se) throws XNIException {
		setValidity(se, "INVALID Recoverable Error:");

	}
	@Override
	public void fatalError(String arg0, String arg1, XMLParseException se) throws XNIException {
		setValidity(se, "INVALID Fatal Error:");

	}
	@Override
	public void warning(String arg0, String arg1, XMLParseException se) throws XNIException {
		setValidity(se, "Warning !");

	}

	public void setList(List<Error> list) {
		this.list = list;
	}

	public List<Error> getList() {
		return this.list;
	}



}
