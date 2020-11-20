package com.ex.mater.mater;

import org.springframework.web.multipart.MultipartFile;

public class FileCommand {
	private MultipartFile flUpFileData;
	
    String mnpbAskYYMM;		//청구년월
    String mtriCustNo;		//사용자ID(사업자번호)
    String mnpbAskSqno;		//대금청구순번
    String mnpbClssCd;		//대금구분코드
    String fildClssCd;		//현장구분코드
    String cntcWkscCd;		//건설공구코드
    String cntrtCrprNm;		//계약업체명
    String cntrtCrprCd;		//계약업체코드
    String cntrtCntcNo;		//대금계약번호
    String cntrtNm;			//대금명
    String cntrtCd;			//대금코드
    String dlgdAmnt;		//수량
    String dlgdUnpr;		//단가
    String askAmt;			//청구금액
    String attflSeq;		//첨부파일일련번호
    String mnpbStatCd;		//대금상태코드
    String ppsTrnmYn;		//전송여부
    String fsttmRgsrId;		//최초등록자ID
    String lsttmModfrId;	//최종수정자ID
    String etcRmrk;			//기타비고
    
    // 추가
	String corpNm;			//법인명
	String custTelno;		//사용자전화번호
	String rpprNm;			//대표자명
	String deprNm;			//예금주
	String trBankNm;		//거래은행명
	String bankActno;		//은행계좌번호
	String attflNm;  		//첨부파일 이름
	String attflPath;  		//첨부파일 경로
	String custNm;			//사용자명
	int total;				//총카운트
	String mnpbRgsrSeq;		//장비등록번호
	
	
	public MultipartFile getFlUpFileData() {
		return flUpFileData;
	}
	public void setFlUpFileData(MultipartFile flUpFileData) {
		this.flUpFileData = flUpFileData;
	}
	public String getMnpbAskYYMM() {
		return mnpbAskYYMM;
	}
	public void setMnpbAskYYMM(String mnpbAskYYMM) {
		this.mnpbAskYYMM = mnpbAskYYMM;
	}
	public String getMtriCustNo() {
		return mtriCustNo;
	}
	public void setMtriCustNo(String mtriCustNo) {
		this.mtriCustNo = mtriCustNo;
	}
	public String getMnpbAskSqno() {
		return mnpbAskSqno;
	}
	public void setMnpbAskSqno(String mnpbAskSqno) {
		this.mnpbAskSqno = mnpbAskSqno;
	}
	public String getMnpbClssCd() {
		return mnpbClssCd;
	}
	public void setMnpbClssCd(String mnpbClssCd) {
		this.mnpbClssCd = mnpbClssCd;
	}
	public String getFildClssCd() {
		return fildClssCd;
	}
	public void setFildClssCd(String fildClssCd) {
		this.fildClssCd = fildClssCd;
	}
	public String getCntcWkscCd() {
		return cntcWkscCd;
	}
	public void setCntcWkscCd(String cntcWkscCd) {
		this.cntcWkscCd = cntcWkscCd;
	}
	public String getCntrtCrprNm() {
		return cntrtCrprNm;
	}
	public void setCntrtCrprNm(String cntrtCrprNm) {
		this.cntrtCrprNm = cntrtCrprNm;
	}
	public String getCntrtCrprCd() {
		return cntrtCrprCd;
	}
	public void setCntrtCrprCd(String cntrtCrprCd) {
		this.cntrtCrprCd = cntrtCrprCd;
	}
	public String getCntrtCntcNo() {
		return cntrtCntcNo;
	}
	public void setCntrtCntcNo(String cntrtCntcNo) {
		this.cntrtCntcNo = cntrtCntcNo;
	}
	public String getCntrtNm() {
		return cntrtNm;
	}
	public void setCntrtNm(String cntrtNm) {
		this.cntrtNm = cntrtNm;
	}
	public String getCntrtCd() {
		return cntrtCd;
	}
	public void setCntrtCd(String cntrtCd) {
		this.cntrtCd = cntrtCd;
	}
	public String getDlgdAmnt() {
		return dlgdAmnt;
	}
	public void setDlgdAmnt(String dlgdAmnt) {
		this.dlgdAmnt = dlgdAmnt;
	}
	public String getDlgdUnpr() {
		return dlgdUnpr;
	}
	public void setDlgdUnpr(String dlgdUnpr) {
		this.dlgdUnpr = dlgdUnpr;
	}
	public String getAskAmt() {
		return askAmt;
	}
	public void setAskAmt(String askAmt) {
		this.askAmt = askAmt;
	}
	public String getAttflSeq() {
		return attflSeq;
	}
	public void setAttflSeq(String attflSeq) {
		this.attflSeq = attflSeq;
	}
	public String getMnpbStatCd() {
		return mnpbStatCd;
	}
	public void setMnpbStatCd(String mnpbStatCd) {
		this.mnpbStatCd = mnpbStatCd;
	}
	public String getPpsTrnmYn() {
		return ppsTrnmYn;
	}
	public void setPpsTrnmYn(String ppsTrnmYn) {
		this.ppsTrnmYn = ppsTrnmYn;
	}
	public String getFsttmRgsrId() {
		return fsttmRgsrId;
	}
	public void setFsttmRgsrId(String fsttmRgsrId) {
		this.fsttmRgsrId = fsttmRgsrId;
	}
	public String getLsttmModfrId() {
		return lsttmModfrId;
	}
	public void setLsttmModfrId(String lsttmModfrId) {
		this.lsttmModfrId = lsttmModfrId;
	}
	public String getEtcRmrk() {
		return etcRmrk;
	}
	public void setEtcRmrk(String etcRmrk) {
		this.etcRmrk = etcRmrk;
	}
	public String getCorpNm() {
		return corpNm;
	}
	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}
	public String getCustTelno() {
		return custTelno;
	}
	public void setCustTelno(String custTelno) {
		this.custTelno = custTelno;
	}
	public String getRpprNm() {
		return rpprNm;
	}
	public void setRpprNm(String rpprNm) {
		this.rpprNm = rpprNm;
	}
	public String getDeprNm() {
		return deprNm;
	}
	public void setDeprNm(String deprNm) {
		this.deprNm = deprNm;
	}
	public String getTrBankNm() {
		return trBankNm;
	}
	public void setTrBankNm(String trBankNm) {
		this.trBankNm = trBankNm;
	}
	public String getBankActno() {
		return bankActno;
	}
	public void setBankActno(String bankActno) {
		this.bankActno = bankActno;
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
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getMnpbRgsrSeq() {
		return mnpbRgsrSeq;
	}
	public void setMnpbRgsrSeq(String mnpbRgsrSeq) {
		this.mnpbRgsrSeq = mnpbRgsrSeq;
	}
	
}
