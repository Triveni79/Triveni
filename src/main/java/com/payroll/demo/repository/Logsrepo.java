//package com.payroll.demo.repository;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.payroll.demo.entity.Logs;
//
//@Repository
//public interface Logsrepo extends JpaRepository<Logs, Long>{
//	
//	 @Query(value = "SELECT l.id, l.log_type, l.approved_By, l.working_Hours, l.date, a.emp_Id " +
//	            "FROM Logs l JOIN AddEmployeeDetails a ON l.emp_Id = a.emp_Id", nativeQuery = true)
//	    List<Map<String, Object>> findAllLogsWithEmpId();
//
//}
