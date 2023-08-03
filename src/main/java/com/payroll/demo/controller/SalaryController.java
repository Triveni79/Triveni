
package com.payroll.demo.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Salary;
import com.payroll.demo.repository.CandidateRepo;
import com.payroll.demo.serviceImpl.Salaryserviceimpl;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/api")
public class SalaryController {

	@Autowired
	private Salaryserviceimpl serviceInter;
	@Autowired
	private CandidateRepo CandidateRepo;

	@PostMapping("/details")
	public Salary Details(@Valid @ModelAttribute("salary") Salary e, BindingResult result,
	                      @RequestParam("month") String month,
	                      @RequestParam int year,
	                      @RequestParam int leaves,
	                      @RequestParam int loans,
	                      @RequestParam double profTax,
	                      @RequestParam int absent,
	                      @RequestParam int apprPoints,
	                      @RequestParam int warningPoints,
	                      @RequestParam int delayPoints,
	                      @RequestParam double allowances,
	                      @RequestParam double bonus,
	                      @RequestParam double netSalary,
	                      @RequestParam double tds,
	                      @RequestParam Date transactionDate,
	                      @RequestParam String sourceBank,
	                      @RequestParam String creditedBy
	                     ) {

	    // Set the values from the request parameters to the Salary object
	
	    e.setMonth(month);
	    e.setYear(year);
	    e.setLeaves(leaves);
	    e.setLoans(loans);
	    e.setProfTax(profTax);
	    e.setAbsent(absent);
	    e.setApprPoints(apprPoints);
	    e.setWarningPoints(warningPoints);
	    e.setDelayPoints(delayPoints);
	    e.setAllowances(allowances);
	    e.setBonus(bonus);
	    e.setNetSalary(netSalary);
	    e.setTds(tds);
	    e.setTransactionDate(transactionDate);
	    e.setSourceBank(sourceBank);
	    e.setCreditedBy(creditedBy);

	    return serviceInter.saveEmpp(e , 
	    		month, 
	    		year, 
	    		leaves, 
	    		loans,
	    		profTax,
	    		absent,
	    		apprPoints,
	    		warningPoints, 
	    		delayPoints,  
	    		allowances,
	    		bonus, 
	    		netSalary,
	    		tds,
	    		transactionDate,
	    		sourceBank,
	    		creditedBy
	   		);    
	}
	
	// get details through transaction_Id
		@GetMapping("/getOne/{transactionId}")
		public Salary getEmployeeDetailsThroughTransID(@PathVariable("transactionId") String transactionId) {

			return serviceInter.getEmppThroughTransID(transactionId);

		}
		
		// get details through EMpID
		@GetMapping("/getOne/EID/{addEmployee}")
		public List<Salary> getEmployeeDetailsThroughEmpId(@PathVariable("addEmployee") AddEmployee addEmployee) {

			return serviceInter.getEmppThroughEmpID(addEmployee);

		}
	
		
		
		@GetMapping("/GetAllSalary")
		public List<Salary> allSalariesGetter() {
			
			return 	serviceInter.getAllSalaryDetails();
		}
		
		
		
		@GetMapping("/pdf/{EmpId}/{ye}/{fmo}/{tmo}")
		public void PdfGenerator(@PathVariable("EmpId") AddEmployee EmpId,@PathVariable int ye,@PathVariable String fmo,@PathVariable String tmo, HttpServletResponse response)
				throws IOException, DocumentException {


				serviceInter.generatePdf(EmpId, ye, fmo,tmo, response);
		
		}
		
		
		
		

}