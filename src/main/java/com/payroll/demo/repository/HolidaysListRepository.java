package com.payroll.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.demo.entity.HolidayList;



public interface HolidaysListRepository extends JpaRepository<HolidayList, Integer>{

}
