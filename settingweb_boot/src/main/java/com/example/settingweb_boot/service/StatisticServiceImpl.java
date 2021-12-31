package com.example.settingweb_boot.service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.settingweb_boot.dao.StatisticMapper;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;;

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
		month = addZero(month);
		
		try {
			retVal = uMapper.selectMonthLogin(month);
			retVal.put("month", (month.charAt(0) == '0' ? month.charAt(1) : month)); //1월~9월 사이면 앞에 0빼고 출력
			retVal.put("is_success", true);
		} catch(Exception e) {
			retVal.put("totCnt", -999);
			retVal.put("month", (month.charAt(0) == '0' ? month.charAt(1) : month));
			retVal.put("is_success", false);
		}
		
		return retVal;
	}
	
	//일별
	@Override
	public HashMap<String, Object> dayloginNum (String day) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		day=addZero(day); //1일~9일 사이면 앞에 0을 붙임
 		
		try {
			retVal = uMapper.selectDayLogin(day);
			retVal.put("day", (day.charAt(0) == '0' ? day.charAt(1) : day));
			retVal.put("is_success", true);
		} catch(Exception e) {
			retVal.put("totCnt", -999);
			retVal.put("day", (day.charAt(0) == '0' ? day.charAt(1) : day));
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
			e.printStackTrace();
			return null;
		}
		
		return retVal;
	}
	
	//휴일제외
	@Override
	public HashMap<String, Object> exceptHolidayloginNum(String year, String month) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		month = addZero(month);
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		try {
			
			String serviceKey = URLDecoder.decode("FbAECBbbJcpLJ4pzfUlN7K%2Bs6PF572QfIQkERi5Qbbu6ZEQC27pc453J8AIKRp0V7ejQuxMPNWJp8cG6MXaWSg%3D%3D", "UTF-8");
			// REST API 호출
	        String result = restTemplate.getForObject(String.format(
	    			"http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?solYear=%s&solMonth=%s&ServiceKey=%s&_type=json"
	    			, "20"+year, month, serviceKey), String.class);
	        
	        System.out.println("------------------ TEST 결과 ------------------");
	        System.out.println(result);
	        
	        JSONParser parser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) parser.parse(result);
	        JSONObject jsonObject2 = (JSONObject) jsonObject.get("response");
	        JSONObject body = (JSONObject) jsonObject2.get("body");
	        System.out.println(body);
	        int num = Integer.parseInt(body.get("totalCount").toString());
	        JSONObject items = null;
	        if(num!=0)  items = (JSONObject) body.get("items");
	        
	        ArrayList<String> list = new ArrayList<>();
	        
	        if(num==1) {
	        	JSONObject item = (JSONObject) items.get("item");
	        	list.add(item.get("locdate").toString().substring(2)); // 공휴일 년, 월 더하기
	        }
	        else if(num!=0){
	        	JSONArray itemList = (JSONArray) items.get("item");
		        
		       	for(int i=0; i<itemList.size(); i++) {
		       		JSONObject tmp = (JSONObject)itemList.get(i);
		       		list.add(tmp.get("locdate").toString().substring(2)); // 공휴일 년, 월 더하기
		       	}
	        }
	       
	       	for(int i=0; i<list.size(); i++) {
	       		System.out.println(list.get(i));
	       	}
	       	try {
	            retVal = uMapper.selectExceptHolidayLogin(year, month, list);
	            retVal.put("year", year);
	            retVal.put("month", (month.charAt(0) == '0' ? month.charAt(1) : month)); // 1월~9월 사이면 앞에 0 빼고 출력
	            retVal.put("is_success", true);
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	            retVal.put("totCnt", -999);
	            retVal.put("year", year);            
	            retVal.put("month", (month.charAt(0) == '0' ? month.charAt(1) : month));
	            retVal.put("is_success", false);
	        }
	            
	        return retVal;
			
		} catch(Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	
	//부서별
	@Override
	public HashMap<String, Object> organizationMonthloginNum (String organ, String month) {
		
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		month = addZero(month);
		
		try {
			retVal = uMapper.selectOrganizationMonthLogin(organ, month);
			retVal.put("organization", organ);
			retVal.put("month", (month.charAt(0) == '0' ? month.charAt(1)  : month));
			retVal.put("is_success", true);
		} catch(Exception e) {
			retVal.put("totCnt", -999);
			retVal.put("organization", null);
			retVal.put("month", null);
			retVal.put("is_success", false);
		}
		
		return retVal;
	}
	
	public String addZero(String num) {
		if(num.length() == 1) {
			num = "0" + num;
		}
		return num;
	}
}
