package com.payroll.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Salary;





@Repository
public interface SalaryRepo extends JpaRepository<Salary,String>
{

	
	@Query(value="SELECT SUBSTR(transaction_id, 8, 10) AS result FROM salarytransaction  order by transaction_id desc limit 1 ",nativeQuery = true)
	String lastValueCheck();
	List<Salary> findByAddEmployee(AddEmployee addEmployee);

	
	@Query(value=" SELECT AC.candidate_Id FROM salarytransaction E JOIN addemployeedetails AE ON E.emp_Id = AE.emp_Id JOIN Candidate AC ON AE.candidate_Id = AC.candidate_Id WHERE E.emp_Id = :empId",nativeQuery = true)
	String findCandidateIdByEmpId(@Param("empId") String empId);
	
}
