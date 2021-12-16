package com.example.settingweb_boot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.settingweb_boot.dao.StatisticMapper;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private StatisticMapper uMapper;
	
	@Override
	public HashMap<String, Object> yearloginNum (String year) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		
		try {
			retVal = uMapper.selectYearLogin(year);
			retVal.put("year", year);
			retVal.put("is_success", true);
		} catch(Exception e) {
			retVal.put("totCnt", -999);
			retVal.put("year", year);
			retVal.put("is_success", false);
		}
		
		return retVal;
	}
	
	//월별
	@Override
	public HashMap<String, Object> monthloginNum (String month) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		
		try {
			retVal = uMapper.selectMonthLogin(month);
			retVal.put("month", month);
			retVal.put("is_success", true);
		} catch(Exception e) {
			retVal.put("totCnt", -999);
			retVal.put("month", month);
			retVal.put("is_success", false);
		}
		
		return retVal;
	}
	
	//일별
	@Override
	public HashMap<String, Object> dayloginNum (String day) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		
		try {
			retVal = uMapper.selectDayLogin(day);
			retVal.put("day", day);
			retVal.put("is_success", true);
		} catch(Exception e) {
			retVal.put("totCnt", -999);
			retVal.put("day", day);
			retVal.put("is_success", false);
		}
		
		return retVal;
	}
	
	//평균하루
	@Override
	public List<HashMap<String, Object>> averageDayloginNum() {
		List<HashMap<String, Object>> retVal;
		
		try {
			retVal = uMapper.selectAverageDayLogin();
			System.out.println("retVal : " + retVal);
		} catch(Exception e) {
			return null;
		}
		
		return retVal;
	}
	
	//휴일제외
	@Override
	public HashMap<String, Object> exceptHolidayloginNum(String year, String month) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		
		try {
			
			return retVal;
			
		} catch(Exception e) {
			return null;
		}
	}
	
	
	//부서별
	@Override
	public HashMap<String, Object> organizationMonthloginNum (String organ, String month) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		
		try {
			retVal = uMapper.selectOrganizationMonthLogin(organ, month);
			retVal.put("organ", organ);
			retVal.put("month", month);
			retVal.put("is_success", true);
		} catch(Exception e) {
			retVal.put("totCnt", -999);
			retVal.put("organization", null);
			retVal.put("month", null);
			retVal.put("is_success", false);
		}
		
		return retVal;
	}
}
