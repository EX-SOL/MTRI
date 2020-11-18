package com.ex.mater.mater;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("MaterService")
public class MaterServiceImpl implements MaterService {

	@Resource
    private MaterMapper materMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Integer insertMaterList(FileCommand fileData) throws Exception {
		if( fileData.getAttflNm().length() < 1 || fileData.getAttflNm() == "" || fileData.getAttflNm() == null ) {
			fileData.setAttflSeq("");
		} else {
			materMapper.insertMaterAttfl(fileData);
		}
		return materMapper.insertMaterList(fileData);
	}

	@Override
	public List<FileCommand> selectMaterList(Map<String, Object> param) throws Exception {
		return materMapper.selectMaterList(param);
	}

	@Override
	public List<FileCommand> selectMainList(Map<String, Object> param) throws Exception {
		return materMapper.selectMainList(param);
	}

	@Override
	public FileCommand selectMaterDetail(Map<String, Object> param) throws Exception {
		return materMapper.selectMaterDetail(param);
	}

	
}
