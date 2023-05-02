package com.payroll.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.demo.entity.AddEmployee;



public interface AddEmployeeRepo extends JpaRepository<AddEmployee, String>{

}
