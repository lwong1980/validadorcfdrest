
package org.common.services.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.2
 * Wed Oct 05 00:11:09 CDT 2011
 * Generated source version: 2.4.2
 */

@XmlRootElement(name = "validaXML", namespace = "http://ws.services.common.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validaXML", namespace = "http://ws.services.common.org/", propOrder = {"arg0", "arg1"})

public class ValidaXML {

    @XmlElement(name = "arg0")
    private byte[] arg0;
    @XmlElement(name = "arg1")
    private java.lang.Integer arg1;

    public byte[] getArg0() {
        return this.arg0;
    }

    public void setArg0(byte[] newArg0)  {
        this.arg0 = newArg0;
    }

    public java.lang.Integer getArg1() {
        return this.arg1;
    }

    public void setArg1(java.lang.Integer newArg1)  {
        this.arg1 = newArg1;
    }

}

