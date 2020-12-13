package com.ex.mater.notice;

import org.springframework.web.multipart.MultipartFile;

public class Notice {
	private MultipartFile flUpFileData;
	
    String notcMtriSeq;
    String notcMtriTitlNm;
    String notcMtriCtnt;
    String bltnStrtDates;
    String bltnEndDates;
    String notcMtriImptYN;
    String attflSeq;
    String notcMtriBltnAthrCd;
    String fsttmRgsrId;
    String fsttmRgstDttm;
    String lsttmModfrId;
    String lsttmAltrDttm;
    String notcMtriDelYN;
    String attflNm;
    String attflPath;
    String mtriCustNo;
    String etcFileName;

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
	public MultipartFile getFlUpFileData() {
		return flUpFileData;
	}
	public void setFlUpFileData(MultipartFile flUpFileData) {
		this.flUpFileData = flUpFileData;
	}
	public String getNotcMtriSeq() {
		return notcMtriSeq;
	}
	public void setNotcMtriSeq(String notcMtriSeq) {
		this.notcMtriSeq = notcMtriSeq;
	}
	public String getNotcMtriTitlNm() {
		return notcMtriTitlNm;
	}
	public void setNotcMtriTitlNm(String notcMtriTitlNm) {
		this.notcMtriTitlNm = notcMtriTitlNm;
	}
	public String getNotcMtriCtnt() {
		return notcMtriCtnt;
	}
	public void setNotcMtriCtnt(String notcMtriCtnt) {
		this.notcMtriCtnt = notcMtriCtnt;
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
	public String getNotcMtriImptYN() {
		return notcMtriImptYN;
	}
	public void setNotcMtriImptYN(String notcMtriImptYN) {
		this.notcMtriImptYN = notcMtriImptYN;
	}
	public String getAttflSeq() {
		return attflSeq;
	}
	public void setAttflSeq(String attflSeq) {
		this.attflSeq = attflSeq;
	}
	public String getNotcMtriBltnAthrCd() {
		return notcMtriBltnAthrCd;
	}
	public void setNotcMtriBltnAthrCd(String notcMtriBltnAthrCd) {
		this.notcMtriBltnAthrCd = notcMtriBltnAthrCd;
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
	public String getLsttmRgstId() {
		return lsttmModfrId;
	}
	public void setLsttmRgstId(String lsttmRgstId) {
		this.lsttmModfrId = lsttmRgstId;
	}
	public String getLsttmRgstDttm() {
		return lsttmAltrDttm;
	}
	public void setLsttmRgstDttm(String lsttmRgstDttm) {
		this.lsttmAltrDttm = lsttmRgstDttm;
	}
	public String getNotcMtriDelYN() {
		return notcMtriDelYN;
	}
	public void setNotcMtriDelYN(String notcMtriDelYN) {
		this.notcMtriDelYN = notcMtriDelYN;
	}
	public String getMtriCustNo() {
		return mtriCustNo;
	}
	public void setMtriCustNo(String mtriCustNo) {
		this.mtriCustNo = mtriCustNo;
	}
	public String getEtcFileName() {
		return etcFileName;
	}
	public void setEtcFileName(String etcFileName) {
		this.etcFileName = etcFileName;
	}
	
	
	@Override
    public String toString() {
        return "Mypage [notcMtriSeq=" + notcMtriSeq + ", notcMtriTitlNm=" + notcMtriTitlNm  + ", notcMtriCtnt=" + notcMtriCtnt  
        		+ ", bltnStrtDates=" +bltnStrtDates
        		+ ", bltnEndDates=" +bltnEndDates
        		+ ", notcMtriImptYN=" +notcMtriImptYN
        		+ ", attflSeq=" + attflSeq
        		+ ", notcMtriBltnAthrCd=" + notcMtriBltnAthrCd  
        		+ ", fsttmRgsrId=" + fsttmRgsrId  
        		+ ", fsttmRgstDttm=" + fsttmRgstDttm
        		+ ", lsttmModfrId=" + lsttmModfrId
        		+ ", lsttmAltrDttm=" + lsttmAltrDttm
        		+ ", notcMtriDelYN=" + notcMtriDelYN  
        		+"]";
    }
}
