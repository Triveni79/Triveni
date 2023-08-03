package com.payroll.demo.entity;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "AddEmployeeDetails")
public class AddEmployee {

	@Id
	@Column(name = "emp_Id")
	private String empId;

	@OneToOne(mappedBy = "addEmployee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Salary salary;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id")
//	private List<Logs> loginLogoutList;

	@OneToOne
	@JoinColumn(name = "candidate_Id", referencedColumnName = "candidate_Id")
	private Candidate candidateId;

	@JsonManagedReference
	@OneToOne(mappedBy = "empId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Loanmaster loanmaster;

//	public List<Logs> getLoginLogoutList() {
//		return loginLogoutList;
//	}
//
//	public void setLoginLogoutList(List<Logs> loginLogoutList) {
//		this.loginLogoutList = loginLogoutList;
//	}

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

	@Lob
	@Column(name = "offer_Letter", length = 50000000)
	private byte[] offerLetter;

	@Lob
	@Column(name = "joining_Letter", length = 50000000)
	private byte[] joiningLetter;

	@Lob
	@Column(name = "agreement", length = 50000000)
	private byte[] agreement;

	@Lob
	@Column(name = "experience_Letter", length = 50000000)
	private byte[] experienceLetter;

	@Lob
	@Column(name = "photo", length = 50000000)
	private byte[] photo;

	@Column(name = "branch")
	private String branch;

	@Column(name = "joining_Date")
	private Date joiningDate;

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

	public Loanmaster getLoanmaster() {
		return loanmaster;
	}

	public void setLoanmaster(Loanmaster loanmaster) {
		this.loanmaster = loanmaster;
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

	public byte[] getOfferLetter() {
		return offerLetter;
	}

	public void setOfferLetter(byte[] offerLetter) {
		this.offerLetter = offerLetter;
	}

	public byte[] getJoiningLetter() {
		return joiningLetter;
	}

	public void setJoiningLetter(byte[] joiningLetter) {
		this.joiningLetter = joiningLetter;
	}

	public byte[] getAgreement() {
		return agreement;
	}

	public void setAgreement(byte[] agreement) {
		this.agreement = agreement;
	}

	public byte[] getExperienceLetter() {
		return experienceLetter;
	}

	public void setExperienceLetter(byte[] experienceLetter) {
		this.experienceLetter = experienceLetter;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public AddEmployee(String empId, Salary salary, Candidate candidateId,
			Loanmaster loanmaster, String accountHolderName, String accountNumber, String bankName, String ifscCode,
			String panCard, String bloodGroup, String nominee, String nomineeContact, byte[] offerLetter,
			byte[] joiningLetter, byte[] agreement, byte[] experienceLetter, byte[] photo, String branch,
			Date joiningDate) {
		super();
		this.empId = empId;
		this.salary = salary;
		//this.loginLogoutList = loginLogoutList;
		this.candidateId = candidateId;
		this.loanmaster = loanmaster;
		this.accountHolderName = accountHolderName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.panCard = panCard;
		this.bloodGroup = bloodGroup;
		this.nominee = nominee;
		this.nomineeContact = nomineeContact;
		this.offerLetter = offerLetter;
		this.joiningLetter = joiningLetter;
		this.agreement = agreement;
		this.experienceLetter = experienceLetter;
		this.photo = photo;
		this.branch = branch;
		this.joiningDate = joiningDate;
	}

	public AddEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

}