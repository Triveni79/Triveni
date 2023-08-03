package com.payroll.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.payroll.demo.entity.Hike;

public interface HikeRepo extends JpaRepository<Hike, String> {


	
}
