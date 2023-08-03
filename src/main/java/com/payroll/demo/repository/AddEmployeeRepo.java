package com.payroll.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.payroll.demo.entity.AddEmployee;

@Repository
public interface AddEmployeeRepo extends JpaRepository<AddEmployee, String>{
	@Query("SELECT e FROM AddEmployee e WHERE e.empId = (SELECT MAX(e2.empId) FROM AddEmployee e2)")
	AddEmployee findLastEmployee();
	  AddEmployee findByEmpId(String empId);
	  
	
}