/**
 * 
 */
package com.ex.mater.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ex.mater.mater.FileCommand;
import com.ex.mater.mater.MaterService;

/**
 * Excel Controller
 * @author sol
 *
 */
@Controller
@RequestMapping("/materExcel")
public class ExcelController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MaterService materService;
    
    //엑셀 다운로드 
  	@GetMapping("/downloadExcel.xlsx")
  	public String downloadExcel(Model model
  								, @RequestParam String sMonth
  								, @RequestParam String eMonth
  								, @RequestParam String mtriCustNo
  								, @RequestParam String sWkscNm
  								, @RequestParam String checkM
  								, @RequestParam String checkE
  								, @RequestParam String custAthrCd
  								, @RequestParam String blngDptcd
  								, @RequestParam String wkscCd ) throws Exception {
  		List<FileCommand> newList = new ArrayList<FileCommand>();
  		Map<String,Object> paramMap = new HashMap<String, Object>();
  		paramMap.put("sMonth", sMonth);
  		paramMap.put("eMonth", eMonth);
  		paramMap.put("mtriCustNo", mtriCustNo);
  		paramMap.put("sWkscNm", sWkscNm);
  		paramMap.put("checkM", checkM);
  		paramMap.put("checkE", checkE);
  		paramMap.put("custAthrCd", custAthrCd);
  		paramMap.put("blngDptcd", blngDptcd);
  		paramMap.put("wkscCd", wkscCd);
  		
  		System.out.println("paramMap : " + paramMap);
  		newList = materService.selectMaterList(paramMap);
  		
  		System.out.println("newList : " + newList);
  		model.addAttribute("dataList", newList);
  		return "excelMaterXlsx";
  	}
}
