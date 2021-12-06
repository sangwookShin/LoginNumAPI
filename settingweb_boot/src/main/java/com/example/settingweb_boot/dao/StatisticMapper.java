package com.example.settingweb_boot.dao;

import java.util.HashMap;

public interface StatisticMapper {
	public HashMap<String, Object> selectYearLogin(String year);
}
