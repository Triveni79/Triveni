package com.payroll.demo.serviceImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Candidate;
import com.payroll.demo.repository.AddEmployeeRepo;
import com.payroll.demo.repository.CandidateRepo;
import com.payroll.demo.service.AddEmployeeService;

import jakarta.transaction.Transactional;


@Service
public class AddEmployeeServiceImpl implements AddEmployeeService {

	@Autowired
	private AddEmployeeRepo addEmployeeRepo;

	@Autowired
	private CandidateRepo candidateRepo;


	public Candidate addCandidate(Candidate candidate) {
		return candidateRepo.save(candidate);
	}

	
	
	@Override
	@Transactional
	public AddEmployee saveEmployeeDetails(@Valid AddEmployee emp, String joiningDateString, MultipartFile offerLetter,
			MultipartFile joiningLetter, MultipartFile agreement, MultipartFile experienceLetter,
			MultipartFile photo, String accountHolderName, String accountNumber, String ifscCode, String branch,
			String bankName, String panCard, String bloodGroup, String nominee, String nomineeContact)
			throws IOException {

	    // Retrieve the last employee from the repository
	    AddEmployee lastEmployee = addEmployeeRepo.findLastEmployee();
	    String lastEmpId = lastEmployee != null ? lastEmployee.getEmpId() : null;

	    // Increment the sequence number
	    int sequenceNumber = 1;
	    if (lastEmpId != null && lastEmpId.length() >= 8) {
	        // Extract the sequence number from the last empId
	        int lastSequenceNumber = Integer.parseInt(lastEmpId.substring(6));
	        sequenceNumber = lastSequenceNumber + 1;

	        // Check if the new sequence number is the same as the last sequence number
	        if (sequenceNumber == lastSequenceNumber) {
	            sequenceNumber++; // Increment the sequence number by 1
	        }
	    }

	    // Generate the new empId using the dateString
	    String empId = joiningDateString.substring(2, 4) + joiningDateString.substring(8) + String.format("%03d", sequenceNumber);

	    // Check if the generated empId already exists in the repository
	    while (addEmployeeRepo.existsById(empId)) {
	        sequenceNumber++; // Increment the sequence number
	        empId = joiningDateString.substring(2, 4) + joiningDateString.substring(8) + String.format("%03d", sequenceNumber);
	    }

	    emp.setEmpId(empId);

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

	    emp.setJoiningDate(java.sql.Date.valueOf(joiningDateString));
	    emp.setExperienceLetter(experienceLetter.getBytes());
	    emp.setAgreement(agreement.getBytes());
	    emp.setJoiningLetter(joiningLetter.getBytes());
	    emp.setPhoto(photo.getBytes());
	    emp.setOfferLetter(offerLetter.getBytes());

	    addEmployeeRepo.save(emp); // Save the employee

	    return emp;
	}
	
	
	@Override
    public byte[] getOfferLetterByEmpId(String empId) {
        AddEmployee employee = addEmployeeRepo.findByEmpId(empId);
        return employee.getOfferLetter();
    }

    @Override
    public byte[] getJoiningLetterByEmpId(String empId) {
        AddEmployee employee = addEmployeeRepo.findByEmpId(empId);
        return employee.getJoiningLetter();
    }

    @Override
    public byte[] getAgreementByEmpId(String empId) {
        AddEmployee employee = addEmployeeRepo.findByEmpId(empId);
        return employee.getAgreement();
    }

    @Override
    public byte[] getExperienceLetterByEmpId(String empId) {
        AddEmployee employee = addEmployeeRepo.findByEmpId(empId);
        return employee.getExperienceLetter();
    }
	public AddEmployee getEmployeeById(String empId) {
		return addEmployeeRepo.findById(empId).orElse(null);
	}

	public List<AddEmployee> getAllEmployees() {
		return addEmployeeRepo.findAll();
	}
 	
	@Override
	public AddEmployee updateEmployee(String empId, MultipartFile offerLetter, MultipartFile joiningLetter,
			MultipartFile agreement, MultipartFile experienceLetter, MultipartFile photo, String accountHolderName,
			String accountNumber, String ifscCode, String branch, String bankName, String panCard, String bloodGroup,
			String nominee, String nomineeContact, Date joiningDate, Candidate candidateId) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}