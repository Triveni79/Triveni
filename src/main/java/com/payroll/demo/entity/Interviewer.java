package com.payroll.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;


@Entity
@NotNull
public class Interviewer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private long id;
	
	@NotNull(message = "Name may not be null")
 	private String candidateid;  
    private String candidatename;
    private LocalDate date;    
    private String interviewername;
    @Transient
    private String employeeid;
    private String interviewerid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCandidateid() {
		return candidateid;
	}
	public void setCandidateid(String candidateid) {
		this.candidateid = candidateid;
	}
	public String getCandidatename() {
		return candidatename;
	}
	public void setCandidatename(String candidatename) {
		this.candidatename = candidatename;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getInterviewername() {
		return interviewername;
	}
	public void setInterviewername(String interviewername) {
		this.interviewername = interviewername;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public Interviewer(long id, @NotNull(message = "Name may not be null") String candidateid, String candidatename,
			LocalDate date, String interviewername, String employeeid) {
		super();
		this.id = id;
		this.candidateid = candidateid;
		this.candidatename = candidatename;
		this.date = date;
		this.interviewername = interviewername;
		this.employeeid = employeeid;
	}
	@Override
	public String toString() {
		return "Interviewer [id=" + id + ", candidateid=" + candidateid + ", candidatename=" + candidatename + ", date="
				+ date + ", interviewername=" + interviewername + ", employeeid=" + employeeid + "]";
	}
	public Interviewer() {
		super();
	}
	public Interviewer(String interviewerid) {
		super();
		this.interviewerid = interviewerid;
	}
	public String getInterviewerid() {
		return interviewerid;
	}
	public void setInterviewerid(String interviewerid) {
		this.interviewerid = interviewerid;
	}
    
    
	


    
	
}
