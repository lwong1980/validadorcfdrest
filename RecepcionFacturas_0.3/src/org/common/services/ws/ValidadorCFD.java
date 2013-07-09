package org.common.services.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "ValidadorCFD", targetNamespace = "http://ws.services.common.org/")
public interface ValidadorCFD {
	@WebMethod(operationName = "validaXML", action = "urn:ValidaXML")
	List<org.common.services.xml.Error> validaXML(@WebParam(name = "arg0") byte [] xml,@WebParam(name = "arg1") Integer correlationID);
	
}
