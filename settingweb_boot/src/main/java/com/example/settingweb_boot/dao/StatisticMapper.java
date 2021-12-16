package com.example.settingweb_boot.dao;

import java.util.HashMap;
import java.util.List;

public interface StatisticMapper {
	public HashMap<String, Object> selectYearLogin(String year);
	public HashMap<String, Object> selectMonthLogin(String month);
	public HashMap<String, Object> selectDayLogin(String day);
	
	public List<HashMap<String, Object>> selectAverageDayLogin();
	public HashMap<String, Object> selectExceptHolidayLogin(String year, String month, List<String> holiday);
	public HashMap<String, Object> selectOrganizationMonthLogin(String organ, String month);
}
