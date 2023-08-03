package com.payroll.demo.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class AddInterviewerName {

	@Id
	@Column(name="interviewer_Id")
	private String interviewerId;

	@OneToOne
	@JoinColumn(name = "emp_Id")
	@JsonBackReference
	private Login empId;

	private String interviewerName;

	@OneToMany(mappedBy = "roundOneInterviewerid")
	@JsonManagedReference
	private List<Interviewer> roundOneInterviewer;

	@OneToMany(mappedBy = "roundTwoInterviewerid")
	@JsonManagedReference
	private List<Interviewer> roundTwoInterviewer;

	@OneToMany(mappedBy = "roundThreeInterviewerid")
@JsonManagedReference
	private List<Interviewer> roundThreeInterviewer;
	
	public String getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(String interviewerId) {
		this.interviewerId = interviewerId;
	}

	public Login getEmpId() {
		return empId;
	}

	public void setEmpId(Login empId) {
		this.empId = empId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public List<Interviewer> getRoundOneInterviewer() {
		return roundOneInterviewer;
	}

	public void setRoundOneInterviewer(List<Interviewer> roundOneInterviewer) {
		this.roundOneInterviewer = roundOneInterviewer;
	}

	public List<Interviewer> getRoundTwoInterviewer() {
		return roundTwoInterviewer;
	}

	public void setRoundTwoInterviewer(List<Interviewer> roundTwoInterviewer) {
		this.roundTwoInterviewer = roundTwoInterviewer;
	}

	public List<Interviewer> getRoundThreeInterviewer() {
		return roundThreeInterviewer;
	}

	public void setRoundThreeInterviewer(List<Interviewer> roundThreeInterviewer) {
		this.roundThreeInterviewer = roundThreeInterviewer;
	}

	public AddInterviewerName() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddInterviewerName(String interviewerId, Login empId, String interviewerName,
			List<Interviewer> roundOneInterviewer, List<Interviewer> roundTwoInterviewer,
			List<Interviewer> roundThreeInterviewer) {
		super();
		this.interviewerId = interviewerId;
		this.empId = empId;
		this.interviewerName = interviewerName;
		this.roundOneInterviewer = roundOneInterviewer;
		this.roundTwoInterviewer = roundTwoInterviewer;
		this.roundThreeInterviewer = roundThreeInterviewer;
	}


}
