package com.ex.mater.user.model;

import org.springframework.web.multipart.MultipartFile;

public class User {
	
	String mtriCustNo;	//사용자아이디
	String custNm;		//사용자명
	String custPswd;	//사용자비밀번호
	String custAthrCd;	//사용자권한코드
	String custAthrNm;	//사용자권한명
	String custTelno;	//사용자전화번호
	String corpNm;		//법인명
	String rpprNm;		//대표자명
	String rprsCrno;	//사업자등록번호
	String mnpbRgsrSeq; //장비등록번호
	String deprNm;		//예금주
	String trBankNm;	//은행명
	String bankActno;	//은행계좌번호
	String mtriEqpmClssNm;	//장비명
	String acitLockYN;
	String fsttmRgsrId;
	String fsttmRgstDttm;
	String lsttmModfrId;
	String lsttmAltrDttm;
	String etcRmrk;
	String blngDptcd;		//소속부서코드
	String wkscCd;			//공구코드
	
	private MultipartFile flUpFileData;		//사업자등록증
	private MultipartFile flUpFileData2;	//장비등록증
	private MultipartFile flUpFileData3;	//계약서
	
	String rprsCrnoAttflNm;  		//사업자등록증 이름
	String rprsCrnoAttflPath;  		//사업자등록증 경로
	String mnpbRgsrSeqAttflNm;  	//장비등록번호 이름
	String mnpbRgsrSeqAttflPath;  	//장비등록번호 경로
	String contractAttflNm;  		//계약서 이름
	String contractAttflPath;  		//계약서 경로
	
	String attflNm;  				//첨부파일 이름
	String attflPath;  				//첨부파일 경로
	String attflSeq;  				//첨부파일 일련번호
	
	String rprsCrnoAttflSeq;		//사업자등록증첨부파일일련번호
	String mnpbRgsrSeqAttflSeq;		//장비등록번호첨부파일일련번호
	
	String check1;				//회원가입동의1
	String check2;				//회원가입동의2
	String check3;				//회원가입동의3
	String check4;				//회원가입동의4
	
	public String getMtriCustNo() {
		return mtriCustNo;
	}
	public void setMtriCustNo(String mtriCustNo) {
		this.mtriCustNo = mtriCustNo;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getCustPswd() {
		return custPswd;
	}
	public void setCustPswd(String custPswd) {
		this.custPswd = custPswd;
	}
	public String getCustAthrCd() {
		return custAthrCd;
	}
	public void setCustAthrCd(String custAthrCd) {
		this.custAthrCd = custAthrCd;
	}
	public String getCustAthrNm() {
		return custAthrNm;
	}
	public void setCustAthrNm(String custAthrNm) {
		this.custAthrNm = custAthrNm;
	}
	public String getCustTelno() {
		return custTelno;
	}
	public void setCustTelno(String custTelno) {
		this.custTelno = custTelno;
	}
	public String getCorpNm() {
		return corpNm;
	}
	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}
	public String getRpprNm() {
		return rpprNm;
	}
	public void setRpprNm(String rpprNm) {
		this.rpprNm = rpprNm;
	}
	public String getRprsCrno() {
		return rprsCrno;
	}
	public void setRprsCrno(String rprsCrno) {
		this.rprsCrno = rprsCrno;
	}
	public String getMnpbRgsrSeq() {
		return mnpbRgsrSeq;
	}
	public void setMnpbRgsrSeq(String mnpbRgsrSeq) {
		this.mnpbRgsrSeq = mnpbRgsrSeq;
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
	public String getMtriEqpmClssNm() {
		return mtriEqpmClssNm;
	}
	public void setMtriEqpmClssNm(String mtriEqpmClssNm) {
		this.mtriEqpmClssNm = mtriEqpmClssNm;
	}
	public String getAcitLockYN() {
		return acitLockYN;
	}
	public void setAcitLockYN(String acitLockYN) {
		this.acitLockYN = acitLockYN;
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
	public String getEtcRmrk() {
		return etcRmrk;
	}
	public void setEtcRmrk(String etcRmrk) {
		this.etcRmrk = etcRmrk;
	}
	public String getBlngDptcd() {
		return blngDptcd;
	}
	public void setBlngDptcd(String blngDptcd) {
		this.blngDptcd = blngDptcd;
	}
	public String getWkscCd() {
		return wkscCd;
	}
	public void setWkscCd(String wkscCd) {
		this.wkscCd = wkscCd;
	}
	public MultipartFile getFlUpFileData() {
		return flUpFileData;
	}
	public void setFlUpFileData(MultipartFile flUpFileData) {
		this.flUpFileData = flUpFileData;
	}
	public MultipartFile getFlUpFileData2() {
		return flUpFileData2;
	}
	public void setFlUpFileData2(MultipartFile flUpFileData2) {
		this.flUpFileData2 = flUpFileData2;
	}
	public MultipartFile getFlUpFileData3() {
		return flUpFileData3;
	}
	public void setFlUpFileData3(MultipartFile flUpFileData3) {
		this.flUpFileData3 = flUpFileData3;
	}
	public String getRprsCrnoAttflNm() {
		return rprsCrnoAttflNm;
	}
	public void setRprsCrnoAttflNm(String rprsCrnoAttflNm) {
		this.rprsCrnoAttflNm = rprsCrnoAttflNm;
	}
	public String getRprsCrnoAttflPath() {
		return rprsCrnoAttflPath;
	}
	public void setRprsCrnoAttflPath(String rprsCrnoAttflPath) {
		this.rprsCrnoAttflPath = rprsCrnoAttflPath;
	}
	public String getMnpbRgsrSeqAttflNm() {
		return mnpbRgsrSeqAttflNm;
	}
	public void setMnpbRgsrSeqAttflNm(String mnpbRgsrSeqAttflNm) {
		this.mnpbRgsrSeqAttflNm = mnpbRgsrSeqAttflNm;
	}
	public String getMnpbRgsrSeqAttflPath() {
		return mnpbRgsrSeqAttflPath;
	}
	public void setMnpbRgsrSeqAttflPath(String mnpbRgsrSeqAttflPath) {
		this.mnpbRgsrSeqAttflPath = mnpbRgsrSeqAttflPath;
	}
	public String getContractAttflNm() {
		return contractAttflNm;
	}
	public void setContractAttflNm(String contractAttflNm) {
		this.contractAttflNm = contractAttflNm;
	}
	public String getContractAttflPath() {
		return contractAttflPath;
	}
	public void setContractAttflPath(String contractAttflPath) {
		this.contractAttflPath = contractAttflPath;
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
	public String getAttflSeq() {
		return attflSeq;
	}
	public void setAttflSeq(String attflSeq) {
		this.attflSeq = attflSeq;
	}
	public String getRprsCrnoAttflSeq() {
		return rprsCrnoAttflSeq;
	}
	public void setRprsCrnoAttflSeq(String rprsCrnoAttflSeq) {
		this.rprsCrnoAttflSeq = rprsCrnoAttflSeq;
	}
	public String getMnpbRgsrSeqAttflSeq() {
		return mnpbRgsrSeqAttflSeq;
	}
	public void setMnpbRgsrSeqAttflSeq(String mnpbRgsrSeqAttflSeq) {
		this.mnpbRgsrSeqAttflSeq = mnpbRgsrSeqAttflSeq;
	}
	public String getCheck1() {
		return check1;
	}
	public void setCheck1(String check1) {
		this.check1 = check1;
	}
	public String getCheck2() {
		return check2;
	}
	public void setCheck2(String check2) {
		this.check2 = check2;
	}
	public String getCheck3() {
		return check3;
	}
	public void setCheck3(String check3) {
		this.check3 = check3;
	}
	public String getCheck4() {
		return check4;
	}
	public void setCheck4(String check4) {
		this.check4 = check4;
	}
	
	
}
