package com.payroll.demo.controller;





import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Candidate;
import com.payroll.demo.repository.AddEmployeeRepo;
import com.payroll.demo.repository.CandidateRepo;


@RestController
@RequestMapping("/home")
public class AddEmployeeController {

	@Autowired
	private AddEmployeeRepo addEmployeeRepo;

	@Autowired
	private CandidateRepo candidateRepo;

	private static int n = 001;

	@PostMapping("/req1")
	public Candidate addCandidate(@RequestBody Candidate candidate)
	{
		return candidateRepo.save(candidate);
	}

	@PostMapping("/save")
	public AddEmployee saveEmployeeDetails(@Valid @ModelAttribute("addEmployee") AddEmployee emp, 
			BindingResult result, Model model, 
			@RequestParam("joiningDateString") String joiningDateString,
			
			@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountNumber") String accountNumber, 
			@RequestParam("ifscCode") String ifscCode,
			@RequestParam("branch") String branch,
			@RequestParam("bankName") String bankName,
			@RequestParam("panCard") String panCard,
			@RequestParam("bloodGroup") String bloodGroup, 
			@RequestParam("nominee") String nominee, 
			@RequestParam("nomineeContact") String nomineeContact)
			throws IOException

	{

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD-MM-YYYY"); // Change format string here
		LocalDate date = LocalDate.parse(joiningDateString, formatter);

		int year = date.getYear() % 100; // Get the last two digits of the year
		int dayOfMonth = date.getDayOfMonth();

		String formattedSequenceNumber = String.format("%03d", n);

		// Combine the year, day of month, and formatted sequence number into the
		// desired format
		String output = String.format("%02d%02d%s", year, dayOfMonth, formattedSequenceNumber);

		emp.setEmpId(output);
		// Increment the sequence number and format it with leading zeros
				n++;
		emp.setAccountHolderName(accountHolderName);
		emp.setAccountNumber(accountNumber);
		emp.setBankName(bankName);
		emp.setBloodGroup(bloodGroup);

		emp.setBranch(branch);
		emp.setIfscCode(ifscCode);
		emp.setBankName(bankName);
		emp.setPanCard(panCard);
		emp.setNominee(nominee);
		emp.setNomineeContact(nomineeContact);

		
		java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		emp.setJoiningDate(sqlDate);
		

		return addEmployeeRepo.save(emp);
	}



//this method used to get one employee record

	@GetMapping("/get/{empId}")
	public AddEmployee getEmployee(@PathVariable String empId) {
		return addEmployeeRepo.findById(empId).get();
	}

	// this method used to get all the records

	@GetMapping("/getAll")
	public List<AddEmployee> getAll() {
		List<AddEmployee> list = addEmployeeRepo.findAll();

		return list;
	}

	// this method used to update the employee records by values
	@PutMapping("/employees/{empId}")
	public AddEmployee updateEmployee(
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
            @RequestParam("candidateId") Candidate candidateId
			, @PathVariable String empId) throws IOException 
	
	{
		AddEmployee emp = addEmployeeRepo.findById(empId).get();
		emp.setAccountHolderName(emp.getAccountHolderName());
		emp.setAccountNumber(emp.getAccountNumber());
		
		emp.setBankName(emp.getBankName());
		emp.setBloodGroup(emp.getBloodGroup());
		emp.setBranch(emp.getBranch());
		
		emp.setIfscCode(emp.getIfscCode());
		emp.setPanCard(emp.getPanCard());
	
		emp.setNominee(emp.getNominee());
		emp.setNomineeContact(emp.getNomineeContact());
	
		emp.setJoiningDate(emp.getJoiningDate());
		
		return addEmployeeRepo.save(emp);

	}

}