package org.common.util;

import org.apache.log4j.Logger;
import org.common.services.ValidaCFDUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class BeanFactory {
	private static Logger logger = Logger.getLogger(BeanFactory.class);
	protected static ApplicationContext applicationContext;

	public static ApplicationContext getCtx() {
		if (applicationContext == null) {
			long initializationTime = System.currentTimeMillis();
			logger.debug("applicationContextFile = classpath:org/common/spring/beans/launch-context.xml");
			 applicationContext = new ClassPathXmlApplicationContext("classpath:org/common/spring/beans/launch-context.xml");
			logger.debug("initializationTime = "+ (System.currentTimeMillis() - initializationTime));
		}

		return applicationContext;
	}

	public static Object getBean(String beanId) {
		Object o = getCtx().getBean(beanId);
		return o;
	}

	public static ValidaCFDUtil getValidaCFDUtilService() {
		return (ValidaCFDUtil) getBean("validacionesCFD");
	}


//	public static CatEstatusDAO getCatEstatusDAO() {
//		return (CatEstatusDAO) getBean("CatEstatusDAO");
//	}

	
}
