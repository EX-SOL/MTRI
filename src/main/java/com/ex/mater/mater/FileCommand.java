package com.ex.mater.mater;

import org.springframework.web.multipart.MultipartFile;

public class FileCommand {
	private MultipartFile flUpFileData;
	
	private String txtItem1;
    private String txtItem2;

    String mrtiMnpbAskYYMM;
	String fildClssCd;
	String cntcWkscCd;
	String cntrtCrprNm;
	String mtriCrprNm;
	String mtriNm;
	String custTelno;
	String mtriCustNo;
	String rpprNm;
	String deprNm;
	String trBankNm;
	String bankActno;
	String askAmt;
	String attflNm;  //첨부파일 이름
	String attflPath;  //첨부파일 경로
	String attflSeq;
	String mtriCd;
	String mtriMnpbAskSqno;
	String cntrtCrprCd;
	String cntrtNo;
	String dlgdAmnt;
	String dlgdUnpr;
	String rgstStat;
	String ppsTrnmYn;
	String fsttmRgsrId;
	String lsttmModfrId;
	String etcRmrk;
	String custNm;
	int total;
	
    public MultipartFile getFlUpFileData() {
        return flUpFileData;
    }

    public void setFlUpFileData(MultipartFile flUpFileData) {
        this.flUpFileData = flUpFileData;
    }

    public String getTxtItem1() {
        return txtItem1;
    }

    public void setTxtItem1(String txtItem1) {
        this.txtItem1 = txtItem1;
    }

    public String getTxtItem2() {
        return txtItem2;
    }

    public void setTxtItem2(String txtItem2) {
        this.txtItem2 = txtItem2;
    }

    public String getMrtiMnpbAskYYMM() {
		return mrtiMnpbAskYYMM;
	}

	public void setMrtiMnpbAskYYMM(String mrtiMnpbAskYYMM) {
		this.mrtiMnpbAskYYMM = mrtiMnpbAskYYMM;
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

	public String getMtriCrprNm() {
		return mtriCrprNm;
	}

	public void setMtriCrprNm(String mtriCrprNm) {
		this.mtriCrprNm = mtriCrprNm;
	}

	public String getMtriNm() {
		return mtriNm;
	}

	public void setMtriNm(String mtriNm) {
		this.mtriNm = mtriNm;
	}

	public String getCustTelno() {
		return custTelno;
	}

	public void setCustTelno(String custTelno) {
		this.custTelno = custTelno;
	}

	public String getMtriCustNo() {
		return mtriCustNo;
	}

	public void setMtriCustNo(String mtriCustNo) {
		this.mtriCustNo = mtriCustNo;
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

	public String getAskAmt() {
		return askAmt;
	}

	public void setAskAmt(String askAmt) {
		this.askAmt = askAmt;
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
	
	public String getMtriCd() {
		return mtriCd;
	}

	public void setMtriCd(String mtriCd) {
		this.mtriCd = mtriCd;
	}

	public String getMtriMnpbAskSqno() {
		return mtriMnpbAskSqno;
	}

	public void setMtriMnpbAskSqno(String mtriMnpbAskSqno) {
		this.mtriMnpbAskSqno = mtriMnpbAskSqno;
	}

	public String getCntrtCrprCd() {
		return cntrtCrprCd;
	}

	public void setCntrtCrprCd(String cntrtCrprCd) {
		this.cntrtCrprCd = cntrtCrprCd;
	}

	public String getCntrtNo() {
		return cntrtNo;
	}

	public void setCntrtNo(String cntrtNo) {
		this.cntrtNo = cntrtNo;
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

	public String getRgstStat() {
		return rgstStat;
	}

	public void setRgstStat(String rgstStat) {
		this.rgstStat = rgstStat;
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

	@Override
    public String toString() {
        return "FileCommand [flUpFileData=" + flUpFileData + ", txtItem1=" + txtItem1  + ", txtItem2=" + txtItem2  
        		+ ", mrtiMnpbAskYYMM=" +mrtiMnpbAskYYMM
        		+ ", fildClssCd=" +fildClssCd
        		+ ", cntcWkscCd=" +cntcWkscCd
        		+ ", cntrtCrprNm=" + cntrtCrprNm
        		+ ", mtriCrprNm=" + mtriCrprNm  
        		+ ", mtriNm=" + mtriNm  
        		+ ", custTelno=" + custTelno  
        		+ ", mtriCustNo=" + mtriCustNo  
        		+ ", rpprNm=" + rpprNm  
        		+ ", deprNm=" + deprNm  
        		+ ", trBankNm=" + trBankNm  
        		+ ", bankActno=" + bankActno  
        		+ ", askAmt=" + askAmt  
        		+"]";
    }
}
