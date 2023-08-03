package com.payroll.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Login {

	@Id
	@Column(name = "emp_Id", nullable = false)
	private String empId;

//	@Column(nullable = false, name = "personal_Email")
//	private String personalEmail;

	@Column(nullable = false, unique = true, name = "official_Email")
	private String officialEmail;

	@Column(name = "official_Mail_Password")
	private String officialMailPassword;
	
    @OneToOne
    @JoinColumn(name = "master_admin_id")
    @JsonBackReference
    private MasterAdmin masterAdmin;


	@OneToOne(mappedBy = "empId", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private AddInterviewerName interviewer;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

//	public String getPersonalEmail() {
//		return personalEmail;
//	}
//
//	public void setPersonalEmail(String personalEmail) {
//		this.personalEmail = personalEmail;
//	}

	public String getOfficialEmail() {
		return officialEmail;
	}

	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}

	public String getOfficialMailPassword() {
		return officialMailPassword;
	}

	public void setOfficialMailPassword(String officialMailPassword) {
		this.officialMailPassword = officialMailPassword;
	}

	public AddInterviewerName getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(AddInterviewerName interviewer) {
		this.interviewer = interviewer;
	}

	public MasterAdmin getMasterAdmin() {
		return masterAdmin;
	}

	public void setMasterAdmin(MasterAdmin masterAdmin) {
		this.masterAdmin = masterAdmin;
	}

	public Login() {
		super();
	}

	public Login(String empId, String officialEmail, String officialMailPassword,
			com.payroll.demo.entity.MasterAdmin masterAdmin, AddInterviewerName interviewer) {
		super();
		this.empId = empId;
		//this.personalEmail = personalEmail;
		this.officialEmail = officialEmail;
		this.officialMailPassword = officialMailPassword;
		this.masterAdmin = masterAdmin;
		this.interviewer = interviewer;
	}
}
