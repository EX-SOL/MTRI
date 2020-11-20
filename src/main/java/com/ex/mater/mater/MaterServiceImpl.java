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

	@Override
	public Integer updateMaterList(FileCommand fileData) throws Exception {
		// 기존 데이터가 비어있는 경우
		if( fileData.getEtcFileName().length() < 1 || fileData.getEtcFileName() == "" || fileData.getEtcFileName() == null ) {
			if( fileData.getAttflNm().length() > 1 || fileData.getAttflNm() != "" || fileData.getAttflNm() != null ) {
				// 처음 데이터가 들어오기떄문에 생성
				materMapper.insertMaterAttfl(fileData);
			}
		} else {
			if( fileData.getAttflNm().length() < 1 || fileData.getAttflNm() == "" || fileData.getAttflNm() == null ) {
			}else {
				// 기존에 데이터가 있고 새로운 데이터도 있음
				if(!fileData.getAttflNm().equals(fileData.getEtcFileName())) {
					// 기존 파일과 새로운 파일이 다를경우 업데이트
					materMapper.updateMaterAttfl(fileData);
				}
			}
		}
		return materMapper.updateMaterList(fileData);
	}

	@Override
	public Integer deleteMater(Map<String, Object> param) throws Exception {
		return materMapper.deleteMater(param);
	}
	
}
