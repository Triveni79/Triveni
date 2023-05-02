package com.payroll.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.entity.HolidayList;
import com.payroll.demo.repository.HolidaysListRepository;
import com.payroll.demo.service.HolidayListService;


@Service
public class HolidayServiceImpl implements HolidayListService {

	@Autowired
	private HolidaysListRepository festivalRepository;

	@Override
	public HolidayList save(HolidayList holidayList) {
		return festivalRepository.save(holidayList);
	}

	@Override
	public List<HolidayList> findAllHolidayLists() {
		return festivalRepository.findAll();
	}

}
