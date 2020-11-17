package com.ex.mater.mater;

import org.springframework.web.multipart.MultipartFile;

public class FileCommand {
	private MultipartFile flUpFileData;
	
	private String txtItem1;
    private String txtItem2;

    String mrtiMnpbAskYYMM;		//자재대금청구년월
	String fildClssCd;			//현장구분코드
	String cntcWkscCd;			//건설공구코드
	String cntrtCrprNm;			//계약업체명
	String mtriCrprNm;			//자재업체명
	String mtriNm;				//자재명
	String custTelno;			//사용자전화번호
	String mtriCustNo;			//사용자id(사업자번호)
	String rpprNm;				//대표자명
	String deprNm;				//예금주
	String trBankNm;			//거래은행명
	String bankActno;			//은행계좌번호
	String askAmt;				//금액
	String attflNm;  			//첨부파일 이름
	String attflPath;  			//첨부파일 경로
	String attflSeq;			//첨부파일 일련번호
	String mtriCd;				//자재코드
	String mtriMnpbAskSqno;		//자재대금청구순번
	String cntrtCrprCd;			//계약업체코드
	String cntrtNo;				//계약번호
	String dlgdAmnt;			//수량
	String dlgdUnpr;			//단가
	String rgstStat;			//상태
	String ppsTrnmYn;			//전송여부
	String fsttmRgsrId;			//등록자
	String lsttmModfrId;		//수정자
	String etcRmrk;				//기타비고
	String custNm;				//사용자명/법인명
	int total;					//총카운트
	
	
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
