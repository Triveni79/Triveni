package com.payroll.demo.entity;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Interviewer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;

	private String candidateId;

	private String lastName;

	@Column(name = "roundOne_Date")
	private Date roundOneDate;

	@Column(name = "roundOne_Interviewername")
	private String roundOneInterviewername;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "roundOne_Interviewerid", referencedColumnName = "interviewer_Id")
	private AddInterviewerName roundOneInterviewerid;

	@Column(name = "roundone_Status")
	private String roundOneStatus;

	@Column(name = "roundone_Remarks")
	private String roundoneRemarks;

	@Column(name = "roundTwo_Date")
	private Date roundTwoDate;

	@Column(name = "roundTwo_Interviewername")
	private String roundTwoInterviewername;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "roundTwo_Interviewerid", referencedColumnName = "interviewer_Id")
	private AddInterviewerName roundTwoInterviewerid;

	@Column(name = "roundtwo_Status")
	private String roundTwoStatus;

	@Column(name = "roundtwo_Remarks")
	private String roundtwoRemarks;

	@Column(name = "roundThree_Date")
	private Date roundThreeDate;

	@Column(name = "roundThree_Interviewername")
	private String roundThreeInterviewername;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "roundThree_Interviewerid", referencedColumnName = "interviewer_Id")
	private AddInterviewerName roundThreeInterviewerid;

	@Column(name = "roundthree_Status")
	private String roundThreeStatus;

	@Column(name = "roundthree_Remarks")
	private String roundthreeRemarks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getRoundOneStatus() {
		return roundOneStatus;
	}

	public void setRoundOneStatus(String roundOneStatus) {
		this.roundOneStatus = roundOneStatus;
	}

	public String getRoundoneRemarks() {
		return roundoneRemarks;
	}

	public void setRoundoneRemarks(String roundoneRemarks) {
		this.roundoneRemarks = roundoneRemarks;
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

	public String getRoundTwoStatus() {
		return roundTwoStatus;
	}

	public void setRoundTwoStatus(String roundTwoStatus) {
		this.roundTwoStatus = roundTwoStatus;
	}

	public String getRoundtwoRemarks() {
		return roundtwoRemarks;
	}

	public void setRoundtwoRemarks(String roundtwoRemarks) {
		this.roundtwoRemarks = roundtwoRemarks;
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

	public String getRoundThreeStatus() {
		return roundThreeStatus;
	}

	public void setRoundThreeStatus(String roundThreeStatus) {
		this.roundThreeStatus = roundThreeStatus;
	}

	public String getRoundthreeRemarks() {
		return roundthreeRemarks;
	}

	public void setRoundthreeRemarks(String roundthreeRemarks) {
		this.roundthreeRemarks = roundthreeRemarks;
	}

	public Interviewer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Interviewer(long id, String candidateId, String lastName, Date roundOneDate, String roundOneInterviewername,
			AddInterviewerName roundOneInterviewerid, String roundOneStatus, String roundoneRemarks, Date roundTwoDate,
			String roundTwoInterviewername, AddInterviewerName roundTwoInterviewerid, String roundTwoStatus,
			String roundtwoRemarks, Date roundThreeDate, String roundThreeInterviewername,
			AddInterviewerName roundThreeInterviewerid, String roundThreeStatus, String roundthreeRemarks) {
		super();
		this.id = id;
		this.candidateId = candidateId;
		this.lastName = lastName;
		this.roundOneDate = roundOneDate;
		this.roundOneInterviewername = roundOneInterviewername;
		this.roundOneInterviewerid = roundOneInterviewerid;
		this.roundOneStatus = roundOneStatus;
		this.roundoneRemarks = roundoneRemarks;
		this.roundTwoDate = roundTwoDate;
		this.roundTwoInterviewername = roundTwoInterviewername;
		this.roundTwoInterviewerid = roundTwoInterviewerid;
		this.roundTwoStatus = roundTwoStatus;
		this.roundtwoRemarks = roundtwoRemarks;
		this.roundThreeDate = roundThreeDate;
		this.roundThreeInterviewername = roundThreeInterviewername;
		this.roundThreeInterviewerid = roundThreeInterviewerid;
		this.roundThreeStatus = roundThreeStatus;
		this.roundthreeRemarks = roundthreeRemarks;
	}

}