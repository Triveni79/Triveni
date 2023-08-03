package com.payroll.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.payroll.demo.entity.AddInterviewerName;

public interface AddInterviewerRepository extends JpaRepository<AddInterviewerName,String> {
//	@Query("SELECT ai.interviewerName FROM AddInterviewerName ai")
//	List<String> getInterviewerNames();
	
	@Query("SELECT DISTINCT e.interviewerName FROM AddInterviewerName e")
	List<String> findDistinctNames();

	@Query("SELECT e.interviewerId FROM AddInterviewerName e WHERE e.interviewerName = :interviewerName")
	String findEmployeeIdByName(@Param("interviewerName") String interviewerName);

}
