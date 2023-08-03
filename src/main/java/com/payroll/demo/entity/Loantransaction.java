package com.payroll.demo.entity;
import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "loan_transaction")

public class Loantransaction {

	@OneToOne
	@JoinColumn(name = "loan_Santioned_id", referencedColumnName = "loan_Santioned_id")
	private Loanmaster loan_Santioned_id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	
	 @Column(nullable = false)
	private long loan_SantionedAmount;
	 
	 @Column(nullable = false)
	private long EMI;
	 
	 @Column(nullable = false)
	private int tenure;
	 
	 @Column(nullable = false)
	private Date SalaryCreditedDate;
	 
	 @Column(nullable = false)
	private double BalanceAmount;
	 
	 @Column(nullable = false)
	private int tenureremaining;
	 
	 @Column(nullable = false)
	private Date approved_date;
	 
	 
	 
	public Loanmaster getLoan_Santioned_id() {
		return loan_Santioned_id;
	}
	public void setLoan_Santioned_id(Loanmaster loan_Santioned_id) {
		this.loan_Santioned_id = loan_Santioned_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getLoan_SantionedAmount() {
		return loan_SantionedAmount;
	}
	public void setLoan_SantionedAmount(long loan_SantionedAmount) {
		this.loan_SantionedAmount = loan_SantionedAmount;
	}
	public long getEMI() {
		return EMI;
	}
	public void setEMI(long eMI) {
		EMI = eMI;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public Date getSalaryCreditedDate() {
		return SalaryCreditedDate;
	}
	public void setSalaryCreditedDate(Date salaryCreditedDate) {
		SalaryCreditedDate = salaryCreditedDate;
	}
	public double getBalanceAmount() {
		return BalanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		BalanceAmount = balanceAmount;
	}
	public int getTenureremaining() {
		return tenureremaining;
	}
	public void setTenureremaining(int tenureremaining) {
		this.tenureremaining = tenureremaining;
	}
	public Date getApproved_date() {
		return approved_date;
	}
	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}
	@Override
	public String toString() {
		return "Loantransaction [loan_Santioned_id=" + loan_Santioned_id + ", id=" + id + ", loan_SantionedAmount="
				+ loan_SantionedAmount + ", EMI=" + EMI + ", tenure=" + tenure + ", SalaryCreditedDate="
				+ SalaryCreditedDate + ", BalanceAmount=" + BalanceAmount + ", tenureremaining=" + tenureremaining
				+ ", approved_date=" + approved_date + "]";
	}
	public Loantransaction(Loanmaster loan_Santioned_id, int id, long loan_SantionedAmount, long eMI, int tenure,
			Date salaryCreditedDate, double balanceAmount, int tenureremaining, Date approved_date) {
		super();
		this.loan_Santioned_id = loan_Santioned_id;
		this.id = id;
		this.loan_SantionedAmount = loan_SantionedAmount;
		EMI = eMI;
		this.tenure = tenure;
		SalaryCreditedDate = salaryCreditedDate;
		BalanceAmount = balanceAmount;
		this.tenureremaining = tenureremaining;
		this.approved_date = approved_date;
	}
	public Loantransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}


