package com.payroll.demo.entity;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salarytransaction")
public class Salary {
	
	

	@Id
	@Column(name = "transaction_id")
	private String transactionId;


	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_Id", referencedColumnName = "emp_Id")
	private AddEmployee addEmployee;

	@NotNull
	@Column(name = "month")
	private String month;

	@NotNull
	@Column(name = "year")
	private int year;

	@NotNull
	@Column(name = "leaves")
	private int leaves;
	
	@NotNull
	@Column(name = "loans")
	private int loans;

	@NotNull
	@Column(name = "prof_Tax")
	private double profTax;

	@NotNull
	@Column(name = "absent")
	private int absent;

	@NotNull
	@Column(name = "appr_Points")
	private int apprPoints;

	@NotNull
	@Column(name = "warning_Points")
	private int warningPoints;

	@NotNull
	@Column(name = "delay_Points")
	private int delayPoints;

	@NotNull
	@Column(name = "allowances")
	private double allowances;

	@NotNull
	@Column(name = "bonus")
	private double bonus;
	
	@NotNull
	@Column(name = "net_Salary")
	private double netSalary;

	@NotNull
	@Column(name = "tds")
	private double tds;

	@NotNull
	@Column(name = "transaction_Date")
	private Date transactionDate;

	@NotNull
	@Column(name = "source_Bank")
	private String sourceBank;

	@NotNull
	@Column(name = "credited_By")
	private String creditedBy;

	@NotNull
	@Column(name = "final_Salary")
	private double finalSalary;

	public Salary() {
		super();
	}

	public Salary(String transactionId, AddEmployee addEmployee, String month, int year, int leaves, int loans,
			double profTax, int absent, int apprPoints, int warningPoints, int delayPoints, double allowances,
			double bonus, double netSalary, double tds, Date transactionDate, String sourceBank, String creditedBy,
			double finalSalary) {
		super();
		this.transactionId = transactionId;
		this.addEmployee = addEmployee;
		this.month = month;
		this.year = year;
		this.leaves = leaves;
		this.loans = loans;
		this.profTax = profTax;
		this.absent = absent;
		this.apprPoints = apprPoints;
		this.warningPoints = warningPoints;
		this.delayPoints = delayPoints;
		this.allowances = allowances;
		this.bonus = bonus;
		this.netSalary = netSalary;
		this.tds = tds;
		this.transactionDate = transactionDate;
		this.sourceBank = sourceBank;
		this.creditedBy = creditedBy;
		this.finalSalary = finalSalary;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public AddEmployee getAddEmployee() {
		return addEmployee;
	}

	public void setAddEmployee(AddEmployee addEmployee) {
		this.addEmployee = addEmployee;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getLeaves() {
		return leaves;
	}

	public void setLeaves(int leaves) {
		this.leaves = leaves;
	}

	public int getLoans() {
		return loans;
	}

	public void setLoans(int loans) {
		this.loans = loans;
	}

	public double getProfTax() {
		return profTax;
	}

	public void setProfTax(double profTax) {
		this.profTax = profTax;
	}

	public int getAbsent() {
		return absent;
	}

	public void setAbsent(int absent) {
		this.absent = absent;
	}

	public int getApprPoints() {
		return apprPoints;
	}

	public void setApprPoints(int apprPoints) {
		this.apprPoints = apprPoints;
	}

	public int getWarningPoints() {
		return warningPoints;
	}

	public void setWarningPoints(int warningPoints) {
		this.warningPoints = warningPoints;
	}

	public int getDelayPoints() {
		return delayPoints;
	}

	public void setDelayPoints(int delayPoints) {
		this.delayPoints = delayPoints;
	}

	public double getAllowances() {
		return allowances;
	}

	public void setAllowances(double allowances) {
		this.allowances = allowances;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}

	public double getTds() {
		return tds;
	}

	public void setTds(double tds) {
		this.tds = tds;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getSourceBank() {
		return sourceBank;
	}

	public void setSourceBank(String sourceBank) {
		this.sourceBank = sourceBank;
	}

	public String getCreditedBy() {
		return creditedBy;
	}

	public void setCreditedBy(String creditedBy) {
		this.creditedBy = creditedBy;
	}

	public double getFinalSalary() {
		return finalSalary;
	}

	public void setFinalSalary(double finalSalary) {
		this.finalSalary = finalSalary;
	}

	@Override
	public String toString() {
		return "Emp [transactionId=" + transactionId + ", addEmployee=" + addEmployee + ", month=" + month + ", year="
				+ year + ", leaves=" + leaves + ", loans=" + loans + ", profTax=" + profTax + ", absent=" + absent
				+ ", apprPoints=" + apprPoints + ", warningPoints=" + warningPoints + ", delayPoints=" + delayPoints
				+ ", allowances=" + allowances + ", bonus=" + bonus + ", netSalary=" + netSalary + ", tds=" + tds
				+ ", transactionDate=" + transactionDate + ", sourceBank=" + sourceBank + ", creditedBy=" + creditedBy
				+ ", finalSalary=" + finalSalary + "]";
	}

		

}
