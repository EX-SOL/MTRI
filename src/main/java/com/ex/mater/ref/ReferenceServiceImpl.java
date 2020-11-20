package com.ex.mater.ref;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("ReferenceService")
public class ReferenceServiceImpl implements ReferenceService {

	@Resource
    private ReferenceMapper referenceMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Reference> selectReferenceList(Map<String, Object> param) throws Exception {
		return referenceMapper.selectReferenceList(param);
	}

	@Override
	public Integer insertReference(Reference ref) throws Exception {
		if( ref.getAttflNm().length() < 1 || ref.getAttflNm() == "" || ref.getAttflNm() == null ) {
			ref.setAttflSeq("");
		} else {
			referenceMapper.insertReferenceAttfl(ref);
		}
		return referenceMapper.insertReference(ref);
	}

	@Override
	public Reference selectReferenceDetail(String rfrmrBlbnSeq) throws Exception {
		return referenceMapper.selectReferenceDetail(rfrmrBlbnSeq);
	}

	@Override
	public Integer updateReference(Reference ref) throws Exception {
		// 기존 데이터가 비어있는 경우
		if( ref.getEtcFileName().length() < 1 || ref.getEtcFileName() == "" || ref.getEtcFileName() == null ) {
			if( ref.getAttflNm().length() > 1 || ref.getAttflNm() != "" || ref.getAttflNm() != null ) {
				// 처음 데이터가 들어오기떄문에 생성
				referenceMapper.insertReferenceAttfl(ref);
			}
		} else {
			if( ref.getAttflNm().length() < 1 || ref.getAttflNm() == "" || ref.getAttflNm() == null ) {
			}else {
				// 기존에 데이터가 있고 새로운 데이터도 있음
				if(!ref.getAttflNm().equals(ref.getEtcFileName())) {
					// 기존 파일과 새로운 파일이 다를경우 업데이트
					referenceMapper.updateReferenceAttfl(ref);
				}
			}
		}
		return referenceMapper.updateReference(ref);
	}

	@Override
	public Integer deleteReference(Map<String, Object> param) throws Exception {
		return referenceMapper.deleteReference(param);
	}
}
