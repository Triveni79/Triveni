package com.payroll.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.demo.entity.AddInterviewerName;
import com.payroll.demo.entity.Interviewer;
import com.payroll.demo.entity.Login;
import com.payroll.demo.serviceImpl.LoginServiceImpl;

  
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginServiceImpl employeeService;
    
    private final String LETTERS = "abcdefghijlmnopqrstuvwxyz";
	private final char[] ALPHANUMERIC = (LETTERS + LETTERS.toUpperCase() + "0123456789").toCharArray();
	
		
	
//	@PostMapping("/register")
//	public ResponseEntity<String> registerEmployee(@RequestParam("empId") String empId, @RequestParam String personalEmail,
//	    @RequestParam String officialEmail, @ModelAttribute("employee") Login employee, Integer length) {
//	    employee.setEmpId(empId);
//	    employee.setPersonalEmail(personalEmail);
//	    employee.setOfficialEmail(officialEmail);
//
//	    StringBuilder result = new StringBuilder();
//
//	    for (int i = 0; i < ALPHANUMERIC.length; i++) {
//	        while (result.length() < 6) {
//	            result.append(ALPHANUMERIC[new Random().nextInt(ALPHANUMERIC.length)]);
//	        }
//	    }
//	    String s = result.toString();
//	    employee.setOfficialMailPassword(s);
//	    employeeService.registerEmployee(employee);
//	    
//	    // Create a JSON response object with the necessary data
//	    String response = "{\"message\": \"Details stored successfully.\"}";
//
//	    // Return the JSON response with a success status code
//	    return ResponseEntity.status(HttpStatus.OK).body(response);
//	}
//
//   			
//	
		   //  Employee Login	
		    @PostMapping("/login")	
		    public Login login(@RequestParam("officialEmail") String officialEmail,	
		                        @RequestParam("password") String password,	
		                        Model model) {	
		        return employeeService.login(officialEmail, password);	
		      
		    }	

		    @PostMapping("/changepassword")	
		    public boolean changePassword(@RequestParam("officialMailPassword") String officialMailPassword,	
		                                 @RequestParam("newPassword") String newPassword,	
		                                 @RequestParam("confirmPassword") String confirmPassword) {	
		        boolean passwordChanged = employeeService.changePassword(officialMailPassword, newPassword, confirmPassword);
		        return passwordChanged;
		    }
		    
		    
		    
		    
		    @GetMapping("/login")
			public List<Login> getAlllogins() {
				return employeeService.getLogins();
			}

		    
		}
