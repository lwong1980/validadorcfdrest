package org.common.services.xml;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.xerces.util.MessageFormatter;

public class XMLFormatOutput implements MessageFormatter {

	public static final String SCHEMA_DOMAIN = "http://www.w3.org/TR/xml-schema-1";
    private Locale fLocale = null;
    private ResourceBundle fResourceBundle = null;

     public String formatMessage(Locale locale, String key, Object[] arguments)
        throws MissingResourceException {
        
        if (fResourceBundle == null || locale != fLocale) {
            if (locale != null) {
                fResourceBundle = ResourceBundle.getBundle("org/common/resources/XMLSchemaMessages", locale);
                // memorize the most-recent locale
                fLocale = locale;
            }
            if (fResourceBundle == null)
                fResourceBundle = ResourceBundle.getBundle("org/common/resources/XMLSchemaMessages");
        }
        
        String msg = fResourceBundle.getString(key);
        
        if (arguments != null) {
            try {
                msg = java.text.MessageFormat.format(msg, arguments);
            } catch (Exception e) {
                msg = fResourceBundle.getString("FormatFailed");
                msg += " " + fResourceBundle.getString(key);
            }
        } 

        if (msg == null) {
            msg = fResourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(msg, "org.common.services.xml.resources.XMLSchemaMessages", key);
        }

        return msg;
    }
}