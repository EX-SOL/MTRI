package com.ex.mater.mater;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterMapper {
	
	public Integer insertMaterList(FileCommand fileData);
	public Integer insertMaterAttfl(FileCommand fileData);
	
}
