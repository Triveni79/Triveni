package com.payroll.demo.entity;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
@Entity
public class Hike{

		@Id
		@Column
		 private String hikeId;
 
	@OneToOne
	@JoinColumn(name="emp_Id",referencedColumnName="emp_Id")
	 private AddEmployee empId;
	
	@NotNull
	 private String	department;
	@NotNull
	 private String	designation;
	@NotNull
	 private Date	dates_Of_Start;
	 @Column(nullable = false)
	 private Date	  dates_Of_End;
	@NotNull
	 private double	  consolidated_sal;
	@NotNull
	 private String	position;
	@NotNull
	 private String	reporting_manager;
	@NotNull
	 private String	approved_by;
	@NotNull
	 private double	hike_percentage;

	 private double final_salary;

	public String getHikeId() {
		return hikeId;
	}

	public void setHikeId(String hikeId) {
		this.hikeId = hikeId;
	}

	public AddEmployee getEmpId() {
		return empId;
	}

	public void setEmpId(AddEmployee empId) {
		this.empId = empId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDates_Of_Start() {
		return dates_Of_Start;
	}

	public void setDates_Of_Start(Date dates_Of_Start) {
		this.dates_Of_Start = dates_Of_Start;
	}

	public Date getDates_Of_End() {
		return dates_Of_End;
	}

	public void setDates_Of_End(Date dates_Of_End) {
		this.dates_Of_End = dates_Of_End;
	}

	public double getConsolidated_sal() {
		return consolidated_sal;
	}

	public void setConsolidated_sal(double consolidated_sal) {
		this.consolidated_sal = consolidated_sal;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getReporting_manager() {
		return reporting_manager;
	}

	public void setReporting_manager(String reporting_manager) {
		this.reporting_manager = reporting_manager;
	}

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	public double getHike_percentage() {
		return hike_percentage;
	}

	public void setHike_percentage(double hike_percentage) {
		this.hike_percentage = hike_percentage;
	}

	public double getFinal_salary() {
		return final_salary;
	}

	public void setFinal_salary(double final_salary) {
		this.final_salary = final_salary;
	}

	@Override
	public String toString() {
		return "Hike [hikeId=" + hikeId + ", empId=" + empId + ", department=" + department + ", designation="
				+ designation + ", dates_Of_Start=" + dates_Of_Start + ", dates_Of_End=" + dates_Of_End
				+ ", consolidated_sal=" + consolidated_sal + ", position=" + position + ", reporting_manager="
				+ reporting_manager + ", approved_by=" + approved_by + ", hike_percentage=" + hike_percentage
				+ ", final_salary=" + final_salary + "]";
	}

	public Hike(@NotNull String hikeId, AddEmployee empId, @NotNull String department, @NotNull String designation,
			@NotNull Date dates_Of_Start, Date dates_Of_End, @NotNull double consolidated_sal, @NotNull String position,
			@NotNull String reporting_manager, @NotNull String approved_by, @NotNull double hike_percentage,
			double final_salary) {
		super();
		this.hikeId = hikeId;
		this.empId = empId;
		this.department = department;
		this.designation = designation;
		this.dates_Of_Start = dates_Of_Start;
		this.dates_Of_End = dates_Of_End;
		this.consolidated_sal = consolidated_sal;
		this.position = position;
		this.reporting_manager = reporting_manager;
		this.approved_by = approved_by;
		this.hike_percentage = hike_percentage;
		this.final_salary = final_salary;
	}

	public Hike() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
