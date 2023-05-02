package com.payroll.demo.entity;

import java.sql.Date;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "AddEmployeeDetails")
public class AddEmployee {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "emp_Id")
	private String empId;

	@OneToOne
	@JoinColumn(name = "candidate_Id", referencedColumnName = "candidate_Id")
	private Candidate candidateId;

	@Column(name = "accountHolder_Name")
	private String accountHolderName;

	@Column(name = "account_Number")
	private String accountNumber;

	@Column(name = "bank_Name")
	private String bankName;

	@Column(name = "ifsc_Code")
	private String ifscCode;

	@Column(name = "pan_Card")
	private String panCard;

	@Column(name = "blood_Group")
	private String bloodGroup;

	@Column(name = "nominee")
	private String nominee;

	@Column(name = "nominee_Contact")
	private String nomineeContact;


	@Column(name = "branch")
	private String branch;

	@Column(name = "joining_Date")
	private Date joiningDate;

	public AddEmployee() {
		super();
	}

	public AddEmployee(String empId, Candidate candidateId, String accountHolderName, String accountNumber,
			String bankName, String ifscCode, String panCard, String bloodGroup, String nominee, String nomineeContact,
			String branch, Date joiningDate) {
		super();
		this.empId = empId;
		this.candidateId = candidateId;
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.panCard = panCard;
		this.bloodGroup = bloodGroup;
		this.nominee = nominee;
		this.nomineeContact = nomineeContact;
		
		this.branch = branch;
		this.joiningDate = joiningDate;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Candidate getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Candidate candidateId) {
		this.candidateId = candidateId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getNominee() {
		return nominee;
	}

	public void setNominee(String nominee) {
		this.nominee = nominee;
	}

	public String getNomineeContact() {
		return nomineeContact;
	}

	public void setNomineeContact(String nomineeContact) {
		this.nomineeContact = nomineeContact;
	}
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "AddEmployee [empId=" + empId + ", candidateId=" + candidateId + ", accountHolderName="
				+ accountHolderName + ", accountNumber=" + accountNumber + ", bankName=" + bankName + ", ifscCode="
				+ ifscCode + ", panCard=" + panCard + ", bloodGroup=" + bloodGroup + ", nominee=" + nominee
				+ ", nomineeContact=" + nomineeContact 
				+ ", branch=" + branch + ", joiningDate=" + joiningDate + "]";
	}

}