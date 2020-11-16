package com.ex.mater.mypage;

public class Mypage {

	String mtriCustNo;
	String custAthrNm;
	String custAthrCd;
	String custNm;
	String prsnCorpClssCd;
	String rpprNm;
	String fildClssCd;
	String cntcWkscCd;
	String acntBankCd;
	String trBankNm;
	String deprNm;
	String bankActno;
	String attflSeq;
	String custEmailAddr;
	String custTelno;
	String custPswd;
	String acitLockYN;
	String fsttmRgsrId;
	String fsttmRgstDttm;
	String lsttmModfrId;
	String lsttmAltrDttm;
	String etcRmrk;

	public String getCustAthrNm() {
		return custAthrNm;
	}

	public void setCustAthrNm(String custAthrNm) {
		this.custAthrNm = custAthrNm;
	}

	public String getMtriCustNo() {
		return mtriCustNo;
	}

	public void setMtriCustNo(String mtriCustNo) {
		this.mtriCustNo = mtriCustNo;
	}

	public String getCustAthrCd() {
		return custAthrCd;
	}

	public void setCustAthrCd(String custAthrCd) {
		this.custAthrCd = custAthrCd;
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getPrsnCorpClssCd() {
		return prsnCorpClssCd;
	}

	public void setPrsnCorpClssCd(String prsnCorpClssCd) {
		this.prsnCorpClssCd = prsnCorpClssCd;
	}

	public String getRpprNm() {
		return rpprNm;
	}

	public void setRpprNm(String rpprNm) {
		this.rpprNm = rpprNm;
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

	public String getAcntBankCd() {
		return acntBankCd;
	}

	public void setAcntBankCd(String acntBankCd) {
		this.acntBankCd = acntBankCd;
	}

	public String getTrBankNm() {
		return trBankNm;
	}

	public void setTrBankNm(String trBankNm) {
		this.trBankNm = trBankNm;
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

	public String getAttflSeq() {
		return attflSeq;
	}

	public void setAttflSeq(String attflSeq) {
		this.attflSeq = attflSeq;
	}

	public String getCustEmailAddr() {
		return custEmailAddr;
	}

	public void setCustEmailAddr(String custEmailAddr) {
		this.custEmailAddr = custEmailAddr;
	}

	public String getCustTelno() {
		return custTelno;
	}

	public void setCustTelno(String custTelno) {
		this.custTelno = custTelno;
	}

	public String getCustPswd() {
		return custPswd;
	}

	public void setCustPswd(String custPswd) {
		this.custPswd = custPswd;
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

	@Override
    public String toString() {
        return "Mypage [custAthrCd=" + custAthrCd + ", custNm=" + custNm  + ", prsnCorpClssCd=" + prsnCorpClssCd  
        		+ ", rpprNm=" +rpprNm
        		+ ", fildClssCd=" +fildClssCd
        		+ ", cntcWkscCd=" +cntcWkscCd
        		+ ", acntBankCd=" + acntBankCd
        		+ ", trBankNm=" + trBankNm  
        		+ ", deprNm=" + deprNm  
        		+ ", bankActno=" + bankActno  
        		+ ", attflSeq=" + attflSeq  
        		+ ", custEmailAddr=" + custEmailAddr  
        		+ ", custTelno=" + custTelno  
        		+ ", custPswd=" + custPswd  
        		+ ", acitLockYN=" + acitLockYN
        		+ ", fsttmRgsrId=" + fsttmRgsrId
        		+ ", fsttmRgstDttm=" + fsttmRgstDttm
        		+ ", lsttmModfrId=" + lsttmModfrId
        		+ ", lsttmAltrDttm=" + lsttmAltrDttm
        		+ ", etcRmrk=" + etcRmrk  
        		+"]";
    }
}
