//package com.payroll.demo.entity;
//
//import java.sql.Date;
//import java.time.Duration;
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "Logs")
//public class Logs {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Column(name = "log_type")
//	private String logType;
//
//	@Column(name = "approved_By")
//	private String approvedBy;
//
//	@Column(name = "working_Hours")
//	private String workinghours;
//
//	@Column(name = "date")
//	private Date date;
//	
//	@Column(name = "totalWorkingHours")
//	private String totalworkinghours;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getLogType() {
//		return logType;
//	}
//
//	public void setLogType(String logType) {
//		this.logType = logType;
//	}
//
//	public String getApprovedBy() {
//		return approvedBy;
//	}
//
//	public void setApprovedBy(String approvedBy) {
//		this.approvedBy = approvedBy;
//	}
//
//	public String getWorkinghours() {
//		return workinghours;
//	}
//
//	public void setWorkinghours(String workinghours) {
//		this.workinghours = workinghours;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public String getTotalworkinghours() {
//		return totalworkinghours;
//	}
//
//	public void setTotalworkinghours(String totalworkinghours) {
//		this.totalworkinghours = totalworkinghours;
//	}
//
//	public Logs(Long id, String logType, String approvedBy, String workinghours, Date date, String totalworkinghours) {
//		super();
//		this.id = id;
//		this.logType = logType;
//		this.approvedBy = approvedBy;
//		this.workinghours = workinghours;
//		this.date = date;
//		this.totalworkinghours = totalworkinghours;
//	}
//
//	public Logs() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	
//	
//
//}
