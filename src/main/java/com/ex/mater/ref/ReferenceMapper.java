package com.ex.mater.ref;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReferenceMapper {
	
	public List<Reference> selectReferenceList(Map<String, Object> param);
	
	public Integer insertReference(Reference ref);
	public Integer insertReferenceAttfl(Reference ref);
	
	public Reference selectReferenceDetail(String rfrmrBlbnSeq);
	
	public Integer updateReference(Reference ref);
	public Integer updateReferenceAttfl(Reference ref);
	
	public Integer deleteReference(Map<String, Object> param);
}
