package com.payroll.demo.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Festival_List")

public class HolidayList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "festival_Name")
	private String festivalName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date")
	private Date date;
	@Column(name = "day")
	private String day;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFestivalName() {
		return festivalName;
	}



	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public HolidayList(int id, String festivalName, Date date, String day) {
		super();
		this.id = id;
		this.festivalName = festivalName;
		this.date = date;
		this.day = day;
	}



	public HolidayList() {
		super();
	}



	@Override
	public String toString() {
		return "Festival [id=" + id + ", festivalName=" + festivalName + ", date=" + date + ", day=" + day + "]";
	}

}
