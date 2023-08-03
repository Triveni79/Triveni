package com.payroll.demo.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Candidate;

public interface AddEmployeeService {

	Candidate addCandidate(Candidate candidate);

	byte[] getOfferLetterByEmpId(String empId);

	byte[] getJoiningLetterByEmpId(String empId);

	byte[] getAgreementByEmpId(String empId);

	byte[] getExperienceLetterByEmpId(String empId);

	AddEmployee getEmployeeById(String empId);

	List<AddEmployee> getAllEmployees();

	public AddEmployee updateEmployee(String empId, MultipartFile offerLetter, MultipartFile joiningLetter,
			MultipartFile agreement, MultipartFile experienceLetter, MultipartFile photo, String accountHolderName,
			String accountNumber, String ifscCode, String branch, String bankName, String panCard, String bloodGroup,
			String nominee, String nomineeContact, Date joiningDate, Candidate candidateId) throws IOException;

	AddEmployee saveEmployeeDetails(@Valid AddEmployee emp, String joiningDateString, MultipartFile offerLetter,
			MultipartFile joiningLetter, MultipartFile agreement, MultipartFile experienceLetter, MultipartFile photo,
			String accountHolderName, String accountNumber, String ifscCode, String branch, String bankName,
			String panCard, String bloodGroup, String nominee, String nomineeContact) throws IOException;

}