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
}
