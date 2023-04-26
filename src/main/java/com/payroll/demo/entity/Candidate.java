package com.payroll.demo.entity;

import java.time.LocalDate;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;


@Entity
public class Candidate {
		
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	private int id;
	 	
	 	@NotNull(message = "Name may not be null")
	 	private String candidateid;  
	    private LocalDate date;
	    private String firstname;    
	    private String lastname;  
	    private String fathername;
	    private String email;
	    private String phonenumber;
	    private String alternatenumber;
	    private String address;
	    private LocalDate dob;  
	    private String city;    
	    private String pincode;    
	    private String qualification;    
	    private String aadharnumber;    
	    private String nationality;    
	    private String maritalstatus;    
	    private String gender;
	    @Lob
	    @Column(length=500000)
	    private byte[] file ;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCandidateid() {
			return candidateid;
		}
		public void setCandidateid(String candidateid) {
			this.candidateid = candidateid;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getFathername() {
			return fathername;
		}
		public void setFathername(String fathername) {
			this.fathername = fathername;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		public String getAlternatenumber() {
			return alternatenumber;
		}
		public void setAlternatenumber(String alternatenumber) {
			this.alternatenumber = alternatenumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public LocalDate getDob() {
			return dob;
		}
		public void setDob(LocalDate dob) {
			this.dob = dob;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getAadharnumber() {
			return aadharnumber;
		}
		public void setAadharnumber(String aadharnumber) {
			this.aadharnumber = aadharnumber;
		}
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
		public String getMaritalstatus() {
			return maritalstatus;
		}
		public void setMaritalstatus(String maritalstatus) {
			this.maritalstatus = maritalstatus;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public byte[] getFile() {
			return file;
		}
		public void setFile(byte[] file) {
			this.file = file;
		}
		public Candidate(int id, @NotNull(message = "Name may not be null") String candidateid, LocalDate date,
				String firstname, String lastname, String fathername, String email, String phonenumber,
				String alternatenumber, String address, LocalDate dob, String city, String pincode,
				String qualification, String aadharnumber, String nationality, String maritalstatus, String gender,
				byte[] file) {
			super();
			this.id = id;
			this.candidateid = candidateid;
			this.date = date;
			this.firstname = firstname;
			this.lastname = lastname;
			this.fathername = fathername;
			this.email = email;
			this.phonenumber = phonenumber;
			this.alternatenumber = alternatenumber;
			this.address = address;
			this.dob = dob;
			this.city = city;
			this.pincode = pincode;
			this.qualification = qualification;
			this.aadharnumber = aadharnumber;
			this.nationality = nationality;
			this.maritalstatus = maritalstatus;
			this.gender = gender;
			this.file = file;
		}
		public Candidate() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
