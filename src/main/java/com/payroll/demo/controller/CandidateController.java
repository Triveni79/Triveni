package com.payroll.demo.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.payroll.demo.entity.Candidate;
import com.payroll.demo.serviceImpl.CandidateServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class CandidateController {
	@Autowired
	private CandidateServiceImpl candidateService;


//	@GetMapping("/candidates")
//	public List<Object> getAllCandites() {
//	    List<Candidate> candidateList = candidateService.getAllCandidates();
//	    List<Object> objectList = new ArrayList<>(candidateList);
//	    return objectList;
//	}

	
	@GetMapping("/candidates")
	public List<Candidate> getAllCandidates() {
	    List<Candidate> candidateList = candidateService.getAllCandidates();
	    Collections.sort(candidateList, Comparator.comparing(candidate -> {
	        String candidateId = candidate.getCandidateId();
	        String lastDigits = candidateId.substring(Math.max(candidateId.length() - 3, 0));
	        return Integer.parseInt(lastDigits);
	    }));
	    return candidateList;
	}

	
	@GetMapping("/onlycandidates/{candidateId}")
	public ResponseEntity<Candidate> getCandidate(@PathVariable String candidateId) {
	    Candidate candidat = candidateService.getCandidateById(candidateId);
	    
	    if (candidat == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    Candidate cc = new Candidate();
	    cc.setCandidateId(candidat.getCandidateId());
	    cc.setLastName(candidat.getLastName());

	    return ResponseEntity.ok(cc);
	}


	
	// create candidate rest api
	@PostMapping("/candidates")
	@Transactional
	public Candidate saveCandidate(@ModelAttribute Candidate candidate, @RequestParam("file") MultipartFile file)
			throws IOException {

		return candidateService.saveCandidate(candidate, file);
	}

	 //get candidate by id rest api
	@GetMapping("/candidates/{candidateId}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable String candidateId) {
	    Candidate candidate = candidateService.getCandidateById(candidateId);
	    return ResponseEntity.ok(candidate);
	}

//    @GetMapping("/viewResume/{candidateId}")
//    public void viewResume(@PathVariable String candidateId, HttpServletResponse response) throws IOException {
//        byte[] resumeBytes = candidateService.getResumeById(candidateId);
//        Candidate candidate = null; // NOTE: This line of code is not used and is unnecessary
//        response.setContentType("application/pdf"); // or the appropriate content type for your file
//        response.setHeader("Content-Disposition", "attachment; filename=" + candidateId + ".pdf");
//        response.setContentLength(resumeBytes.length);
//        response.getOutputStream().write(resumeBytes);
//    }
	
	@GetMapping("/viewResume/{candidateId}")
	public void viewResume(@PathVariable String candidateId, HttpServletResponse response) throws IOException {
	  byte[] resumeBytes = candidateService.getResumeById(candidateId);
	  
	  response.setContentType("application/pdf"); // Set the content type to PDF
	  response.setHeader("Content-Disposition", "inline; filename=" + candidateId + ".pdf"); // Display inline instead of attachment
	  response.setContentLength(resumeBytes.length);
	  response.getOutputStream().write(resumeBytes);
	}
	
	    

	@PutMapping("/candidates/{candidateId}")
	    public ResponseEntity<Candidate> editCandidateById(@PathVariable String candidateId,
	             @RequestParam("file") MultipartFile file,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String fatherName,@RequestParam String
	             email, @RequestParam String phoneNumber,
                 @RequestParam String alternateNumber,
                 @RequestParam String address,
                 @RequestParam Date dob,
                 @RequestParam String city,
                 @RequestParam int pincode,
                 @RequestParam String qualification,
                 @RequestParam Long aadharNumber,
                 @RequestParam String nationality,
                 @RequestParam String maritalStatus,
                 @RequestParam String gender

	    		) throws IOException {
	        Candidate candidate = candidateService.getCandidateById(candidateId);
	        if (candidate == null) {
	            return ResponseEntity.notFound().build();
	        }
	        candidate.setFirstName(firstName);
	        candidate.setLastName(lastName);
	        candidate.setFatherName(fatherName);
	        candidate.setEmail(email);
	        candidate.setPhoneNumber(phoneNumber);
	        candidate.setAlternateNumber(alternateNumber);
	        candidate.setAddress(address);
	        candidate.setDob(dob);
	        candidate.setCity(city);
	        candidate.setPincode(pincode);
	        candidate.setQualification(qualification);
	        candidate.setAadharNumber(aadharNumber);
	        candidate.setNationality(nationality);
	        candidate.setMaritalStatus(maritalStatus);
	        candidate.setGender(gender);
	        candidate.setResume(file.getBytes());
	        candidateService.editCandidate(candidate, file);
	        
	        return ResponseEntity.ok(candidate);
	    }
}
