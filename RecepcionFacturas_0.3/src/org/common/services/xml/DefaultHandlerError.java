package org.common.services.xml;

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DefaultHandlerError  extends DefaultHandler {
	public static final Logger logger = Logger.getLogger(DefaultHandlerError.class);

	// Receive notification of a recoverable error.
	public void error(SAXParseException se) {
		setValidity(se, "INVALID Recoverable Error:");
	}

	// Receive notification of a non-recoverable error.
	public void fatalError(SAXParseException se) {
		setValidity(se, "INVALID Fatal Error:");
	}

	// Receive notification of a warning.
	public void warning(SAXParseException se) {
		setValidity(se, "Warning !");
	}

	private void setValidity(SAXParseException se, String errortype) {
		String saxerror = se.getMessage();
		logger.debug("DefaultHandler... " + saxerror);
	}

	
}
