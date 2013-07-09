package org.common.services;

import java.io.InputStream;
import java.util.List;

public interface ValidaCFDUtil {
	public List<org.common.services.xml.Error> validaCFD(InputStream xml, String correlationID);

}
