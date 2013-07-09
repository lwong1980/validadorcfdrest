package org.common.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.common.dao.FoliosCFDDAO;
import org.common.dao.dto.Csd;
import org.common.dao.dto.FoliosCFD;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.CollectionUtils;

public class FoliosCFDDAOHibernateImpl extends HibernateDaoSupport implements
		FoliosCFDDAO {
	private static final Logger logger = Logger.getLogger(FoliosCFDDAOHibernateImpl.class);
	
	public Collection<FoliosCFD> getFoliosCFD() {
		return getHibernateTemplate().loadAll(FoliosCFD.class);
	}
	
	public FoliosCFD getFoliosCFDbyRfc(FoliosCFD cfd ) {
		FoliosCFD foliosCFD = null;
		try {
			logger.debug("Entrando a consulta de folio y serie ...");
			DetachedCriteria criteria = DetachedCriteria.forClass(FoliosCFD.class);
			criteria.add(Restrictions.eq("rfc",cfd.getRfc() ));
			criteria.add(Restrictions.eq("noAprobacion",cfd.getNoAprobacion() ));
			criteria.add(Restrictions.eq("anoAprobacion",cfd.getAnoAprobacion() ));
			logger.debug(criteria.toString());
			List<FoliosCFD> result = getHibernateTemplate().findByCriteria(criteria);
			if(!CollectionUtils.isEmpty(result) ){
				if( result.size()> 1){
					logger.debug("Hay mas de 1 resultado en la consulta. "+cfd.getRfc());
					//throw new PersistenceException("Hay mas de 1 resultado en la consulta. "+cfd.getRfc());
				}else{
					foliosCFD = result.get(0);
					logger.debug(foliosCFD.toString());
				}

			}
			logger.debug("Terminando consulta de folio y serie ...");

		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		
		return foliosCFD;
	}
	
	
	public Csd getCsdbyNoSerie(Csd csd ) {
		Csd csdResult = null;
		try {
			logger.debug("Entrando a consulta de no.certificado ...");

			DetachedCriteria criteria = DetachedCriteria.forClass(Csd.class);
			criteria.add(Restrictions.eq("noSerie",csd.getNoSerie() ));
			List<Csd> result = getHibernateTemplate().findByCriteria(criteria);
			if(!CollectionUtils.isEmpty(result) ){
				if( result.size()> 1){
					logger.debug("Hay mas de 1 resultado en la consulta. "+csd.getNoSerie());
					//throw new PersistenceException("Hay mas de 1 resultado en la consulta. "+csd.getNoSerie());
				}else{
					csdResult = result.get(0);
					logger.debug(csdResult.toString());
				}

			}
			logger.debug("Entrando a consulta de no.certificado ...");

		} catch (Exception e) {
			logger.fatal(e.getMessage(),e);
			// TODO: handle exception
		}
		
		return csdResult;
	}
	
	

	public FoliosCFD saveFoliosCFD(FoliosCFD foliosCFD) {
		getHibernateTemplate().saveOrUpdate(foliosCFD);
		return foliosCFD;
	}
	
	

}
