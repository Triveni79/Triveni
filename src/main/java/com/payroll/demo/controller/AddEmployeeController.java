package com.payroll.demo.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Candidate;
import com.payroll.demo.serviceImpl.AddEmployeeServiceImpl;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class AddEmployeeController {

	    @Autowired
	    private AddEmployeeServiceImpl employeeService;
	    
//	    @Autowired
//	    private AddCandidateServiceImpl candidateService;

	    
	    @GetMapping("/all/{empId}")
	    public AddEmployee getEmployee(@PathVariable String empId) {
	      return employeeService.getEmployeeById(empId);
	    }

	    @PostMapping("/employee")
	    public AddEmployee saveEmployeeDetails(@Valid @ModelAttribute("addEmployee") AddEmployee emp,
	                                BindingResult result, Model model,
	                                @RequestParam("joiningDateString") String joiningDateString,
	                                @RequestParam("offerLetter") MultipartFile offerLetter,
	                                @RequestParam("joiningLetter") MultipartFile joiningLetter,
	                                @RequestParam("agreement") MultipartFile agreement,
	                                @RequestParam("experienceLetter") MultipartFile experienceLetter,
	                                @RequestParam("photo") MultipartFile photo,
	                                @RequestParam("accountHolderName") String accountHolderName,
	                                @RequestParam("accountNumber") String accountNumber, 
	                                @RequestParam("ifscCode") String ifscCode,
	                                @RequestParam("branch") String branch,
	                                @RequestParam("bankName") String bankName,
	                                @RequestParam("panCard") String panCard,
	                                @RequestParam("bloodGroup") String bloodGroup, 
	                                @RequestParam("nominee") String nominee, 
	                                @RequestParam("nomineeContact") String nomineeContact
	                                )
	            throws IOException {
	    	java.sql.Date sqlDate = java.sql.Date.valueOf(joiningDateString);
	        emp.setJoiningDate(sqlDate);
	        emp.setExperienceLetter(experienceLetter.getBytes());
	        emp.setAgreement(agreement.getBytes());
	        emp.setJoiningLetter(joiningLetter.getBytes());
	        emp.setPhoto(photo.getBytes());
	        emp.setOfferLetter(offerLetter.getBytes());
	        emp.setAccountHolderName(accountHolderName);
	        emp.setAccountNumber(accountNumber);
	        emp.setBankName(bankName);
	        emp.setBloodGroup(bloodGroup);
	        emp.setBranch(branch);
	        emp.setIfscCode(ifscCode);
	        emp.setPanCard(panCard);
	        emp.setNominee(nominee);
	        emp.setNomineeContact(nomineeContact);

	        return employeeService.saveEmployeeDetails( emp, joiningDateString, offerLetter,
	    			joiningLetter, agreement, experienceLetter, photo,
	    			 accountHolderName, accountNumber, ifscCode, branch, bankName,
	    			 panCard,  bloodGroup, nominee,  nomineeContact);
	    }
	    
	   
	    

	    @GetMapping("/employee")
	    public List<AddEmployee> getAllEmployees() {
	        List<AddEmployee> employeeList = employeeService.getAllEmployees();
	        Collections.sort(employeeList, Comparator.comparing(employee -> {
	            // Add your sorting logic for employees here
	            // For example, if you have an employeeId field in AddEmployee class
	            // and you want to sort based on the last three digits of employeeId:
	            String employeeId = employee.getEmpId();
	            String lastDigits = employeeId.substring(Math.max(employeeId.length() - 3, 0));
	            return Integer.parseInt(lastDigits);
	        }));
	        return employeeList;
	    }
	    
	        @PutMapping("/employee/{empId}")
	        public AddEmployee updateEmployee(@RequestParam("offerLetter") MultipartFile offerLetter,
	                                           @RequestParam("joiningLetter") MultipartFile joiningLetter,
	                                           @RequestParam("agreement") MultipartFile agreement,
	                                           @RequestParam("experienceLetter") MultipartFile experienceLetter,
	                                           @RequestParam("photo") MultipartFile photo,
	                                           @RequestParam("accountHolderName") String accountHolderName,
	                                           @RequestParam("accountNumber") String accountNumber,
	                                           @RequestParam("ifscCode") String ifscCode,
	                                           @RequestParam("branch") String branch,
	                                           @RequestParam("bankName") String bankName,
	                                           @RequestParam("panCard") String panCard,
	                                           @RequestParam("bloodGroup") String bloodGroup,
	                                           @RequestParam("nominee") String nominee,
	                                           @RequestParam("nomineeContact") String nomineeContact,
	                                           @RequestParam("joiningDate") Date joiningDate,
	                                           @RequestParam("candidateId") Candidate candidateId,
	                                           @PathVariable String empId) throws IOException {

	            AddEmployee updatedEmployee = employeeService.updateEmployee(empId, offerLetter, joiningLetter, agreement, experienceLetter,
	            		photo, accountHolderName, accountNumber, ifscCode, branch, bankName, panCard, bloodGroup, nominee, nomineeContact,
	            		joiningDate, candidateId);
	            return updatedEmployee;
	        }
	        
	        
	        
	        private void writeResponseBytes(byte[] bytes, String filename, HttpServletResponse response) throws IOException {
	            response.setContentType("application/pdf");
	            response.setHeader("Content-Disposition", "inline; filename=" + filename);
	            ServletOutputStream outputStream = response.getOutputStream();
	            outputStream.write(bytes);
	            outputStream.flush();
	        }

	        @GetMapping("/viewOfferLetter/{empId}")
	        public void viewOfferLetter(@PathVariable String empId, HttpServletResponse response) throws IOException {
	            byte[] offerLetterBytes = employeeService.getOfferLetterByEmpId(empId);
	            writeResponseBytes(offerLetterBytes, "offer_letter.pdf", response);
	        }

	        @GetMapping("/viewJoiningLetter/{empId}")
	        public void viewJoiningLetter(@PathVariable String empId, HttpServletResponse response) throws IOException {
	            byte[] joiningLetterBytes = employeeService.getJoiningLetterByEmpId(empId);
	            writeResponseBytes(joiningLetterBytes, "joining_letter.pdf", response);
	        }

	        @GetMapping("/viewAgreement/{empId}")
	        public void viewAgreement(@PathVariable String empId, HttpServletResponse response) throws IOException {
	            byte[] agreementBytes = employeeService.getAgreementByEmpId(empId);
	            writeResponseBytes(agreementBytes, "agreement.pdf", response);
	        }

	        @GetMapping("/viewExperienceLetter/{empId}")
	        public void viewExperienceLetter(@PathVariable String empId, HttpServletResponse response) throws IOException {
	            byte[] experienceLetterBytes = employeeService.getExperienceLetterByEmpId(empId);
	            writeResponseBytes(experienceLetterBytes, "experience_letter.pdf", response);
	        }
}