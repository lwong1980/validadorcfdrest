package org.common.dao;

import java.util.Collection;

import org.common.dao.dto.Csd;
import org.common.dao.dto.FoliosCFD;

public interface FoliosCFDDAO {

	 Collection<FoliosCFD> getFoliosCFD();
	 FoliosCFD saveFoliosCFD(FoliosCFD foliosCFD);
	 FoliosCFD getFoliosCFDbyRfc(FoliosCFD cfd ) ;
	 Csd getCsdbyNoSerie(Csd csd );
	 
	
}
