package com.payroll.demo.DTO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.payroll.demo.entity.AddInterviewerName;

import jakarta.persistence.ManyToOne;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterviewerDto {

	private String candidateId;

	private String lastName;

	private Date roundOneDate;

	private String roundOneInterviewername;
	
	@ManyToOne
	@JsonBackReference
	private AddInterviewerName roundOneInterviewerid;

	private Date roundTwoDate;

	private String roundTwoInterviewername;
	
	@ManyToOne
	@JsonBackReference
//	@JoinColumn(name = "roundTwo_Interviewerid", referencedColumnName = "interviewer_Id")
	private AddInterviewerName roundTwoInterviewerid;

	private Date roundThreeDate;

	private String roundThreeInterviewername;
	@ManyToOne
@JsonBackReference
	private AddInterviewerName roundThreeInterviewerid;

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getRoundOneDate() {
		return roundOneDate;
	}

	public void setRoundOneDate(Date roundOneDate) {
		this.roundOneDate = roundOneDate;
	}

	public String getRoundOneInterviewername() {
		return roundOneInterviewername;
	}

	public void setRoundOneInterviewername(String roundOneInterviewername) {
		this.roundOneInterviewername = roundOneInterviewername;
	}

	public AddInterviewerName getRoundOneInterviewerid() {
		return roundOneInterviewerid;
	}

	public void setRoundOneInterviewerid(AddInterviewerName roundOneInterviewerid) {
		this.roundOneInterviewerid = roundOneInterviewerid;
	}

	public Date getRoundTwoDate() {
		return roundTwoDate;
	}

	public void setRoundTwoDate(Date roundTwoDate) {
		this.roundTwoDate = roundTwoDate;
	}

	public String getRoundTwoInterviewername() {
		return roundTwoInterviewername;
	}

	public void setRoundTwoInterviewername(String roundTwoInterviewername) {
		this.roundTwoInterviewername = roundTwoInterviewername;
	}

	public AddInterviewerName getRoundTwoInterviewerid() {
		return roundTwoInterviewerid;
	}

	public void setRoundTwoInterviewerid(AddInterviewerName roundTwoInterviewerid) {
		this.roundTwoInterviewerid = roundTwoInterviewerid;
	}

	public Date getRoundThreeDate() {
		return roundThreeDate;
	}

	public void setRoundThreeDate(Date roundThreeDate) {
		this.roundThreeDate = roundThreeDate;
	}

	public String getRoundThreeInterviewername() {
		return roundThreeInterviewername;
	}

	public void setRoundThreeInterviewername(String roundThreeInterviewername) {
		this.roundThreeInterviewername = roundThreeInterviewername;
	}

	public AddInterviewerName getRoundThreeInterviewerid() {
		return roundThreeInterviewerid;
	}

	public void setRoundThreeInterviewerid(AddInterviewerName roundThreeInterviewerid) {
		this.roundThreeInterviewerid = roundThreeInterviewerid;
	}
	
}