package org.common.services;

import java.io.InputStream;
import java.util.List;

import org.common.services.xml.Error;

public interface ValidateSchemaAppService {
	public List<Error> validateSchema(InputStream xml, String lang) throws Exception ;
	public List<Error> validateSchema(InputStream xml, String lang,String version) throws Exception ;
	
}
