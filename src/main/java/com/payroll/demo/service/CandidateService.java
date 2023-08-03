package com.payroll.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.payroll.demo.entity.Candidate;


public interface CandidateService {
	
	public List<Candidate> getAllCandidates();

	public Candidate saveCandidate(Candidate candidate,MultipartFile file) throws IOException;

	public Candidate getCandidateById(String candidateId);
	
    void editCandidate(Candidate candidate, MultipartFile file) throws IOException;
    
    byte[] getResumeById(String candidateId);
    
    
    

}
