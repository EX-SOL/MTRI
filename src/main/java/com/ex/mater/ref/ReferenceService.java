package com.ex.mater.ref;

import java.util.List;
import java.util.Map;

public interface ReferenceService {
	
	public List<Reference> selectReferenceList(Map<String, Object> param) throws Exception; 

	public Integer insertReference(Reference ref) throws Exception;
	
	public Reference selectReferenceDetail(String rfrmrBlbnSeq) throws Exception;
	
	public Integer updateReference(Reference ref) throws Exception;

	public Integer deleteReference(Map<String, Object> param) throws Exception;
}
