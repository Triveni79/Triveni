package com.payroll.demo.entity;


import java.sql.Date;
import java.util.Arrays;

import org.hibernate.sql.ast.tree.from.MappedByTableGroup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Candidate{

		@Id
		@Column(name = "candidate_Id")
		private String candidateId;

		@Column(name = "fisrt_Name")
		private String firstName;

		@Column(name = "last_Name")
		private String lastName;

		@Column(name = "father_Name")
		private String fatherName;

		
		private String email;

		@Column(name = "phone_Number", unique = true)
		private String phoneNumber;

		@Column(name = "alternate_Number", unique = true)
		private String alternateNumber;

		@Column(name = "address")
		private String address;

		@Column(name = "dob")
		private Date dob;

		@Column(name = "city")
		private String city;

		@Column(name = "pincode", length = 10)
		private int pincode;

		@Column(name = "qualification")
		private String qualification;

		@Column(name = "aadhar_Number", length = 12, unique = true)
		private long aadharNumber;

		@Column(name = "nationality")
		private String nationality;

		@Column(name = "marital_Status")
		private String maritalStatus;

		@Column(name = "gender")
		private String gender;
	
		@Lob
		@Column(name = "resume",length = 50000000)
		private byte[] resume;

		public String getCandidateId() {
			return candidateId;
		}

		public void setCandidateId(String candidateId) {
			this.candidateId = candidateId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFatherName() {
			return fatherName;
		}

		public void setFatherName(String fatherName) {
			this.fatherName = fatherName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getAlternateNumber() {
			return alternateNumber;
		}

		public void setAlternateNumber(String alternateNumber) {
			this.alternateNumber = alternateNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPincode() {
			return pincode;
		}

		public void setPincode(int pincode) {
			this.pincode = pincode;
		}

		public String getQualification() {
			return qualification;
		}

		public void setQualification(String qualification) {
			this.qualification = qualification;
		}

		public long getAadharNumber() {
			return aadharNumber;
		}

		public void setAadharNumber(long aadharNumber) {
			this.aadharNumber = aadharNumber;
		}

		public String getNationality() {
			return nationality;
		}

		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public String getMaritalStatus() {
			return maritalStatus;
		}

		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public byte[] getResume() {
			return resume;
		}

		public void setResume(byte[] resume) {
			this.resume = resume;
		}

		public Candidate(String candidateId, String firstName, String lastName, String fatherName, String email,
				String phoneNumber, String alternateNumber, String address, Date dob, String city, int pincode,
				String qualification, long aadharNumber, String nationality, String maritalStatus, String gender,
				byte[] resume) {
			super();
			this.candidateId = candidateId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.fatherName = fatherName;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.alternateNumber = alternateNumber;
			this.address = address;
			this.dob = dob;
			this.city = city;
			this.pincode = pincode;
			this.qualification = qualification;
			this.aadharNumber = aadharNumber;
			this.nationality = nationality;
			this.maritalStatus = maritalStatus;
			this.gender = gender;
			this.resume = resume;
		}

		@Override
		public String toString() {
			return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", fatherName=" + fatherName + ", email=" + email + ", phoneNumber=" + phoneNumber
					+ ", alternateNumber=" + alternateNumber + ", address=" + address + ", dob=" + dob + ", city="
					+ city + ", pincode=" + pincode + ", qualification=" + qualification + ", aadharNumber="
					+ aadharNumber + ", nationality=" + nationality + ", maritalStatus=" + maritalStatus + ", gender="
					+ gender + ", resume=" + Arrays.toString(resume) + "]";
		}

		public Candidate() {
			super();
			// TODO Auto-generated constructor stub
		}

	
}