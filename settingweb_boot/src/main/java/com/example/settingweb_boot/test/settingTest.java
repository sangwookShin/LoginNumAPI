package com.example.settingweb_boot.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.settingweb_boot.service.StatisticService;

@Controller
public class settingTest {

	@Autowired
	private StatisticService service;
	
	@ResponseBody
	@RequestMapping("/sqlyearStatistic")
	public Map<String, Object> sqlyeartest(String year) throws Exception {
		
		return service.yearloginNum(year);
	}
	
	@ResponseBody
	@RequestMapping("/sqlmonthStatistic")
	public Map<String, Object> sqlmonthtest(String month) throws Exception {
		
		return service.monthloginNum(month);
	}
	
	@ResponseBody
	@RequestMapping("/sqldayStatistic")
	public Map<String, Object> sqldaytest(String day) throws Exception {
		
		return service.dayloginNum(day);
	}
	
	
	@ResponseBody
	@RequestMapping("/sqlaveragedayStatistic")
	public List<HashMap<String, Object>> sqlaveragedaytest() throws Exception {
		
		return service.averageDayloginNum();
	}
	
	@ResponseBody
	@RequestMapping("/sqlExceptHolidayStatistic")
	public Map<String, Object> sqlExceptHolidaytest(String year, String month) throws Exception {
		
		return service.exceptHolidayloginNum(year, month);
	}
	
	@ResponseBody
	@RequestMapping("/sqlOrganizationMonthStatistic")
	public Map<String, Object> sqlOrganizationMonthtest(String organ, String month) throws Exception {
		
		return service.organizationMonthloginNum(organ, month);
	}
	
	
	@RequestMapping("/test")
	public ModelAndView test() throws Exception {
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("name", "devfunpj");
		List<String> resultList = new ArrayList<String>();
		resultList.add("!!!HELLO WORLD!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!!!"); 
        resultList.add("설정 TEST!!!!!!"); 
        mav.addObject("list", resultList); 
        return mav; 
	}
}
