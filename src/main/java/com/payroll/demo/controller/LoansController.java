package com.payroll.demo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Loanmaster;
import com.payroll.demo.entity.Loantransaction;
import com.payroll.demo.repository.AddEmployeeRepo;
import com.payroll.demo.repository.LoanmasterRepo;
import com.payroll.demo.repository.LoantransactionRepo;
import com.payroll.demo.service.LoanService;

@RestController
@RequestMapping("/api")
public class LoansController {

	@Autowired
	private LoanmasterRepo masterrepo;

	@SuppressWarnings("unused")
	@Autowired
	private LoantransactionRepo Transactionrepos;

	@Autowired
	private LoanService loanservice;
	
	@Autowired
	AddEmployeeRepo addEmployeeRepo;

	// This is to store values into Loan master table
	@PostMapping("/s")
	@jakarta.transaction.Transactional
	public Loanmaster method2(@RequestParam int tenure, @RequestParam String loan_Transaction_id,
			@RequestParam String loan_Source_Bank, @RequestParam String loan_Credited_By,@RequestParam long loan_SantionedAmount,
			@RequestParam Date approved_date,@RequestParam long EMI	) {

		// Retrieve the last Loanmaster entity from the repository
		Loanmaster lastLoanmaster = masterrepo.findLastLoanmaster();

		// Increment the sequence number
		int sequenceNumber = 1;
		if (lastLoanmaster != null && lastLoanmaster.getLoan_Santioned_id().length() >= 7) {
			// Extract the sequence number from the last loan ID
			int lastSequenceNumber = Integer.parseInt(lastLoanmaster.getLoan_Santioned_id().substring(5));
			sequenceNumber = lastSequenceNumber + 1;

			// Check if the new sequence number is the same as the last sequence number
			if (sequenceNumber == lastSequenceNumber) {
				sequenceNumber++; // Increment the sequence number by 1
			}
		}

		String yearSuffix = new SimpleDateFormat("yy").format(approved_date);

		// Generate the loan ID suffix using the current sequence number
		String suffix = String.format("%03d", sequenceNumber);

		// Combine the loan ID prefix and suffix to create the loan ID
		String loanID = "LN" + yearSuffix + suffix;

		// Check if the generated loan ID already exists in the repository
		while (masterrepo.existsById(loanID)) {
			sequenceNumber++; // Increment the sequence number
			suffix = String.format("%03d", sequenceNumber);
			loanID = "LN" + yearSuffix + suffix;
		}

		Loanmaster l = new Loanmaster();
		l.setLoan_Santioned_id(loanID);
		l.setTenure(tenure);
		l.setLoan_SantionedAmount(loan_SantionedAmount);
		l.setEMI(EMI);
		l.setLoan_Transaction_id(loan_Transaction_id);
		l.setLoan_Source_Bank(loan_Source_Bank);
		l.setLoan_Credited_By(loan_Credited_By);
		l.setApproved_date(approved_date);

		return loanservice.saveDetails(l);
	}

	// 2nd method This METHOD IS ONLY FOR GET ID IN POSTMAN
	@GetMapping("/get/{loan_Santionedid}")
	public ResponseEntity<Loanmaster> getLoanById(@PathVariable("loan_Santionedid") String loan_Santionedid) {
		Loanmaster loan = masterrepo.findById(loan_Santionedid).orElse(null);
		if (loan == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(loan);
		}
	}

	// this method used to update loanmaster details
	@PutMapping("/{loan_Santionedid}")
	public Loanmaster updateLoanmaster(@PathVariable("loan_Santionedid") String loan_Santionedid,
			@RequestBody Loanmaster updatedLoanMaster) {
		return loanservice.updateLoanmaster(loan_Santionedid, updatedLoanMaster);
	}

	// Get all List In LoanMaster
	@GetMapping("/loanmasters")
	public List<Loanmaster> findAllLoanmaster() {
		return loanservice.getAllLoanmaster();
	}

	// Delete as id on loanmaster
	@DeleteMapping("/{loan_Santionedid}")
	public void deleteLoanmaster(@PathVariable("loan_Santionedid") String loan_Santionedid) {
		loanservice.deleteLoanmaster(loan_Santionedid);
	}

	// FROM THIS STARTED LOAN TRANSACTION TABLE

	// THIS method use to calucate emi as per Date and store in Loan transaction

	@PostMapping("/calculate-emi")
	@Transactional
	public Loantransaction calculateEMI(Loantransaction loan, Loanmaster master,
			@RequestParam("SalaryCreditedDate") java.sql.Date SalaryCreditedDate) {
		// Get loan information from the model attributes
		long amount = master.getLoan_SantionedAmount();
		int tenure = master.getTenure();
		long emi = master.getEMI();

		LocalDate approvalDate = master.getApproved_date().toLocalDate();
		LocalDate salaryCreditLocalDate = SalaryCreditedDate.toLocalDate();

		// Calculate number of EMIs already paid
		long emisPaid = ChronoUnit.MONTHS.between(approvalDate, salaryCreditLocalDate);

		// Calculate remaining months and balance
		int remainingMonths = tenure - (int) emisPaid;
		long remainingBalance = amount - (emisPaid * emi);

		// Update loan object with remaining balance and tenure
		loan.setBalanceAmount(remainingBalance);

		loan.setTenureremaining(remainingMonths);

		// Save the loan transaction to the database
		// Transactionrepos.save(loan);

		return loanservice.save(loan);
	}

	// Get All List In LoanTransaction
	@GetMapping("/loantransaction")
	public List<Loantransaction> findAllLoantransaction() {
		return loanservice.getAllLoantransaction();
	}

	// THIS METHOD USE TO GET PARTICULAR BALANCE AMOUNT BASED ON ID
	@GetMapping("/{id}/balanceAmount")
	public double getLoantransactionBalanceAmount(@PathVariable("id") int id) {
		return loanservice.getLoantransactionBalanceAmountById(id);
	}

	// THIS METHOD USE TO GET PARTICULAR TENUREREMAINING BASED ON ID
	@GetMapping("/{id}/tenureRemaining")
	public int getLoantransactionTenureRemaining(@PathVariable("id") int id) {
		return loanservice.getLoantransactionTenureRemainingById(id);
	}

//	@GetMapping("/emi/{empId}")
//	public Loanmaster getEmi(@PathVariable("empId") String empId) {
//		Optional<AddEmployee> addemp=	addEmployeeRepo.findByEmpId(empId);
//		AddEmployee emm=addemp.get();
//		Loanmaster loanmaster = masterrepo.findEMIByEmpId(emm.getEmpId());
//
//		Loanmaster loan = new Loanmaster();
//		loan.setEMI(loanmaster.getEMI());
//		return loan;
//	}

}