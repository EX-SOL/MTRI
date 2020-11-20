package com.ex.mater.ref;

import org.springframework.web.multipart.MultipartFile;

public class Reference {
	private MultipartFile flUpFileData;
	String rfrmrBlbnSeq;
	String rfrmrBlbnTitlNm;
	String rfrmrBlbnCtnt;
	String bltnStrtDates;
	String bltnEndDates;
	String rfrmrBlbnImptYn;
	String attflSeq;
	String rfrmrBlbnBltnAthrCd;
	String fsttmRgsrId;
	String fsttmRgstDttm;
	String lsttmModfrId;
	String lsttmAltrDttm;
	String rfrmrBlbnDelYn;
	
	String attflNm;  		//첨부파일 이름
	String attflPath;  		//첨부파일 경로	
	String etcFileName;		//파일명 비교시 사용
	String mtriCustNo;		//사용자ID(사업자번호)
	
	
	public MultipartFile getFlUpFileData() {
		return flUpFileData;
	}
	public void setFlUpFileData(MultipartFile flUpFileData) {
		this.flUpFileData = flUpFileData;
	}
	public String getRfrmrBlbnSeq() {
		return rfrmrBlbnSeq;
	}
	public void setRfrmrBlbnSeq(String rfrmrBlbnSeq) {
		this.rfrmrBlbnSeq = rfrmrBlbnSeq;
	}
	public String getRfrmrBlbnTitlNm() {
		return rfrmrBlbnTitlNm;
	}
	public void setRfrmrBlbnTitlNm(String rfrmrBlbnTitlNm) {
		this.rfrmrBlbnTitlNm = rfrmrBlbnTitlNm;
	}
	public String getRfrmrBlbnCtnt() {
		return rfrmrBlbnCtnt;
	}
	public void setRfrmrBlbnCtnt(String rfrmrBlbnCtnt) {
		this.rfrmrBlbnCtnt = rfrmrBlbnCtnt;
	}
	public String getBltnStrtDates() {
		return bltnStrtDates;
	}
	public void setBltnStrtDates(String bltnStrtDates) {
		this.bltnStrtDates = bltnStrtDates;
	}
	public String getBltnEndDates() {
		return bltnEndDates;
	}
	public void setBltnEndDates(String bltnEndDates) {
		this.bltnEndDates = bltnEndDates;
	}
	public String getRfrmrBlbnImptYn() {
		return rfrmrBlbnImptYn;
	}
	public void setRfrmrBlbnImptYn(String rfrmrBlbnImptYn) {
		this.rfrmrBlbnImptYn = rfrmrBlbnImptYn;
	}
	public String getAttflSeq() {
		return attflSeq;
	}
	public void setAttflSeq(String attflSeq) {
		this.attflSeq = attflSeq;
	}
	public String getRfrmrBlbnBltnAthrCd() {
		return rfrmrBlbnBltnAthrCd;
	}
	public void setRfrmrBlbnBltnAthrCd(String rfrmrBlbnBltnAthrCd) {
		this.rfrmrBlbnBltnAthrCd = rfrmrBlbnBltnAthrCd;
	}
	public String getFsttmRgsrId() {
		return fsttmRgsrId;
	}
	public void setFsttmRgsrId(String fsttmRgsrId) {
		this.fsttmRgsrId = fsttmRgsrId;
	}
	public String getFsttmRgstDttm() {
		return fsttmRgstDttm;
	}
	public void setFsttmRgstDttm(String fsttmRgstDttm) {
		this.fsttmRgstDttm = fsttmRgstDttm;
	}
	public String getLsttmModfrId() {
		return lsttmModfrId;
	}
	public void setLsttmModfrId(String lsttmModfrId) {
		this.lsttmModfrId = lsttmModfrId;
	}
	public String getLsttmAltrDttm() {
		return lsttmAltrDttm;
	}
	public void setLsttmAltrDttm(String lsttmAltrDttm) {
		this.lsttmAltrDttm = lsttmAltrDttm;
	}
	public String getRfrmrBlbnDelYn() {
		return rfrmrBlbnDelYn;
	}
	public void setRfrmrBlbnDelYn(String rfrmrBlbnDelYn) {
		this.rfrmrBlbnDelYn = rfrmrBlbnDelYn;
	}
	public String getAttflNm() {
		return attflNm;
	}
	public void setAttflNm(String attflNm) {
		this.attflNm = attflNm;
	}
	public String getAttflPath() {
		return attflPath;
	}
	public void setAttflPath(String attflPath) {
		this.attflPath = attflPath;
	}
	public String getEtcFileName() {
		return etcFileName;
	}
	public void setEtcFileName(String etcFileName) {
		this.etcFileName = etcFileName;
	}
	public String getMtriCustNo() {
		return mtriCustNo;
	}
	public void setMtriCustNo(String mtriCustNo) {
		this.mtriCustNo = mtriCustNo;
	}
	
}
