package com.ex.mater.mater;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("MaterService")
public class MaterServiceImpl implements MaterService {

	@Resource
    private MaterMapper materrMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Integer insertMaterList(FileCommand fileData) throws Exception {
		if( fileData.getAttflNm() != "" || fileData.getAttflNm() != null ) {
			materrMapper.insertMaterAttfl(fileData);
			System.out.println("==============================");
			System.out.println("attflSeq : " + fileData.getAttflSeq());
		}
		return materrMapper.insertMaterList(fileData);
	}

	
}
