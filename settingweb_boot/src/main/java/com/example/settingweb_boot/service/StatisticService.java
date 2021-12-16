package com.example.settingweb_boot.service;

import java.util.HashMap;
import java.util.List;

public interface StatisticService {

	public HashMap<String, Object> yearloginNum (String year);
	public HashMap<String, Object> monthloginNum (String month);
	public HashMap<String, Object> dayloginNum (String day);
	
	public List<HashMap<String, Object>> averageDayloginNum();
	public HashMap<String, Object> exceptHolidayloginNum (String year, String month);
	public HashMap<String, Object> organizationMonthloginNum (String organ, String month);
}
