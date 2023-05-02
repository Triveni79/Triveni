package com.payroll.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.demo.entity.HolidayList;
import com.payroll.demo.serviceImpl.HolidayServiceImpl;


@RestController
@RequestMapping("/api/v1/")
public class HolidayListController {
	@Autowired
	private HolidayServiceImpl holidayServiceImpl;

	
	@PostMapping("/Festival")
	public HolidayList saveDetailss(@RequestBody HolidayList holidayList) {
		return holidayServiceImpl.save(holidayList);

	}

	@GetMapping("/Festival")
	public List<HolidayList> Data() {
		return holidayServiceImpl.findAllHolidayLists();
	}

}
