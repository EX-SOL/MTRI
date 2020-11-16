package com.ex.mater.mater;

import java.util.List;
import java.util.Map;

public interface MaterService {
	
	public Integer insertMaterList(FileCommand fileData) throws Exception;
	
	public List<FileCommand> selectMaterList(Map<String, Object> param) throws Exception; 
}
