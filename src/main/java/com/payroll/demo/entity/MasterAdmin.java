package com.payroll.demo.entity;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MasterAdmin")
public class MasterAdmin {

	@Id
	private String businessId;

	@Column(nullable = false)
	private String organisationName;

	@Column(nullable = false)
	private String numberOfEmployees;

	private long phoneNo;

	@Column(nullable = false)
	private String contactPerson;

	@Column(nullable = false)
	private String emailId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String address;

	private String about;

	@Lob
	@Column(columnDefinition = "LongBlob")
	private byte[] qrCode;

	private Date registrationDate;

	@Lob
	@Column(columnDefinition = "LongBlob")
	private byte[] logo;

	@OneToOne(mappedBy = "masterAdmin", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Login login;

//    public void setLoginData(Login login) {
//       
//        if (login != null) {
//            login.setMasterAdmin(this);
//            login.setOfficialEmail(emailId); // Set emailId as officialEmail in Login
//            login.setOfficialMailPassword(password); // Set password as officialMailPassword in Login
//        }
//        
//    }

//	public void setLoginsData() {
//		if (login == null) {
//			login = new Login();
//		}
//		login.setOfficialEmail(emailId);
//		login.setOfficialMailPassword(password);
//		login.setMasterAdmin(this);
//	}
//	

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(String numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public byte[] getQrCode() {
		return qrCode;
	}

	public void setQrCode(byte[] qrCode) {
		this.qrCode = qrCode;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public MasterAdmin(String businessId, String organisationName, String numberOfEmployees, long phoneNo,
			String contactPerson, String emailId, String password, String address, String about, byte[] qrCode,
			Date registrationDate, byte[] logo, Login login) {
		super();		
		this.businessId = businessId;
		this.organisationName = organisationName;
		this.numberOfEmployees = numberOfEmployees;
		this.phoneNo = phoneNo;
		this.contactPerson = contactPerson;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
		this.about = about;
		this.qrCode = qrCode;
		this.registrationDate = registrationDate;
		this.logo = logo;
		this.login = login;
	}

	public MasterAdmin() {
		super();
	}
}
