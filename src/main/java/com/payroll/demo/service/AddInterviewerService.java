package com.payroll.demo.service;

import java.util.List;
import com.payroll.demo.entity.AddInterviewerName;

public interface AddInterviewerService {
	
	public String EmployeeId(String interviewerName );
	
	public List<String>getName();
	
	public void AddInterviewer(AddInterviewerName interviewer,String empId);
	
	AddInterviewerName getInterviewerId(String interviewerId);
}
