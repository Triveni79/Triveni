package com.payroll.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.payroll.demo.entity.AddInterviewerName;
import com.payroll.demo.repository.AddInterviewerRepository;
import com.payroll.demo.serviceImpl.AddInterviewerServiceImpl;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class AddInterviewerController {
	
	@Autowired
	AddInterviewerRepository addInterviewerRepository;
	
	@Autowired
	AddInterviewerServiceImpl addInterviewerService;

	@PostMapping("/{empId}/saveinterviewer")
	public void addInterviewer(@PathVariable String empId,AddInterviewerName interviewer) {
		addInterviewerService.AddInterviewer(interviewer, empId);
	   	}
	
	@GetMapping("/getnameslist")
	public List<String>namesList(){
		//return addInterviewerRepository.findDistinctNames();
		return addInterviewerService.getName();
	}
	
	@GetMapping("/getintid/{interviewerName}")
	public String employeeId(@PathVariable("interviewerName")String interviewerName) {
		
		return addInterviewerService.EmployeeId(interviewerName);
	}
	
	@GetMapping("/interviewers")
	public List<AddInterviewerName> getAllInterviewers() {
	    return addInterviewerService.getAllInterviewers();
	}
	  @GetMapping("/{interviewerId}")
	    public AddInterviewerName getInterviewerId(@PathVariable String interviewerId) {
	        return addInterviewerService.getInterviewerId(interviewerId);
	    }

	// Delete Interviewer Rest Api
	@DeleteMapping("/interviewers/{interviewerId}")
	public ResponseEntity<Map<String, Boolean>> deleteInterviewer(@PathVariable String interviewerId ){
	    Optional<AddInterviewerName> interviewerOptional = addInterviewerRepository.findById(interviewerId);

	    if (interviewerOptional.isPresent()) {
	        AddInterviewerName addinterviewer = interviewerOptional.get();
	        addInterviewerRepository.delete(addinterviewer);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return ResponseEntity.ok(response);
	    } else {
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Interviewer does not exist with id " + interviewerId);
	    }
	}
}
