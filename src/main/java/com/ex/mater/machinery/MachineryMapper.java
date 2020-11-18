package com.ex.mater.machinery;

import org.apache.ibatis.annotations.Mapper;

import com.ex.mater.mater.FileCommand;

@Mapper
public interface MachineryMapper {
	
	
	public Integer insertMachineryAttfl(FileCommand fileData);
	public Integer insertMachineryList(FileCommand fileData);
}
