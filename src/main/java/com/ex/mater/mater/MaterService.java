package com.ex.mater.mater;

import java.util.List;
import java.util.Map;

public interface MaterService {
	
	public Integer insertMaterList(FileCommand fileData) throws Exception;
	
	public List<FileCommand> selectMaterList(Map<String, Object> param) throws Exception; 

	public List<FileCommand> selectMainList (Map<String, Object> param) throws Exception;
	
	public FileCommand selectMaterDetail(Map<String, Object> param) throws Exception;

	public Integer updateMaterList(FileCommand fileData) throws Exception;

	public Integer deleteMater(Map<String, Object> param) throws Exception;
	
	public List<Map<String, Object>> selectFildData() throws Exception;
	public List<Map<String, Object>> selectWkscData(Map<String, Object> param) throws Exception;

	public List<Map<String, Object>> selectCntrtCrprList(Map<String, Object> param) throws Exception;

	public List<FileCommand> selectAdminList (Map<String, Object> param) throws Exception;
	
	public Integer createAdmin(Map<String, Object> param) throws Exception;
}
