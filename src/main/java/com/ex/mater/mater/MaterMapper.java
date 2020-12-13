package com.ex.mater.mater;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterMapper {
	
	public Integer insertMaterList(FileCommand fileData);
	public Integer insertMaterAttfl(FileCommand fileData);
	
	public List<FileCommand> selectMaterList(Map<String, Object> param);

	public List<FileCommand> selectMainList(Map<String, Object> param);

	public FileCommand selectMaterDetail(Map<String, Object> param);

	public Integer updateMaterList(FileCommand fileData);
	public Integer updateMaterAttfl(FileCommand fileData);
	
	public Integer deleteMater(Map<String, Object> param);
	
	public List<Map<String, Object>> selectFildData();
	public List<Map<String, Object>> selectWkscData(Map<String, Object> param);

	public List<Map<String, Object>> selectCntrtCrprList(Map<String, Object> param);
	
	public List<FileCommand> selectAdminList (Map<String, Object> param);
	
	public Integer createAdmin(Map<String, Object> param);
}
