package com.ex.mater.machinery;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ex.mater.mater.FileCommand;


@Service("MachineryService")
public class MachineryServiceImpl implements MachineryService {

	@Resource
    private MachineryMapper machineryMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Integer insertMachineryList(FileCommand fileData) throws Exception {
		if( fileData.getAttflNm().length() < 1 || fileData.getAttflNm() == "" || fileData.getAttflNm() == null ) {
			fileData.setAttflSeq("");
		} else {
			machineryMapper.insertMachineryAttfl(fileData);
		}
		
		return machineryMapper.insertMachineryList(fileData);
	}


	
}
