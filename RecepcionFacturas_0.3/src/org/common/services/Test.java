package org.common.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.common.dao.FoliosCFDDAO;
import org.common.dao.dto.Csd;
import org.common.dao.dto.FoliosCFD;
import org.common.services.xml.Error;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"launch-context.xml"});
			BeanFactory factory = context;
			//ExampleFTP ftp = (ExampleFTP) factory.getBean("ftpService");
			FoliosCFDDAO cfddao = (FoliosCFDDAO) factory.getBean("foliosDAO");
			FoliosCFD cfd = new FoliosCFD();
			cfd.setRfc("CCP880202KQ0");
			cfd.setAnoAprobacion(new Integer("2005"));
			cfd.setNoAprobacion(new Integer("8"));
			FoliosCFD res = cfddao.getFoliosCFDbyRfc(cfd);
			if(res == null){
				System.out.println("No hay nada");
			}
			
			Csd csdIn = new Csd();
			csdIn.setNoSerie("00001000000000068278");
			Csd csd = cfddao.getCsdbyNoSerie(csdIn);
			if(csd == null){
				System.out.println("No hay nada");
			}
			
			ValidateSchemaAppService xmlService = (ValidateSchemaAppService) factory.getBean("validadorXML");
			 File xmlFi = new File("C:/Users/lwong/Desktop/CFDI_11_FIN051111SL7.XML");
			 InputStream xml = new FileInputStream(xmlFi);
		 	// System.out.println(new String(org.common.services.util.Base64Binary.getBytesFromFile(xmlFi) ));
			List<Error> lista = xmlService.validateSchema(xml, "es");
			for (Iterator<Error> it=lista.iterator(); it.hasNext(); ) {
			    Error element =  it.next();
			    System.out.println(element.getDescripcion());
			}
			
//			Collection<FoliosCFD> listo = cfddao.getFoliosCFD();
//			for (Iterator it=listo.iterator(); it.hasNext(); ) {
//			    FoliosCFD element = (FoliosCFD) it.next();
//			    System.out.println(element.getRfc());
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
