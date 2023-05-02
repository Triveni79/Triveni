package com.payroll.demo.service;

import java.util.List;

import com.payroll.demo.entity.HolidayList;


public interface HolidayListService {
	
	HolidayList save(HolidayList holidayList);

	List<HolidayList> findAllHolidayLists();

}
