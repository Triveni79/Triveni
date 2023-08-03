package com.payroll.demo.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.payroll.demo.entity.Hike;


@Service
public interface HikeServiceInter {

	
	public Hike saveHike(Hike h,
				 String	department,
				 String	designation,
				 Date	dates_Of_Start,
				 Date	dates_Of_End,
				double	consolidated_sal,
				String	position,
				String	reporting_manager,
				String	approved_by,
				double	hike_percentage
				);


	public Optional<Hike> getEmppThroughHikeID(String hikeId);
	
}


