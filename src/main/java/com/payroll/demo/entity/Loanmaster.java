package com.payroll.demo.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_master")

public class Loanmaster {
    @JsonBackReference
	@OneToOne
	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
	private AddEmployee empId;

	@Id
	@Column(nullable = false, length = 10)
	private String loan_Santioned_id;
	
	@Column(nullable = false, length = 30)
	private long loan_SantionedAmount;
	
	private int tenure;
	
	@Column(nullable = false, length = 10, precision = 2)
	private long EMI;
	
	@Column(nullable = false, length = 20)
	private String loan_Transaction_id;
	
	@Column(nullable = false, length = 50)
	private String loan_Source_Bank;
	
	@Column(nullable = false, length = 50)
	private String loan_Credited_By;
	
	@Column(nullable = false)
	private Date approved_date;

	public AddEmployee getEmpId() {
		return empId;
	}

	public void setEmpId(AddEmployee empId) {
		this.empId = empId;
	}

	public String getLoan_Santioned_id() {
		return loan_Santioned_id;
	}

	public void setLoan_Santioned_id(String loan_Santioned_id) {
		this.loan_Santioned_id = loan_Santioned_id;
	}

	public long getLoan_SantionedAmount() {
		return loan_SantionedAmount;
	}

	public void setLoan_SantionedAmount(long loan_SantionedAmount) {
		this.loan_SantionedAmount = loan_SantionedAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public long getEMI() {
		return EMI;
	}

	public void setEMI(long eMI) {
		EMI = eMI;
	}

	public String getLoan_Transaction_id() {
		return loan_Transaction_id;
	}

	public void setLoan_Transaction_id(String loan_Transaction_id) {
		this.loan_Transaction_id = loan_Transaction_id;
	}

	public String getLoan_Source_Bank() {
		return loan_Source_Bank;
	}

	public void setLoan_Source_Bank(String loan_Source_Bank) {
		this.loan_Source_Bank = loan_Source_Bank;
	}

	public String getLoan_Credited_By() {
		return loan_Credited_By;
	}

	public void setLoan_Credited_By(String loan_Credited_By) {
		this.loan_Credited_By = loan_Credited_By;
	}

	public Date getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}

	public Loanmaster(AddEmployee empId, String loan_Santioned_id, long loan_SantionedAmount, int tenure, long eMI,
			String loan_Transaction_id, String loan_Source_Bank, String loan_Credited_By, Date approved_date) {
		super();
		this.empId = empId;
		this.loan_Santioned_id = loan_Santioned_id;
		this.loan_SantionedAmount = loan_SantionedAmount;
		this.tenure = tenure;
		EMI = eMI;
		this.loan_Transaction_id = loan_Transaction_id;
		this.loan_Source_Bank = loan_Source_Bank;
		this.loan_Credited_By = loan_Credited_By;
		this.approved_date = approved_date;
	}

	public Loanmaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	
	
	
	
	
	
}