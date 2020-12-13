package com.ex.mater.excel;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.ex.mater.mater.FileCommand;

@Component("excelMaterXlsx")
public class ExcelMaterXlsxView extends AbstractXlsxView {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<FileCommand> list = (List<FileCommand>) model.get("dataList");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		String fileName  = new String("자재관리목록_".getBytes("utf-8"),"8859_1")+dateformat.format(System.currentTimeMillis())+".xlsx";
        
	 	response.setHeader("Content-Disposition", "attachment; filename=" + fileName + " ;");
	 	response.setHeader("Content-Description", "JSP Generated Data");
	 	response.setContentType("application/vnd.ms-excel;charset=euc-kr");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		OutputStream fileOut = null;
		
		XSSFWorkbook objWorkBook = new XSSFWorkbook();  //워크북 생성
		XSSFSheet objSheet = objWorkBook.createSheet("sheet1");  //워크시트 생성
		XSSFRow row = null;  //로우 생성
		XSSFCell objCell = null;  //셀 생성
//		int rowLocation = 0;  //행 추적을 위한 변수 
		objSheet.setDefaultRowHeight((short)500);  //행 높이 600
		objSheet.setColumnWidth((short)0,(short)4000);
		objSheet.setColumnWidth((short)1,(short)4000);
		objSheet.setColumnWidth((short)2,(short)4000);
		objSheet.setColumnWidth((short)3,(short)3000);
		objSheet.setColumnWidth((short)4,(short)4000);
		objSheet.setColumnWidth((short)5,(short)3000);
		objSheet.setColumnWidth((short)6,(short)3000);
		objSheet.setColumnWidth((short)7,(short)3000);
		objSheet.setColumnWidth((short)8,(short)3000);
		objSheet.setColumnWidth((short)9,(short)4000);
		objSheet.setColumnWidth((short)10,(short)5000);
		objSheet.setColumnWidth((short)11,(short)4000);
		
		objSheet.addMergedRegion(new CellRangeAddress(0,0,0,11));
		row = objSheet.createRow(0);		
		objCell = row.createCell((short)0);
		objCell.setCellValue("자재장비 청구 목록");
		objCell.setCellStyle(cellStyle(objWorkBook, "title"));
		
	    row = objSheet.createRow(2);		
		objCell = row.createCell((short)0);
		objCell.setCellValue("자재장비업체명");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)1);
		objCell.setCellValue("사업자번호");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)2);
		objCell.setCellValue("연락처");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)3);
		objCell.setCellValue("대표자명");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));		

		objCell = row.createCell((short)4);
		objCell.setCellValue("자재장비구분");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)5);
		objCell.setCellValue("건설기계구분");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)6);
		objCell.setCellValue("임차구매품목");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)7);
		objCell.setCellValue("예금주");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)8);
		objCell.setCellValue("은행명");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)9);
		objCell.setCellValue("계좌번호");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)10);
		objCell.setCellValue("청구금액");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		objCell = row.createCell((short)11);
		objCell.setCellValue("타인계좌사유");
		objCell.setCellStyle(cellStyle(objWorkBook, "head"));
		
		for(int i=0; i<list.size(); i++) {
			int rownum = i+3;
			row = objSheet.createRow(rownum);
			objCell = row.createCell((short)0);
			objCell.setCellValue(String.valueOf(list.get(i).getCorpNm()));
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)1);
			objCell.setCellValue(String.valueOf(list.get(i).getRprsCrno()));
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));

			objCell = row.createCell((short)2);
			objCell.setCellValue(String.valueOf(list.get(i).getCustTelno()));
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)3);
			objCell.setCellValue(String.valueOf(list.get(i).getRpprNm()));
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)4);
			if( String.valueOf(list.get(i).getMnpbClssCd()) == "M" ) {
				objCell.setCellValue("자재");
			} else {
				objCell.setCellValue("장비");
			}
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));

			objCell = row.createCell((short)5);
			objCell.setCellValue("");
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)6);
			objCell.setCellValue("");
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)7);
			objCell.setCellValue(String.valueOf(list.get(i).getDeprNm()));
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)8);
			objCell.setCellValue(String.valueOf(list.get(i).getTrBankNm()));
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)9);
			objCell.setCellValue(String.valueOf(list.get(i).getBankActno()));
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
			
			objCell = row.createCell((short)10);
			objCell.setCellValue(String.valueOf(list.get(i).getAskAmt()));
			objCell.setCellStyle(cellStyle(objWorkBook, "amt"));
			
			objCell = row.createCell((short)11);
			objCell.setCellValue("");
			objCell.setCellStyle(cellStyle(objWorkBook, "data"));
		}
		
		fileOut = response.getOutputStream();
		objWorkBook.write(fileOut);
		fileOut.close();
	}
	
    
    //셀 스타일 설정하는 함수
  	public static CellStyle cellStyle(XSSFWorkbook xlsWb, String kind) {
  		CellStyle cellStyle = xlsWb.createCellStyle();
  		
  		Font font = xlsWb.createFont();
  		font.setFontName("맑은고딕");
		font.setFontHeightInPoints((short)13);
  		
		cellStyle.setFont(font);
  		cellStyle.setAlignment(HorizontalAlignment.CENTER); //가운데 정렬
  		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //중앙 정렬
  		
  		if(kind.equals("title")) {
  			font.setUnderline(Font.U_SINGLE);
  			font.setFontHeightInPoints((short)15);
  			font.setBold(true);
  			cellStyle.setBorderTop(BorderStyle.THIN);
	  		cellStyle.setBorderLeft(BorderStyle.THIN);
	  		cellStyle.setBorderRight(BorderStyle.THIN);
  		}else if(kind.equals("head")) {
  			font.setBold(true);
  			cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); //회색 25%
  			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); //색상 패턴처리
  			cellStyle.setBorderTop(BorderStyle.THIN);
  			cellStyle.setBorderBottom(BorderStyle.THIN);
  			cellStyle.setBorderLeft(BorderStyle.THIN);
  			cellStyle.setBorderRight(BorderStyle.THIN);
  		}else if(kind.equals("data")) {
  			cellStyle.setBorderTop(BorderStyle.THIN);
  			cellStyle.setBorderBottom(BorderStyle.THIN);
  			cellStyle.setBorderLeft(BorderStyle.THIN);
  			cellStyle.setBorderRight(BorderStyle.THIN);
  		}else if(kind.equals("amt")) {
  			cellStyle.setBorderTop(BorderStyle.THIN);
  			cellStyle.setBorderBottom(BorderStyle.THIN);
  			cellStyle.setBorderLeft(BorderStyle.THIN);
  			cellStyle.setBorderRight(BorderStyle.THIN);
  			cellStyle.setAlignment(HorizontalAlignment.RIGHT); //우측정렬
  		}
  		return cellStyle;
  	} 

}
