package com.ex.mater.user.model;

public class User {
	
	String mtriCustNo;	//사용자아이디
	String custPswd;	//사용자비밀번호
	String custAthrCd;	//사용자권한코드
	String custTelno;	//사용자전화번호
	String rpprNm;		//대표자명
	String deprNm;		//예금주
	String bankActno;	//은행계좌번호
	String trBankNm;	//은행명
	String corpNm;		//법인명
	
	public String getMtriCustNo() {
		return mtriCustNo;
	}
	public void setMtriCustNo(String mtriCustNo) {
		this.mtriCustNo = mtriCustNo;
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
	public String getBankActno() {
		return bankActno;
	}
	public void setBankActno(String bankActno) {
		this.bankActno = bankActno;
	}
	public String getTrBankNm() {
		return trBankNm;
	}
	public void setTrBankNm(String trBankNm) {
		this.trBankNm = trBankNm;
	}
	public String getCorpNm() {
		return corpNm;
	}
	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}
	
	
}
