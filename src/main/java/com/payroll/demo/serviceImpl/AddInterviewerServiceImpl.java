package com.payroll.demo.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payroll.demo.entity.AddInterviewerName;
import com.payroll.demo.entity.Login;
import com.payroll.demo.repository.AddInterviewerRepository;
import com.payroll.demo.repository.LoginRepository;
import com.payroll.demo.service.AddInterviewerService;
import jakarta.persistence.EntityNotFoundException;

@Service
public  class AddInterviewerServiceImpl implements AddInterviewerService {
	
	@Autowired
	AddInterviewerRepository addInterviewerRepository;
	
	@Autowired
	private  LoginRepository loginRepository;

	@Override
	public String EmployeeId(String interviewerName) {
		// TODO Auto-generated method stub
		return addInterviewerRepository.findEmployeeIdByName(interviewerName);
	}

	@Override
	public List<String> getName() {
		// TODO Auto-generated method stub
		 return addInterviewerRepository.findDistinctNames();
	}

	public List<AddInterviewerName> getAllInterviewers() {
		// TODO Auto-generated method stub
		return addInterviewerRepository.findAll();
	}

	@Override
	public AddInterviewerName getInterviewerId(String interviewerId) {
		return addInterviewerRepository.findById(interviewerId).orElse(null);
	}
	
	@Override
	public void AddInterviewer(AddInterviewerName interviewer, String empId) {
		 String stati = "INT";
	        String formattedEmployeeId = String.format("%07d", Integer.parseInt(empId));
	        String interviewers = stati.concat(formattedEmployeeId);
	        
	    Login login = loginRepository.findById(empId).get();
	    if (login == null) {
	        throw new EntityNotFoundException("Login entity not found for empId: " + empId);
	    }

	    interviewer.setEmpId(login);
	    interviewer.setInterviewerId(interviewers);
	    interviewer.setInterviewerName(interviewer.getInterviewerName());
	     addInterviewerRepository.save(interviewer);
		}
		

//	public AddInterviewerName addInterviewer(String employeeId, String interviewerName) {
//		AddInterviewerName interview=new AddInterviewerName(employeeId, interviewerName);
//		interview.setEmployeeId(employeeId);
//		interview.setInterviewerName(interviewerName);
//		return addInterviewerRepository.save(interview);
//		
//	}
//	public List<String>getName(){
//		return addInterviewerRepository.findDistinctNames();
//	}
//		
//	public String EmployeeId(String interviewerName ) {
//		return addInterviewerRepository.findEmployeeIdByName(interviewerName);
//	}
		
	

}
